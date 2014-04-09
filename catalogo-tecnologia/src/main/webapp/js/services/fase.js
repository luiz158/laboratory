'use strict';

/* Services */
var services = angular.module('catalogo.services');

services.factory('FaseService', function($http, $q) {
	var service = {};				

	service.excluir = function(id) {
	   var deferred = $q.defer();
	   $http({
			url : 'api/fase/excluir/'+id,
			method : "DELETE"
		}).success(function(data) {
			deferred.resolve(data);
		}).error(function(data, status) {
			if (status == 401 || status == 412 ) {
				deferred.reject(data);
			}else{
				deferred.reject({message: 'Não foi possível executar a operação'});
			}
		});		   
	   return deferred.promise;
	};
	
	
	  
	return service;
});
