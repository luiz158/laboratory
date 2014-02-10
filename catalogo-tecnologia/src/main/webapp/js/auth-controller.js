'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers', []);

controllers.controller('Login',
		function Login($scope, $http, $location) {

			function carregarAnalises() {
				$http.get('api/analise').success(function(data) {
					$scope.analises = data;
				});
			}

			$scope.login = function() {
				$location.path('/analise/edit');
			};

			$scope.editar = function(analise) {
				$location.path('/analise/edit/' + analise.id);
			};

			$scope.excluir = function(id) {
				$http({
					url : 'api/analise/' + id,
					method : "DELETE"

				}).success(function(data) {
					carregarAnalises();

				}).error(function(data, status) {
				});
			};

			carregarAnalises();
		});