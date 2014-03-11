package br.gov.serpro.catalogo.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Declinio;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.Internalizacao;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.entity.Sustentacao;
import br.gov.serpro.catalogo.persistence.FaseDAO;
import br.gov.serpro.catalogo.persistence.FaseProdutoDAO;

@BusinessController
public class FaseBC {

	@Inject
	private FaseDAO faseDAO;
	
	@Inject
	private FaseProdutoDAO faseProdutoDAO;

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

			if (fase.getProximaFaseGestor() == null
					|| fase.getProximaFaseGestor().isEmpty())
				throw new ValidationException()
						.addViolation("proximaFaseGestor",
								"Para finalizar é preciso definir o gestor da próxima fase.")
						.addViolation(null,
								"Para finalizar é preciso definir a próxima fase.");

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
		proximafase.setArea(fase.getProximaFaseArea());
		proximafase.setGestor(fase.getProximaFaseGestor());

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
		
		if (fase.getGestor() == null || fase.getGestor().isEmpty())
			throw new ValidationException().addViolation("gestor", "É preciso definir o gestor.");
				
	}
	
	private void validarSalvar(Analise fase) {
		validarSalvar((Fase)fase);
		if (fase.getDemandante() == null || fase.getDemandante().isEmpty())
			throw new ValidationException().addViolation("demandante", "Favor informar o demandante.");

	}
	

}
