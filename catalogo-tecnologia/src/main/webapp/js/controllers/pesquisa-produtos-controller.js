'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('DemoCtrl', function($scope, $http, $rootScope) {
	//$scope.modal = ({title: 'Title', content: 'Hello Modal<br />This is a multiline message!'});

	$scope.selected = "";

	var servico = "";  

    if($scope.produtoParaPesquisa != ""){
    	servico = 'api/produto/listarProdutosPorNome/'+$scope.produtoParaPesquisa;
    }else{
    	servico = 'api/produto';
    }

    $http.get(servico).success(function(data) {
		$scope.produtosPesquisados = data;
	});	
	
	$scope.seleciona = function(produto) {
		$scope.selected = produto;
		$rootScope.produto.produtoAnterior = $scope.selected.nome + " - " + $scope.selected.versao;
	};
});