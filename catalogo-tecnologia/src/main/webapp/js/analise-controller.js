'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers', []);

controllers.controller('Analise', function Analise($scope, $http) {

	$scope.analise = {};
	$scope.analise.detalhamento = "...";
	$scope.analises = [];

	function carregarAnalises() {
		$scope.analise = {};
		$http.get('api/analise').success(function(data) {
			$scope.analises = data;
		});
	}

	$scope.salvar = function() {
		console.log("AnaliseController " + $scope.analise);
		if ($scope.analise.id)
			$http({
				url : 'api/analise',
				method : "PUT",
				data : $scope.analise,
				headers : {
					'Content-Type' : 'application/json;charset=utf8'
				}

			}).success(function(data) {
				$("[id$='-message']").text("");
				carregarAnalises();

			}).error(
					function(data, status) {
						if (status = 412) {
							$.each(data, function(i, violation) {
								$("#" + violation.property + "-message").text(
										violation.message);
							});
						}
					});
		else {
			$http({
				url : 'api/analise',
				method : "POST",
				data : $scope.analise,
				headers : {
					'Content-Type' : 'application/json;charset=utf8'
				}

			}).success(function(data) {
				$("[id$='-message']").text("");
				carregarAnalises();

			}).error(
					function(data, status) {
						if (status = 412) {
							$.each(data, function(i, violation) {
								$("#" + violation.property + "-message").text(
										violation.message);
							});
						}
					});
		}
	};

	$scope.editar = function(analise) {
		$scope.analise = analise;
	};

	$scope.excluir = function(id) {
		$http({
			url : 'api/analise/' + id,
			method : "DELETE"

		}).success(function(data) {
			carregarAnalises();

		}).error(function(data, status) {
		});
	};

	carregarAnalises();
});
