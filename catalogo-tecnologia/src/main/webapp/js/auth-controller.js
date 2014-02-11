'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('Auth', function Auth($scope, $http, $location, AuthService) {

	console.log('Instanciando Auth Controller');
	
	function sucesso(data){
		console.log('----------SUCESSO----------')
	}
	
	function sucessologout(){
		console.log('----------SAIR----------')
	}
	
	function erro(data){
		console.log('----------ERRO----------')
	}
	
	$scope.entrar = function() {
		AuthService.login($scope.formData, sucesso, erro);
	}
	
	$scope.sair = function() {
		AuthService.logout(sucessologout);
	}
	
	$scope.isLoggedIn = function(){
		return AuthService.isLoggedIn();
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