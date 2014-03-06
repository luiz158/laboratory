'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('DashboardCtrl', function DashboardCtrl($scope) {

	$scope.data = [
	               {label: "Análise", value: 12},
	               {label: "Prospecção", value: 18},
	               {label: "Internalização", value: 15},
	               {label: "Sustentação", value: 50},
	               {label: "Declínio", value: 6}
	             ];
	
	$scope.colors = ['#228B22','#483D8B','#FFD700','#1E90FF', '#DC143C'];
});
