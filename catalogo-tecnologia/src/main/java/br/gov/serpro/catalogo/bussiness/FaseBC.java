package br.gov.serpro.catalogo.bussiness;

import java.util.Date;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Declinio;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.Internalizacao;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.entity.Sustentacao;
import br.gov.serpro.catalogo.persistence.AnaliseDAO;
import br.gov.serpro.catalogo.persistence.FaseDAO;

@BusinessController
public class FaseBC {

	@Inject
	private AnaliseDAO analiseDAO;

	@Inject
	private FaseDAO faseDAO;

	/**
	 * Método que finaliza a fase e retorna a próxima fase ou null.
	 * 
	 * @param fase
	 * @return
	 */
	public Fase finalizarFase(Fase fase) {

		fase.setDataFinalizacao(new Date());

		Fase proximafase = null;

		if (fase.getFase().equals(FaseEnum.ANALISE)) {
			proximafase = finalizarAnalise((Analise) fase);
		} else if (fase.getFase().equals(FaseEnum.PROSPECCAO)) {
			proximafase = null;
		} else if (fase.getFase().equals(FaseEnum.INTERNALIZACAO)) {
			proximafase = null;
		} else if (fase.getFase().equals(FaseEnum.SUSTENTACAO)) {
			proximafase = null;
		} else if (fase.getFase().equals(FaseEnum.DECLINIO)) {
			proximafase = null;
		}

		return proximafase;
	}

	private Fase finalizarAnalise(Analise analise) {

		Fase proximafase = null;
		
		if (analise.getSituacao() == null
				|| analise.getSituacao().equals(Situacao.RASCUNHO))
			throw new ValidationException().addViolation("situacao",
					"Favor informar a situação desta análise.");

		// Se foi aprovado tem uma proxima fase;
		if (analise.getSituacao().equals(Situacao.APROVADO)) {

			if (analise.getSituacaoJustificativa() == null
					|| analise.getSituacaoJustificativa().length() <= 10)
				throw new ValidationException().addViolation("justificativa",
						"Favor informar uma justificativa.");

			if (analise.getProximaFase() == null)
				throw new ValidationException().addViolation("proximaFase",
						"Para finalizar é preciso definir a próxima fase.");

			if (analise.getProximaFaseGestor() == null
					|| analise.getProximaFaseGestor().isEmpty())
				throw new ValidationException()
						.addViolation("proximaFaseGestor",
								"Para finalizar é preciso definir o gestor da próxima fase.");

			if (analise.getProximaFaseJustificativa() == null
					|| analise.getProximaFaseJustificativa().isEmpty())
				throw new ValidationException()
						.addViolation("proximaFaseJustificativa",
								"Para finalizar é preciso preencher a justificativa da próxima fase.");

			proximafase = getProximaFase(analise);
			faseDAO.insert(proximafase);
		}
		
		analiseDAO.update(analise);

		return proximafase;
	}

	private Fase getProximaFase(Fase fase) {
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

}
