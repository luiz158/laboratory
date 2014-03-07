import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Declinio;
import br.gov.serpro.catalogo.entity.Fabricante;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.FaseProduto;
import br.gov.serpro.catalogo.entity.Fornecedor;
import br.gov.serpro.catalogo.entity.Licenciamento;
import br.gov.serpro.catalogo.entity.PlataformaTecnologica;
import br.gov.serpro.catalogo.entity.Produto;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.entity.Subcategoria;
import br.gov.serpro.catalogo.entity.Tema;
import br.gov.serpro.catalogo.persistence.AnaliseDAO;
import br.gov.serpro.catalogo.persistence.DeclinioDAO;
import br.gov.serpro.catalogo.persistence.FabricanteDAO;
import br.gov.serpro.catalogo.persistence.FaseProdutoDAO;
import br.gov.serpro.catalogo.persistence.FornecedorDAO;
import br.gov.serpro.catalogo.persistence.LicenciamentoDAO;
import br.gov.serpro.catalogo.persistence.PlataformaTecnologicaDAO;
import br.gov.serpro.catalogo.persistence.ProdutoDAO;
import br.gov.serpro.catalogo.persistence.ProspeccaoDAO;
import br.gov.serpro.catalogo.persistence.SubcategoriaDAO;
import br.gov.serpro.catalogo.persistence.TemaDAO;



@BusinessController
public class CargaInicial {
	
	@Inject
	private AnaliseDAO analiseDAO;
	
	@Inject
	private ProspeccaoDAO prospeccaoDAO;
	
	@Inject
	private ProdutoDAO produtoDAO;
	
	@Inject
	private LicenciamentoDAO licenciamentoDAO;
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	@Inject
	private FornecedorDAO fornecedorDAO;
	
	@Inject
	private PlataformaTecnologicaDAO plataformaTecnologicaDAO;
	
	@Inject
	private FaseProdutoDAO faseProdutoDAO;
	
	@Inject
	private DeclinioDAO declinioDAO;
	
	@Inject
	private TemaDAO temaDAO;
	
	@Inject
	private SubcategoriaDAO subcategoriaDAO;
	
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
		
		for(int i=0; i<3; i++){		
			a = new Analise();		
			a.setArea("CETEC");
			a.setCodigoReferencia("R17100"+i);
			a.setDataRealizacao(new Date());
			a.setDemandante("Fulano "+i);
			a.setObjetivo("Objetivo "+i);
			a.setGestor("Gestor "+i);
			a.setOrigemReferencia("ALM");
			a.setSituacao(Situacao.APROVADO);
			a.setSituacaoJustificativa("blablabalab blabalbal");
			a.setProximaFase(FaseEnum.INTERNALIZACAO);
			a.setProximaFaseArea("CTSDR");			
			a.setProximaFaseGestor("Robson Ximenes");
			a.setProximaFaseGestorEmail("robson.ximenes@serpro.gov.br");
			a.setProximaFaseJustificativa("Robgol vai internalizar essa parada");
			a = analiseDAO.insert(a);	
		}
		
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
		
		Declinio d = new Declinio();
		d.setArea("CTSDR");
		d.setCodigoReferencia("R172");
		d.setOrigemReferencia("ALM");
		d.setObjetivo("Acabar com essa palhaçada.");
		d.setGestor("Robson Ximenes");
		d.setFaseAnterior(p);
		d.setDataRealizacao(new Date());
		d.setSituacao(Situacao.APROVADO);
		d.setNovaAnaliseRiscos(false);
		d.setConclusao("Chega de papaiagada... bola normal.");
		declinioDAO.insert(d);
		
		Licenciamento l = new Licenciamento();
		l.setNome("GPL");
		l.setDescricao("GPL");
		licenciamentoDAO.insert(l);
		
		l = new Licenciamento();
		l.setNome("LGPL");
		l.setDescricao("LGPL");
		licenciamentoDAO.insert(l);
		
		Fabricante fabricante = new Fabricante();
		fabricante.setNome("Mozilla");
		fabricante.setRepresentante("Thiago");
		fabricante.setTelefone("(75)9128-0410");
		fabricante.setEmail("mariano.thiago@gmail.com");
		fabricanteDAO.insert(fabricante);
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Mozilla");
		fornecedor.setRepresentante("Thiago");
		fornecedor.setTelefone("(75)9128-0410");
		fornecedor.setEmail("mariano.thiago@gmail.com");
		fornecedorDAO.insert(fornecedor);
		
		PlataformaTecnologica pl = new PlataformaTecnologica();
		pl.setNome("Plataforma 1");
		pl.setDescricao("Plataforma 1");
		plataformaTecnologicaDAO.insert(pl);
		
		pl = new PlataformaTecnologica();
		pl.setNome("Plataforma 2");
		pl.setDescricao("Plataforma 2");
		plataformaTecnologicaDAO.insert(pl);
		
		pl = new PlataformaTecnologica();
		pl.setNome("Plataforma 3");
		pl.setDescricao("Plataforma 3");
		plataformaTecnologicaDAO.insert(pl);
		
		List<PlataformaTecnologica> listaPlataformas = new ArrayList<PlataformaTecnologica>();
		listaPlataformas = plataformaTecnologicaDAO.findAll();

		Produto produto = new Produto();
		produto.setDescricao("Melhor produto educativo dos ultimos 20 anos.");
		produto.setLicenciamento(l);
		produto.setFabricante(fabricante);
		produto.setFornecedor(null);
		produto.setPlataformasSuportadas(listaPlataformas);
		produto.setNome("Havaianas de Pau");
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
		produto.setFabricante(fabricante);
		produto.setFornecedor(null);
		produto.setNome("Uotsap");
		produto.setLicenciamento(l);
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
			produto.setFabricante(fabricante);
			produto.setFornecedor(null);
			produto.setNome("Produto Fake");
			produto.setLicenciamento(l);
			produto.setLegado(false);
			produto.setVersao("2."+i);
			produto.setRelease("2."+i);
			produtoDAO.insert(produto);			
			
			fp = new FaseProduto();
			fp.setFase(p);
			fp.setProduto(produto);
			faseProdutoDAO.insert(fp);		
		}
		
		Tema tema = new Tema();
		tema.setNome("Temax");
		tema.setDescricao("Temax");
		tema.setTecnologia(null);
		temaDAO.insert(tema);
		
		Subcategoria subcategoria = new Subcategoria();
		subcategoria.setNome("Subcategoriax");
		subcategoria.setDescricao("Subcategoriax");
		subcategoria.setTema(null);
		subcategoriaDAO.insert(subcategoria);
		
	}

	
	
}
