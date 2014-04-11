'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');


controllers.controller('ProspeccaoCtrl', function ProspeccaoCtrl($scope, $rootScope, $http,$location, $routeParams, AlertService, OrigemDemandaService, ValidationService, FaseService) {

	$(window).scrollTop(0);
	
	$scope.fase = {};
	$scope.fase.id = $routeParams.id;
	$scope.fase.fase = 2;
	$scope.origemDemanda = [];
		
	OrigemDemandaService.getItens().then(function(data) {
		$scope.origemDemanda = data;
	});

	if ($scope.fase.id) {
		$http.get('api/prospeccao/' + $scope.fase.id).success(function(data) {
			$scope.fase = data;
			$scope.fase.faseAnterior = {
					id: 				data.faseAnterior.id, 
					fase: 				data.faseAnterior.fase, 
					origemReferencia: 	data.faseAnterior.origemReferencia,
					codigoReferencia: 	data.faseAnterior.codigoReferencia
			};
			$scope.fase.faseInicial = {
					id: 				data.faseInicial.id, 
					fase: 				data.faseInicial.fase
			};
		}).error( function(data, status) {
			AlertService.addWithTimeout('danger','Não foi possível encontrar a prospecção');
			history.back();
		});
	} else {
		AlertService.addWithTimeout('danger','Não foi possível encontrar a prospecção');
		history.back();
	}
		
	$scope.salvar = function(finalizar) {
		var url = 'api/prospeccao';
		if(finalizar) url = url+"/finalizar";
		ValidationService.clear();
		$http({
			url : url,
			method : $scope.fase.id ? "PUT" : "POST",
			data : $scope.fase,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			if(finalizar){
				AlertService.addWithTimeout('success','Prospecção finalizada com sucesso');
			}else{
				AlertService.addWithTimeout('success','Prospecção salva com sucesso');
			}
			//$location.path('/pesquisa/fases/2');
		}).error( function(data, status) {
			if (status = 412) {
				ValidationService.registrarViolacoes(data);
			}
		});

	};

	$scope.aprovar = function(aprovado) {
		$scope.fase.situacao = aprovado ? 'Aprovado' : 'Reprovado';
	};
	
	$scope.finalizar = function() {
		$scope.salvar(true);
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
