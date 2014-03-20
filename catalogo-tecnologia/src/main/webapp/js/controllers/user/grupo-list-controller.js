'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('GrupoList', function Analise($scope, $http, $location) {

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
