'use strict';


// Declare app level module which depends on filters, and services
angular.module('catalogo', [
  'ngRoute',
  'catalogo.controllers',
  'catalogo.directives'
]).
config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/analise', {templateUrl: 'partials/analise-listar.html', controller: 'AnaliseList'});
	  $routeProvider.when('/analise/edit', {templateUrl: 'partials/analise-edit.html', controller: 'AnaliseEdit'});
	  $routeProvider.when('/analise/edit/:id', {templateUrl: 'partials/analise-edit.html', controller: 'AnaliseEdit'});
	  //$routeProvider.otherwise({templateUrl: 'partials/analise-listar.html'});
	}]);
