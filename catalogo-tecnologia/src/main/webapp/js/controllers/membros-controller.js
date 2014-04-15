'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

/*"Para o funcionamento deste controlador é preciso disponibilizar o id da fase em $rootScope.demandaId"*/
controllers.controller('MembrosCtrl', function MembrosCtrl($scope, $rootScope, $http,
		$routeParams, AlertService, UserService) {
	
	/* Pega a fase diretamente da diretiva*/
	$scope.fase = $scope.$parent.ngModel;
	
	$scope.membros = [];
	$scope.resultadoPesquisa = [];
	
	
	carregarMembros();	
	
	function carregarMembros() {		
		if($scope.fase.id){
			$http.get('api/fase/'+$scope.fase.id+"/membros").success(function(data) {
				$scope.membros = data;
			});
		}
	
	}
		
	$scope.adicionar = function(m){
		$http({
			url : 'api/fase/'+$scope.fase.id+'/membros/add',
			method : "POST",
			data : m,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			carregarMembros();
			AlertService.addWithTimeout('success','Membro ('+data.name+') adicionado');			
		}).error( function(data, status) {
			AlertService.addWithTimeout('danger','Não foi possível adicionar o membro.');
		});
	};
	
	$scope.remover = function(m){
		$http({
			url : 'api/fase/membros/excluir/'+m.id,
			method : "DELETE"
		}).success(function(data) {
			carregarMembros();
			AlertService.addWithTimeout('success','Membro excluído com sucesso');
		}).error(function(data, status) {
			AlertService.addWithTimeout('danger','Não foi possível excluir o membro.');
		});			
	};
	
	
	$scope.pesquisar = function(){
		UserService.searchByName($scope.palavraChave).then( 
			function(data) {
				$scope.resultadoPesquisa = data;
			}
		);
	};
		

});
