'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('RootController', function RootController($rootScope, AlertService) {
	$rootScope.closeAlert = AlertService.closeAlert; 
});
