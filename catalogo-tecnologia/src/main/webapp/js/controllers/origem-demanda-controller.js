'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('OrigemDemandaList', function OrigemDemanda($scope,
		$http, $location, AlertService) {

	function carregarOrigensDemandas() {
		$http.get('api/origemDemanda').success(function(data) {
			$scope.origensDemandas = data;
		});
	}

	$scope.novoOrigemDemanda = function() {
		$location.path('/origemDemanda/edit');
	};

	$scope.editarOrigemDemanda = function(origemDemanda) {
		$location.path('/origemDemanda/edit/' + origemDemanda.id);
	};

	$scope.excluirOrigemDemanda = function(id) {
		$http({
			url : 'api/origemDemanda/' + id,
			method : "DELETE"
		}).success(function(data) {
			carregarOrigensDemandas();
		}).error(function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warning', data.message);
				$location.path('origemDemanda');
			} else {
				AlertService.addWithTimeout('danger', 'Não foi possível executar a operação');
			}
		});
	};

	carregarOrigensDemandas();
});

controllers.controller('OrigemDemandaEdit', function OrigemDemanda($scope,
		$http, $location, $routeParams, $upload, $rootScope, AlertService) {

	var id = $routeParams.id;

	// Necessário para compartilhar entre os controladores: Anexo,
	// Colaboradores...
	$rootScope.demandaId = id;

	if (id) {
		$http.get('api/origemDemanda/' + id).success(function(data) {
			$scope.origemDemanda = data;
		});
	} else {
		$scope.origemDemanda = {};
	}

	$scope.salvarOrigemDemanda = function() {

		console.log("OrigemDemandaController " + $scope.origemDemanda);

		$("[id$='-message']").text("");
		$http({
			url : 'api/origemDemanda',
			method : $scope.origemDemanda.id ? "PUT" : "POST",
			data : $scope.origemDemanda,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success', 'Origem da Demanda salva com sucesso');
			$location.path('origemDemanda');
		}).error(function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warning', data.message);
				$location.path('origemDemanda');
			}else if(status == 412){
				$.each(data, function(i, violation) {
					$("#" + violation.property + "-message").text(violation.message);
				});
			}else {
				AlertService.addWithTimeout('danger', 'Não foi possível executar a operação');
			};
		});
	};
});