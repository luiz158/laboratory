'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('PesquisaFasesCtrl', function PesquisaFasesCtrl($scope, $routeParams, $http, AlertService) {
	$scope.fase = {};
	$scope.fase.fase = $routeParams.fase;
	
	
		

});
