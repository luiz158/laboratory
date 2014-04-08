package br.gov.serpro.catalogo.carga;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.bussiness.FaseBC;
import br.gov.serpro.catalogo.bussiness.UsuarioBC;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Categoria;
import br.gov.serpro.catalogo.entity.Declinio;
import br.gov.serpro.catalogo.entity.Fabricante;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.FaseProduto;
import br.gov.serpro.catalogo.entity.Fornecedor;
import br.gov.serpro.catalogo.entity.Grupo;
import br.gov.serpro.catalogo.entity.Internalizacao;
import br.gov.serpro.catalogo.entity.Licenciamento;
import br.gov.serpro.catalogo.entity.Observacao;
import br.gov.serpro.catalogo.entity.OrigemDemanda;
import br.gov.serpro.catalogo.entity.Perfil;
import br.gov.serpro.catalogo.entity.PlataformaTecnologica;
import br.gov.serpro.catalogo.entity.Produto;
import br.gov.serpro.catalogo.entity.Prospeccao;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.entity.Sustentacao;
import br.gov.serpro.catalogo.entity.Tecnologia;
import br.gov.serpro.catalogo.entity.User;
import br.gov.serpro.catalogo.persistence.CategoriaDAO;
import br.gov.serpro.catalogo.persistence.FabricanteDAO;
import br.gov.serpro.catalogo.persistence.FaseProdutoDAO;
import br.gov.serpro.catalogo.persistence.FornecedorDAO;
import br.gov.serpro.catalogo.persistence.GrupoDAO;
import br.gov.serpro.catalogo.persistence.LicenciamentoDAO;
import br.gov.serpro.catalogo.persistence.ObservacaoDAO;
import br.gov.serpro.catalogo.persistence.OrigemDemandaDAO;
import br.gov.serpro.catalogo.persistence.PlataformaTecnologicaDAO;
import br.gov.serpro.catalogo.persistence.ProdutoDAO;
import br.gov.serpro.catalogo.persistence.TecnologiaDAO;

@Path("carga/xyz48")
@Produces(APPLICATION_JSON)
public class CargaService {

	@Inject
	private FaseBC faseBC;

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
	private CategoriaDAO categoriaDAO;

	@Inject
	private UsuarioBC usuarioBC;

	@Inject
	private GrupoDAO grupoDAO;

	@Inject
	private OrigemDemandaDAO origemDemandaDAO;

	@Inject
	private ObservacaoDAO obsDAO;

	static User usuario1;


	@GET
	@Path("usuarios")
	@Transactional
	@LoggedIn
	public void usuarios() {

		List<Perfil> perfis1 = new ArrayList<Perfil>();
		perfis1.add(Perfil.ANALISE);
		perfis1.add(Perfil.PROSPECCAO);
		perfis1.add(Perfil.ADMINISTRADOR);
		Grupo grupo = new Grupo();
		grupo.setNome("Grupo 1");
		grupo.setDescricao("grupo 1");
		grupo.setPerfis(perfis1);

		grupoDAO.insert(grupo);

		usuario1 = new User();
		usuario1.setCPF("82546010549");
		usuario1.setName("Robson Saraiva Ximenes");
		usuario1.setEmail("robson.ximenes@serpro.gov.br");
		usuario1.setSetor("CETEC/CTSDR");
		usuario1.setGrupos(new ArrayList<Grupo>());
		usuario1.getGrupos().add(grupo);

		usuario1 = usuarioBC.carregarOuInserir(usuario1);

	}

