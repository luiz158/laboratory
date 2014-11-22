'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('Home', function Home($scope, $http,
		$location, AlertService) {
	
	$scope.variavel = "";
		
	$scope.pesquisarProduto = function(){
		$location.path('/produtosPesquisados/'+$scope.variavel);
	};

});