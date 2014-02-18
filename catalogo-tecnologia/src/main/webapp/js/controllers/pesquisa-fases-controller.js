'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('PesquisaFasesCtrl', function PesquisaFasesCtrl($scope, $routeParams, $http, AlertService) {
	/* Inicializando as variáveis */
	$scope.fase = {};
	$scope.fase.fase = $routeParams.fase;

	/* Reinicia o objeto fase, caso a fase venha da url.*/
	$scope.limpar = function(){
		$scope.fase = {};
		$scope.fase.fase = $routeParams.fase;
		$scope.resultado = [];
	};
		
	$scope.pesquisar = function() {
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
			AlertService.addWithTimeout('success','Foram encontrada(s) '+$scope.resultado.length+' fase(s)');
		}).error(function(data, status) {
			AlertService.addWithTimeout('danger','Nenhum resultado encontrado.');	
			console.log(data, status);
		});

	};
		

});
