package br.gov.serpro.catalogo.bussiness.validation;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.Internalizacao;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.entity.Sustentacao;
import br.gov.serpro.catalogo.persistence.FaseProdutoDAO;

@BusinessController
public class FaseValidator {
		
	@Inject 
	private FaseProdutoDAO faseProdutoDAO;
	
	
	public void validarSalvar(final Fase fase) {		
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
	
	public void validarSalvar(Analise fase) {
		validarSalvar((Fase)fase);
		if (fase.getDemandanteUnidade()== null || fase.getDemandanteUnidade().isEmpty())
			throw new ValidationException().addViolation("demandanteUnidade", "Favor informar oa unidade demandante.");

		if (fase.getDemandanteRepresentante()== null)
			throw new ValidationException().addViolation("demandanteRepresentante", "Favor informar o representante.");
	}
	
	public void validarSalvar(Internalizacao fase) {
		validarSalvar((Fase)fase);
		if (fase.getAquisicaoNecessaria() == null )
			throw new ValidationException().addViolation("aquisicaoNecessaria", "Favor informar a necessidade de aquisição.");

		if (fase.getCapacitacao() == null )
			throw new ValidationException().addViolation("capacitacao", "Favor informar a necessidade de capacitação.");

	}
	
	/**
	 * Valida os campos obrigatórios para finalizar uma fase.
	 * 
	 * @param fase
	 */
	public void validarFinalizar(final Fase fase) {
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
		
		if(fase.getFase().equals(FaseEnum.INTERNALIZACAO)){
			Internalizacao i = (Internalizacao)fase;
			if(i.getAquisicaoNecessaria()>0){
				if (i.getAquisicaoItens() == null || i.getAquisicaoItens().isEmpty())
					throw new ValidationException().addViolation("aquisicaoItens", "Favor as aquisições necessárias")
							.addViolation(null, "Favor as aquisições necessárias");
			}
			
			if(i.getCapacitacao()>0){
				if (i.getCapacitacaoNecessarias()== null || i.getCapacitacaoNecessarias().isEmpty())
					throw new ValidationException().addViolation("capacitacaoNecessarias", "Favor as capacitações necessárias")
							.addViolation(null, "Favor as capacitações necessárias");
			}
			
			if(i.getRestricao()>0){
				if (i.getRestricaoQuemPode()== null || i.getRestricaoQuemPode().isEmpty())
					throw new ValidationException().addViolation("restricaoQuemPode", "Favor informar quais clientes e/ou áreas internas podem utilizar o Produto")
							.addViolation(null, "Favor informar quais clientes e/ou áreas internas podem utilizar o Produto");
				if (i.getRestricaoJustificativa()== null || i.getRestricaoJustificativa().isEmpty())
					throw new ValidationException().addViolation("restricaoJustificativa", "Favor informar a justificativa da restrição de uso.")
							.addViolation(null, "Favor informar a justificativa da restrição de uso.");
			}
			
			if(i.getAnaliseDeRiscos()<1){
				if (i.getAnaliseDeRiscosJustificativa()== null || i.getAnaliseDeRiscosJustificativa().isEmpty())
					throw new ValidationException().addViolation("analiseDeRiscosJustificativa", "Favor justificar a inexistencia da Análise de riscos.")
							.addViolation(null, "Favor justificar a inexistencia da Análise de riscos.");
			}
		}
		
		if(fase.getFase().equals(FaseEnum.SUSTENTACAO)){
			Sustentacao i = (Sustentacao)fase;
			if(i.getAquisicaoNecessaria()>0){
				if (i.getAquisicaoItens() == null || i.getAquisicaoItens().isEmpty())
					throw new ValidationException().addViolation("aquisicaoItens", "Favor as aquisições necessárias")
							.addViolation(null, "Favor as aquisições necessárias");
			}
			
			if(i.getCapacitacao()>0){
				if (i.getCapacitacaoNecessarias()== null || i.getCapacitacaoNecessarias().isEmpty())
					throw new ValidationException().addViolation("capacitacaoNecessarias", "Favor as capacitações necessárias")
							.addViolation(null, "Favor as capacitações necessárias");
			}
			
			if(i.getRestricao()>0){
				if (i.getRestricaoQuemPode()== null || i.getRestricaoQuemPode().isEmpty())
					throw new ValidationException().addViolation("restricaoQuemPode", "Favor informar quais clientes e/ou áreas internas podem utilizar o Produto")
							.addViolation(null, "Favor informar quais clientes e/ou áreas internas podem utilizar o Produto");
				if (i.getRestricaoJustificativa()== null || i.getRestricaoJustificativa().isEmpty())
					throw new ValidationException().addViolation("restricaoJustificativa", "Favor informar a justificativa da restrição de uso.")
							.addViolation(null, "Favor informar a justificativa da restrição de uso.");
			}
			
			if(i.getAnaliseDeRiscos()<1){
				if (i.getAnaliseDeRiscosJustificativa()== null || i.getAnaliseDeRiscosJustificativa().isEmpty())
					throw new ValidationException().addViolation("analiseDeRiscosJustificativa", "Favor justificar a inexistencia da Análise de riscos.")
							.addViolation(null, "Favor justificar a inexistencia da Análise de riscos.");
			}
		}
		
		validarFinalizarCamposProximaFase(fase);
	}
	
	/**
	 * Valida os campos da proxima fase apenas se a mesma tiver sido aprovada.
	 * @param fase
	 */
	private void validarFinalizarCamposProximaFase(final Fase fase) {
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

}
