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

import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.bussiness.FaseBC;
import br.gov.serpro.catalogo.bussiness.FaseHistoricoBC;
import br.gov.serpro.catalogo.bussiness.UsuarioBC;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseHistorico;
import br.gov.serpro.catalogo.entity.FaseInteressado;
import br.gov.serpro.catalogo.entity.FaseMembro;
import br.gov.serpro.catalogo.entity.Internalizacao;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Sustentacao;
import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.security.Roles;

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
	
	@DELETE
	@Path("excluir/{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		faseBC.delete(id);
	}

	@GET
	@Path("fluxo/{id}")
	public List<Fase> obterCadeiaDasFases(@PathParam("id") Long id) {
		return faseBC.obterCadeiaDasFases(id);

	}
	
	@GET
	@Path("proximafase/{id}")
	public Fase obterProximaFase(@PathParam("id") Long id) {
		return faseBC.obterProximaFase(id);

	}
	
	@POST
	@Transactional
	@Path("proximafase/analise")
	@RequiredRole({Roles.ADMINISTRADOR})
	public Fase criarProximaFaseAnalise(Analise fase) {
		return faseBC.criarProximaFase(faseBC.salvar(fase));
	}
	
	@POST
	@Transactional
	@Path("proximafase/prospeccao")
	@RequiredRole({Roles.ADMINISTRADOR})
	public Fase criarProximaFaseProspeccao(Prospeccao fase) {
		return faseBC.criarProximaFase(faseBC.salvar(fase));
	}
	
	@POST
	@Transactional
	@Path("proximafase/internalizacao")
	@RequiredRole({Roles.ADMINISTRADOR})
	public Fase criarProximaFaseInternalizacao(Internalizacao fase) {
		return faseBC.criarProximaFase(faseBC.salvar(fase));
	}
	
	@POST
	@Transactional
	@Path("proximafase/sustentacao")
	@RequiredRole({Roles.ADMINISTRADOR})
	public Fase criarProximaFaseSustentacao(Sustentacao fase) {
		return faseBC.criarProximaFase(faseBC.salvar(fase));
	}
	
	@GET
	@Path("historico/{id}")
	@Produces(APPLICATION_JSON)
	public List<FaseHistorico> obterHistorico(@PathParam("id") Long id) {
		List<FaseHistorico> result = faseHistoricoBC.obterHistorico(id);
		return result;

	}
	
	@POST
	@Path("usuario/carregar")
	public User carregarUsuario(User user) {
		return usuarioBC.carregarOuInserir(user);
	}

	@POST
	@Path("{id}/membros/add")
	public User adicionarMembro(User user, @PathParam("id") Long id) {
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
	public void excluirMembro(@NotNull @PathParam("id") Long id) {
		faseBC.deleteMembro(id);
	}

	@POST
	@Path("{id}/interessados/add")
	public User adicionarInteressado(User user, @PathParam("id") Long id) {
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
