package br.gov.serpro.catalogo.persistence;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseMembro;
import br.gov.serpro.catalogo.entity.User;

@PersistenceController
public class FaseMembroDAO extends JPACrud<FaseMembro, Long> {
	
	private static final long serialVersionUID = 1L;
		
	@SuppressWarnings("unchecked")
	public List<FaseMembro> membrosDaFase(Fase fase) {
		Query query = getEntityManager().createNamedQuery(FaseMembro.MEMBROS_DA_FASE, FaseMembro.class);
		query.setParameter("fase", fase.getId());		
		return query.getResultList();
	}
	
	public boolean membroJaCadastrado(Long fase, String cpf) {
		String jpql = "select f from FaseMembro f INNER JOIN f.user u where f.fase.id = :fase and u.cpf = :cpf";
		TypedQuery<FaseMembro> query = getEntityManager().createQuery(jpql, FaseMembro.class);
		query.setParameter("cpf", cpf);
		query.setParameter("fase", fase);
		return !query.getResultList().isEmpty();
	}

	public List<FaseMembro> fasesComoMembro(User usuario) {
		String jpql = "select fm from FaseMembro fm JOIN fetch fm.fase where fm.user.id = :id";
		TypedQuery<FaseMembro> query = getEntityManager().createQuery(jpql, FaseMembro.class);
		query.setParameter("id", Long.valueOf(usuario.getId()));
		return query.getResultList();
	}


	
}
