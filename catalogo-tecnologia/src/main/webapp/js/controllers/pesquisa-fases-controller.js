'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('PesquisaFasesCtrl', function PesquisaFasesCtrl($scope, $rootScope, $routeParams, $http, AlertService, OrigemDemandaService) {
	
	$(window).scrollTop(0);
	
	$scope.origemDemanda = [];		
	OrigemDemandaService.getItens().then(function(data) {
		$scope.origemDemanda = data;
	});
	
	/* Paginação */
	$scope.paginacao = {
			paginaAtual :0, 
			registrosPorPagina: 5, 
			data: [], 
			paginas: function(){
		        return Math.ceil($scope.paginacao.data.length/$scope.paginacao.registrosPorPagina);                
		    },
		    anterior: function(){
		    	if($scope.paginacao.paginaAtual>0)
		    		$scope.paginacao.paginaAtual = $scope.paginacao.paginaAtual-1;                
		    },
		    proxima: function(){
		    	if($scope.paginacao.paginaAtual<$scope.paginacao.paginas()-1)
		    		$scope.paginacao.paginaAtual = $scope.paginacao.paginaAtual+1;                
		    }
	};	
	
	
	
	/* Inicializando as variáveis */
	$scope.fase = {};
	$scope.fase.fase = $routeParams.fase;
	
	
	/* Reinicia o objeto fase, caso a fase venha da url.*/
	$scope.limpar = function(){
		$rootScope.pesquisaForm = null;
		$rootScope.pesquisaResultado = [];
		$scope.fase = {};
		//$scope.fase.fase = $routeParams.fase;
		$scope.paginacao.data = [];
	};
		
	$scope.pesquisar = function() {
		$rootScope.pesquisaForm = $scope.fase;
		$http({
			url : 'api/fase',
			method : "POST",
			data : $scope.fase,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			$scope.paginacao.data = data;
			$rootScope.pesquisaResultado = data;
			if($scope.paginacao.data.length<1){
				AlertService.addWithTimeout('danger','Nenhum resultado encontrado.');
			}
		}).error(function(data, status) {
			AlertService.addWithTimeout('danger','Nenhum resultado encontrado.');	
			console.log(data, status);
		});

	};
	
	
	/*
	 * Recupera os dados da pesquisa ou faz uma nova. 
	 * 
	 * */
	if($rootScope.pesquisaForm && $rootScope.pesquisaForm.fase == $scope.fase.fase){
		$scope.fase = $rootScope.pesquisaForm;
		//$scope.paginacao.data = $rootScope.pesquisaResultado;
	}else{
		//$scope.pesquisar();
	}
	$scope.pesquisar();
		

});
