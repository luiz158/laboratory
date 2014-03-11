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
		if($rootScope.demandaId){
			$http.get('api/membros/'+$scope.fase.id).success(function(data) {
				$scope.membros = data;
			});
		}
	
	}
		
	$scope.adicionar = function(m){
		$scope.membros.push(m);
	};
	
	$scope.remover = function(m){
		for( var i = 0; i < $scope.membros.length; i++){
			if($scope.membros[i].id == m.id)
				$scope.membros.splice(i, 1);
		}		
	};
	
	$scope.pesquisar = function(){
		$http.get('api/user/nome/' + $scope.palavraChave).success(function(data) {
			if (data == "") {
				AlertService.addWithTimeout('warning', 'Usuário não encontrado no LDAP');
			}else{
				$scope.resultadoPesquisa = [];				
				angular.forEach(data, function(ldap){
					var user = {};
					user.email = ldap.email;
					user.nome = ldap.displayName;
					user.ramal = ldap.telephoneNumber;
					user.cpf = ldap.name;					
					$scope.resultadoPesquisa.push(user);
				});
				
			}
		}).error(function(data, status) {
			if (status == 412) {
				AlertService.addWithTimeout('danger', data[0].message);
			}
		});
	};
		

});
