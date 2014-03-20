'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('Auth', function Auth($scope, $http, $location, AuthService) {
	$scope.usuario = {};
	$scope.formData = {"username": "", "password": ""};
	
	function sucesso(data){
		$scope.usuario = data;
		location.href="home.html";
	}
	
	function sucessologout(){
		location.href="index.html";
		$scope.usuario = null;
	}
	
	function erro(data, status){
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
	};
	
	$scope.sair = function() {
		AuthService.logout(sucessologout);
	};
	
	$scope.isLoggedIn = function(){
		return AuthService.isLoggedIn();
	};
	
	$scope.getUsuario = function(){
		return AuthService.getUsuario();
	};
});