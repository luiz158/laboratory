'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('TemaList',
		function Tema($scope, $http, $location, AlertService) {
			
			function carregarTemas() {
				$http.get('api/tema').success(function(data) {
					$scope.temas = data;
				});
			}

			$scope.novoTema = function() {
				$location.path('/tema/edit');
			};

			$scope.editarTema = function(tema) {
				$location.path('/tema/edit/' + tema.id);
			};

			$scope.excluirTema = function(id) {
				$http({
					url : 'api/tema/' + id,
					method : "DELETE"

				}).success(function(data) {
					carregarTemas();

				}).error(function(data, status) {
					AlertService.addWithTimeout('danger','Não foi possível executar a operação');
					console.log('vai vltar...');
				});
			};

			carregarTemas();
		});

controllers.controller('TemaEdit', function Tema($scope, $http,
		$location, $routeParams, $upload, $rootScope, AlertService) {
	
	var id = $routeParams.id;
	$scope.tecnologia = "";
	
	// Necessário para compartilhar entre os controladores: Anexo, Colaboradores...
	$rootScope.demandaId = id;

	if (id) {
		$http.get('api/tema/' + id).success(function(data) {
			$scope.tema = data;
			
			$http.get('api/tecnologia').success(function(data) {
				$scope.tecnologias = [];
				if(typeof($scope.tema.tecnologia) != "undefined"){
					$scope.tecnologias = data;
					var index = buscaElemento($scope.tema.tecnologia,$scope.tecnologias);
					if(index!=-1){
						$scope.tecnologia = $scope.tecnologias[index];
					}
				}else{
					$http.get('api/tecnologia').success(function(data) {
						$scope.tecnologias = [];
						$scope.tecnologias = data;
					});
				}
			});
			
		});
	} else {
		$scope.tema = {};
		$http.get('api/tecnologia').success(function(data) {
			$scope.tecnologias = [];
			$scope.tecnologias = data;
		});
	}

	$scope.salvarTema = function() {
		
		console.log("TemaController " + $scope.tema);

		if(typeof($scope.tecnologia) != "undefined" && $scope.tecnologia != ""){
			$scope.tema.tecnologia = $scope.tecnologia;
			
			$("[id$='-message']").text("");
			$http({
				url : 'api/tema',
				method : $scope.tema.id ? "PUT" : "POST",
				data : $scope.tema,
				headers : {
					'Content-Type' : 'application/json;charset=utf8'
				}
		
			}).success(function(data) {
				AlertService.addWithTimeout('success','Tema salvo com sucesso');
				$location.path('tema');
			}).error(
					function(data, status) {
						if (status = 412) {
							$.each(data, function(i, violation) {
								$("#" + violation.property + "-message").text(
										violation.message);
							});
						}
			});
		}else{
			AlertService.addWithTimeout('danger','É preciso selecionar uma tecnologia!');
		}
		
		
	};
	
	function buscaElemento(elemento,lista){
		var index = -1;
		for ( var i = 0 ; i < lista.length ; i++ ) {
			if (lista[i].nome === elemento.nome) {
                index = i;
                break;
            }
		}
		return index;
	}
	
});