package br.gov.serpro.catalogo.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	
	public List<FaseProduto> listarFaseProdutoPorNomeProduto(String nomeProduto){
		String jpql = "select fp2 from FaseProduto fp2 where fp2.id in ("
				+ "select max(fp.id) from FaseProduto fp where upper(fp.produto.nome) like upper('%"+nomeProduto+"%') group by fp.fase.faseInicial.id, fp.produto.id) ORDER BY fp2.produto.id";
		return super.findByJPQL(jpql);
	}

	public Map<String, Object> versoesDosProdutosPorNomeProduto(String nomeProduto) {
		String jpql = "select fp from FaseProduto fp where fp.id in "
				+ "(select max(fp2.id) from FaseProduto fp2 where fp2.produto.nome = '"+nomeProduto+"' and fp2.fase.fase <> 'ANALISE' group by fp2.fase.faseInicial.id) ORDER BY fp.produto.versao";
		
		List<FaseProduto> listaFaseProduto = super.findByJPQL(jpql);
		
		Map<String, Object> produtoMap = new HashMap<String,Object>();
		List<Map<String,Object>> listaVersoes = new ArrayList<Map<String,Object>>();
		
		Iterator i = listaFaseProduto.iterator();
		while (i.hasNext()) {
			FaseProduto fp = (FaseProduto)i.next();
			
			Map<String, Object> faseMap = new HashMap<String,Object>();
			faseMap.put("id",fp.getId());
			faseMap.put("fase", fp.getFase().getFase());
			
			Map<String, Object> versaoMap = new HashMap<String,Object>();
			versaoMap.put("id", fp.getProduto().getId());
			versaoMap.put("versao", fp.getProduto().getVersao());
			versaoMap.put("fase", faseMap);
			
			listaVersoes.add(versaoMap);
		}
		produtoMap.put("nome", nomeProduto);
		produtoMap.put("versoes", listaVersoes);
		return produtoMap;
	}
	
}
