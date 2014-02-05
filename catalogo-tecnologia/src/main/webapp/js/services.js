'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
var services = angular.module('catalogo.services', []);

services.factory('AnaliseService', function($http) {
	
	return {
		salvar: function(modelAnalise, sucesso, falha) {
			console.log("AnalisService.salvar() "+ JSON.stringify(modelAnalise));
            $http({
                url: 'api/analise',
                method: "POST",
                data: modelAnalise,
                headers: {
                    'Content-Type': 'application/json;charset=utf8'
                }
            }).success(function(response) {
            	console.log(response);
               sucesso(response);
            }).error(function(data, status) {
            	console.log(data);
            	console.log(status);
               falha(data);
            });
		}
	};
	  
});
