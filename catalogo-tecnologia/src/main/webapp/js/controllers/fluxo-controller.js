'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');


controllers.controller('FluxoCtrl', function FluxoCtrl($scope, $http, $routeParams) {
	
	/* Pega a fase diretamente da diretiva*/
	$scope.fase = $scope.$parent.ngModel;
	
	if($scope.fase.id){
		$http.get('api/fase/fluxo/' + $scope.fase.id).success(function(data) {
			$scope.fluxo = data;
		});	
	}

});
