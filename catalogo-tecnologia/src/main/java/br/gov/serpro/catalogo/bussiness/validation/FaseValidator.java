package br.gov.serpro.catalogo.bussiness.validation;

import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.resteasy.util.ValidationException.Violation;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Declinio;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.Internalizacao;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.entity.StatusEnum;
import br.gov.serpro.catalogo.entity.Sustentacao;
import br.gov.serpro.catalogo.persistence.FaseDAO;
import br.gov.serpro.catalogo.persistence.FaseProdutoDAO;
import br.gov.serpro.catalogo.security.Roles;

@BusinessController
public class FaseValidator {

	@Inject
	private FaseProdutoDAO faseProdutoDAO;

	@Inject
	private FaseDAO faseDAO;
	
	@Inject
	private SecurityContext securityCtx;

	private Set<Violation> validarSalvar(final Fase fase) {
		Set<Violation> violacoes = new LinkedHashSet<Violation>();
		if (fase.getDataFinalizacao() != null && !securityCtx.hasRole(Roles.ADMINISTRADOR)) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			throw new ValidationException().addViolation(null,
					"Esta fase (" + fase.getFase() + ") já foi finalizada em "
							+ format.format(fase.getDataFinalizacao()));
		}

		if (fase.getObjetivo() == null || fase.getObjetivo().isEmpty())
			violacoes.add(new Violation("objetivo", "Favor informar o objetivo."));

