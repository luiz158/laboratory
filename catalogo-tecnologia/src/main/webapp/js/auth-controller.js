'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('Auth', function Auth($scope, $http, $location, AuthService) {

	var logado = "";
	
	function sucesso(data){
		console.log('----------SUCESSO----------')
		console.log(data);
		logado = true;
	}
	
	function erro(data){
		console.log('----------ERRO----------')
		console.log(data);
		logado = false;
	}
	
	$scope.entrar = function() {
		AuthService.login($scope.formData, sucesso, erro);
		console.log(logado);
	}
});

/*
controllers.controller('Logout', function Logout($scope, $http, $location) {

	$scope.sair = function() {

		// console.log('---$scope.formData---');
		// console.log($scope.formData);

		$http({
			method : 'DELETE',
			url : 'api/auth',
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data, status, headers, config) {
			console.log('usuário deslogado');
		}).error(function(data, status, headers, config) {
			console.log('não logado');
		});
	}
});
*/