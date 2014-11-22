package br.gov.serpro.catalogo.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseProduto;
import br.gov.serpro.catalogo.entity.Produto;

@PersistenceController
public class FaseProdutoDAO extends JPACrud<FaseProduto, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDAO produtoDAO;
		
	@SuppressWarnings("unchecked")
	public List<FaseProduto> produtosDaFase(Fase fase) {
		Query query = getEntityManager().createNamedQuery(FaseProduto.PRODUTOS_DA_FASE, FaseProduto.class);
		query.setParameter("fase", fase.getId());		
		return query.getResultList();
	}
	
	public List<FaseProduto> listarFaseProdutoPorNomeProduto(String nomeProduto){
		String jpql = "select fp2 from FaseProduto fp2 where fp2.id in ("
				+ "select max(fp.id) from FaseProduto fp where upper(fp.produto.nome) like upper('%"+nomeProduto+"%') group by fp.fase.faseInicial.id, fp.produto.id) ORDER BY fp2.produto.id";
		return super.findByJPQL(jpql);
	}

	public List<Map<String, Object>> produtoComVersoesEFases(String nomeProduto) {
		String jpql = "select fp from FaseProduto fp where fp.produto.nome = '"+nomeProduto+"' and fp.fase.fase <> 'ANALISE' ORDER BY fp.produto.versao, fp.fase.id";
		
		List<FaseProduto> listaFaseProduto = super.findByJPQL(jpql);
		
		List<Map<String,Object>> listaProdutoVersaoFases = new ArrayList<Map<String,Object>>();
		
		for (int x=0; x<listaFaseProduto.size(); x++){
			
			FaseProduto fp = (FaseProduto)listaFaseProduto.get(x);
			
			List<Map<String,Object>> listaFases = new ArrayList<Map<String,Object>>();
			Map<String, Object> faseMap = new HashMap<String,Object>();
			Map<String, Object> produtoVersaoFases = new HashMap<String,Object>();
			
			produtoVersaoFases.put("id", fp.getProduto().getId());
			produtoVersaoFases.put("versao", fp.getProduto().getVersao());
			produtoVersaoFases.put("nome", fp.getProduto().getNome());
			
			faseMap.put("id",fp.getFase().getId());
			faseMap.put("fase", fp.getFase().getFase());
			faseMap.put("dataRealizacao", fp.getFase().getDataRealizacao());
			faseMap.put("dataFinalizacao", fp.getFase().getDataFinalizacao());
			listaFases.add(faseMap);
			
			for (int y=x+1; y<listaFaseProduto.size(); y++){
				FaseProduto fp2 = (FaseProduto)listaFaseProduto.get(y);
				if(fp2.getProduto().getVersao().equals(fp.getProduto().getVersao())) {
					faseMap = new HashMap<String,Object>();
					faseMap.put("id",fp2.getFase().getId());
					faseMap.put("fase", fp2.getFase().getFase());
					faseMap.put("dataRealizacao", fp2.getFase().getDataRealizacao());
					faseMap.put("dataFinalizacao", fp2.getFase().getDataFinalizacao());
					listaFases.add(faseMap);
					if(y == listaFaseProduto.size() -1) {
						x = listaFaseProduto.size();
					}
				
				}else {
					x = y;
					break;
				}
			}
			produtoVersaoFases.put("fases", listaFases);
			listaProdutoVersaoFases.add(produtoVersaoFases);
		}
		
		jpql = "select p from FaseProduto fp right outer join fp.produto p where fp IS NULL and p.nome='"+nomeProduto+"'";
		List<Produto> produtosSemFase = produtoDAO.findByJPQL(jpql);
		
		Iterator<Produto> i = produtosSemFase.iterator();
		while(i.hasNext()) {
			Produto p = (Produto)i.next();
			Map<String, Object> produtoVersaoFases = new HashMap<String,Object>();
			
			produtoVersaoFases.put("id", p.getId());
			produtoVersaoFases.put("versao", p.getVersao());
			produtoVersaoFases.put("nome", p.getNome());
			produtoVersaoFases.put("fases", null);
			listaProdutoVersaoFases.add(produtoVersaoFases);
		}
		
		return listaProdutoVersaoFases;
	}
	
	public List<Map<String, Object>> versoesDoProduto(String nomeProduto) {
		List<Map<String,Object>> versoes = new ArrayList<Map<String,Object>>();
		String jpql = "select p from Produto p where p.nome='"+nomeProduto+"'";
		List<Produto> produtos = produtoDAO.findByJPQL(jpql);
		for (Produto produto : produtos) {
			List<Map<String,Object>> listaFases = new ArrayList<Map<String,Object>>();
			String jpqlfases = "select fp from FaseProduto fp where fp.produto.id = "+produto.getId()+" and fp.fase.fase <> 'ANALISE' ORDER BY fp.fase.id";			
			List<FaseProduto> listaFaseProduto = super.findByJPQL(jpqlfases);
			for (FaseProduto faseProduto : listaFaseProduto) {
				Map<String,Object> faseMap = new HashMap<String,Object>();
				faseMap.put("id",faseProduto.getFase().getId());
				faseMap.put("fase", faseProduto.getFase().getFase());
				faseMap.put("dataRealizacao", faseProduto.getFase().getDataRealizacao());
				faseMap.put("dataFinalizacao", faseProduto.getFase().getDataFinalizacao());
				listaFases.add(faseMap);
			}
			Map<String, Object> produtoVersaoFases = new HashMap<String,Object>();			
			produtoVersaoFases.put("id", produto.getId());
			produtoVersaoFases.put("versao", produto.getVersao());
			produtoVersaoFases.put("nome", produto.getNome());
			produtoVersaoFases.put("data", produto.getDataLancamento());
			produtoVersaoFases.put("dataDescontinuidade", produto.getDataDescontinuidade());
			produtoVersaoFases.put("fases", listaFases);
			versoes.add(produtoVersaoFases);
		}
		return versoes;
	}
	
}
