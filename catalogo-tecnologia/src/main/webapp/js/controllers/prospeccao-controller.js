'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');


controllers.controller('ProspeccaoCtrl', function ProspeccaoCtrl($scope, $rootScope, $http,$location, $routeParams, AlertService) {

	$scope.fase = {};
	$scope.fase.id = $routeParams.id;
	$scope.fase.fase = 2;
	
	$scope.produtos = [];
	
	carregarProdutos();

	if ($scope.fase.id) {
		$http.get('api/prospeccao/' + $scope.fase.id).success(function(data) {
			$scope.fase = data;
		});
	} else {
		AlertService.addWithTimeout('danger','Não foi possível encontrar a prospecção');
		history.back();
	}
		
	$scope.salvar = function() {
		$("[id$='-message']").text("");
		$http({
			url : 'api/prospeccao',
			method : $scope.fase.id ? "PUT" : "POST",
			data : $scope.fase,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success','Prospecção salva com sucesso');
			$location.path('/pesquisa/fases/2');
		}).error( function(data, status) {
			if (status = 412) {
				$.each(data, function(i, violation) {
					$("#" + violation.property + "-message").text(
							violation.message);
				});
			}
		});

	};

	$scope.aprovar = function(aprovado) {
		$scope.fase.situacao = aprovado ? 'Aprovado' : 'Reprovado';
	};
	
	$scope.finalizar = function() {
		$scope.fase.dataFinalizacao = new Date();
		$scope.salvar();
	};
	
	$http.get('api/produto').success(function(data) {
		$scope.resultadoProdutos = data;
	});
	
	$scope.adicionarProduto = function(p){		
		$http({
			url : 'api/fase/produto',
			method : "POST",
			data : {fase: {id:$scope.fase.id}, produto: {id: p.id}},
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success','Produto relacionado ');
			carregarProdutos();
		}).error( function(data, status) {
			console.log(data);
			AlertService.addWithTimeout('danger','Não foi possível relacionar o produto');
		});
		
		
	};
	
	
	function carregarProdutos(){
		$http.get('api/fase/produto/' + $scope.fase.id).success(function(data) {
			$scope.produtos = data;
		});
	}
	
	$scope.removerProduto = function(id) {
		$http({
			url : 'api/fase/produto/' + id,
			method : "DELETE"
		}).success(function(data) {
			carregarProdutos();
		}).error(function(data, status) {
			AlertService.addWithTimeout('danger','Não foi possível remover o produto.');
		});
	};

});
