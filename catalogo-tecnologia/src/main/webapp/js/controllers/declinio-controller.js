'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');


controllers.controller('DeclinioCtrl', function ProspeccaoCtrl($scope, $http,$location, $routeParams, AlertService) {

	$scope.fase = {};
	$scope.fase.id = $routeParams.id;
	$scope.fase.fase = 4;
	
	if ($scope.fase.id) {
		$http.get('api/declinio/' + $scope.fase.id).success(function(data) {
			$scope.fase = data;
			$scope.fase.faseAnterior = {id: data.faseAnterior.id, 
					fase: data.faseAnterior.fase, 
					origemReferencia: data.origemReferencia,
					codigoReferencia: data.codigoReferencia
}			;
		});
	} else {
		AlertService.addWithTimeout('danger','Não foi possível encontrar este ítem.');
		history.back();
	}
		
	$scope.salvar = function() {
		$("[id$='-message']").text("");
		$http({
			url : 'api/declinio',
			method : $scope.fase.id ? "PUT" : "POST",
			data : $scope.fase,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success','Declínio salvo com sucesso');
			$location.path('/pesquisa/fases/5');
		}).error( function(data, status) {
			if (status = 412) {
				$.each(data, function(i, violation) {
					$("#" + violation.property + "-message").text(violation.message);
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
