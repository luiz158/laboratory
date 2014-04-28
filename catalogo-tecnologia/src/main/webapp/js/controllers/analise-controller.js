'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');


controllers.controller('AnaliseEdit', function Analise($scope, $http, $filter,
		$location, $routeParams, $upload, $rootScope, AlertService, OrigemDemandaService, ValidationService, FaseService) {

	
	$scope.fase = {};
	$scope.fase.id = $routeParams.id;
	$scope.fase.fase = 'ANALISE';
	$scope.origemDemanda = [];
	
	OrigemDemandaService.getItens().then(function(data) {
		$scope.origemDemanda = data;
	});	
	
	function carregar(){
		$http.get('api/analise/' + $scope.fase.id).success(function(data) {
			$scope.analise = data;
			$scope.fase = data;
		});	
		
		$http.get('api/fase/proximafase/' + $scope.fase.id).success(function(data) {
			$scope.proximafase = data;
		});	
		
	}
	
	if ($scope.fase.id) {
		carregar();	
	} else {
		$scope.fase  = {};
		$scope.fase.situacao = 'Rascunho';
		$scope.fase.fase = 'ANALISE';
		$scope.fase.executarProximaFase = 0;
	}
		

	$scope.salvar = function() {
		ValidationService.clear();
		
		$http({
			url : 'api/analise',
			method : $scope.fase .id ? "PUT" : "POST",
			data : $scope.fase ,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}

		}).success(function(data) {
			$scope.fase.id = data;
			carregar();
			AlertService.addWithTimeout('success','Análise salva com sucesso');
			$location.path('/analise/edit/'+data);
		}).error(function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warning',data.message);
				//$location.path('/analise');
			} else if(status == 412){
				ValidationService.registrarViolacoes(data);
			}else{
				AlertService.addWithTimeout('danger','Não foi possível executar a operação');
			}
		});
	};

	$scope.aprovar = function(aprovado) {
		ValidationService.remove('situacao');
		$scope.fase .situacao = aprovado ? 'Aprovado' : 'Reprovado';
	};
	
	$scope.finalizar = function() {
		ValidationService.clear();
		$http({
			url : 'api/analise/finalizar',
			method : "PUT",
			data : $scope.fase ,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success','Análise finalizada com sucesso');
			$location.path('analise');
		}).error(function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warning',data.message);
				//$location.path('/analise');
			} else if(status == 412){
				ValidationService.registrarViolacoes(data);
			}else{
				AlertService.addWithTimeout('danger','Não foi possível executar a operação');
			}
		});
	};
	
	$scope.criarProximaFase = function() {
		console.log($scope.fase);
		ValidationService.clear();
		$http({
			url : 'api/fase/proximafase/analise',
			method : "POST",
			data : $scope.fase ,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success','Próxima fase criada com sucesso');
			var url = $filter("faseUrl")(data.fase);
			$location.path(url+'/'+data.id);
		}).error(function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warning',data.message);
			} else if(status == 412){
				ValidationService.registrarViolacoes(data);
			}else{
				AlertService.addWithTimeout('danger','Não foi possível executar a operação');
			}
		});
	};
	
	$scope.excluir = function(id) {
		FaseService.excluir(id).then(
			function(data) {
				AlertService.addWithTimeout('success','Análise excluída com sucesso');
				$location.path('/pesquisa/fases');
			},function(data) {
				ValidationService.registrarViolacoes(data);				
			}
		);
	};
	
});








controllers.controller('AnaliseList',
		function Analise($scope, $http, $location) {
	
	
			$scope.analises = [];
			function carregarAnalises() {
				$http.get('api/analise').success(function(data) {
					$scope.analises = data;
				});
			}

			$scope.novo = function() {
				$location.path('/analise/edit');
			};

			$scope.editar = function(analise) {
				$location.path('/analise/edit/' + analise.id);
			};

			$scope.excluir = function(id) {
				$http({
					url : 'api/analise/' + id,
					method : "DELETE"

				}).success(function(data) {
					carregarAnalises();

				}).error(function(data, status) {
					if(status == 401){
						AlertService.addWithTimeout('warning',data.message);
						$location.path('/analise');
					}else{
						AlertService.addWithTimeout('danger','Não foi possível executar a operação');
					}
				});
			};

			carregarAnalises();
		});

