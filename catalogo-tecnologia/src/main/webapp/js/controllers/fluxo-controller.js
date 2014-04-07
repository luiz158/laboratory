'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');


controllers.controller('FluxoCtrl', function FluxoCtrl($scope, $http, $routeParams) {
	$scope.fase = $scope.$parent.ngModel;
	$scope.fluxo = [];
	if($scope.fase.id){
		$http.get('api/fase/fluxo/' + $scope.fase.id).success(function(data) {
			$scope.fluxo = [];
			var sustentacoes = [];
			angular.forEach(data, function(f, index) {
				if((sustentacoes.length>0 && f.fase != "SUSTENTACAO")
						||(sustentacoes.length>0 && index>= data.length-1)){
					var s = sustentacoes[sustentacoes.length-1];
					s.ciclos = sustentacoes.length;
					s.sustentacoes = sustentacoes.slice(0, sustentacoes.length-1);
					$scope.fluxo.push(s);
					sustentacoes=[];
				}
				
				if(f.fase == "SUSTENTACAO"){					
					sustentacoes.push(f);					
				}else{					
					$scope.fluxo.push(f);
				}						
			});
		});	
	}
	
	$scope.toggleCiclos = function(){
		if($scope.exibirCiclos){
			$scope.exibirCiclos = false;
		}else{
			$scope.exibirCiclos = true;
		}
	};

});
