'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('UserList',

function Analise($scope, $http, $location) {

	function carregarUser() {
		$http.get('api/user').success(function(data) {
			$scope.users = data;
			console.log($scope.users);
		});
	}

	$scope.editar = function(analise) {
		$location.path('/user/edit/' + analise.id);
	};

	carregarUser();
});

controllers.controller('UserEdit', function Analise($scope, $http, $location,
		$routeParams, AlertService) {

	var id = $routeParams.id;
	
	$http.get('api/user/' + id).success(function(data) {
		$scope.user = data;
		console.log("user:");
		console.log($scope.user);
	});

	$http({
		url : 'api/grupo',
		method : "GET"
	}).success(function(data) {
		$scope.grupos = data;
	}).error(function(data, status) {
	});

	// toggle selection for a given fruit by name
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
	
	$scope.isGrupoInGrupos = function isGrupoInGrupos(grupo){
		var id = grupo.id;
		
		for(var i=0; i<$scope.user.grupos.length; i++){
			if(id == $scope.user.grupos[i].id){
				return 0;
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
		}).success(
				function(data) {
					AlertService.addWithTimeout('success',
							'Usuário salvo com sucesso');
					$location.path('/user');
				}).error(
				function(data, status) {
					if (status == 412) {
						$.each(data, function(i, violation) {
							$("#" + violation.property + "-message").text(
									violation.message);
						});
					}
				});

	}
});