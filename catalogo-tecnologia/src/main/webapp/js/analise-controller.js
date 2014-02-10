'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers', []);

controllers.controller('AnaliseList',
		function Analise($scope, $http, $location) {

			function carregarAnalises() {
				$http.get('api/analise').success(function(data) {
					$scope.analises = data;
				});
			}

			$scope.novo = function() {
				$location.path('/analise/edit');
			};

			$scope.editar = function(analise) {
				$location.path('/analise/edit/' + analise.id);
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

controllers.controller('AnaliseEdit', function Analise($scope, $http,
		$location, $routeParams) {

	var id = $routeParams.id;

	if (id) {
		$http.get('api/analise/' + id).success(function(data) {
			$scope.analise = data;
		});
	} else {
		$scope.analise = {};
		$scope.analise.situacao = 'Rascunho';
	}

	$scope.salvar = function() {
		console.log("AnaliseController " + $scope.analise);
		$("[id$='-message']").text("");
		$http({
			url : 'api/analise',
			method : $scope.analise.id ? "PUT" : "POST",
			data : $scope.analise,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}

		}).success(function(data) {			
			$location.path('analise');
		}).error(
				function(data, status) {
					if (status = 412) {
						$.each(data, function(i, violation) {
							$("#" + violation.property + "-message").text(
									violation.message);
						});
					}
				});

	};
	
	$scope.aprovar = function(aprovado){
		$scope.analise.situacao = aprovado?'Aprovado':'Reprovado';
		//$scope.salvar();
	};
	

});
