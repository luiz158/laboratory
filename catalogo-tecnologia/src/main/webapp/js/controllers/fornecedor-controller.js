'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('FornecedorList',
		function Fornecedor($scope, $http, $location, AlertService) {
			
			function carregarFornecedores() {
				$http.get('api/fornecedor').success(function(data) {
					$scope.fornecedores = data;
				});
			}

			$scope.novoFornecedor = function() {
				$location.path('/fornecedor/edit');
			};

			$scope.editarFornecedor = function(fornecedor) {
				$location.path('/fornecedor/edit/' + fornecedor.id);
			};

			$scope.excluirFornecedor = function(id) {
				$http({
					url : 'api/fornecedor/' + id,
					method : "DELETE"

				}).success(function(data) {
					carregarFornecedores();

				}).error(function(data, status) {
					if(status == 401){
						AlertService.addWithTimeout('warnig',data.message);
						$location.path('/fornecedor');
					}else{
						AlertService.addWithTimeout('danger','Não foi possível executar a operação');
					}
				});
			};

			carregarFornecedores();
		});

controllers.controller('FornecedorEdit', function Fornecedor($scope, $http,
		$location, $routeParams, $upload, $rootScope, AlertService) {
	
	var id = $routeParams.id;
	
	// Necessário para compartilhar entre os controladores: Anexo, Colaboradores...
	$rootScope.demandaId = id;

	if (id) {
		$http.get('api/fornecedor/' + id).success(function(data) {
			$scope.fornecedor = data;
		});
	} else {
		$scope.fornecedor = {};
	}

	$scope.salvarFornecedor = function() {
		
		console.log("FornecedorController " + $scope.fornecedor);

		$("[id$='-message']").text("");
		$http({
			url : 'api/fornecedor',
			method : $scope.fornecedor.id ? "PUT" : "POST",
			data : $scope.fornecedor,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
	
		}).success(function(data) {
			AlertService.addWithTimeout('success','Fornecedor salvo com sucesso');
			$location.path('fornecedor');
		}).error(function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warnig',data.message);
				$location.path('/fornecedor');
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