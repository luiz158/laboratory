'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers', []);

controllers.controller('Analise', function Analise($scope, AnaliseService) {
    
		function sucessoSalvar(){
			console.log('sucesso salvar');
		}
		
		function falhaSalvar(violations){
			console.log('falha  salvar');
			$.each(violations, function(i, violation){
				$("#msg-" + violation.property).text(violation.message);
			});
		}
	
    	$scope.analise = {};
    	$scope.analise.detalhamento = "...";
    
    	$scope.salvar = function(){
    		console.log("AnaliseController " + $scope.analise);
    		AnaliseService.salvar($scope.analise, sucessoSalvar, falhaSalvar);
    	};
});


