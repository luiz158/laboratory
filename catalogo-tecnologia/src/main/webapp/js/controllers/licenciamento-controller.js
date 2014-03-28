'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('LicenciamentoList',
		function Licenciamento($scope, $http, $location, AlertService) {
			
			function carregarLicenciamentos() {
				$http.get('api/licenciamento').success(function(data) {
					$scope.licenciamentos = data;
				});
			}

			$scope.novoLicenciamento = function() {
				$location.path('/licenciamento/edit');
			};

			$scope.editarLicenciamento = function(licenciamento) {
				$location.path('/licenciamento/edit/' + licenciamento.id);
			};

			$scope.excluirLicenciamento = function(id) {
				$http({
					url : 'api/licenciamento/' + id,
					method : "DELETE"
				}).success(function(data) {
					carregarLicenciamentos();
				}).error(function(data, status) {
					if (status == 401) {
						AlertService.addWithTimeout('warning', data.message);
						$location.path('/licenciamento');
					} else {
						AlertService.addWithTimeout('danger','Não foi possível executar a operação');
					}
				});
			};

			carregarLicenciamentos();
		});

controllers.controller('LicenciamentoEdit', function Licenciamento($scope, $http,
		$location, $routeParams, $upload, $rootScope, AlertService) {
	
	var id = $routeParams.id;
	
	// Necessário para compartilhar entre os controladores: Anexo, Colaboradores...
	$rootScope.demandaId = id;

	if (id) {
		$http.get('api/licenciamento/' + id).success(function(data) {
			$scope.licenciamento = data;
		});
	} else {
		$scope.licenciamento = {};
	}

	$scope.salvarLicenciamento = function() {
		
		console.log("LicenciamentoController " + $scope.licenciamento);

		$("[id$='-message']").text("");
		$http({
			url : 'api/licenciamento',
			method : $scope.licenciamento.id ? "PUT" : "POST",
			data : $scope.licenciamento,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success','Licenciamento salvo com sucesso');
			$location.path('licenciamento');
		}).error(function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warning', data.message);
				$location.path('/licenciamento');
			} else if (status == 412){
				$.each(data, function(i, violation) {
					$("#" + violation.property + "-message").text(violation.message);
				});
			} else {
				AlertService.addWithTimeout('danger','Não foi possível executar a operação');
			}
		});
	};
});