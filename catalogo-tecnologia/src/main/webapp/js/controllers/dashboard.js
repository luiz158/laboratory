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
	
	
	/* Gráfico das fases*/
	$http.get('api/dashboard/totalfases').success(function(data) {
		$scope.data = [];
		$scope.colors = ['#228B22','#483D8B','#FFD700','#1E90FF', '#DC143C'];
		angular.forEach(data, function(value, key) {
		    $scope.data.push({label: key, value: value});		    
		});
	});
	/* Gráfico das fases*/
	
	
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
	DashboardService.obterDemandas().then(function(data){
		$scope.demandas = data;	
		$scope.paginacaoDemandas.data = data;
	});
	
		
	
	
	
	function getFasesUrl(fluxo){
		var html = "";
		for (var i=0; i<fluxo.length; i++){
			var f = fluxo[i];
			html+= '<a href="#/'+$filter("faseUrl")(f.fase)+'/'+f.id+'">'+$filter("nomeFase")(f.fase)+'</a>';
		}
		return html;
	}
	
	
	function formatarData(date){
		if(date == null) return new Date();
		var from = date.split("-");
		var f = new Date(from[0], from[1]-1, from[2]);
		return f;
	}
	
	var templateMercado = '<img src="images/Rocket.png"> {version} ';
	var templateMercadoDescontinuidade= '<img src="images/Grave.png"> {version} ';	
	var templateSerpro = '<img src="images/serpro.png"> {version} - {fases}';
	$scope.carregarTimeline = function(){		
		$scope.versoes = null;
		$http.get('api/fase/produto/produtoComVersoesEFases/'+$scope.produto.nome).success(function(data) {			
			$scope.versoes = [];
			for (var x = 0; x < data.length; x++) {
				var v = data[x];
				var classe = 'version'+(x+1);
				if(v.data!=null){
					$scope.versoes.push({
	                    'start': formatarData(v.data),
	                    'content': templateMercado.replace("{version}", v.versao),
	                    'className': classe                    	
	                });
				}
				if(v.dataDescontinuidade!=null){
					$scope.versoes.push({
		                'start': formatarData(v.dataDescontinuidade),
		                'content': templateMercadoDescontinuidade.replace("{version}", v.versao),
		                'className': classe                    	
		            });
				}
			
			
				if(v.fases){
					for (var y = 0; y < v.fases.length; y++) {
						var fase = v.fases[y];
						$scope.versoes.push({
		                     'start': formatarData(fase.dataRealizacao),
		                     'end': formatarData(fase.dataFinalizacao),
		                     'content': templateSerpro.replace("{version}", v.versao)
		                     						.replace("{fases}",getFasesUrl([{id: fase.id, fase: fase.fase}]) ),
		                     'className': classe
		                 });
					}					
				}
			}

		});
	};
	

});
