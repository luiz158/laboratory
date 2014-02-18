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
	
	$scope.pesquisar = function(){
		$scope.resultado = [];
		for(var i=0; i<5; i++){
			var fase = {};
			fase['id']=i+1;
			fase['fase']=i+1;
			fase['referencia']='SPEKX';
			fase['codigo']='R000'+i;
			fase['gestor']='Robgol Miserê';
			fase['gestorArea']='CETEC/CTSDR';
			fase['dataRegistro']='10/10/2014';
			fase['dataFinalizacao']='10/10/2014';
			fase['objetivo']='nono nonon nonono nononon onononono nononono. nonononon onononono nononono, nononono nonononon onononon noononon onononon. ononono nonon on onon onononono nonon onononon onononon on.';
			
			$scope.resultado.push(fase);
		}
		var length = $scope.resultado.length;
		if(length>0){
			AlertService.addWithTimeout('success','Foram encontrada(s) '+$scope.resultado.length+' fase(s)');
		}else{
			AlertService.addWithTimeout('danger','Não foram encontrados resultados com estes parâmetros de pesquisa.');
		}		
	};
	
	$scope.pesquisar = function() {
		$http({
			url : 'api/fase/pesquisar',
			method : "GET",
			data : $scope.fase,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			$scope.resultado = data;
			AlertService.addWithTimeout('success','Foram encontrada(s) '+$scope.resultado.length+' fase(s)');
		}).error(function(data, status) {
			AlertService.addWithTimeout('danger','Nenhum resultado encontrado.');		
		});

	};
		

});
