'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('SubcategoriaList',
		function Subcategoria($scope, $http, $location, AlertService) {
			
			function carregarSubcategorias() {
				$http.get('api/subcategoria').success(function(data) {
					$scope.subcategorias = data;
				});
			}

			$scope.novaSubcategoria = function() {
				$location.path('/subcategoria/edit');
			};

			$scope.editarSubcategoria = function(subcategoria) {
				$location.path('/subcategoria/edit/' + subcategoria.id);
			};

			$scope.excluirSubcategoria = function(id) {
				$http({
					url : 'api/subcategoria/' + id,
					method : "DELETE"

				}).success(function(data) {
					carregarSubcategorias();

				}).error(function(data, status) {
					AlertService.addWithTimeout('danger','Não foi possível executar a operação');
					console.log('vai vltar...');
				});
			};

			carregarSubcategorias();
		});

controllers.controller('SubcategoriaEdit', function Subcategoria($scope, $http,
		$location, $routeParams, $upload, $rootScope, AlertService) {
	
	var id = $routeParams.id;
	$scope.tecnologia = "";
	$scope.tema = "";
	
	// Necessário para compartilhar entre os controladores: Anexo, Colaboradores...
	$rootScope.demandaId = id;

	if (id) {
		$http.get('api/subcategoria/' + id).success(function(data) {
			$scope.subcategoria = data;
			
			$http.get('api/tema').success(function(data) {
				$scope.temas = [];
				$scope.temas = data;
				if(typeof($scope.subcategoria.tema) != "undefined"){
					var index = buscaElemento($scope.subcategoria.tema,$scope.temas);
					if(index!=-1){
						$scope.tema = $scope.temas[index];
						
						$http.get('api/tecnologia').success(function(data) {
							$scope.tecnologias = [];
							$scope.tecnologias = data;
							if(typeof($scope.tema.tecnologia) != "undefined"){
								var index = buscaElemento($scope.tema.tecnologia,$scope.tecnologias);
								if(index!=-1){
									$scope.tecnologia = $scope.tecnologias[index];
								}
							}
						});
						
					}
				}
			});
			
		});
	} else {
		$scope.subcategoria = {};
		
		$http.get('api/tecnologia').success(function(data) {
			$scope.tecnologias = [];
			$scope.tecnologias = data;
		});
		
	}

	$scope.salvarSubcategoria = function() {
		
		console.log("SubcategoriaController " + $scope.subcategoria);

		if(typeof($scope.tecnologia) != "undefined" && $scope.tecnologia != ""){
			if(typeof($scope.tema) != "undefined" && $scope.tema != ""){
				$scope.subcategoria.tema = $scope.tema;
				
				$("[id$='-message']").text("");
				$http({
					url : 'api/subcategoria',
					method : $scope.subcategoria.id ? "PUT" : "POST",
					data : $scope.subcategoria,
					headers : {
						'Content-Type' : 'application/json;charset=utf8'
					}
			
				}).success(function(data) {
					AlertService.addWithTimeout('success','Subcategoria salva com sucesso');
					$location.path('subcategoria');
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
				AlertService.addWithTimeout('danger','É preciso selecionar um tema!');
			}
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

	$scope.carregarTemas = function() {
		$scope.tema = "";
		$http.get('api/tema/listar/'+$scope.tecnologia.id).success(function(data) {
			$scope.temas = [];
			$scope.temas = data;
			console.log($scope.temas);
		});
	}
	
});