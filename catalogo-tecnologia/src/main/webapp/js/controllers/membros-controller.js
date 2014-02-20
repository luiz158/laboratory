'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

/*"Para o funcionamento deste controlador é preciso disponibilizar o id da fase em $rootScope.demandaId"*/
controllers.controller('MembrosCtrl', function MembrosCtrl($scope, $rootScope, $http,
		$routeParams, AlertService) {
	
	/* Pega a fase diretamente da diretiva*/
	$scope.fase = $scope.$parent.ngModel;
	
	carregarMembros();
	
	
	
	function carregarMembros() {
		if($rootScope.demandaId){
			$http.get('api/membros/'+$rootScope.fase.id).success(function(data) {
				$scope.membros = data;
			});
		}
	
	}
		
	$scope.resultadoPesquisa = [
	    {id: 1, nome: 'Fulano', area: 'CETEC', ramal: '#71 1750'},
	    {id: 2, nome: 'Sicrano', area: 'CETEC', ramal: '#71 1751'},
	    {id: 3, nome: 'Beltrano', area: 'CETEC', ramal: '#71 1752'},
	    {id: 4, nome: 'Sicrano', area: 'CETEC', ramal: '#71 1753'},
	    {id: 5, nome: 'Sicrano', area: 'CETEC', ramal: '#71 1754'}
	 ];
	
	$scope.membros = $scope.resultadoPesquisa;
	
	$scope.adicionar = function(m){
		$scope.membros.push(m);
	};
	
	$scope.remover = function(m){
		for( var i = 0; i < $scope.membros.length; i++){
			if($scope.membros[i].id == m.id)
				$scope.membros.splice(i, 1);
		}		
	};
	
	$scope.pesquisar = function(m){
		$scope.adicionar({id: 10, nome: 'fake', area: 'CETEC', ramal: '#71 1750'});
	};
		

});
