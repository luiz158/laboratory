'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('UserEdit', 
	function Analise($scope, $http, $location, $routeParams, AlertService) {

	var id = $routeParams.id;
	$scope.grupos = [];
	$scope.user = {};
	$scope.user.grupos = [];

	$http.get('api/grupo').success(function(response) {
		$scope.grupos = response;
	});

	$http.get('api/user/' + id).success(function(response) {
		$scope.user = response;
	});

	$scope.toggleSelection = function toggleSelection(grupo) {
		var idx = $scope.isGrupoInGrupos(grupo);
		// is currently selected
		if (idx > -1) {
			$scope.user.grupos.splice(idx, 1);
		}
		// is newly selected
		else {
			$scope.user.grupos.push(grupo);
		}
	};

	$scope.isGrupoInGrupos = function isGrupoInUserGrupos(grupo) {
		var id = grupo.id;
		for ( var i = 0; i < $scope.user.grupos.length; i++) {
			if (id == $scope.user.grupos[i].id) {
				return i;
			}
		}
		return -1;
	};

	$scope.salvar = function() {
		$("[id$='-message']").text("");
		$http({
			url : 'api/user',
			method : "PUT",
			data : $scope.user,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(	function(data) {
			AlertService.addWithTimeout('success', 'Usuário salvo com sucesso');
			$location.path('/user');
		}).error(function(data, status) {
			if (status == 412) {
				$.each(data, function(i, violation) {
					$("#" + violation.property + "-message").text(violation.message);
				});
			}else if (status = 401){
				AlertService.addWithTimeout('warning', data.message);
			}
		});
	};

});