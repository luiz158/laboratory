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
	
	carregarProdutos();

	function carregarProdutos() {

		$http.get('api/fase/produto/ANALISE/'+$scope.variavel).success(function(data) {
			$scope.produtosAnalise = data;
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
							});
						});
					});
				});
			});
		});
	};
});