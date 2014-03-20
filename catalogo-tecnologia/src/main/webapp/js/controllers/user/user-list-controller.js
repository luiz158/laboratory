'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('UserList',

	function UserList($scope, $http, $location) {

	function carregarUser() {
		$http.get('api/user').success(function(data) {
			$scope.users = data;
		});
	}

	$scope.editar = function(user) {
		$location.path('/user/edit/' + user.id);
	};

	$scope.novo = function() {
		$location.path('/user/new');
	};

	carregarUser();
});