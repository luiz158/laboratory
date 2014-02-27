import java.util.Date;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.FaseProduto;
import br.gov.serpro.catalogo.entity.Produto;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.persistence.AnaliseDAO;
import br.gov.serpro.catalogo.persistence.FaseProdutoDAO;
import br.gov.serpro.catalogo.persistence.ProdutoDAO;
import br.gov.serpro.catalogo.persistence.ProspeccaoDAO;



@BusinessController
public class CargaInicial {
	
	@Inject
	private AnaliseDAO analiseDAO;
	
	@Inject
	private ProspeccaoDAO prospeccaoDAO;
	
	@Inject
	private ProdutoDAO produtoDAO;
	
	@Inject
	private FaseProdutoDAO faseProdutoDAO;
	
	
	@Startup
	@Transactional
	public void criarAnalisesDeExemplo(){
		Analise a = new Analise();		
		a.setArea("SUPDE");
		a.setCodigoReferencia("R??????");
		a.setDataRealizacao(new Date());
		a.setDemandante("Jacinto Aquino Rego");
		a.setObjetivo("Gostaria de utilizar PHP no desenvolvimento de aplicativos móveis para iPhone");
		a.setGestor("Wilson Simonha Tchê");
		a.setOrigemReferencia("ALM");
		a.setSituacao(Situacao.REPROVADO);
		a.setSituacaoJustificativa("Isso não tem cabimento. iPhones não suporta esta avançada tecnologia.");
		analiseDAO.insert(a);	
		
		a = new Analise();		
		a.setArea("CETEC");
		a.setCodigoReferencia("R171");
		a.setDataRealizacao(new Date());
		a.setDemandante("Edson Arantes Nascimento");
		a.setObjetivo("Analisar o uso de bolas de futsal na copa do mundo.");
		a.setGestor("Sérgio Hein");
		a.setOrigemReferencia("ALM");
		a.setSituacao(Situacao.APROVADO);
		a.setSituacaoJustificativa("É possível utilizar esta bola, pois será facilitada a ocorrência de tabacas e gols.");
		a.setProximaFase(FaseEnum.PROSPECCAO);
		a.setProximaFaseArea("CTSDR");
		a.setProximaFaseGestor("Robson Ximenes");
		a.setProximaFaseGestorEmail("robson.ximenes@serpro.gov.br");
		a.setProximaFaseJustificativa("Robgol vai internalizar essa parada, pois ele é o melhor jogador de futebol que o SERPRO tem.");
		a = analiseDAO.insert(a);	
		
		Prospeccao p = new Prospeccao();
		p.setArea("CTSDR");
		p.setCodigoReferencia("R172");
		p.setOrigemReferencia("ALM");
		p.setObjetivo("Selecionar os tipos de bolas de futsal na copa do mundo.");
		p.setGestor("Robson Ximenes");
		p.setFaseAnterior(a);
		p.setDataRealizacao(new Date());
		p.setSituacao(Situacao.APROVADO);
		p.setTestes("Os testes realizados foram feitos na regional Salvador em laboratório isolado. Foram averiguados testes de blablablabla. Maiores detalhes sobre os testes realizados podem ser encontrados em Arquivo1.odt.");
		p.setConclusao("A prospecção foi bem sucedida em que, pelos testes realizados, o Ubuntu 12 foi selecionado para ser internalizado.");
		prospeccaoDAO.insert(p);
		
		
		Produto produto = new Produto();
		produto.setDescricao("Melhor produto educativo dos ultimos 20 anos.");
		produto.setFabricante("Tabajaras");
		produto.setFornecedor("Fornicador");
		produto.setNome("Havaianas de Pau");
		produto.setLicenciamento("GPL");
		produto.setLegado(false);
		produto.setVersao("2.0");
		produto.setRelease("2.0");
		produtoDAO.insert(produto);
		
		
		FaseProduto fp = new FaseProduto();
		fp.setFase(p);
		fp.setProduto(produto);
		faseProdutoDAO.insert(fp);
				
		
		produto = new Produto();
		produto.setDescricao("Maior trocador de porcaria via telefones modernos que tem internê");
		produto.setFabricante("Zuzkw");
		produto.setFornecedor("Faice");
		produto.setNome("Uotsap");
		produto.setLicenciamento("GPL");
		produto.setLegado(false);
		produto.setVersao("2.0");
		produto.setRelease("2.0");
		produtoDAO.insert(produto);
		
		
		fp = new FaseProduto();
		fp.setFase(p);
		fp.setProduto(produto);
		faseProdutoDAO.insert(fp);
		
		for(int i=1; i<=5; i++){		
			produto = new Produto();
			produto.setDescricao("Descricao do produto");
			produto.setFabricante("Fabricante fake");
			produto.setFornecedor("Fornecedor fake");
			produto.setNome("Produto Fake");
			produto.setLicenciamento("GPL");
			produto.setLegado(false);
			produto.setVersao("2."+i);
			produto.setRelease("2."+i);
			produtoDAO.insert(produto);			
			
			fp = new FaseProduto();
			fp.setFase(p);
			fp.setProduto(produto);
			faseProdutoDAO.insert(fp);		
		}
		
	}

	
	
}