		return violacoes;
	}

	public Set<Violation> validarSalvar(Analise fase) {
		Set<Violation> violacoes = validarSalvar((Fase) fase);
				
		if (fase.getDemandanteUnidade() == null
				|| fase.getDemandanteUnidade().isEmpty())
			violacoes.add(new Violation("demandanteUnidade",
					"Favor informar a unidade demandante."));

		if (fase.getDemandanteRepresentante() == null)
			violacoes.add(new Violation("demandanteRepresentante",
					"Favor informar o representante."));
		lancarExcecoesDeViolacao(violacoes);
		return violacoes;
	}
	
	public Set<Violation> validarSalvar(Prospeccao fase) {
		Set<Violation> violacoes = validarSalvar((Fase) fase);
		lancarExcecoesDeViolacao(violacoes);
		return violacoes;
	}

	public Set<Violation> validarSalvar(Internalizacao fase) {
		Set<Violation> violacoes = validarSalvar((Fase) fase);
		lancarExcecoesDeViolacao(violacoes);
		return violacoes;
	}
	
	public Set<Violation> validarSalvar(Sustentacao fase) {
		Set<Violation> violacoes = validarSalvar((Fase) fase);
		lancarExcecoesDeViolacao(violacoes);
		return violacoes;
	}
	
	public Set<Violation> validarSalvar(Declinio fase) {
		Set<Violation> violacoes = validarSalvar((Fase) fase);
		lancarExcecoesDeViolacao(violacoes);
		return violacoes;
	}

	/**
	 * Valida os campos obrigatórios para finalizar uma fase.
	 * 
	 * @param fase
	 */
	public void validarFinalizar(final Fase fase) {
		Set<Violation> violacoes = new LinkedHashSet<Violation>();
		if (fase.getDataRealizacao() == null)
			violacoes.add(new Violation("dataRealizacao",
					"Favor informar a data de realização."));

		if (fase.getUnidadeGestora() == null
				|| fase.getUnidadeGestora().isEmpty())
			violacoes.add(new Violation("unidadeGestora",
					"É preciso definir a unidade gestora."));
		
		if (fase.getFase().equals(FaseEnum.ANALISE)) {
			violacoes.addAll(validarSalvar((Analise) fase));

			if (fase.getSituacao() == null
					|| fase.getSituacao().equals(Situacao.RASCUNHO))
				violacoes.add(new Violation("situacao",
						"Favor informar a situação desta análise."));

			if (fase.getSituacaoJustificativa() == null
					|| fase.getSituacaoJustificativa().isEmpty())
				violacoes.add(new Violation("justificativa",
						"Favor informar uma justificativa."));

		}
		if (fase.getFase().equals(FaseEnum.PROSPECCAO)) {
			Prospeccao p = (Prospeccao) fase;
			violacoes.addAll(validarSalvar(p));
			if (p.getTestes() == null || p.getTestes().isEmpty()) {
				violacoes.add(new Violation("testes",
						"Favor informar os testes realizados."));
				violacoes.add(new Violation(null,
						"Favor informar os testes realizados."));
			}
			if (fase.getConclusao() == null || fase.getConclusao().isEmpty()) {
				violacoes.add(new Violation("conclusao",
						"Favor informar a conclusão."));
				violacoes
						.add(new Violation(null, "Favor informar a conclusão."));
			}

			if (faseProdutoDAO.produtosDaFase(fase).isEmpty()) {
				violacoes.add(new Violation(null,
						"Ao menos um produto deve ter sido prospectado."));
			}
		}

		if (fase.getFase().equals(FaseEnum.INTERNALIZACAO)) {
			Internalizacao i = (Internalizacao) fase;
			violacoes.addAll(validarSalvar(i));
			
			if (i.getAquisicaoNecessaria() == null)
				violacoes.add(new Violation("aquisicaoNecessaria",
						"Favor informar a necessidade de aquisição."));

			if (i.getCapacitacao() == null)
				violacoes.add(new Violation("capacitacao",
						"Favor informar a necessidade de capacitação."));
			
			if (i.getAquisicaoNecessaria() > 0) {
				if (i.getAquisicaoItens() == null
						|| i.getAquisicaoItens().isEmpty()) {
					violacoes.add(new Violation("aquisicaoItens",
							"Favor as aquisições necessárias"));
					violacoes.add(new Violation(null,
							"Favor as aquisições necessárias"));
				}
			}

			if (i.getCapacitacao() > 0) {
				if (i.getCapacitacaoNecessarias() == null
						|| i.getCapacitacaoNecessarias().isEmpty()) {
					violacoes.add(new Violation("capacitacaoNecessarias",
							"Favor as capacitações necessárias"));
					violacoes.add(new Violation(null,
							"Favor as capacitações necessárias"));
				}
			}

			if (i.getRestricao() > 0) {
				if (i.getRestricaoQuemPode() == null
						|| i.getRestricaoQuemPode().isEmpty()) {
					violacoes
							.add(new Violation("restricaoQuemPode",
									"Favor informar quais clientes e/ou áreas internas podem utilizar o Produto"));
					violacoes
							.add(new Violation(null,
									"Favor informar quais clientes e/ou áreas internas podem utilizar o Produto"));
				}

				if (i.getRestricaoJustificativa() == null
						|| i.getRestricaoJustificativa().isEmpty()) {
					violacoes
							.add(new Violation("restricaoJustificativa",
									"Favor informar a justificativa da restrição de uso."));
					violacoes
							.add(new Violation(null,
									"Favor informar a justificativa da restrição de uso."));
				}
			}

			if (i.getAnaliseDeRiscos() < 1) {
				if (i.getAnaliseDeRiscosJustificativa() == null
						|| i.getAnaliseDeRiscosJustificativa().isEmpty()) {
					violacoes
							.add(new Violation("analiseDeRiscosJustificativa",
									"Favor justificar a inexistencia da Análise de riscos."));
					violacoes
							.add(new Violation(null,
									"Favor justificar a inexistencia da Análise de riscos."));
				}
			}
		}

		if (fase.getFase().equals(FaseEnum.SUSTENTACAO)) {
			Sustentacao i = (Sustentacao) fase;
			violacoes.addAll(validarSalvar(i));
			if (i.getAquisicaoNecessaria() > 0) {
				if (i.getAquisicaoItens() == null
						|| i.getAquisicaoItens().isEmpty())
					violacoes.add(new Violation("aquisicaoItens",
							"Favor as aquisições necessárias"));
				violacoes.add(new Violation(null,
						"Favor as aquisições necessárias"));
			}

			if (i.getCapacitacao() > 0) {
				if (i.getCapacitacaoNecessarias() == null
						|| i.getCapacitacaoNecessarias().isEmpty()) {
					violacoes.add(new Violation("capacitacaoNecessarias",
							"Favor as capacitações necessárias"));
					violacoes.add(new Violation(null,
							"Favor as capacitações necessárias"));
				}
			}

			if (i.getRestricao() > 0) {
				if (i.getRestricaoQuemPode() == null
						|| i.getRestricaoQuemPode().isEmpty()) {
					violacoes
							.add(new Violation("restricaoQuemPode",
									"Favor informar quais clientes e/ou áreas internas podem utilizar o Produto"));
					violacoes
							.add(new Violation(null,
									"Favor informar quais clientes e/ou áreas internas podem utilizar o Produto"));
				}
				if (i.getRestricaoJustificativa() == null
						|| i.getRestricaoJustificativa().isEmpty()) {
					violacoes
							.add(new Violation("restricaoJustificativa",
									"Favor informar a justificativa da restrição de uso."));
					violacoes
							.add(new Violation(null,
									"Favor informar a justificativa da restrição de uso."));
				}
			}

			if (i.getAnaliseDeRiscos() < 1) {
				if (i.getAnaliseDeRiscosJustificativa() == null
						|| i.getAnaliseDeRiscosJustificativa().isEmpty()) {
					violacoes
							.add(new Violation("analiseDeRiscosJustificativa",
									"Favor justificar a inexistencia da Análise de riscos."));
					violacoes
							.add(new Violation(null,
									"Favor justificar a inexistencia da Análise de riscos."));
				}
			}
		}

		if (fase.getFase().equals(FaseEnum.DECLINIO)) {
			violacoes.addAll(validarSalvar((Declinio) fase));
		}

		violacoes.addAll(validarFinalizarCamposProximaFase(fase));
		
		lancarExcecoesDeViolacao(violacoes);

	}

	private void lancarExcecoesDeViolacao(Set<Violation> violacoes) {
		if (!violacoes.isEmpty()) {
			ValidationException ex = new ValidationException();
			for (Violation v : violacoes) {
				ex.addViolation(v.property, v.message);
			}
			ex.addViolation(null,"Favor, verificar os campos obrigatórios");
			throw ex;
		}
	}

	
	public void validarCriarProximaFase(final Fase pf){
		lancarExcecoesDeViolacao(validarFinalizarCamposProximaFase(pf));
	}
	
	/**
	 * Valida os campos da proxima fase apenas se a mesma tiver sido aprovada.
	 * 
	 * @param fase
	 */
	private Set<Violation> validarFinalizarCamposProximaFase(final Fase fase) {
		Set<Violation> violacoes = new LinkedHashSet<ValidationException.Violation>();
		if (fase.getExecutarProximaFase() > 0) {
			if (fase.getProximaFase() == null) {
				violacoes.add(new Violation("proximaFase",
						"Para finalizar é preciso definir a próxima fase."));
				violacoes.add(new Violation(null,
						"Para finalizar é preciso definir a próxima fase."));
			}

			if (fase.getProximaFaseUnidadeGestora() == null
					|| fase.getProximaFaseUnidadeGestora().isEmpty()) {
				violacoes
						.add(new Violation("proximaFaseUnidadeGestora",
								"Para finalizar é preciso definir a unidade gestora da próxima fase."));
				violacoes
						.add(new Violation(null,
								"Para finalizar é preciso definir a unidade gestora da próxima fase."));
			}

			if (fase.getProximaFaseLider() == null) {
				violacoes
						.add(new Violation("proximaFaseLider",
								"Para finalizar é preciso definir o lider da unidade gestora da próxima fase."));
				violacoes
						.add(new Violation(null,
								"Para finalizar é preciso definir o lider da unidade gestora da próxima fase."));
			}

			if (fase.getProximaFaseJustificativa() == null
					|| fase.getProximaFaseJustificativa().isEmpty()) {
				violacoes
						.add(new Violation("proximaFaseJustificativa",
								"Para finalizar é preciso preencher a justificativa da próxima fase."));
				violacoes.add(new Violation(null,
						"Para finalizar é preciso definir a próxima fase."));
			}
		}
		return violacoes;
	}

	public void validarExcluir(Fase fase) {
		Fase fasePosterior = faseDAO.obterFasePosterior(fase.getId());
		if (fasePosterior != null
				&& !fasePosterior.getStatus().equals(StatusEnum.EXCLUIDO)) {
			throw new ValidationException().addViolation(null,
					"Não é possível excluir a " + fase.getFase()
							+ ". Pois existe uma " + fasePosterior.getFase()
							+ " em sequência.");
		}
	}

}
