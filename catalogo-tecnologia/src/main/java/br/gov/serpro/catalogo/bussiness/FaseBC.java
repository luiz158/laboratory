package br.gov.serpro.catalogo.bussiness;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.bussiness.validation.FaseValidator;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Declinio;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.FaseHistorico;
import br.gov.serpro.catalogo.entity.FaseHistorico.OPERACAO;
import br.gov.serpro.catalogo.entity.FaseInteressado;
import br.gov.serpro.catalogo.entity.FaseMembro;
import br.gov.serpro.catalogo.entity.Internalizacao;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.entity.Sustentacao;
import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.persistence.FaseDAO;
import br.gov.serpro.catalogo.persistence.FaseHistoricoDAO;
import br.gov.serpro.catalogo.persistence.FaseInteressadoDAO;
import br.gov.serpro.catalogo.persistence.FaseMembroDAO;
import br.gov.serpro.catalogo.persistence.UserDAO;
import br.gov.serpro.catalogo.rest.FaseDTO;

@BusinessController
public class FaseBC {

	@Inject
	private FaseDAO faseDAO;
	
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private FaseMembroDAO faseMembroDAO;
	
	@Inject
	private FaseInteressadoDAO faseInteressadoDAO;
	
	@Inject
	private FaseHistoricoDAO faseHistoricoDAO;
		
	@Inject
	private FaseValidator faseValidator;
		
	@Inject
	private EmailBC emailBC;
	
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
					
		OPERACAO operacao = (fase.getSituacao().equals(Situacao.APROVADO))?OPERACAO.APROVAR:OPERACAO.REPROVAR;
		faseHistoricoDAO.insert(new FaseHistorico(fase,operacao));	
		
		// Se foi aprovado tem uma proxima fase;
		if (fase.getSituacao().equals(Situacao.APROVADO) && !fase.getFase().equals(FaseEnum.DECLINIO)) {			
			proximafase = getProximaFase(fase);
			faseDAO.insert(proximafase);			
			faseHistoricoDAO.insert(new FaseHistorico(proximafase,OPERACAO.CRIAR));
			
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
			faseHistoricoDAO.insert(new FaseHistorico(fase,OPERACAO.ATUALIZAR));
		}else{
			faseDAO.insert(fase);
			faseHistoricoDAO.insert(new FaseHistorico(fase,OPERACAO.CRIAR));
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
	public User adicionarMembro(User user, Long id){
		FaseMembro faseMembro = new FaseMembro();
		System.out.println("Verificando o cpf: "+user.getCPF());
		if(!userDAO.existeCadastroParaCPF(user.getCPF())){
			user = userDAO.insert(user);
		}else{			
			if(faseMembroDAO.membroJaCadastrado(id, user.getCPF()))
				throw new ValidationException().addViolation(null, "Membro já relacionado.");
			user = userDAO.loadByCPF(user.getCPF());
		}
		
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
	public User adicionarInteressado(User user, Long id){
		FaseInteressado faseMembro = new FaseInteressado();
		System.out.println("Verificando o cpf: "+user.getCPF());
		if(!userDAO.existeCadastroParaCPF(user.getCPF())){
			user = userDAO.insert(user);
		}else{			
			if(faseInteressadoDAO.interessadoJaCadastrado(id, user.getCPF()))
				throw new ValidationException().addViolation(null, "Interessado já relacionado.");
			user = userDAO.loadByCPF(user.getCPF());
		}		
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

	public User carregarUsuario(User user) {
		User userBanco = userDAO.loadByCPF(user.getCPF());
		if(userBanco == null){
			userBanco = userDAO.insert(user);
		}
		return userBanco;
	}

	public void delete(Long id) {
		faseDAO.delete(id);
		Fase fase = new Fase();
		fase.setId(id);
		faseHistoricoDAO.insert(new FaseHistorico(fase,OPERACAO.EXCLUIR));
	}

	public Fase load(Long id) {
		return faseDAO.load(id);
	}

	public List<FaseHistorico> obterHistorico(Long id) {
		List<Fase> fases = obterCadeiaDasFases(id);
		
		List<Long> ids = new ArrayList<Long>();
		for (Fase fase : fases) {
			ids.add(fase.getId());
		}
		return faseHistoricoDAO.obterHistorico(ids);
	}


	

}
