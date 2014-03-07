package br.gov.serpro.catalogo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Produto;
import br.gov.serpro.catalogo.entity.Subcategoria;
import br.gov.serpro.catalogo.persistence.ProdutoDAO;

@ValidateRequest
@Path("/api/produto")
@Produces(APPLICATION_JSON)
public class ProdutoService {

	@Inject
	private ProdutoDAO produtoDAO;
	
	@POST
	@Transactional
	public Long salvar(@Valid Produto produto) {
		return produtoDAO.insert(produto).getId();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void excluir(@NotNull @PathParam("id") Long id) {
		produtoDAO.delete(id);
	}

	@GET
	@Path("/listar/{nome}")
	public List<Produto> listarLike(@NotNull @PathParam("nome") String nome) {
		String jpql = "SELECT p FROM Produto p WHERE upper(p.nome) like upper('%"+nome+"%')";
		return produtoDAO.findByJPQL(jpql);
	}
	
	@GET
	public List<Produto> listar() {
		return produtoDAO.findAll();
	}
	
	@PUT
	@Transactional
	public void alterar(@Valid Produto produto) {
		produtoDAO.update(produto);
	}
	
	@GET
	@Path("/{id}")
	public Produto carregar(@NotNull @PathParam("id") Long id) {
		return produtoDAO.load(id);
	}
	
	@GET
	@Path("/listarBySubcategoria/{subcategoriaId}")
	public List<Produto> listarSubcategoriaByTema(@NotNull @PathParam("subcategoriaId") Long subcategoriaId) {
		String jpql = "SELECT p FROM Produto p WHERE p.subcategoria.id ="+subcategoriaId;
		return produtoDAO.findByJPQL(jpql);
	}
}
