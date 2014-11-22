'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('ProdutosPesquisados', function ProdutosPesquisados($scope, $routeParams, $http,
		$location, AlertService) {

	$scope.variavel = $routeParams.variavel;
	$scope.produtosFase = "";
	$scope.produtosSemFase = "";
	$scope.qtdRegistrosEncontrados = 0;
	
	carregarProdutos();

	function carregarProdutos() {
		$http.get('api/fase/produto/listarProdutoFasePorNomeProduto/'+$scope.variavel).success(function(data) {
			$scope.produtosFase = data;
			$http.get('api/produto/listarProdutosSemFase/'+$scope.variavel).success(function(data) {
				$scope.produtosSemFase = data;
				$scope.qtdRegistrosEncontrados = $scope.produtosFase.length + $scope.produtosSemFase.length;
			});
		});
	};
});