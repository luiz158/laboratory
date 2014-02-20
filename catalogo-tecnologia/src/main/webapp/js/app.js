'use strict';

angular.module('catalogo', [
  'ngRoute',
  'catalogo.controllers',
  'catalogo.directives',
  'catalogo.services',
  'catalogo.filters',
  'angularFileUpload',
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

  $routeProvider.when('/produto', {templateUrl: 'partials/produto-listar.html', controller: 'ProdutoList'});
  $routeProvider.when('/produto/edit', {templateUrl: 'partials/produto-edit.html', controller: 'ProdutoEdit'});
  $routeProvider.when('/produto/edit/:id', {templateUrl: 'partials/produto-edit.html', controller: 'ProdutoEdit'});
  
  $routeProvider.when('/grupo', {templateUrl: 'partials/grupo-listar.html', controller: 'GrupoList'});
  $routeProvider.when('/grupo/edit', {templateUrl: 'partials/grupo-edit.html', controller: 'GrupoEdit'});
  $routeProvider.when('/grupo/edit/:id', {templateUrl: 'partials/grupo-edit.html', controller: 'GrupoEdit'});
  $routeProvider.otherwise({templateUrl: 'partials/dashboard.html'});
}]);

var controllers = angular.module('catalogo.controllers',[]);
