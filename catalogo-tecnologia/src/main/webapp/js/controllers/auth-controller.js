'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('Auth', function Auth($scope, $http, $location, AuthService) {

	console.log('AUTH CONTROLLER');
	
	$scope.usuario = {};
	
	function sucesso(data){
		console.log('FUNÇÃO CALLBACK SUCESSO LOGIN')
		$scope.usuario = data;
		console.log($scope.usuario);
		$location.path("/analise");
	}
	
	function sucessologout(){
		console.log('FUNÇÃO CALLBACK SUCESSO LOGOUT')
		$location.path("/login");
		$scope.usuario = null;
	}
	
	function erro(data){
		console.log('FUNÇÃO CALLBACK ERRO LOGIN')
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