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
	
	$scope.produtos = [
	    {
	    	nome: "Java",
	    	versoes:[
	    	         {id: 1, versao: "1.4", fase: {id:10, fase: 'SUSTENTACAO'}},
	    	         {id: 2, versao: "1.5", fase: {id:10, fase: 'INTERNALIZACAO'}},
	    	         {id: 3, versao: "1.6", fase: {id:10, fase: 'PROSPECCAO'}},
	    	         {id: 4, versao: "1.7"},
	    	         {id: 4, versao: "1.8"}
	    	]
	    },
	    {
	    	nome: "Ubuntu",
	    	versoes:[
	    	         {id: 1, versao: "10.04", fase: {id:10, fase: 'SUSTENTACAO'}},
	    	         {id: 2, versao: "11", fase: {id:10, fase: 'INTERNALIZACAO'}},
	    	         {id: 3, versao: "12.", fase: {id:10, fase: 'PROSPECCAO'}},
	    	         {id: 4, versao: "13"},
	    	         {id: 4, versao: "14"}
	    	]
	    },
	    {
	    	nome: "Mozilla Firefox",
	    	versoes:[
	    	         {id: 1, versao: "109", fase: {id:10, fase: 'SUSTENTACAO'}},
	    	         {id: 2, versao: "12.0.0"},
	    	         {id: 3, versao: "14.0.9", fase: {id:10, fase: 'PROSPECCAO'}},
	    	         {id: 4, versao: "17.0.9"},
	    	         {id: 4, versao: "28"}
	    	]
	    }
	];

});
