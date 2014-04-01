'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('CategoriaList',
		function Categoria($scope, $http, $location, AlertService) {
			
			function carregarCategorias() {
				$http.get('api/categoria').success(function(data) {
					$scope.categorias = data;
				});
			}

			$scope.novaCategoria = function() {
				$location.path('/categoria/edit');
			};

			$scope.editarCategoria = function(categoria) {
				$location.path('/categoria/edit/' + categoria.id);
			};

			$scope.excluirCategoria = function(id) {
				$http({
					url : 'api/categoria/' + id,
					method : "DELETE"

				}).success(function(data) {
					carregarCategorias();
				}).error(function(data, status) {
					if(status == 401){
						AlertService.addWithTimeout('warning',data.message);
						$location.path('/categoria');
					}else{
						AlertService.addWithTimeout('danger','Não foi possível executar a operação');
					}
				});
			};

			carregarCategorias();
		});

controllers.controller('CategoriaEdit', function Categoria($scope, $http,
		$location, $routeParams, $upload, $rootScope, AlertService) {
	
	var id = $routeParams.id;
	$scope.tecnologia = "";
	
	// Necessário para compartilhar entre os controladores: Anexo, Colaboradores...
	$rootScope.demandaId = id;

	if (id) {
		$http.get('api/categoria/' + id).success(function(data) {
			$scope.categoria = data;

			$http.get('api/tecnologia').success(function(data) {
				$scope.tecnologias = [];
				$scope.tecnologias = data;
				if(typeof($scope.categoria.tecnologia) != "undefined"){
					var index = buscaElemento($scope.categoria.tecnologia,$scope.tecnologias);
					if(index!=-1){
						$scope.tecnologia = $scope.tecnologias[index];
					}
				}	
			});
		});
	} else {
		$scope.categoria = {};
		
		$http.get('api/tecnologia').success(function(data) {
			$scope.tecnologias = [];
			$scope.tecnologias = data;
		});
		
	}

	$scope.salvarCategoria = function() {
		
		console.log("CategoriaController " + $scope.categoria);

		if(typeof($scope.tecnologia) != "undefined" && $scope.tecnologia != ""){
			$scope.categoria.tecnologia = $scope.tecnologia;
				
			$("[id$='-message']").text("");
			$http({
				url : 'api/categoria',
				method : $scope.categoria.id ? "PUT" : "POST",
				data : $scope.categoria,
				headers : {
					'Content-Type' : 'application/json;charset=utf8'
				}
			
			}).success(function(data) {
				AlertService.addWithTimeout('success','Categoria salva com sucesso');
				$location.path('categoria');
			}).error(function(data, status) {
				if(status == 401){
					AlertService.addWithTimeout('warning',data.message);
					$location.path('/categoria');
				}else if (status == 412) {
					$.each(data, function(i, violation) {
						$("#" + violation.property + "-message").text(violation.message);
					});
				} else {
					AlertService.addWithTimeout('danger','Não foi possível executar a operação');
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