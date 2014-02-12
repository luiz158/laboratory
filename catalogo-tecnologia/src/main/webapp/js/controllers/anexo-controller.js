'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

controllers.controller('AnexoCtrl', function AnexoCtrl($scope, $rootScope, $http,
		$location, $routeParams, $upload) {
	
	$scope.anexo = {};
		
	$scope.init = function(fase){
		$scope.fase = fase;
		carregarAnexos();
	};	
		
	$scope.onFileSelect = function($files) {
		$scope.progress = 0;
		
		console.log($rootScope.demandaId);
		
		for (var i = 0; i < $files.length; i++) {
			var file = $files[i];
			console.log(file);
			console.log($scope.anexos);
			$scope.upload = $upload.upload({
				url : 'api/anexo',
				method : 'POST',
				data : {
					anexo : {
						analise: {id: $rootScope.demandaId},
						fase: $scope.fase,
						nomeArquivo: file.name,
						tipoArquivo: file.type,
					}
				},
				file : file,
				fileFormDataName: 'file'
			}).progress(
				function(evt) {
					$scope.progress = parseInt(100.0 * evt.loaded / evt.total);
					$scope.$apply();
					console.log('percent: '+ $scope.progress);
			}).success(function(data, status, headers, config) {
				console.log(data);
				$scope.progress = 0;
				carregarAnexos();
			});
		}
	};
	
	$scope.excluir = function(id) {
		$http({
			url : 'api/anexo/' + id,
			method : "DELETE"

		}).success(function(data) {
			carregarAnexos();
		}).error(function(data, status) {
		});
	};
	
	function carregarAnexos() {
		$http.get('api/anexo/'+$rootScope.demandaId+'/'+$scope.fase).success(function(data) {
			console.log(data);
			$scope.anexos = data;
		});
	}

});
