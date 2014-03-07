'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');


controllers.controller('ProspeccaoCtrl', function ProspeccaoCtrl($scope, $rootScope, $http,$location, $routeParams, AlertService, OrigemDemandaService) {

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
		});
	} else {
		AlertService.addWithTimeout('danger','Não foi possível encontrar a prospecção');
		history.back();
	}
		
	$scope.salvar = function() {
		$("[id$='-message']").text("");
		console.log($scope.fase);
		$http({
			url : 'api/prospeccao',
			method : $scope.fase.id ? "PUT" : "POST",
			data : $scope.fase,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success','Prospecção salva com sucesso');
			$location.path('/pesquisa/fases/2');
		}).error( function(data, status) {
			console.log(data);
			if (status = 412) {
				$.each(data, function(i, violation) {
					$("#" + violation.property + "-message").text(
							violation.message);
				});
			}
		});

	};

	$scope.aprovar = function(aprovado) {
		$scope.fase.situacao = aprovado ? 'Aprovado' : 'Reprovado';
	};
	
	$scope.finalizar = function() {
		$scope.fase.dataFinalizacao = new Date();
		$scope.salvar();
	};
	

});
