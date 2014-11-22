package br.gov.serpro.catalogo.persistence;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseInteressado;
import br.gov.serpro.catalogo.entity.FaseMembro;
import br.gov.serpro.catalogo.entity.User;

@PersistenceController
public class FaseInteressadoDAO extends JPACrud<FaseInteressado, Long> {
	
	private static final long serialVersionUID = 1L;
		
	@SuppressWarnings("unchecked")
	public List<FaseInteressado> interessados(Fase fase) {
		Query query = getEntityManager().createNamedQuery(FaseInteressado.INTERESSADOS_DA_FASE, FaseInteressado.class);
		query.setParameter("fase", fase.getId());		
		return query.getResultList();
	}
	
	public boolean interessadoJaCadastrado(Long fase, String cpf) {
		String jpql = "select f from FaseInteressado f INNER JOIN f.user u where f.fase.id = :fase and u.cpf = :cpf";
		TypedQuery<FaseInteressado> query = getEntityManager().createQuery(jpql, FaseInteressado.class);
		query.setParameter("cpf", cpf);
		query.setParameter("fase", fase);
		return !query.getResultList().isEmpty();
	}

	public List<FaseInteressado> fasesComoInteressado(User usuario) {
		String jpql = "select fm from FaseInteressado fm JOIN fetch fm.fase where fm.user.id = :id";
		TypedQuery<FaseInteressado> query = getEntityManager().createQuery(jpql, FaseInteressado.class);
		query.setParameter("id", Long.valueOf(usuario.getId()));
		return query.getResultList();
	}


	
}
