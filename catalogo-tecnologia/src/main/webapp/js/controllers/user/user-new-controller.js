'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('UserNew', function UserNew($scope, $http, $location, AlertService, UserService) {

	$scope.users = [];
	$scope.grupos = [];
	$scope.name = {};

	UserService.getGrupos().then(function(data) {
		$scope.grupos = data;
	});

	$scope.pesquisar = function(cpf, nome) {
		$scope.users = [];
		$scope.user = {};
		if (cpf != "" && cpf != null) {
			UserService.searchByCPF(cpf).then(function(data) {
				$scope.user = data;
				$scope.user.grupos = [];
				$scope.users.push($scope.user);
			});
		} else if (nome != "" && nome != null) {
			UserService.searchByName(nome).then(function(data) {
				$scope.users = data;
				$.each($scope.users, function(i, user) {
					user.grupos = [];
				});
			});
		} else {
			AlertService.addWithTimeout('warning', 'Preencha um dos campos antes executar a pesquisa!');
		}
	};

	$scope.editar = function(user) {
		$scope.user = user;
	};

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
			method : "POST",
			data : $scope.user,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success', 'Usuário salvo com sucesso');
			$location.path('/user');
		}).error(function(data, status) {
			if (status == 412) {
				AlertService.addWithTimeout('danger', data[0].message);
			}else if(status == 401){
				AlertService.addWithTimeout('warning', data.message);
			}
		});
	};
});