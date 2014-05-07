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
		
		Iterator<FaseProduto> i = listaFaseProduto.iterator();
		while (i.hasNext()) {
			FaseProduto fp = i.next();
			
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

	public List<Map<String,Object>> versoesEFasesDosProdutosPorNomeProduto(String nomeProduto) {
		String jpql = "select fp from FaseProduto fp where fp.produto.nome = '"+nomeProduto+"' and fp.fase.fase <> 'ANALISE' ORDER BY fp.produto.versao, fp.fase.id";
		
		List<FaseProduto> listaFaseProduto = super.findByJPQL(jpql);
		
		List<Map<String,Object>> listaFases = new ArrayList<Map<String,Object>>();
		
		Iterator<FaseProduto> i = listaFaseProduto.iterator();
		while (i.hasNext()) {
			
			Map<String, Object> faseMap = new HashMap<String,Object>();
			List<Map<String,Object>> listaFasesPorChave = new ArrayList<Map<String,Object>>();
			
			FaseProduto fp = i.next();
			
			Map<String, Object> fasesMap = new HashMap<String,Object>();
			fasesMap.put("id",fp.getId());
			fasesMap.put("fase", fp.getFase().getFase());
			
			listaFasesPorChave.add(fasesMap);
			
			faseMap.put("start", fp.getFase().getDataRealizacao());
			faseMap.put("end", fp.getFase().getDataFinalizacao());
			faseMap.put("versao", fp.getProduto().getVersao());
			faseMap.put("fases", listaFasesPorChave);
			listaFases.add(faseMap);
		}
		return listaFases;
	}
	
}
