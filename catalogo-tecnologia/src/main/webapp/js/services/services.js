'use strict';

/* Services */
var services = angular.module('catalogo.services', []);

services.factory('AlertService', function($rootScope, $timeout) {
	var alertService = {};

	// create an array of alerts available globally
	$rootScope.alerts = [];

	alertService.addWithTimeout = function(type, msg, timeout) {
  	    var alert = alertService.add(type, msg);
		$timeout(function() {
			alertService.closeAlert(alert);
		}, timeout ? timeout: 3000);
	};
	
	alertService.add = function(type, msg, timeout) {
		$rootScope.alerts.push({
			'type' : type,
			'msg' : msg
		});
	};


	alertService.closeAlert = function(alert) {
		return this.closeAlertIdx($rootScope.alerts.indexOf(alert));
	};
	
	alertService.closeAlertIdx = function(index) {
		return $rootScope.alerts.splice(index, 1);
	};
	
	return alertService;
});
/*
 * services.factory('AlertService', ['$rootScope', '$timeout',
 * function($rootScope, $timeout) { var alertService; $rootScope.alerts = [];
 * return alertService = { addAlert : function(type, msg, timeout) {
 * $rootScope.alerts.push({ type : type, msg : msg, close : function() { return
 * alertService.closeAlert(this); } }); console.log("add"+timeout); if (timeout) {
 * console.log("timeout"+timeout); $timeout(function() {
 * alertService.closeAlert(this); }, timeout); } }, closeAlert : function(alert) {
 * return this.closeAlertIdx($rootScope.alerts.indexOf(alert)); }, closeAlertIdx :
 * function(index) { return $rootScope.alerts.splice(index, 1); }
 *  }; } ]);
 */
services.factory('AuthService', function($http, $rootScope) {

	var logado = false;
	$rootScope.usuario = {};
	
	// Construtor
	$http({
		url : "api/auth",
		method : "GET"
	}).success(function(response) {
		if (response == 'false'){
			logado = false;
		} else {
			logado = true;
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
	}
});
/*
 * logout : function(callback, errorCallback) { $http( { url : getBaseUrl() +
 * '/api/auth', method : "DELETE", headers : { 'Content-Type' :
 * 'application/json;charset=utf8' } }).success(function(response) {
 * console.log(response); callback(response); currentUser = null;
 * }).error(function(response) { console.log(response); }); },
 * 
 * 
 * isLoggedIn : function(callback, errorCallback) { },
 * 
 * 
 * currentUser : function(callback, errorCallback) { $http( { url : getBaseUrl() +
 * '/api/auth', method : "GET", headers : { 'Content-Type' :
 * 'application/json;charset=utf8' } }).success(function(response) {
 * console.log(response); callback(response); }).error(function(response) {
 * console.log(response); }); } };
 */
