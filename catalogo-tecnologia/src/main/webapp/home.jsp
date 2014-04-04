<!DOCTYPE html>
<html ng-app="catalogo" lang="pt-br">

<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/angular-strap.styles.min.css" rel="stylesheet">
<link href="css/sb-admin.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<link href="css/plugins/timeline/timeline.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.css" rel="stylesheet">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cat�logo de Tecnologia</title>

</head>

<!--body logged-in-->
<body>

	esse � o JSP

	<div id="wrapper">

		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Catálogo de Tecnologia</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				
				<li class="dropdown" ng-controller="Auth">
					<a ng-if="isLoggedIn()" class="dropdown-toggle" data-toggle="dropdown" > 
							<span><i class="fa fa-user fa-fw"></i> {{getUsuario().name}} <i
								class="fa fa-caret-down"></i></span>
					</a>
						<ul class="dropdown-menu dropdown-user">
							<li><a href="#"><i class="fa fa-user fa-fw"></i> Perfil
									do Usu�rio</a></li>
							<li><a href="#"><i class="fa fa-gear fa-fw"></i>
									Configura��es</a></li>
							<li class="divider"></li>
							<li><a style="cursor: pointer" ng-click="sair()"><i
									class="fa fa-sign-out fa-fw"></i> Logout</a></li>
						</ul>
					<!-- /.dropdown-user -->
				</li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

		</nav>
		<!-- /.navbar-static-top -->

		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="sidebar-search">
						<div class="input-group custom-search-form" ng-controller="Home">
							<input type="text" class="form-control" placeholder="Procurar..."
								ng-model="variavel"> <span class="input-group-btn">
								<button class="btn btn-default" type="button" ng-click="pesquisarProduto()">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div> <!-- /input-group -->
					</li>
					<li><a href="index.html"><i class="fa fa-dashboard fa-fw"></i>
							Painel</a></li>
					<li><a href="#"><i class="fa fa-sitemap fa-fw"></i> Fases<span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level active">
							<li><a href="#pesquisa/fases"><i
									class="glyphicon glyphicon-list"></i> Pesquisa</a></li>
							<li if-has-role='ADMINISTRADOR,ANALISE'>
								<a href="#analise/edit"><i class="glyphicon glyphicon-plus-sign"></i> Nova Analise</a></li>

						</ul> <!-- /.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-wrench fa-fw"></i>
							Cadastros<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="#origemDemanda">Origem da demanda</a></li>
							<li><a href="#licenciamento">Licenciamento</a></li>
							<li><a href="#fabricante">Fabricante</a></li>
							<li><a href="#fornecedor">Fornecedor</a></li>
							<li><a href="#plataformaTecnologica">Plataforma
									Tecnol�gica</a></li>
							<li><a href="#tecnologia">Tecnologia</a></li>
							<li><a href="#categoria">Categoria</a></li>
							<li><a href="#produto">Produto</a></li>
							<li><a href="#grupo">Grupo</a></li>
							<li><a href="#user">Usu�rio</a></li>
							<li><a href="typography.html">...</a></li>
						</ul> <!-- /.nav-second-level --></li>
				</ul>
				<!-- /#side-menu -->
			</div>
			<!-- /.sidebar-collapse -->
		</nav>
		<!-- /.navbar-static-side -->

		<div id="page-wrapper">
			<br />
			<div ng-view autoscroll></div>
		</div>

		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<script src="js/lib/plugins/angular-file-upload/angular-file-upload-shim.min.js"></script>
	<script src="js/lib/angular.js"></script>
	<script src="js/lib/angular-locale_pt-br.js"></script>
	<script src="js/lib/angular-animate.js"></script>
	<script src="js/lib/angular-route.min.js"></script>
	<script src="js/lib/plugins/angular-file-upload/angular-file-upload.min.js"></script>
	<script src="js/lib/plugins/angular-strap/angular-strap.js"></script>
	<script src="js/lib/plugins/angular-strap/angular-strap.tpl.js"></script>

	<script src="js/app.js"></script>	
	<script src="js/directives.js"></script>
	<script src="js/filters.js"></script>
	<script src="js/services/auth-service.js"></script>
	<script src="js/services/services.js"></script>
	<script src="js/services/user-service.js"></script>	
	<script src="js/controllers/analise-controller.js"></script>
	<script src="js/controllers/prospeccao-controller.js"></script>
	<script src="js/controllers/internalizacao-controller.js"></script>
	<script src="js/controllers/sustentacao-controller.js"></script>
	<script src="js/controllers/declinio-controller.js"></script>
	<script src="js/controllers/produto-controller.js"></script>
	<script src="js/controllers/licenciamento-controller.js"></script>
	<script src="js/controllers/fornecedor-controller.js"></script>
	<script src="js/controllers/fabricante-controller.js"></script>
	<script src="js/controllers/plataforma-tecnologica-controller.js"></script>
	<script src="js/controllers/tecnologia-controller.js"></script>
	<script src="js/controllers/categoria-controller.js"></script>
	<script src="js/controllers/origem-demanda-controller.js"></script>
	<script src="js/controllers/auth-controller.js"></script>
	<script src="js/controllers/anexo-controller.js"></script>
	<script src="js/controllers/observacao-controller.js"></script>
	<script src="js/controllers/fluxo-controller.js"></script>
	<script src="js/controllers/membros-controller.js"></script>
	<script src="js/controllers/interessados-controller.js"></script>
	<script src="js/controllers/produtos-da-fase-controller.js"></script>
	<script src="js/controllers/root-controller.js"></script>
	<script src="js/controllers/dashboard.js"></script>
	<script src="js/controllers/pesquisa-fases-controller.js"></script>
	<script src="js/controllers/pesquisa-produtos-controller.js"></script>
	<script src="js/controllers/user/grupo-list-controller.js"></script>
	<script src="js/controllers/user/grupo-edit-controller.js"></script>
	<script src="js/controllers/user/user-edit-controller.js"></script>
	<script src="js/controllers/user/user-new-controller.js"></script>
	<script src="js/controllers/user/user-list-controller.js"></script>
    <script src="js/controllers/home-controller.js"></script>
    <script src="js/controllers/produtos-pesquisados-controller.js"></script>
    
	<script src="js/lib/jquery-1.10.2.js"></script>
	<script src="js/lib/bootstrap.min.js"></script>
	<script src="js/lib/bootbox.min.js"></script>
	<script src="js/lib/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="js/lib/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="js/lib/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="js/lib/plugins/morris/raphael-2.1.0.min.js"></script>
	<script src="js/lib/plugins/morris/morris.js"></script>
	<script src="js/lib/sb-admin.js"></script>
	<script src="js/lib/underscore.js"></script>
</body>

</html>
