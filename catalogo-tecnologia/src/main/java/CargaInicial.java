import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Declinio;
import br.gov.serpro.catalogo.entity.Fabricante;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.FaseProduto;
import br.gov.serpro.catalogo.entity.Fornecedor;
import br.gov.serpro.catalogo.entity.Grupo;
import br.gov.serpro.catalogo.entity.Internalizacao;
import br.gov.serpro.catalogo.entity.Licenciamento;
import br.gov.serpro.catalogo.entity.OrigemDemanda;
import br.gov.serpro.catalogo.entity.Perfil;
import br.gov.serpro.catalogo.entity.PlataformaTecnologica;
import br.gov.serpro.catalogo.entity.Produto;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.entity.Subcategoria;
import br.gov.serpro.catalogo.entity.Sustentacao;
import br.gov.serpro.catalogo.entity.Tecnologia;
import br.gov.serpro.catalogo.entity.Tema;
import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.persistence.AnaliseDAO;
import br.gov.serpro.catalogo.persistence.DeclinioDAO;
import br.gov.serpro.catalogo.persistence.FabricanteDAO;
import br.gov.serpro.catalogo.persistence.FaseProdutoDAO;
import br.gov.serpro.catalogo.persistence.FornecedorDAO;
import br.gov.serpro.catalogo.persistence.GrupoDAO;
import br.gov.serpro.catalogo.persistence.InternalizacaoDAO;
import br.gov.serpro.catalogo.persistence.LicenciamentoDAO;
import br.gov.serpro.catalogo.persistence.OrigemDemandaDAO;
import br.gov.serpro.catalogo.persistence.PlataformaTecnologicaDAO;
import br.gov.serpro.catalogo.persistence.ProdutoDAO;
import br.gov.serpro.catalogo.persistence.ProspeccaoDAO;
import br.gov.serpro.catalogo.persistence.SubcategoriaDAO;
import br.gov.serpro.catalogo.persistence.SustentacaoDAO;
import br.gov.serpro.catalogo.persistence.TecnologiaDAO;
import br.gov.serpro.catalogo.persistence.TemaDAO;
import br.gov.serpro.catalogo.persistence.UserDAO;



@BusinessController
public class CargaInicial {
	
	@Inject
	private AnaliseDAO analiseDAO;
	
	@Inject
	private ProspeccaoDAO prospeccaoDAO;
	
	@Inject
	private InternalizacaoDAO internalizacaoDAO;
	
	@Inject
	private SustentacaoDAO sustentacaoDAO;
	
	@Inject
	private DeclinioDAO declinioDAO;
	
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
	private TecnologiaDAO tecnologiaDAO;
	
	@Inject
	private TemaDAO temaDAO;
	
	@Inject
	private SubcategoriaDAO subcategoriaDAO;
	
	@Inject
	private UserDAO usuarioDAO;
	
	
	@Inject
	private GrupoDAO grupoDAO;
	
	
	@Inject 
	private OrigemDemandaDAO origemDemandaDAO;
	

	User usuario1;
	User usuario2;
	
	@Startup @Priority(0)
	@Transactional
	public void criarUsuarios(){
		
		List<Perfil> perfis1 = new ArrayList<Perfil>();
		perfis1.add(Perfil.ANALISE);
		perfis1.add(Perfil.PROSPECCAO);
		Grupo grupo = new Grupo();
		grupo.setNome("Grupo 1");
		grupo.setDescricao("grupo 1");
		grupo.setPerfis(perfis1);
		
		grupoDAO.insert(grupo);
		
		usuario1 = new User();
		usuario1.setCPF("Robson Saraiva Ximenes");
		usuario1.setEmail("robson.ximenes@serpro.gov.br");
		usuario1.setGrupos(new ArrayList<Grupo>());
		usuario1.getGrupos().add(grupo);
		
		usuarioDAO.insert(usuario1);
		
		usuario2 = new User();
		usuario2.setCPF("Thiago de Lima Mariano");
		usuario2.setEmail("thiago.mariano@serpro.gov.br");
		usuario2.setGrupos(new ArrayList<Grupo>());
		usuario2.getGrupos().add(grupo);
		
		usuarioDAO.insert(usuario2);
		
	}
	
	
	
