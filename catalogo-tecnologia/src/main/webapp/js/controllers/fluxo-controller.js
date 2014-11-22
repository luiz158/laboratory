'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');


controllers.controller('FluxoCtrl', function FluxoCtrl($scope, $http, $routeParams) {
	console.log("Fluxo");
	console.log($scope.ngModel);
	$scope.fase = $scope.$parent.ngModel;
	$scope.fluxo = [];
	if($scope.fase.id){
		$http.get('api/fase/fluxo/' + $scope.fase.id).success(function(data) {
			$scope.fluxo = [];
			var indexSustentacao = -1;
			var sustentacoes = [];
			angular.forEach(data, function(f, index) {				
				if(f.fase != "SUSTENTACAO"){
					$scope.fluxo.push(f);
				}else{
					if(sustentacoes.length == 0){
						indexSustentacao = index;
						$scope.fluxo.push({});
					}
					sustentacoes.push(f);

				}				
			});
			
			if(sustentacoes.length > 0){
				$scope.fluxo[indexSustentacao] = sustentacoes[sustentacoes.length-1];
				$scope.fluxo[indexSustentacao].ciclos = sustentacoes.length;
				$scope.fluxo[indexSustentacao].sustentacoes = [];
				for(var x = sustentacoes.length -2; x>=0; x--){
					$scope.fluxo[indexSustentacao].sustentacoes.push(sustentacoes[x]);
				}
			}
			
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
