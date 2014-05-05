'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('DashboardCtrl', function DashboardCtrl($scope, DashboardService, $filter, $http) {
	
	$scope.tecnologia = "";
	$scope.categoria = "";
	$scope.resultadoProdutos = [];
	$scope.tecnologias = [];
	
	$http.get('api/tecnologia').success(function(data) {
		$scope.tecnologias = data;
	});
	
	$scope.carregarCategorias = function() {
		$scope.resultadoProdutos = [];
		$http.get('api/categoria/listarCategoriaPorTecnologia/'+$scope.tecnologia.id).success(function(data) {
			$scope.categorias = data;
		});
	};
	
	$scope.carregarProdutos = function() {
		$http.get('api/produto/listarProdutosUnicosPorCategoria/'+$scope.categoria.id).success(function(data) {
			$scope.resultadoProdutos = data;
		});
	};
	
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
	
	
	
	var templateMercado = '<img src="images/Rocket.png"> {version} ';
	var templateSerpro = '<img src="images/serpro.png"> {version} - {fases}';
	
	function getFasesUrl(fluxo){

		var html = "";
		for (var i=0; i<fluxo.length; i++){
			var f = fluxo[i];
			var percentual = 80 / fluxo.length;
			html+= '<a href="#/'+$filter("faseUrl")(f.fase)+'/'+f.id+'">'+$filter("nomeFase")(f.fase)+'</a>';
			//html+= '<div class="timelinefase" style="width: '+percentual+'%; "> <a href="#/'+$filter("faseUrl")(f.fase)+'/'+f.id+'">'+$filter("nomeFase")(f.fase)+'</a> </div>';
		}
		return html;
	}
	
	$scope.dados  = [
        {
            'start': new Date(2010,2,23),
            'content': templateMercado.replace("{version}", "1.4"),
            'className': 'version1'
            	
        },
        {
            'start': new Date(2011,2,23),
            'content': templateMercado.replace("{version}", "1.5"),
            'className': 'version2'
        },
        {
            'start': new Date(2012,2,23),
            'content': templateMercado.replace("{version}", "1.6"),
            'className': 'version3'
        },
        {
            'start': new Date(2013,2,23),
            'content': templateMercado.replace("{version}", "1.7"),
            'className': 'version4'
        },
        {
            'start': new Date(2014,2,23),
            'content': templateMercado.replace("{version}", "1.8"),
            'className': 'version5'
        },
        {
            'start': new Date(2010,7,23),
            'end': new Date(2010,12,23),
            'content': templateSerpro.replace("{version}", "1.4")
            						.replace("{fases}",getFasesUrl([{id: 31, fase: 'PROSPECCAO'}]) ),
            'className': 'version1'
        },
        {
            'start': new Date(2010,12,23),
            'end': new Date(2011,6,23),
            'content': templateSerpro.replace("{version}", "1.4")
            						.replace("{fases}",getFasesUrl([{id: 31, fase: 'INTERNALIZACAO'}]) ),
            'className': 'version1'
        },
        {
            'start': new Date(2011,6,23),
            'end': new Date(2014,12,23),
            'content': templateSerpro.replace("{version}", "1.4")
            						.replace("{fases}",getFasesUrl([{id: 31, fase: 'SUSTENTACAO'}]) ),
            'className': 'version1'
        },
        {
            'start': new Date(2012,7,23),
            'end': new Date(2013,12,23),
            'content': templateSerpro.replace("{version}", "1.5")
            						.replace("{fases}",getFasesUrl([{id: 31, fase: 'PROSPECCAO'}]) ),
            'className': 'version2'
        },
        {
            'start': new Date(2013,12,23),
            'end': new Date(2014,12,23),
            'content': templateSerpro.replace("{version}", "1.5")
            						.replace("{fases}",getFasesUrl([{id: 31, fase: 'INTERNALIZACAO'}]) ),
            'className': 'version2'
        },
        {
            'start': new Date(2014,1,1),
            'end': new Date(2014,4,23),
            'content': templateSerpro.replace("{version}", "1.6")
							.replace("{fases}",getFasesUrl([{id: 31, fase: 'PROSPECCAO'}]) ),
			'className': 'version3'
        },
    ];
	
	

});
