package br.gov.serpro.catalogo.persistence;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Produto;

@PersistenceController
public class ProdutoDAO extends JPACrud<Produto, Long> {

	private static final long serialVersionUID = 1L;
	
	public List<Produto> listarProdutoPorCategoria(Long categoriaId){
		String jpql = "select p from Produto p inner join p.categorias s where s.id = "+categoriaId;
		return super.findByJPQL(jpql);
	}
	
	public List<Produto> listarProdutosSemFase(String nomeProduto){
		String jpql = "select p from FaseProduto fp right outer join fp.produto p where fp IS NULL and upper(p.nome) like upper('%"+nomeProduto+"%')";
		return super.findByJPQL(jpql);
	}
	
	public List<Produto> listarProdutosPorNome(String nomeProduto){
		String jpql = "SELECT p FROM Produto p WHERE upper(p.nome) like upper('%"+nomeProduto+"%')";
		return super.findByJPQL(jpql);
	}

	public List<Produto> listarProdutosUnicosPorCategoria(Long categoriaId) {
		String jpql = "select p from Produto p inner join p.categorias s where s.id = "+categoriaId+" and p.atualizacao = false";
		return super.findByJPQL(jpql);
	}
	
	public List<Produto> findByJPQL(String jpql){
		return super.findByJPQL(jpql);
	}
}