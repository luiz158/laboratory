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
				console.log(data);
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
	
	
		

});
