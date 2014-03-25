package br.gov.serpro.catalogo.bussiness;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.bussiness.validation.FaseValidator;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Declinio;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.FaseInteressado;
import br.gov.serpro.catalogo.entity.FaseMembro;
import br.gov.serpro.catalogo.entity.Internalizacao;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.entity.Sustentacao;
import br.gov.serpro.catalogo.entity.Usuario;
import br.gov.serpro.catalogo.event.FaseEvent;
import br.gov.serpro.catalogo.event.FaseEvent.ATUALIZAR;
import br.gov.serpro.catalogo.event.FaseEvent.CRIAR;
import br.gov.serpro.catalogo.event.FaseEvent.EXCLUIR;
import br.gov.serpro.catalogo.event.FaseEvent.FINALIZAR;
import br.gov.serpro.catalogo.persistence.FaseDAO;
import br.gov.serpro.catalogo.persistence.FaseInteressadoDAO;
import br.gov.serpro.catalogo.persistence.FaseMembroDAO;
import br.gov.serpro.catalogo.rest.FaseDTO;

@BusinessController
public class FaseBC {

	@Inject
	private FaseDAO faseDAO;

	@Inject
	private UsuarioBC usuarioBC;

	@Inject
	private FaseMembroDAO faseMembroDAO;

	@Inject
	private FaseInteressadoDAO faseInteressadoDAO;

	@Inject
	private FaseValidator faseValidator;

	@Inject
	private EmailBC emailBC;

	@Inject @CRIAR private Event<FaseEvent> eventoFaseCriar;
	@Inject @ATUALIZAR private Event<FaseEvent> eventoFaseAtualizar;
	@Inject @FINALIZAR private Event<FaseEvent> eventoFaseFinalizar;
	@Inject @EXCLUIR private Event<FaseEvent> eventoFaseExcluir;
		
	/**
	 * Pesquisa por criteria usando o DTO
	 * 
	 * @param fase
	 * @return
	 */
	public List<Fase> pesquisar(FaseDTO fase) {
		return faseDAO.pesquisar(fase);
	}

	/**
	 * Monta a lista com o fluxo de fases da demanda.
	 * @param id de uma fase
	 * @return
	 */
	public List<Fase> obterCadeiaDasFases(Long id){
		List<Fase> lista = new ArrayList<Fase>();

		Fase faseInicial = faseDAO.load(id);
		lista.add(faseInicial);

		Fase faseAnterior = (faseInicial.getFaseAnterior()!=null)? faseDAO.load(faseInicial.getFaseAnterior().getId()): null;
		while(faseAnterior!=null){			
			lista.add(0, faseAnterior);
			faseAnterior = (faseAnterior.getFaseAnterior()!=null)? faseDAO.load(faseAnterior.getFaseAnterior().getId()): null;
		}

		Fase fasePosterior = faseDAO.obterFasePosterior(faseInicial.getId());
		while(fasePosterior!=null){			
			lista.add(fasePosterior);
			fasePosterior  = faseDAO.obterFasePosterior(fasePosterior.getId());
		}

		return lista;

	}




	/**
	 * Método que finaliza a fase e retorna a próxima fase ou null.
	 * 
	 * @param fase
	 * @return
	 */
	@Transactional
	public Fase finalizarFase(Fase fase) {

		if (fase.getDataFinalizacao()!=null){
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			throw new ValidationException().addViolation(null,
					"Esta fase ("+fase.getFase()+") já foi finalizada em "+format.format(fase.getDataFinalizacao()));
		}

		fase.setDataFinalizacao(new Date());
		Fase proximafase = null;

		faseValidator.validarFinalizar(fase);	
		
		faseDAO.update(fase);		
		eventoFaseFinalizar.fire(new FaseEvent(fase));
		
		
		// Se foi aprovado tem uma proxima fase;
		if (fase.getSituacao().equals(Situacao.APROVADO) && !fase.getFase().equals(FaseEnum.DECLINIO)) {			
			proximafase = getProximaFase(fase);
			faseDAO.insert(proximafase);	
			eventoFaseCriar.fire(new FaseEvent(proximafase));
		
			FaseMembro fm = new FaseMembro();
			fm.setFase(proximafase);
			fm.setUser(fase.getProximaFaseLider());			
			faseMembroDAO.insert(fm);					
			emailBC.enviarEmailUsuarioAdicionadoEquipe(fm.getUser(), fm.getFase());
		}

		return proximafase;
	}


