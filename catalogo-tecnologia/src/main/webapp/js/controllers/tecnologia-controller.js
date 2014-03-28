'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('TecnologiaList',
		function Tecnologia($scope, $http, $location, AlertService) {
			
			function carregarTecnologias() {
				$http.get('api/tecnologia').success(function(data) {
					$scope.tecnologias = data;
				});
			}

			$scope.novaTecnologia = function() {
				$location.path('/tecnologia/edit');
			};

			$scope.editarTecnologia = function(tecnologia) {
				$location.path('/tecnologia/edit/' + tecnologia.id);
			};

			$scope.excluirTecnologia = function(id) {
				$http({
					url : 'api/tecnologia/' + id,
					method : "DELETE"

				}).success(function(data) {
					carregarTecnologias();

				}).error(function(data, status) {
					if(status == 401){
						AlertService.addWithTimeout('warning',data.message);
						$location.path('/tecnologia');
					}else{
						AlertService.addWithTimeout('danger','Não foi possível executar a operação');
					}
				});
			};

			carregarTecnologias();
		});

controllers.controller('TecnologiaEdit', function Tecnologia($scope, $http,
		$location, $routeParams, $upload, $rootScope, AlertService) {
	
	var id = $routeParams.id;
	
	// Necessário para compartilhar entre os controladores: Anexo, Colaboradores...
	$rootScope.demandaId = id;

	if (id) {
		$http.get('api/tecnologia/' + id).success(function(data) {
			$scope.tecnologia = data;
		});
	} else {
		$scope.tecnologia = {};
	}

	$scope.salvarTecnologia = function() {
		
		console.log("TecnologiaController " + $scope.tecnologia);

		$("[id$='-message']").text("");
		$http({
			url : 'api/tecnologia',
			method : $scope.tecnologia.id ? "PUT" : "POST",
			data : $scope.tecnologia,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
	
		}).success(function(data) {
			AlertService.addWithTimeout('success','Tecnologia salva com sucesso');
			$location.path('tecnologia');
		}).error(function(data, status) {
			if(status == 401){
				AlertService.addWithTimeout('danger','Não foi possível executar a operação');
				$location.path('/tecnologia');
			}else if (status = 412) {
				$.each(data, function(i, violation) {
					$("#" + violation.property + "-message").text(violation.message);
				});
			} else {
				AlertService.addWithTimeout('danger','Não foi possível executar a operação');
			}
		});
	};
});