	@GET
	@Path("analises")
	@Transactional
	@LoggedIn
	public void analises() {

		OrigemDemanda origem = origemDemandaDAO.findAll().get(0);

		Analise a = new Analise();
		a.setCodigoReferencia("R173");
		a.setDataRealizacao(new Date());
		a.setDemandanteUnidade("CETEC/CTSDR");
		a.setDemandanteRepresentante(usuario1);
		a.setObjetivo("Precise de uma ferramenta que facilite a geração de lero-lero para piadas");
		a.setUnidadeGestora("CTSDR");
		a.setOrigemReferencia(origem);
		a.setSituacao(Situacao.APROVADO);
		a.setSituacaoJustificativa("Me parece ser bem plausível. Devemos selecionar algumas tecnologias para isso.");
		a.setProximaFase(FaseEnum.PROSPECCAO);
		a.setProximaFaseLider(usuario1);
		a.setProximaFaseUnidadeGestora(usuario1.getSetor());
		a.setProximaFaseJustificativa("Precisamos que este estudo seja prospectado com no mínimo 3 tecnologias.");

		a = (Analise) faseBC.salvar(a);

		Observacao obs = new Observacao();
		obs.setData(new Date());
		obs.setUsuario(usuario1);
		obs.setFase(a);
		obs.setObservacao("Apenas observando como ficou legal.");

		obsDAO.insert(obs);

		Prospeccao p = (Prospeccao) faseBC.finalizarFase(a);
		p.setCodigoReferencia("R174");
		p.setOrigemReferencia(origem);
		p.setObjetivo("Selecionar os tipos de bolas de futsal na copa do mundo.");
		p.setDataRealizacao(new Date());
		p.setSituacao(Situacao.APROVADO);
		p.setSituacaoJustificativa("Aprovei pois a prospeccao foi bem sucedida.");
		p.setTestes("Os testes realizados foram feitos na regional Salvador em laboratório isolado. Foram averiguados testes de blablablabla. Maiores detalhes sobre os testes realizados podem ser encontrados em Arquivo1.odt.");
		p.setConclusao("A prospecção foi bem sucedida em que, pelos testes realizados, o Ubuntu 12 foi selecionado para ser internalizado.");
		p.setProximaFase(FaseEnum.INTERNALIZACAO);
		p.setProximaFaseLider(usuario1);
		p.setProximaFaseUnidadeGestora(usuario1.getSetor());
		p.setProximaFaseJustificativa("Precisamos que este estudo seja prospectado com no mínimo 3 tecnologias.");

		p = (Prospeccao) faseBC.salvar(p);

		List<Produto> produtos = produtoDAO.findAll();
		for (Produto produto : produtos) {
			FaseProduto fp = new FaseProduto();
			fp.setFase(p);
			fp.setProduto(produto);
			faseProdutoDAO.insert(fp);
		}

		Internalizacao i = (Internalizacao) faseBC.finalizarFase(p);
		i.setCodigoReferencia("R175");
		i.setOrigemReferencia(origem);
		i.setObjetivo("Fornecer as bolas que já foram prospectadas com seus devidos condicionamentos");
		i.setDataRealizacao(new Date());
		i.setSituacao(Situacao.APROVADO);
		i.setSituacaoJustificativa("asahkajhakj");
		i.setRestricao(1);
		i.setRestricaoQuemPode("Apenas robgol pode usar este tipo de bola");
		i.setRestricaoJustificativa("Ele é o único com habilidade suficiente para isso.");
		i.setAquisicaoNecessaria(0);
		i.setCapacitacao(0);
		i.setAnaliseDeRiscos(0);
		i.setAnaliseDeRiscosJustificativa("nada");
		i.setProximaFase(FaseEnum.SUSTENTACAO);
		i.setProximaFaseLider(usuario1);
		i.setProximaFaseUnidadeGestora(usuario1.getSetor());
		i.setProximaFaseJustificativa("Precisamos que este estudo seja prospectado com no mínimo 3 tecnologias.");

		i = (Internalizacao) faseBC.salvar(i);
		
		Sustentacao s = (Sustentacao) faseBC.finalizarFase(i);
		s.setCodigoReferencia("R175");
		s.setOrigemReferencia(origem);
		s.setObjetivo("Fornecer as bolas que já foram prospectadas com seus devidos condicionamentos");
		s.setDataRealizacao(new Date());
		s.setSituacao(Situacao.APROVADO);
		s.setSituacaoJustificativa("asahkajhakj");
		s.setRestricao(1);
		s.setRestricaoQuemPode("Apenas robgol pode usar este tipo de bola");
		s.setRestricaoJustificativa("Ele é o único com habilidade suficiente para isso.");
		s.setAquisicaoNecessaria(0);
		s.setCapacitacao(0);
		s.setAnaliseDeRiscos(0);
		s.setAnaliseDeRiscosJustificativa("nada");
		s.setProximaFase(FaseEnum.DECLINIO);
		s.setProximaFaseLider(usuario1);
		s.setProximaFaseUnidadeGestora(usuario1.getSetor());
		s.setProximaFaseJustificativa("Precisamos que este estudo seja prospectado com no mínimo 3 tecnologias.");
		s = (Sustentacao) faseBC.salvar(s);
		
		Declinio d = (Declinio) faseBC.finalizarFase(s);
		

		a = new Analise();
		a.setCodigoReferencia("????????");
		a.setDataRealizacao(new Date());
		a.setDemandanteUnidade("CETEC/CTSDR");
		a.setDemandanteRepresentante(usuario1);
		a.setObjetivo("Nonono nononon ononononon ononononon onononono non");
		a.setUnidadeGestora("CTSDR");
		a.setOrigemReferencia(origem);
		a.setSituacao(Situacao.APROVADO);
		a.setSituacaoJustificativa("nonononono nonononono nononononon onononon on");
		a = (Analise) faseBC.salvar(a);

		a = new Analise();
		a.setCodigoReferencia("????????");
		a.setDataRealizacao(new Date());
		a.setDemandanteUnidade("CETEC/CTSDR");
		a.setDemandanteRepresentante(usuario1);
		a.setObjetivo("Nonono nononon ononononon ononononon onononono non");
		a.setUnidadeGestora("CTSDR");
		a.setOrigemReferencia(origem);
		a.setSituacao(Situacao.REPROVADO);
		a.setSituacaoJustificativa("nonononono nonononono nononononon onononon on");
		a = (Analise) faseBC.salvar(a);

		a = new Analise();
		a.setCodigoReferencia("Completo");
		a.setDataRealizacao(new Date());
		a.setDemandanteUnidade("CETEC/Completo");
		a.setDemandanteRepresentante(usuario1);
		a.setObjetivo("Completo");
		a.setUnidadeGestora("CTSDR");
		a.setOrigemReferencia(origem);
		a.setSituacao(Situacao.RASCUNHO);
		a = (Analise) faseBC.salvar(a);
		
		

		
	}

