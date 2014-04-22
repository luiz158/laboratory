'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('DashboardCtrl', function DashboardCtrl($scope, DashboardService) {
	
	/* Paginação */
	$scope.paginacaoDemandas = {
			paginaAtual :0, 
			registrosPorPagina: 5, 
			data: [], 
			paginas: function(){
		        return Math.ceil($scope.paginacaoDemandas.data.length/$scope.paginacaoDemandas.registrosPorPagina);                
		    },
		    anterior: function(){
		    	if($scope.paginacaoDemandas.paginaAtual>0)
		    		$scope.paginacaoDemandas.paginaAtual = $scope.paginacaoDemandas.paginaAtual-1;                
		    },
		    proxima: function(){
		    	if($scope.paginacaoDemandas.paginaAtual<$scope.paginacaoDemandas.paginas()-1)
		    		$scope.paginacaoDemandas.paginaAtual = $scope.paginacaoDemandas.paginaAtual+1;                
		    }
	};	
	
	$scope.demandas = [];
	$scope.data = [
	               {label: "Análise", value: 12},
	               {label: "Prospecção", value: 18},
	               {label: "Internalização", value: 15},
	               {label: "Sustentação", value: 50},
	               {label: "Declínio", value: 6}
	             ];
	
	$scope.colors = ['#228B22','#483D8B','#FFD700','#1E90FF', '#DC143C'];
	
	DashboardService.obterDemandas().then(function(data){
		$scope.demandas = data;	
		$scope.paginacaoDemandas.data = data;
	});
	
	
});
