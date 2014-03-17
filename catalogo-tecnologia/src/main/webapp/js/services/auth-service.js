'use strict';

/* Services */
var services = angular.module('catalogo.services');

services.factory('AuthService', function($http, $rootScope) {

	var logado = false;
	$rootScope.usuario = {};
	
	function getLoggedUser(){
		$http({
			url : "api/auth/user",
			method : "GET"
		}).success(function(response) {
			$rootScope.usuario = response;
		}).error(function(response) {
			$rootScope.usuario = {};
		});
	}
	
	// Construtor
	$http({
		url : "api/auth",
		method : "GET"
	}).success(function(response) {
		if (response == 'false'){
			logado = false;
		} else {
			logado = true;
			getLoggedUser();
		}
	}).error(function(response) {
		console.log('erro init auth service');
	});

	return {
		login : function(credential, callback, errorCallback) {
			console.log(credential);
			$http({
				url : 'api/auth',
				method : "POST",
				data : credential,
				headers : {
					'Content-Type' : 'application/json;charset=utf8'
				}
			}).success(function(response) {
				$rootScope.usuario = response;
				logado = true;
				callback(response);
				console.log($rootScope.usuario);
			}).error(function(response, status) {
				$rootScope.usuario = {};
				logado = false;
				errorCallback(response, status);
			});
		},
		logout : function(callback) {
			console.log('--------LOGOUT----------');
			$http({
				url : 'api/auth',
				method : "DELETE",
				headers : {
					'Content-Type' : 'application/json;charset=utf8'
				}
			}).success(function(response) {
				console.log('AuthService Logout Success');
				logado = false;
				callback(response.data);
			});
		},
		isLoggedIn : function() {
			return logado;
		},
		getUsuario : function(){
			return $rootScope.usuario;
		}
	};
});