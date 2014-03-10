package br.gov.serpro.catalogo.bussiness;

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

@BusinessController
public class FaseBC {

	@Inject
	private FaseDAO faseDAO;

	/**
	 * Método que finaliza a fase e retorna a próxima fase ou null.
	 * 
	 * @param fase
	 * @return
	 */
	@Transactional
	public Fase finalizarFase(Fase fase) {

		if (fase.getDataFinalizacao()!=null)
			throw new ValidationException().addViolation("dataFinalizacao",
					"Esta "+fase.getFase()+" já foi finalizada em "+fase.getDataFinalizacao());
		
		fase.setDataFinalizacao(new Date());

		Fase proximafase = null;

		if (fase.getSituacao() == null
				|| fase.getSituacao().equals(Situacao.RASCUNHO))
			throw new ValidationException().addViolation("situacao",
					"Favor informar a situação desta análise.");

		// Se foi aprovado tem uma proxima fase;
		if (fase.getSituacao().equals(Situacao.APROVADO)) {

			if (fase.getSituacaoJustificativa() == null
					|| fase.getSituacaoJustificativa().isEmpty())
				throw new ValidationException().addViolation("justificativa",
						"Favor informar uma justificativa.");

			if (fase.getProximaFase() == null)
				throw new ValidationException().addViolation("proximaFase",
						"Para finalizar é preciso definir a próxima fase.");

			if (fase.getProximaFaseGestor() == null
					|| fase.getProximaFaseGestor().isEmpty())
				throw new ValidationException()
						.addViolation("proximaFaseGestor",
								"Para finalizar é preciso definir o gestor da próxima fase.");

			if (fase.getProximaFaseJustificativa() == null
					|| fase.getProximaFaseJustificativa().isEmpty())
				throw new ValidationException()
						.addViolation("proximaFaseJustificativa",
								"Para finalizar é preciso preencher a justificativa da próxima fase.");

			proximafase = getProximaFase(fase);
			
			faseDAO.insert(proximafase);
		}

		faseDAO.update(fase);

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
		proximafase.setArea(fase.getProximaFaseArea());
		proximafase.setGestor(fase.getProximaFaseGestor());

		return proximafase;
	}



	public Fase salvar(Analise analise) {
		validar(analise);
		if(analise.getId()!=null){
			faseDAO.update(analise);
		}else{
			faseDAO.insert(analise);
		}
		return analise;
	}
	
	public Fase insert(Analise analise) {
		validar(analise);
		return faseDAO.insert(analise);
	}


	private void validar(Analise fase) {
		validar((Fase)fase);
		if (fase.getDemandante() == null || fase.getDemandante().isEmpty())
			throw new ValidationException().addViolation("demandante", "Favor informar o demandante.");

	}


	private void validar(Fase fase) {
		if (fase.getObjetivo() == null || fase.getObjetivo().isEmpty())
			throw new ValidationException().addViolation("objetivo", "Favor informar o objetivo.");
		
		if (fase.getDataRealizacao() == null)
			throw new ValidationException().addViolation("dataRealizacao", "Favor informar a data de realização.");
		
		if (fase.getGestor() == null || fase.getGestor().isEmpty())
			throw new ValidationException().addViolation("gestor", "É preciso definir o gestor.");
				
	}

}
