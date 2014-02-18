'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

/*"Para o funcionamento deste controlador é preciso disponibilizar o id da demanda em $rootScope.demandaId"*/
controllers.controller('AnexoCtrl', function AnexoCtrl($scope, $rootScope, $http,
		$location, $routeParams, $upload, AlertService) {
	
	
	$scope.anexo = {};
	$scope.progress = 0;
	$scope.labelArquivos = 'Nenhum arquivo selecionado';
	
	$scope.init = function(fase){
		$scope.fase = fase;
		carregarAnexos();
	};	
		
	$scope.onFileSelect = function($files) {
		$scope.progress = 0;
		for (var i = 0; i < $files.length; i++) {
			var file = $files[i];
			$scope.upload = $upload.upload({
				url : 'api/anexo',
				method : 'POST',
				data : {
					anexo : {
						analise: {id: $rootScope.demandaId},
						fase: $scope.fase,
						nomeArquivo: file.name,
						tipoArquivo: file.type,
						tamanhoArquivo: file.size,
					}
				},
				file : file,
				fileFormDataName: 'file'
			}).progress(
				function(evt) {
					var percent = parseInt(100.0 * evt.loaded / evt.total);
					$scope.progress =  (percent == 100) ? 0 : percent;
			}).success(function(data, status, headers, config) {
				$scope.progress = 0;				
				carregarAnexos();
			});
		}
		if ($files.length == 1) {
			$scope.labelArquivos = $files[0].name;
		} else if ($files.length > 1) {
			$scope.labelArquivos = $files.length + ' arquivos selecionados';
		}		
	};
	
	$scope.excluir = function(id) {
		$http({
			url : 'api/anexo/' + id,
			method : "DELETE"
		}).success(function(data) {
			AlertService.addWithTimeout('success','Anexo excluído com sucesso');
			carregarAnexos();
		}).error(function(data, status) {
			AlertService.addWithTimeout('danger','Não foi possível excluir o anexo.');
		});
	};
	
	$scope.cancelarUpload = function() {
		$scope.labelArquivos = 'Upload cancelado';
		$scope.progress = 0;		
		$scope.upload.abort();
	}
	
	function carregarAnexos() {
		if($rootScope.demandaId){
			$http.get('api/anexo/'+$rootScope.demandaId+'/'+$scope.fase).success(function(data) {
				$scope.anexos = data;
			});
		}
	}

});
