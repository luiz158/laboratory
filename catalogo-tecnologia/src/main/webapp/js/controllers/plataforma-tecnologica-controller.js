'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('PlataformaTecnologicaList',
		function PlataformaTecnologica($scope, $http, $location, AlertService) {
			
			function carregarPlataformasTecnologicas() {
				$http.get('api/plataformaTecnologica').success(function(data) {
					$scope.plataformasTecnologicas = data;
				});
			}

			$scope.novoPlataformaTecnologica = function() {
				$location.path('/plataformaTecnologica/edit');
			};

			$scope.editarPlataformaTecnologica = function(plataformaTecnologica) {
				$location.path('/plataformaTecnologica/edit/' + plataformaTecnologica.id);
			};

			$scope.excluirPlataformaTecnologica = function(id) {
				$http({
					url : 'api/plataformaTecnologica/' + id,
					method : "DELETE"

				}).success(function(data) {
					carregarPlataformasTecnologicas();

				}).error(function(data, status) {
					if(status == 401){
						AlertService.addWithTimeout('warning',data.message);
					}else{
						AlertService.addWithTimeout('danger','Não foi possível executar a operação');
					}
				});
			};

			carregarPlataformasTecnologicas();
		});

controllers.controller('PlataformaTecnologicaEdit', function PlataformaTecnologica($scope, $http,
		$location, $routeParams, $upload, $rootScope, AlertService) {
	
	var id = $routeParams.id;
	
	// Necessário para compartilhar entre os controladores: Anexo, Colaboradores...
	$rootScope.demandaId = id;

	if (id) {
		$http.get('api/plataformaTecnologica/' + id).success(function(data) {
			$scope.plataformaTecnologica = data;
		});
	} else {
		$scope.plataformaTecnologica = {};
	}

	$scope.salvarPlataformaTecnologica = function() {
		
		console.log("PlataformaTecnologicaController " + $scope.plataformaTecnologica);

		$("[id$='-message']").text("");
		$http({
			url : 'api/plataformaTecnologica',
			method : $scope.plataformaTecnologica.id ? "PUT" : "POST",
			data : $scope.plataformaTecnologica,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
	
		}).success(function(data) {
			AlertService.addWithTimeout('success','PlataformaTecnologica salva com sucesso');
			$location.path('plataformaTecnologica');
		}).error(function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warning',data.message);
				$location.path('/plataformaTecnologica');
			} else	if (status == 412) {
				$.each(data, function(i, violation) {
					$("#" + violation.property + "-message").text(violation.message);
				});
			} else {
				AlertService.addWithTimeout('danger','Não foi possível executar a operação');
			}
		});
	};
});