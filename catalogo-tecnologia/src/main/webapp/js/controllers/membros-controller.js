'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

/*"Para o funcionamento deste controlador é preciso disponibilizar o id da fase em $rootScope.demandaId"*/
controllers.controller('MembrosCtrl', function MembrosCtrl($scope, $rootScope, $http,
		$routeParams, AlertService) {
	
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
		if(!$scope.palavraChave || $scope.palavraChave.length<3){
			AlertService.addWithTimeout('warning', "Para pesquisar digite pelo menos 3 letras.");
		}else{
			$http.get('api/user/nome/' + $scope.palavraChave).success(function(data) {
				if (data == "") {
					AlertService.addWithTimeout('warning', 'Usuário não encontrado no LDAP');
				}else{
					$scope.resultadoPesquisa = data;
				}
			}).error(function(data, status) {
				if (status == 412) {
					AlertService.addWithTimeout('danger', data[0].message);
				}
			});
		}
	};
		

});