    /**
     * Instancia a proxima fase com os dados basicos.
     * 
     * Verifica se está seguindo o fluxo correto, caso contrario levanta uma validation execption
     * 
     * @param fase
     * @return
     */
	private Fase getProximaFase(Fase fase) {
		// Apenas a fase de analise é livre para pular fases
		if(fase.getFase()!= FaseEnum.ANALISE){
			if(fase.getProximaFase().ordinal() - fase.getFase().ordinal() != 1){
				throw new ValidationException().addViolation("proximaFase",
						"Não é possível alterar de fase "+fase.getFase()+" para a "+fase.getProximaFase());
			}
		}		
		Fase proximafase = null;
		if (fase.getProximaFase().equals(FaseEnum.PROSPECCAO)) {
			proximafase = new Prospeccao();
		} else if (fase.getProximaFase().equals(FaseEnum.INTERNALIZACAO)) {
			proximafase = new Internalizacao();
		} else if (fase.getProximaFase().equals(FaseEnum.SUSTENTACAO)) {
			proximafase = new Sustentacao();
		} else if (fase.getProximaFase().equals(FaseEnum.DECLINIO)) {
			proximafase = new Declinio();
		}		

		proximafase.setFaseAnterior(fase);
		proximafase.setSituacao(Situacao.RASCUNHO);
		proximafase.setUnidadeGestora(fase.getProximaFaseUnidadeGestora());

		return proximafase;
	}

	private Fase salvar(Fase fase) {
		if(fase.getId()!=null){
			faseDAO.update(fase);
			eventoFaseAtualizar.fire(new FaseEvent(fase));
		}else{
			faseDAO.insert(fase);
			eventoFaseCriar.fire(new FaseEvent(fase));
		}				
		return fase;
	}

	public Fase salvar(Analise fase) {
		faseValidator.validarSalvar(fase);
		return salvar((Fase) fase);
	}

	public Fase salvar(Prospeccao fase) {
		faseValidator.validarSalvar(fase);
		return salvar((Fase) fase);
	}

	public Fase salvar(Internalizacao fase) {
		faseValidator.validarSalvar(fase);
		return salvar((Fase) fase);
	}

	public Fase salvar(Declinio fase) {
		faseValidator.validarSalvar(fase);
		return salvar((Fase) fase);
	}





	@Transactional
	public Usuario adicionarMembro(Usuario user, Long id){
		FaseMembro faseMembro = new FaseMembro();		

		if(faseMembroDAO.membroJaCadastrado(id, user.getCPF()))
				throw new ValidationException().addViolation(null, "Membro já relacionado.");

		user = usuarioBC.carregarOuInserir(user);	

		faseMembro.setUser(user);
		faseMembro.setFase(faseDAO.load(id));		
		faseMembroDAO.insert(faseMembro);
		emailBC.enviarEmailUsuarioAdicionadoEquipe(faseMembro.getUser(), faseMembro.getFase());
		return faseMembro.getUser();
	}

	public List<FaseMembro> obterMembros(Long id) {		
		Fase fase = new Fase();
		fase.setId(id);		
		return faseMembroDAO.membrosDaFase(fase);
	}

	public void deleteMembro(Long id) {
		faseMembroDAO.delete(id);
	}


	@Transactional
	public Usuario adicionarInteressado(Usuario user, Long id){
		FaseInteressado faseMembro = new FaseInteressado();

		if(faseInteressadoDAO.interessadoJaCadastrado(id, user.getCPF()))
			throw new ValidationException().addViolation(null, "Interessado já relacionado.");

		user = usuarioBC.carregarOuInserir(user);

		faseMembro.setUser(user);
		faseMembro.setFase(faseDAO.load(id));		
		faseInteressadoDAO.insert(faseMembro);		
		return faseMembro.getUser();
	}

	public List<FaseInteressado> obterInteressados(Long id) {		
		Fase fase = new Fase();
		fase.setId(id);		
		return faseInteressadoDAO.interessados(fase);
	}

	public void deleteInteressado(Long id) {
		faseInteressadoDAO.delete(id);
	}

	public void delete(Long id) {
		faseDAO.delete(id);
		Fase fase = new Fase();
		fase.setId(id);
		eventoFaseExcluir.fire(new FaseEvent(fase));
	}

	public Fase load(Long id) {
		return faseDAO.load(id);
	}


}
