package br.gov.serpro.catalogo.bussiness;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
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
import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.persistence.FaseDAO;
import br.gov.serpro.catalogo.persistence.FaseInteressadoDAO;
import br.gov.serpro.catalogo.persistence.FaseMembroDAO;
import br.gov.serpro.catalogo.persistence.FaseProdutoDAO;
import br.gov.serpro.catalogo.persistence.UserDAO;
import br.gov.serpro.catalogo.rest.FaseDTO;

@BusinessController
public class FaseBC {

	@Inject
	private FaseDAO faseDAO;
	
	@Inject
	private FaseProdutoDAO faseProdutoDAO;
	
	
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private FaseMembroDAO faseMembroDAO;
	
	@Inject
	private FaseInteressadoDAO faseInteressadoDAO;
	
	
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
		
		validarFinalizar(fase);

		// Se foi aprovado tem uma proxima fase;
		if (fase.getSituacao().equals(Situacao.APROVADO)) {			
			proximafase = getProximaFase(fase);
			faseDAO.insert(proximafase);
		}

		faseDAO.update(fase);

		return proximafase;
	}

	
	/**
	 * Valida os campos obrigatórios para finalizar uma fase.
	 * 
	 * @param fase
	 */
	private void validarFinalizar(Fase fase) {
		if (fase.getSituacao() == null
				|| fase.getSituacao().equals(Situacao.RASCUNHO))
			throw new ValidationException().addViolation("situacao",
					"Favor informar a situação desta análise.");
		
		if (fase.getSituacaoJustificativa() == null
				|| fase.getSituacaoJustificativa().isEmpty())
			throw new ValidationException().addViolation("justificativa",
					"Favor informar uma justificativa.");
		
		
		if(fase.getFase().equals(FaseEnum.PROSPECCAO)){
			Prospeccao p = (Prospeccao)fase;
			if (p.getTestes() == null || p.getTestes().isEmpty())
				throw new ValidationException().addViolation("testes", "Favor informar os testes realizados.").addViolation(null, "Favor informar os testes realizados.");
			if (fase.getConclusao() == null || fase.getConclusao().isEmpty())
				throw new ValidationException().addViolation("conclusao", "Favor informar a conclusão.").addViolation(null, "Favor informar a conclusão.");	
					
			if (faseProdutoDAO.produtosDaFase(fase).isEmpty())
				throw new ValidationException().addViolation(null, "Ao menos um produto deve ter sido prospectado.");	
		}
		

		validarFinalizarCamposProximaFase(fase);
	}


	/**
	 * Valida os campos da proxima fase apenas se a mesma tiver sido aprovada.
	 * @param fase
	 */
	private void validarFinalizarCamposProximaFase(Fase fase) {
		if (fase.getSituacao().equals(Situacao.APROVADO)) {
			if (fase.getProximaFase() == null)
				throw new ValidationException()
						.addViolation("proximaFase",
								"Para finalizar é preciso definir a próxima fase.")
						.addViolation(null,
								"Para finalizar é preciso definir a próxima fase.");
			
			
			if (fase.getProximaFaseUnidadeGestora() == null || fase.getProximaFaseUnidadeGestora().isEmpty())
				throw new ValidationException()
						.addViolation("proximaFaseUnidadeGestora",
								"Para finalizar é preciso definir a unidade gestora da próxima fase.")
						.addViolation(null,
								"Para finalizar é preciso definir a unidade gestora da próxima fase.");
			
			if (fase.getProximaFaseLider() == null)
				throw new ValidationException()
						.addViolation("proximaFaseLider",
								"Para finalizar é preciso definir o lider da unidade gestora da próxima fase.")
						.addViolation(null,
								"Para finalizar é preciso definir o lider da unidade gestora da próxima fase.");

			if (fase.getProximaFaseJustificativa() == null
					|| fase.getProximaFaseJustificativa().isEmpty())
				throw new ValidationException()
						.addViolation("proximaFaseJustificativa",
								"Para finalizar é preciso preencher a justificativa da próxima fase.")
						.addViolation(null,
								"Para finalizar é preciso definir a próxima fase.");
		}
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



	public Fase salvar(Analise fase) {
		validarSalvar(fase);
		if(fase.getId()!=null){
			faseDAO.update(fase);
		}else{
			faseDAO.insert(fase);
		}
		return fase;
	}
	
	public Fase salvar(Prospeccao fase) {
		validarSalvar(fase);
		if(fase.getId()!=null){
			faseDAO.update(fase);
		}else{
			faseDAO.insert(fase);
		}
		return fase;
	}
		


	private void validarSalvar(Fase fase) {
		
		if (fase.getDataFinalizacao()!=null){
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			throw new ValidationException().addViolation(null,
					"Esta fase ("+fase.getFase()+") já foi finalizada em "+format.format(fase.getDataFinalizacao()));
		}
		
		if (fase.getObjetivo() == null || fase.getObjetivo().isEmpty())
			throw new ValidationException().addViolation("objetivo", "Favor informar o objetivo.");
		
		if (fase.getDataRealizacao() == null)
			throw new ValidationException().addViolation("dataRealizacao", "Favor informar a data de realização.");
		
		if (fase.getUnidadeGestora() == null || fase.getUnidadeGestora().isEmpty())
			throw new ValidationException().addViolation("unidadeGestora", "É preciso definir a unidade gestora.");
				
	}
	
	private void validarSalvar(Analise fase) {
		validarSalvar((Fase)fase);
		if (fase.getDemandanteUnidade()== null || fase.getDemandanteUnidade().isEmpty())
			throw new ValidationException().addViolation("demandanteUnidade", "Favor informar oa unidade demandante.");

		if (fase.getDemandanteRepresentante()== null)
			throw new ValidationException().addViolation("demandanteRepresentante", "Favor informar o representante.");
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

	;;;
	
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
	

}
