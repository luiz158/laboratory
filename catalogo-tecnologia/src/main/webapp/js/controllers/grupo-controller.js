'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers', []);

controllers.controller('GrupoList',
		
		function Analise($scope, $http, $location) {
	
			function carregarGrupos() {
				$http.get('api/grupo').success(function(data) {
					$scope.grupos = data;
				});
			}
			
			$scope.novo = function() {
				$location.path('/grupo/edit');
			};
			
			$scope.editar = function(analise) {
				$location.path('/grupo/edit/' + analise.id);
			};

			$scope.excluir = function(id) {
				$http({
					url : 'api/grupo/' + id,
					method : "DELETE"

				}).success(function(data) {
					carregarGrupos();

				}).error(function(data, status) {
				});
			};
			
			carregarGrupos();
		});

controllers.controller('GrupoEdit',
		function Analise($scope, $http, $location, $routeParams, AlertService) {
	
			var id = $routeParams.id;
			
			if (id) {
				$http.get('api/grupo/' + id).success(function(data) {
					$scope.grupo = data;
				});
			} else {
				$scope.grupo = {};
			}
	
			$scope.salvar = function() {
				$("[id$='-message']").text("");
				$http({
					url : 'api/grupo',
					method : $scope.grupo.id ? "PUT" : "POST",
					data : $scope.grupo,
					headers : {
						'Content-Type' : 'application/json;charset=utf8'
					}
				}).success(function(data) {
					AlertService.addWithTimeout('success','Grupo salvo com sucesso');
					$location.path('/grupo');
				}).error(
						function(data, status) {
							if (status == 412) {
								$.each(data, function(i, violation) {
									$("#" + violation.property + "-message").text(violation.message);
								});
							}
						});
	
		}
});