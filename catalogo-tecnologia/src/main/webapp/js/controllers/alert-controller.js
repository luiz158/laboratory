'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('AlertController', function AlertController($scope) {
	$scope.alerts = [];

	$scope.addAlert = function(type, msg, timeout) {
		$scope.alerts.push({
			type: type,
			msg : msg,
			timeout: timeout,
			close : function() {
				return $scope.closeAlert(this);
			}
		});
		/*
		console.log("add"+timeout);
		if (timeout) {
			console.log("timeout"+timeout);
			$timeout(function() {
				alertService.closeAlert(this);
			}, timeout);
		}*/		
	};

	/*
	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};
	*/
	
	$scope.closeAlert = function(alert) {
		return this.closeAlertIdx($rootScope.alerts.indexOf(alert));
	};
	
	$scope.closeAlertIdx = function(index) {
		return $scope.alerts.splice(index, 1);
	};
});


																																																		// if
																																																				// (timeout)
																																																				// {
																																																				// $timeout(function(){
																																																				// alertService.closeAlert(this);
																																																				// },
																																																				// timeout);
																																																				// } };
																																																				// alertService.closeAlert
																																																				// =
																																																				// function(alert)
																																																				// {
																																																				// return
																																																				// this.closeAlertIdx($rootScope.alerts.indexOf(alert));
																																																				// };
																																																				// alertService.closeAlertIdx
																																																				// =
																																																				// function(index)
																																																				// {
																																																				// return
																																																				// $rootScope.alerts.splice(index,
																																																				// 1);
																																																				// };
																																																				// return
																																																				// alertService;
