'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('Auth', function Auth($scope,$routeParams, $http, $location, AuthService) {
	$scope.usuario = {};
	
	$scope.formData = {"username": "", "password": ""};	
	
	var url = location.href;
	if(url.indexOf("#")>0){ // Guarda a rota existente.
		$scope.rotaOriginal = url.substring(url.indexOf("#"),url.length);
	}
	
	function sucesso(data){
		$scope.usuario = data;
		if($scope.rotaOriginal){
			location.href="home.html"+$scope.rotaOriginal;
		}else{
			location.href="home.html";
		}	
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
		$scope.$broadcast("autofill:update");
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