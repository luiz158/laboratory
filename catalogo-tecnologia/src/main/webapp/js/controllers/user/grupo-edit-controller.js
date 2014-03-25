'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('GrupoEdit', function Analise($scope, $http, $location,
		$routeParams, AlertService) {

	var id = $routeParams.id;

	$http({
		url : 'api/perfis',
		method : "GET"
	}).success(function(data) {
		$scope.perfis = data;
	}).error(function(data, status) {
	});

	if (id) {
		$http.get('api/grupo/' + id).success(function(data) {
			$scope.grupo = data;
		});
	} else {
		$scope.grupo = {};
		$scope.grupo.perfis = [];
	}

	// toggle selection for a given fruit by name
	$scope.toggleSelection = function toggleSelection(perfil) {
		var idx = $scope.grupo.perfis.indexOf(perfil);

		// is currently selected
		if (idx > -1) {
			$scope.grupo.perfis.splice(idx, 1);
		}
		// is newly selected
		else {
			$scope.grupo.perfis.push(perfil);
		}
	};

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
			AlertService.addWithTimeout('success', 'Grupo salvo com sucesso');
			$location.path('/grupo');
		}).error(function(data, status) {
			if (status == 412) {
				$.each(data, function(i, violation) {
					$("#" + violation.property + "-message").text(violation.message);
				});
			}else if(status == 401){
				AlertService.addWithTimeout('warning', data.message);
				$location.path('/grupo');
			};
		});
	};
});