	@GET
	@Path("cadastro")
	@Transactional
	@LoggedIn
	public void criarOutras() {

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

		Tecnologia tecnologia = new Tecnologia();
		tecnologia.setNome("Software");
		tecnologia.setDescricao("tecn de software");
		tecnologiaDAO.insert(tecnologia);

		Categoria categoria1 = new Categoria();
		categoria1.setNome("Liguagem de programação");
		categoria1.setDescricao("Categoriax");
		categoria1.setTecnologia(tecnologia);
		categoriaDAO.insert(categoria1);

		Categoria categoria2 = new Categoria();
		categoria2.setNome("Banco de dados");
		categoria2.setDescricao("Banco de dados");
		categoria2.setTecnologia(tecnologia);
		categoriaDAO.insert(categoria2);

		Produto produto = new Produto();
		produto.setDescricao("Melhor produto educativo dos ultimos 20 anos.");
		produto.setLicenciamento(l);
		produto.setFabricante(fabricante);
		produto.setFornecedor(null);
		produto.setPlataformasSuportadas(listaPlataformas);
		produto.setNome("Havaianas de Pau");
		produto.setLegado(false);
		produto.setVersao("2.0");
		produtoDAO.insert(produto);

		produto.setCategorias(new ArrayList<Categoria>());
		produto.getCategorias().add(categoria1);
		produtoDAO.update(produto);

		produto = new Produto();
		produto.setDescricao("Maior trocador de porcaria via telefones modernos que tem internê");
		produto.setFabricante(fabricante);
		produto.setFornecedor(null);
		produto.setNome("Uotsap");
		produto.setLicenciamento(l);
		produto.setLegado(false);
		produto.setVersao("2.0");
		produtoDAO.insert(produto);

		produto.setCategorias(new ArrayList<Categoria>());
		produto.getCategorias().add(categoria1);
		produto.getCategorias().add(categoria2);
		produtoDAO.update(produto);

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
