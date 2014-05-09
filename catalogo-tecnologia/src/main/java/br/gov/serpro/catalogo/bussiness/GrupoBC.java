package br.gov.serpro.catalogo.bussiness;

import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.catalogo.entity.Grupo;
import br.gov.serpro.catalogo.event.GrupoEvent;
import br.gov.serpro.catalogo.event.GrupoEvent.ATUALIZAR;
import br.gov.serpro.catalogo.event.GrupoEvent.CRIAR;
import br.gov.serpro.catalogo.event.GrupoEvent.EXCLUIR;
import br.gov.serpro.catalogo.persistence.GrupoDAO;

@BusinessController
public class GrupoBC {

	@Inject
	private GrupoDAO grupoDAO;
	
	@Inject @CRIAR private Event<GrupoEvent> eventoGrupoCriar;
	@Inject @ATUALIZAR private Event<GrupoEvent> eventoGrupoAtualizar;
	@Inject @EXCLUIR private Event<GrupoEvent> eventoGrupoExcluir;

	public Grupo insert(Grupo grupo) {
		Grupo g = grupoDAO.insert(grupo);
		eventoGrupoCriar.fire(new GrupoEvent(grupo));
		return g;
	}

	public void update(Grupo grupo) {
		grupoDAO.update(grupo);
		eventoGrupoAtualizar.fire(new GrupoEvent(grupo));
	}

	public void delete(Long id) {
		Grupo grupo = grupoDAO.load(id);
		grupoDAO.delete(id);
		eventoGrupoExcluir.fire(new GrupoEvent(grupo));
	}

	public List<Grupo> findAll() {
		return grupoDAO.findAll();
	}

	public Grupo load(Long id) {
		return grupoDAO.load(id);
	}
}