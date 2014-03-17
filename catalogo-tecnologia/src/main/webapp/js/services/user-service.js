'use strict';

/* Services */
var services = angular.module('catalogo.services');

services.factory('UserService', function($http, $q){
	
	var grupos = [];
	var service = {};				

	service.getGrupos = function() {
	    var deferred = $q.defer();
	    	$http({
				url : 'api/grupo',
				method : "GET"
			}).success(function(data) {
				grupos = data;	
				deferred.resolve(grupos);
			}).error(function(data, status) {	
				console.log(data, status);
			});

	    return deferred.promise;
	};
	
	return service;
});