'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('Auth', function Auth($scope, $http, $location, AuthService) {

	console.log('AUTH CONTROLLER');
	$scope.usuario = {};
	$scope.formData = {"username": "", "password": ""};
	
	function sucesso(data){
		console.log('FUNÇÃO CALLBACK SUCESSO LOGIN')
		$scope.usuario = data;
		$location.path("/analise");
	}
	
	function sucessologout(){
		console.log('FUNÇÃO CALLBACK SUCESSO LOGOUT')
		$location.path("/login");
		$scope.usuario = null;
	}
	
	function erro(data, status){
		console.log('FUNÇÃO CALLBACK ERRO LOGIN');
		$("[id$='-message']").text("");
		switch (status) {
		case 412: 
			$.each(data, function(i, violation) {
				$("#" + violation.property + "-message").text(violation.message);
			});
			break;
		case 401: 
			$("#message").html("Usuário ou senha inválidos.");
			break;
		}
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
	
	$scope.getUsuario = function(){
		return AuthService.getUsuario();
	}
});