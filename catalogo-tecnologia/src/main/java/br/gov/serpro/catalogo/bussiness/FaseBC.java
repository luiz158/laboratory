package br.gov.serpro.catalogo.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import br.gov.serpro.catalogo.entity.StatusEnum;
import br.gov.serpro.catalogo.entity.Sustentacao;
import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.event.FaseEvent;
import br.gov.serpro.catalogo.event.FaseEvent.ADD_INTERESSADO;
import br.gov.serpro.catalogo.event.FaseEvent.ADD_MEMBRO;
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

	@Inject @CRIAR private Event<FaseEvent> eventoFaseCriar;
	@Inject @ATUALIZAR private Event<FaseEvent> eventoFaseAtualizar;
	@Inject @FINALIZAR private Event<FaseEvent> eventoFaseFinalizar;
	@Inject @EXCLUIR private Event<FaseEvent> eventoFaseExcluir;
	@Inject @ADD_INTERESSADO private Event<FaseEvent> eventoFaseAdicionarInteressado;
	@Inject @ADD_MEMBRO private Event<FaseEvent> eventoFaseAdicionarMembro;
		
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
		Fase fase = faseDAO.load(id);
		return faseDAO.obterCadeiaApartirDafaseInicial(fase.getFaseInicial()!=null?fase.getFaseInicial().getId():fase.getId());
	}


	public List<Fase> obterCadeiaDasFasesComExcluidos(Long id) {
		Fase fase = faseDAO.load(id);
		return faseDAO.obterCadeiaApartirDafaseInicialComExcluidos(fase.getFaseInicial()!=null?fase.getFaseInicial().getId():fase.getId());
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
		
		Fase proximafase = null;
		faseValidator.validarFinalizar(fase);	
		
		fase.setDataFinalizacao(new Date());
		faseDAO.update(fase);		
		eventoFaseFinalizar.fire(new FaseEvent(fase));
		
		
		// Se foi aprovado tem uma proxima fase;
		if (fase.getExecutarProximaFase()>0) {			
			proximafase = criarProximaFase(fase);
		}

		return proximafase;
	}

	public Fase criarProximaFase(Fase fase) {		
		Fase proximafase;
		faseValidator.validarCriarProximaFase(fase);
		this.salvar(fase);
		
		proximafase = getProximaFase(fase);		
		faseDAO.insert(proximafase);	
		eventoFaseCriar.fire(new FaseEvent(proximafase));

		faseMembroDAO.insert(new FaseMembro(proximafase,fase.getProximaFaseLider()));						
		eventoFaseAdicionarMembro.fire(new FaseEvent(fase.getProximaFaseLider(), proximafase));
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
			if(fase.getFase().equals(FaseEnum.SUSTENTACAO)){
				if(fase.getProximaFase().ordinal()<fase.getFase().ordinal())
					throw new ValidationException().addViolation("proximaFase",
							"Não é possível alterar de fase "+fase.getFase()+" para a "+fase.getProximaFase());
			}else{				
				if(fase.getProximaFase().ordinal() - fase.getFase().ordinal() != 1){
					throw new ValidationException().addViolation("proximaFase",
							"Não é possível alterar de fase "+fase.getFase()+" para a "+fase.getProximaFase());
				}
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
		proximafase.setFaseInicial((fase.getFaseInicial()!=null?fase.getFaseInicial(): fase));
		proximafase.setSituacao(Situacao.RASCUNHO);
		proximafase.setUnidadeGestora(fase.getProximaFaseUnidadeGestora());

		return proximafase;
	}

	private Fase salvar(Fase fase) {
		
		if(fase.getId()!=null){
			faseDAO.update(fase);
			eventoFaseAtualizar.fire(new FaseEvent(fase));
		}else{
			
			Fase faseSalva = faseDAO.insert(fase);
			faseSalva.setIdentificador(formataIdentificador(faseSalva.getId()));
			faseDAO.update(faseSalva);
			eventoFaseCriar.fire(new FaseEvent(fase));
		}				
		return fase;
	}

	public String formataIdentificador(Long id) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		
		String idString = String.valueOf(id);
		
		for (int x=idString.length(); x<6; x++) {
			idString = "0" + idString;
		}
		
		return format.format(new Date())+idString;
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
	
	public Fase salvar(Sustentacao fase) {
		faseValidator.validarSalvar(fase);
		return salvar((Fase) fase);
	}

	public Fase salvar(Declinio fase) {
		faseValidator.validarSalvar(fase);
		return salvar((Fase) fase);
	}





	@Transactional
	public User adicionarMembro(User user, Long id){
		FaseMembro faseMembro = new FaseMembro();		

		if(faseMembroDAO.membroJaCadastrado(id, user.getCPF()))
				throw new ValidationException().addViolation(null, "Membro já relacionado.");

		user = usuarioBC.carregarOuInserir(user);	

		faseMembro.setUser(user);
		faseMembro.setFase(faseDAO.load(id));		
		faseMembroDAO.insert(faseMembro);
		eventoFaseAdicionarMembro.fire(new FaseEvent(faseMembro.getUser(), faseMembro.getFase()));
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
	public User adicionarInteressado(User user, Long id){
		FaseInteressado faseMembro = new FaseInteressado();

		if(faseInteressadoDAO.interessadoJaCadastrado(id, user.getCPF()))
			throw new ValidationException().addViolation(null, "Interessado já relacionado.");

		user = usuarioBC.carregarOuInserir(user);

		faseMembro.setUser(user);
		faseMembro.setFase(faseDAO.load(id));		
		faseInteressadoDAO.insert(faseMembro);	
		eventoFaseAdicionarInteressado.fire(new FaseEvent(faseMembro.getUser(), faseMembro.getFase()));
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
		Fase fase = faseDAO.load(id);
		faseValidator.validarExcluir(fase);
		fase.setStatus(StatusEnum.EXCLUIDO);
		faseDAO.update(fase);
		eventoFaseExcluir.fire(new FaseEvent(fase));
	}

	public Fase load(Long id) {
		return faseDAO.load(id);
	}

	public List<FaseMembro> obterFasesDoUsuarioComoMembro(User usuario) {
		return faseMembroDAO.fasesComoMembro(usuario);
	}

	public List<FaseInteressado> obterFasesDoUsuarioComoInteressado(User usuario) {
		return faseInteressadoDAO.fasesComoInteressado(usuario);
	}

	public Fase obterProximaFase(Long id) {
		return faseDAO.obterFasePosterior(id);
	}

	public Map<FaseEnum, Integer> totalPorFase(){
		return faseDAO.totalPorFase();
	}


}
