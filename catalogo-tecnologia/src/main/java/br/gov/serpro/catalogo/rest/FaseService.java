package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.bussiness.FaseBC;
import br.gov.serpro.catalogo.bussiness.FaseHistoricoBC;
import br.gov.serpro.catalogo.bussiness.UsuarioBC;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseHistorico;
import br.gov.serpro.catalogo.entity.FaseInteressado;
import br.gov.serpro.catalogo.entity.FaseMembro;
import br.gov.serpro.catalogo.entity.Usuario;

@Path("fase")
@Produces(APPLICATION_JSON)
public class FaseService {

	@Inject
	private FaseBC faseBC;
	
	@Inject
	private FaseHistoricoBC faseHistoricoBC;
	
	@Inject
	private UsuarioBC usuarioBC;

	@POST
	public List<Fase> pesquisar(FaseDTO fase) {
		return faseBC.pesquisar(fase);
	}

	@GET
	@Path("fluxo/{id}")
	public List<Fase> obterCadeiaDasFases(@PathParam("id") Long id) {
		return faseBC.obterCadeiaDasFases(id);

	}
	
	@GET
	@Path("historico/{id}")
	public List<FaseHistorico> obterHistorico(@PathParam("id") Long id) {
		return faseHistoricoBC.obterHistorico(id);

	}
	
	@POST
	@Path("usuario/carregar")
	public Usuario carregarUsuario(Usuario user) {
		return usuarioBC.carregarOuInserir(user);
	}

	@POST
	@Path("{id}/membros/add")
	public Usuario adicionarMembro(Usuario user, @PathParam("id") Long id) {
		return faseBC.adicionarMembro(user, id);
	}

	@GET
	@Path("{id}/membros")
	public List<FaseMembro> obterMembros(@PathParam("id") Long id) {
		return faseBC.obterMembros(id);
	}

	@DELETE
	@Path("membros/excluir/{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		faseBC.deleteMembro(id);
	}

	@POST
	@Path("{id}/interessados/add")
	public Usuario adicionarInteressado(Usuario user, @PathParam("id") Long id) {
		return faseBC.adicionarInteressado(user, id);
	}

	@GET
	@Path("{id}/interessados")
	public List<FaseInteressado> obterInteressados(@PathParam("id") Long id) {
		return faseBC.obterInteressados(id);
	}

	@DELETE
	@Path("interessados/excluir/{id}")
	@Transactional
	public void excluirInteressados(@NotNull @PathParam("id") Long id) {
		faseBC.deleteInteressado(id);
	}

}
