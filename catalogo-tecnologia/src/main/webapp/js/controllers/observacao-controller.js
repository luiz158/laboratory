'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('ObservacaoCtrl', function ObservacaoCtrl($scope, $rootScope, $http,
		$routeParams, AlertService, AuthService) {
	
	/* Pega a fase diretamente da diretiva*/
	$scope.fase = $scope.$parent.ngModel;
	$scope.observacoes = [];	
	$scope.observacao = {
			fase: $scope.fase,
			usuario: AuthService.getUsuario()
	};
	
	carregarObservacoes();	
	
	function carregarObservacoes() {		
		if($scope.fase.id){
			$http.get('api/observacao/fase/'+$scope.fase.id).success(function(data) {
				$scope.observacoes = data;
			});
		}	
	}
		
	$scope.adicionar = function(){
		$scope.observacao.usuario = AuthService.getUsuario();
		$http({
			url : 'api/observacao/fase/'+$scope.fase.id+'/add',
			method : "POST",
			data : $scope.observacao,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			$scope.observacao.observacao="";
			carregarObservacoes();
			AlertService.addWithTimeout('success','Observação registrada');			
		}).error( function(data, status) {
			AlertService.addWithTimeout('danger','Não foi possível adicionar a observação.');
		});
	};
	
	$scope.salvar = function(obs){		
		obs.fase = {id: obs.fase.id};		
		$http({
			url : 'api/observacao',
			method : "PUT",
			data : obs,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			$scope.observacao.observacao="";
			carregarObservacoes();
			AlertService.addWithTimeout('success','Observação salva');			
		}).error( function(data, status) {
			AlertService.addWithTimeout('danger','Não foi possível salvar a observação.');
		});
	};
	
	$scope.podeEditar = function(obs){
		return AuthService.getUsuario().id == obs.usuario.id;
	};
	
	$scope.excluir = function(obs) {
		$http({
			url : 'api/observacao/'+obs.id,
			method : "DELETE",
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			carregarObservacoes();
			AlertService.addWithTimeout('success','Observação excluída');			
		}).error( function(data, status) {
			AlertService.addWithTimeout('danger','Não foi possível excluir a observação.');
		});
	};
		

});
