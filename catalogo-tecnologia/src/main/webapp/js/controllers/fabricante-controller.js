'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('FabricanteList',
		function Fabricante($scope, $http, $location, AlertService) {
			
			function carregarFabricantes() {
				$http.get('api/fabricante').success(function(data) {
					$scope.fabricantes = data;
				});
			}

			$scope.novoFabricante = function() {
				$location.path('/fabricante/edit');
			};

			$scope.editarFabricante = function(fabricante) {
				$location.path('/fabricante/edit/' + fabricante.id);
			};

			$scope.excluirFabricante = function(id) {
				$http({
					url : 'api/fabricante/' + id,
					method : "DELETE"
				}).success(function(data) {
					carregarFabricantes();
				}).error(function(data, status) {
					if(status == 401){
						AlertService.addWithTimeout('warning',data.message);
						$location.path('/fabricante');
					}else{
						AlertService.addWithTimeout('danger','Não foi possível executar a operação');
					}
				});
			};

			carregarFabricantes();
		});

controllers.controller('FabricanteEdit', function Fabricante($scope, $http,
		$location, $routeParams, $upload, $rootScope, AlertService) {
	
	var id = $routeParams.id;
	
	// Necessário para compartilhar entre os controladores: Anexo, Colaboradores...
	$rootScope.demandaId = id;

	if (id) {
		$http.get('api/fabricante/' + id).success(function(data) {
			$scope.fabricante = data;
		});
	} else {
		$scope.fabricante = {};
	}

	$scope.salvarFabricante = function() {
		
		console.log("FabricanteController " + $scope.fabricante);

		$("[id$='-message']").text("");
		$http({
			url : 'api/fabricante',
			method : $scope.fabricante.id ? "PUT" : "POST",
			data : $scope.fabricante,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success','Fabricante salvo com sucesso');
			$location.path('fabricante');
		}).error(function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warning',data.message);
				$location.path('/fabricante');
			} else if (status == 412){
				$.each(data, function(i, violation) {
					$("#" + violation.property + "-message").text(violation.message);
				});
			}else{
				AlertService.addWithTimeout('danger','Não foi possível executar a operação');
			}
		});
	};
});