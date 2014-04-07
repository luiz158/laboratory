'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('ProdutosPesquisados', function ProdutosPesquisados($scope, $routeParams, $http,
		$location, AlertService) {

	$scope.variavel = $routeParams.variavel;
	$scope.produtosAnalise = "";
	$scope.produtosProspeccao = "";
	$scope.produtosInternalizacao = "";
	$scope.produtosSustentacao = "";
	$scope.produtosDeclinio = "";
	$scope.produtosSemFase = "";
	$scope.qtdRegistrosEncontrados = 0;
	
	carregarProdutos();

	function carregarProdutos() {

		$http.get('api/fase/produto/PROSPECCAO/'+$scope.variavel).success(function(data) {
			$scope.produtosProspeccao = data;
			$http.get('api/fase/produto/INTERNALIZACAO/'+$scope.variavel).success(function(data) {
				$scope.produtosInternalizacao = data;
				$http.get('api/fase/produto/SUSTENTACAO/'+$scope.variavel).success(function(data) {
					$scope.produtosSustentacao = data;
					$http.get('api/fase/produto/DECLINIO/'+$scope.variavel).success(function(data) {
						$scope.produtosDeclinio = data;
						$http.get('api/produto/produtoSemFase/'+$scope.variavel).success(function(data) {
							$scope.produtosSemFase = data;
							$scope.qtdRegistrosEncontrados = $scope.produtosProspeccao.length + $scope.produtosInternalizacao.length + 
							$scope.produtosSustentacao.length + $scope.produtosDeclinio.length + $scope.produtosSemFase.length;
						});
					});
				});
			});
		});
	};
});