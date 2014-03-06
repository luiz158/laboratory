package br.gov.serpro.catalogo.persistence;

import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseProduto;

@PersistenceController
public class FaseProdutoDAO extends JPACrud<FaseProduto, Long> {
	
	private static final long serialVersionUID = 1L;
		
	@SuppressWarnings("unchecked")
	public List<FaseProduto> produtosDaFase(Fase fase) {
		Query query = getEntityManager().createNamedQuery(FaseProduto.PRODUTOS_DA_FASE, FaseProduto.class);
		query.setParameter("fase", fase.getId());		
		return query.getResultList();
	}
	
}
