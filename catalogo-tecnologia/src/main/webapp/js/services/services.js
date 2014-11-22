'use strict';

/* Services */
var services = angular.module('catalogo.services');

services.factory('DocumentoService', function($http, $q) {
	var service = {};				

	service.inserir = function(doc) {
	   var deferred = $q.defer();
	   $http({
			url : 'api/documento/fase/'+doc.fase.id+'/add',
			method : "POST",
			data : doc,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			deferred.resolve(data);
		}).error(function(data, status) {	
			deferred.reject(data);
		});		   
	   return deferred.promise;
	};
	
	service.excluir = function(doc) {
		   var deferred = $q.defer();
		   $http({
				url : 'api/documento/'+doc.id,
				method : "DELETE",
				data : doc,
				headers : {
					'Content-Type' : 'application/json;charset=utf8'
				}
			}).success(function(data) {
				deferred.resolve(data);
			}).error(function(data, status) {	
				deferred.reject(data);
			});		   
		   return deferred.promise;
		};
	
	service.getDocumentos = function(id) {
		var deferred = $q.defer();
    	$http({
			url : 'api/documento/fase/'+id,
			method : "GET"
		}).success(function(data) {
			deferred.resolve(data);
		}).error(function(data, status) {	
			deferred.reject(data);
		}); 
	   return deferred.promise;
	};
	  
	return service;
});

services.factory('OrigemDemandaService', function($http, $q) {
	var itens = [];
	var service = {};				

	service.getItens = function() {
	    var deferred = $q.defer();
	    if(itens.length<=0){
	    	$http({
				url : 'api/origemDemanda',
				method : "GET"
			}).success(function(data) {
				itens = data;	
				deferred.resolve(itens);
			}).error(function(data, status) {	
				console.log(data, status);
			});
		}else{
			deferred.resolve(itens);
		}

	    return deferred.promise;
	  };
	return service;
});

services.factory('AlertService', function($rootScope, $timeout) {
	var alertService = {};

	// create an array of alerts available globally
	$rootScope.alerts = [];

	alertService.addWithTimeout = function(type, msg, timeout) {
  	    var alert = alertService.add(type, msg);
		$timeout(function() {
			alertService.closeAlert(alert);
		}, timeout ? timeout: 4000);
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

services.factory('ValidationService', function(AlertService) {
	var service = {};
	
	service.validation = {};

	service.add = function(nome, msg) {
		if(nome == null){
			AlertService.addWithTimeout("danger",msg);
		}else{
			service.validation[nome] = msg;
		}
	};
	
	service.remove = function(nome) {
		service.validation[nome] = null;
	};
	
	service.clear = function() {
		service.validation = {};
	};
	
	service.registrarViolacoes = function(data){
		
		var violations=null;
		if (angular.isArray(data)){
			violations = data;
		}else{
			if(angular.isArray(data.constraintViolations)){
				violations = data.constraintViolations;
			}
		}
		if (angular.isArray(violations)){
			angular.forEach(violations, function(violation){	
				if(violation.message){
					service.add(violation.property,violation.message);
				}
			});
		}else{
			service.add(null,"Falha ao executar a operação.");
			console.log(data);
		}
	};
	
	return service;
});


services.factory('DashboardService', function($http, $q) {
	var service = {};				

	service.obterDemandas = function() {
	    var deferred = $q.defer();
	    $http({
			url : 'api/dashboard/minhasDemandas',
			method : "GET"
		}).success(function(data) {
			deferred.resolve(data);
		}).error(function(data, status) {	
			console.log(data, status);
		});		

	    return deferred.promise;
	  };
	return service;
});