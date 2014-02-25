'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');

/*"Para o funcionamento deste controlador é preciso disponibilizar o id da demanda em $rootScope.demandaId"*/
controllers.controller('AnexoCtrl', function AnexoCtrl($scope, $rootScope, $http,
		$location, $routeParams, $upload, AlertService) {
	
	/* Pega a fase diretamente da diretiva*/
	$scope.fase = $scope.$parent.ngModel;
	
	$scope.anexo = {};
	$scope.progress = 0;
	$scope.labelArquivos = 'Nenhum arquivo selecionado';
	
	carregarAnexos();
		
	$scope.onFileSelect = function($files) {
		$scope.progress = 0;
		for (var i = 0; i < $files.length; i++) {
			var file = $files[i];
			$scope.labelArquivos = 'Anexando ' + file.name + ' (' + i + '/' + $files.length + ')';
			$scope.upload = $upload.upload({
				url : 'api/anexo',
				method : 'POST',
				data : {
					anexo : {
						fase: {id: $scope.fase.id, fase: $scope.fase.fase},						
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
					//$scope.progress = parseInt(100.0 * evt.loaded / evt.total);
					//$scope.apply();
			}).success(function(data, status, headers, config) {
				//$scope.labelArquivos = 'Incluindo anexo...';
				carregarAnexos();
				$scope.progress = 0;
				$scope.labelArquivos = '';				
			}).error(function(data, status) {
				$scope.labelArquivos = '';
				$scope.progress = 0;					
				AlertService.add('danger','Não foi possível incluir o anexo.' + data + ' erro: ' + status);
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
	};
	
	function carregarAnexos() {
		if($scope.fase.id){
			$http.get('api/anexo/'+$scope.fase.id).success(function(data) {
				$scope.anexos = data;
			});
		}
	}

});
