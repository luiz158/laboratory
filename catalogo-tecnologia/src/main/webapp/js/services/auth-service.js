'use strict';

/* Services */
var services = angular.module('catalogo.services');

services.factory('AuthService', function($http, $q, $rootScope) {

	var logado = false;
	$rootScope.usuario = {};
	
	var service = {};
	
	service.getLoggedUserService = function(){
		var deferred = $q.defer();
		$http({
			url : "api/auth/user",
			method : "GET"
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			console.log("Erro!");
		});
		
		return deferred.promise;
	};
	
	
	function getLoggedUser(){
		$http({
			url : "api/auth/user",
			method : "GET"
		}).success(
			function(response) {
				$rootScope.usuario = response;
			}
		).error(function(response) {
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

	
	service.login = function(credential, callback, errorCallback) {
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
		}).error(function(response, status) {
			$rootScope.usuario = {};
			logado = false;
			errorCallback(response, status);
		});
	};
	
	service.logout = function(callback) {
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
	};
	
	service.isLoggedIn = function(){
		return logado;
	};
	
	service.getUsuario = function(){
		return $rootScope.usuario;
	};
	
	return service;
});