	@Startup @Priority(99)
	@Transactional
	public void criarAnalises(){
		
		OrigemDemanda origem = origemDemandaDAO.findAll().get(0);
		
		Analise a = new Analise();		
		a.setArea("SUPDE");
		a.setCodigoReferencia("R173");
		a.setDataRealizacao(new Date());
		a.setDemandante("Didi Mocó Sonrrisal Colesteró");
		a.setObjetivo("Precise de uma ferramenta que facilite a geração de lero-lero para piadas");
		a.setGestor("Atrogildo Enfezado");
		a.setOrigemReferencia(origem);
		a.setSituacao(Situacao.APROVADO);
		a.setSituacaoJustificativa("Me parece ser bem plausível. Devemos selecionar algumas tecnologias para isso.");
		a.setProximaFase(FaseEnum.PROSPECCAO);
		a.setProximaFaseArea("CETEC");
		a.setProximaFaseGestor("Robgol");
		a.setProximaFaseGestorEmail("robson.ximenes@serpro.gov.br");
		a.setProximaFaseJustificativa("Precisamos que este estudo seja prospectado com no mínimo 3 tecnologias.");
		
		
		a = analiseDAO.insert(a);	
		
		Prospeccao p = new Prospeccao();		
		p.setCodigoReferencia("R174");
		p.setOrigemReferencia(origem);
		p.setObjetivo("Selecionar os tipos de bolas de futsal na copa do mundo.");
		p.setGestor(a.getProximaFaseGestor());
		p.setArea(a.getProximaFaseArea());
		p.setFaseAnterior(a);
		p.setDataRealizacao(new Date());
		p.setSituacao(Situacao.REPROVADO);
		p.setTestes("Os testes realizados foram feitos na regional Salvador em laboratório isolado. Foram averiguados testes de blablablabla. Maiores detalhes sobre os testes realizados podem ser encontrados em Arquivo1.odt.");
		p.setConclusao("A prospecção foi bem sucedida em que, pelos testes realizados, o Ubuntu 12 foi selecionado para ser internalizado.");
		p.setProximaFase(FaseEnum.INTERNALIZACAO);
		p.setProximaFaseArea("SUPST");
		p.setProximaFaseGestor("Wilson Elias");
		
		p = prospeccaoDAO.insert(p);
		
		List<Produto> produtos = produtoDAO.findAll();		
		for (Produto produto : produtos) {
			FaseProduto fp = new FaseProduto();
			fp.setFase(p);
			fp.setProduto(produto);
			faseProdutoDAO.insert(fp);
		}
		
		Internalizacao i = new Internalizacao();
		i.setCodigoReferencia("R175");
		i.setOrigemReferencia(origem);
		i.setObjetivo("Fornecer as bolas que já foram prospectadas com seus devidos condicionamentos");
		i.setGestor(p.getProximaFaseGestor());
		i.setArea(p.getProximaFaseArea());
		i.setFaseAnterior(p);
		i.setDataRealizacao(new Date());
		i.setSituacao(Situacao.APROVADO);		
		i.setRestricao(true);
		i.setRestricaoQuemPode("Apenas robgol pode usar este tipo de bola");
		i.setRestricaoJustificativa("Ele é o único com habilidade suficiente para isso.");		
		i.setCapacitacao(false);		
		i.setAnaliseDeRiscos(false);		
		i.setProximaFase(FaseEnum.SUSTENTACAO);
		i.setProximaFaseArea("SUPDE");
		i.setProximaFaseGestor("Fulano da Silva Sauro");		
		i.setConclusao("Produto internalizado com sucesso. Vamos manter ele na empresa.");		
		internalizacaoDAO.insert(i);
				
		Sustentacao s = new Sustentacao();
		s.setCodigoReferencia("R176");
		s.setOrigemReferencia(origem);
		s.setObjetivo("Manter as novas bolas em pratica");
		s.setGestor(i.getProximaFaseGestor());
		s.setArea(i.getProximaFaseArea());
		s.setFaseAnterior(i);
		s.setDataRealizacao(new Date());
		s.setSituacao(Situacao.REPROVADO);
		s.setProximaFase(FaseEnum.DECLINIO);
		s.setProximaFaseArea("SUPDE");
		s.setProximaFaseGestor("Fulano da Silva Sauro");	
		sustentacaoDAO.insert(s);
		
		
		Declinio d = new Declinio();
		d.setCodigoReferencia("R177");
		d.setOrigemReferencia(origem);
		d.setObjetivo("Acabar com essa palhaçada.");
		d.setGestor(s.getProximaFaseGestor());
		d.setArea(s.getProximaFaseArea());
		d.setFaseAnterior(s);
		d.setDataRealizacao(new Date());
		d.setSituacao(Situacao.REPROVADO);
		d.setNovaAnaliseRiscos(false);
		d.setConclusao("Chega de papaiagada... bola normal.");
		declinioDAO.insert(d);
		
	}
	
	
	@Startup @Priority(1)
	@Transactional
	public void criarOutras(){
				
		Licenciamento l = new Licenciamento();
		l.setNome("GPL");
		l.setDescricao("GPL");
		licenciamentoDAO.insert(l);
		
		l = new Licenciamento();
		l.setNome("LGPL");
		l.setDescricao("LGPL");
		licenciamentoDAO.insert(l);
		
		Fabricante fabricante = new Fabricante();
		fabricante.setNome("Arno");
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
		pl.setNome("Ubuntu 12");
		pl.setDescricao("Plataforma 1");
		plataformaTecnologicaDAO.insert(pl);
		
		pl = new PlataformaTecnologica();
		pl.setNome("Windows 97");
		pl.setDescricao("Plataforma 2");
		plataformaTecnologicaDAO.insert(pl);
		
		pl = new PlataformaTecnologica();
		pl.setNome("MacOSX");
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
		
		
		Tecnologia tecnologia = new Tecnologia();
		tecnologia.setNome("Software");
		tecnologia.setDescricao("tecn de software");
		tecnologiaDAO.insert(tecnologia);
		
		
		Tema tema = new Tema();
		tema.setNome("Engenharia de Software");
		tema.setDescricao("Temax");
		tema.setTecnologia(tecnologia);
		temaDAO.insert(tema);
		
		Subcategoria subcategoria = new Subcategoria();
		subcategoria.setNome("Liguagem de programação");
		subcategoria.setDescricao("Subcategoriax");
		subcategoria.setTema(tema);
		subcategoriaDAO.insert(subcategoria);
		
		subcategoria = new Subcategoria();
		subcategoria.setNome("Banco de dados");
		subcategoria.setDescricao("Banco de dados");
		subcategoria.setTema(tema);
		subcategoriaDAO.insert(subcategoria);
		
		OrigemDemanda origemDemanda = new OrigemDemanda();
		origemDemanda.setNome("SPECX");
		origemDemanda.setDescricao("SPECX...");
		origemDemandaDAO.insert(origemDemanda);
		
		origemDemanda = new OrigemDemanda();
		origemDemanda.setNome("ALM");
		origemDemanda.setDescricao("ALM...");
		origemDemandaDAO.insert(origemDemanda);
		
		origemDemanda = new OrigemDemanda();
		origemDemanda.setNome("Demandas RFB");
		origemDemanda.setDescricao("Demandas RFB...");
		origemDemandaDAO.insert(origemDemanda);
		
		origemDemanda = new OrigemDemanda();
		origemDemanda.setNome("Solicita");
		origemDemanda.setDescricao("Solicita...");
		origemDemandaDAO.insert(origemDemanda);
	}

	
	
}
