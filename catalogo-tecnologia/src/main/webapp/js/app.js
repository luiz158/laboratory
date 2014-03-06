'use strict';

var app = angular.module('catalogo', [
  'ngRoute',
  'catalogo.controllers',
  'catalogo.directives',
  'catalogo.services',
  'catalogo.filters',
  'angularFileUpload',
  'ngAnimate',
  'mgcrea.ngStrap'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {templateUrl: 'partials/login.html', controller: 'Auth'});
  $routeProvider.when('/pesquisa/fases/:fase', {templateUrl: 'partials/pesquisa-fases.html', controller: 'PesquisaFasesCtrl'});
  $routeProvider.when('/pesquisa/fases', {templateUrl: 'partials/pesquisa-fases.html', controller: 'PesquisaFasesCtrl'});
  $routeProvider.when('/analise', {templateUrl: 'partials/analise-listar.html', controller: 'AnaliseList'});
  $routeProvider.when('/analise/edit', {templateUrl: 'partials/analise-edit.html', controller: 'AnaliseEdit'});
  $routeProvider.when('/analise/edit/:id', {templateUrl: 'partials/analise-edit.html', controller: 'AnaliseEdit'});
  $routeProvider.when('/prospeccao/edit/:id', {templateUrl: 'partials/prospeccao-edit.html', controller: 'ProspeccaoCtrl'});

  $routeProvider.when('/licenciamento', {templateUrl: 'partials/licenciamento-listar.html', controller: 'LicenciamentoList'});
  $routeProvider.when('/licenciamento/edit', {templateUrl: 'partials/licenciamento-edit.html', controller: 'LicenciamentoEdit'});
  $routeProvider.when('/licenciamento/edit/:id', {templateUrl: 'partials/licenciamento-edit.html', controller: 'LicenciamentoEdit'});
  
  $routeProvider.when('/fornecedor', {templateUrl: 'partials/fornecedor-listar.html', controller: 'FornecedorList'});
  $routeProvider.when('/fornecedor/edit', {templateUrl: 'partials/fornecedor-edit.html', controller: 'FornecedorEdit'});
  $routeProvider.when('/fornecedor/edit/:id', {templateUrl: 'partials/fornecedor-edit.html', controller: 'FornecedorEdit'});
  
  $routeProvider.when('/fabricante', {templateUrl: 'partials/fabricante-listar.html', controller: 'FabricanteList'});
  $routeProvider.when('/fabricante/edit', {templateUrl: 'partials/fabricante-edit.html', controller: 'FabricanteEdit'});
  $routeProvider.when('/fabricante/edit/:id', {templateUrl: 'partials/fabricante-edit.html', controller: 'FabricanteEdit'});
  
  $routeProvider.when('/plataformaTecnologica', {templateUrl: 'partials/plataforma-tecnologica-listar.html', controller: 'PlataformaTecnologicaList'});
  $routeProvider.when('/plataformaTecnologica/edit', {templateUrl: 'partials/plataforma-tecnologica-edit.html', controller: 'PlataformaTecnologicaEdit'});
  $routeProvider.when('/plataformaTecnologica/edit/:id', {templateUrl: 'partials/plataforma-tecnologica-edit.html', controller: 'PlataformaTecnologicaEdit'});

  $routeProvider.when('/produto', {templateUrl: 'partials/produto-listar.html', controller: 'ProdutoList'});
  $routeProvider.when('/produto/edit', {templateUrl: 'partials/produto-edit.html', controller: 'ProdutoEdit'});
  $routeProvider.when('/produto/edit/:id', {templateUrl: 'partials/produto-edit.html', controller: 'ProdutoEdit'});
  
  $routeProvider.when('/grupo', {templateUrl: 'partials/grupo-listar.html', controller: 'GrupoList'});
  $routeProvider.when('/grupo/edit', {templateUrl: 'partials/grupo-edit.html', controller: 'GrupoEdit'});
  $routeProvider.when('/grupo/edit/:id', {templateUrl: 'partials/grupo-edit.html', controller: 'GrupoEdit'});
  
  $routeProvider.when('/user', {templateUrl: 'partials/user-listar.html', controller: 'UserList'});
  $routeProvider.when('/user/new', {templateUrl: 'partials/user-new.html', controller: 'UserNew'});
  $routeProvider.when('/user/edit/:id', {templateUrl: 'partials/user-edit.html', controller: 'UserEdit'});
  $routeProvider.otherwise({templateUrl: 'partials/dashboard.html'});
}]);

// Fonte: http://stackoverflow.com/questions/15025979/can-i-make-a-function-available-in-every-controller-in-angular
app.run(function($rootScope, AuthService) {
    $rootScope.hasRole = function(roles) {
    	console.log('blá');
		var paramRoles = roles.split(",");
		var userRoles = []; 
		$.each(AuthService.getUsuario().grupos, function (i, grupo){
			$.each(grupo.perfis, function (i, perfil){
				userRoles.push(perfil);
			});
		});
		userRoles = _.uniq(userRoles);
		return _.intersection(userRoles, paramRoles).length; // 0 no javascript é false
    };
});

var controllers = angular.module('catalogo.controllers',[]);
