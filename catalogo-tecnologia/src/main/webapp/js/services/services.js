'use strict';

/* Services */
var services = angular.module('catalogo.services', []);

/*
services.factory('AlertService', ['$rootScope', '$timeout', function($rootScope, $timeout) { 
	var alertService;
	$rootScope.alerts = [];
	return alertService = {
		addAlert : function(type, msg, timeout) {
			$rootScope.alerts.push({
				type : type,
				msg : msg,
				close : function() {
					return alertService.closeAlert(this);
				}
			});
			console.log("add"+timeout);
			if (timeout) {
				console.log("timeout"+timeout);
				$timeout(function() {
					alertService.closeAlert(this);
				}, timeout);
			}
		},
		closeAlert : function(alert) {
			return this.closeAlertIdx($rootScope.alerts.indexOf(alert));
		},
		closeAlertIdx : function(index) {
			return $rootScope.alerts.splice(index, 1);
		}

	};
} ]);
*/
services.factory('AuthService', function($http) {

	var logado = false;

	$http({
		url : "api/auth",
		method : "GET"
	}).success(function(response) {
		console.log('sucesso init auth service');
		if (response != null && response != "") {
			console.log(response);
			logado = true;
		}
	}).error(function(response) {
		console.log('erro init auth service');
	});

	console.log('')

	return {
		login : function(credential, callback, errorCallback) {
			console.log('--------CREDENTIAL----------');
			console.log(credential);
			$http({
				url : 'api/auth',
				method : "POST",
				data : credential,
				headers : {
					'Content-Type' : 'application/json;charset=utf8'
				}
			}).success(function(response) {
				logado = true;
				callback(response.data);
			}).error(function(response) {
				logado = false;
				errorCallback(response.data);
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
				logado = false;
				callback(response.data);
			});
		},
		isLoggedIn : function() {
			return logado;
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
