'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('PesquisaFasesCtrl', function PesquisaFasesCtrl($scope, $rootScope, $routeParams, $http, AlertService) {
	/* Inicializando as variáveis */
	$scope.fase = {};
	$scope.fase.fase = $routeParams.fase;
	
	if($rootScope.pesquisaForm){
		$scope.fase = $rootScope.pesquisaForm;
		$scope.resultado = $rootScope.pesquisaResultado;
	}

	/* Reinicia o objeto fase, caso a fase venha da url.*/
	$scope.limpar = function(){
		$rootScope.pesquisaForm = null;
		$rootScope.pesquisaResultado = [];
		$scope.fase = {};
		$scope.fase.fase = $routeParams.fase;
		$scope.resultado = [];
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
			console.log(data);
			$scope.resultado = data;
			$rootScope.pesquisaResultado = data;
			AlertService.addWithTimeout('success','Foram encontrada(s) '+$scope.resultado.length+' fase(s)');
		}).error(function(data, status) {
			AlertService.addWithTimeout('danger','Nenhum resultado encontrado.');	
			console.log(data, status);
		});

	};
		

});
