'use strict';

/* Controllers */
var controllers = angular.module('catalogo.controllers');


controllers.controller('InternalizacaoCtrl', function InternalizacaoCtrl($scope,$filter, $rootScope, $http,$location, $routeParams, AlertService, OrigemDemandaService, ValidationService, DocumentoService, FaseService) {

	$(window).scrollTop(0);
	
	$scope.fase = {};
	$scope.fase.id = $routeParams.id;
	$scope.fase.fase = 3;
	$scope.origemDemanda = [];
	
	$scope.documentos = [];
	
	$scope.ciclo = {numero: 0, fator: 1};
	
	// Obtém os itens para o combo de Origem
	OrigemDemandaService.getItens().then(function(data) {
		$scope.origemDemanda = data;
	});
	
		

	function atualizarCiclo(newValue){	
		var ciclo = $scope.ciclo.numero * $scope.ciclo.fator;
		$scope.fase.proximaFaseCiclo = ciclo;
	}
   
	
	$scope.$watch('ciclo.numero', function(newValue, oldValue) {
		atualizarCiclo(newValue);
     });
	
	$scope.$watch('ciclo.fator', function(newValue, oldValue) {
		atualizarCiclo(newValue);
     });
	

	if ($scope.fase.id) {
		$http.get('api/internalizacao/' + $scope.fase.id).success(function(data) {
			$scope.fase = data;
			$scope.fase.faseAnterior = {
					id: 				data.faseAnterior.id,
					identificador: 		data.faseAnterior.identificador,  
					fase: 				data.faseAnterior.fase, 
					origemReferencia: 	data.faseAnterior.origemReferencia,
					codigoReferencia: 	data.faseAnterior.codigoReferencia
			};
			$scope.fase.faseInicial = {
					id: 				data.faseInicial.id, 
					fase: 				data.faseInicial.fase
			};
			if($scope.fase.proximaFaseCiclo){
				$scope.ciclo.numero = $scope.fase.proximaFaseCiclo;
			}
			listarDocumentos();
		}).error( function(data, status) {
			AlertService.addWithTimeout('danger','Não foi possível encontrar o registro');
			history.back();
		});
		
		$http.get('api/fase/proximafase/' + $scope.fase.id).success(function(data) {
			$scope.proximafase = data;
		});	
		
	} else {
		AlertService.addWithTimeout('danger','Não foi possível encontrar a prospecção');
		history.back();
	}
		
	$scope.salvar = function(finalizar) {
		var url = 'api/internalizacao';
		if(finalizar) url = url+"/finalizar";
		ValidationService.clear();
		console.log($scope.fase);
		$http({
			url : url,
			method : $scope.fase.id ? "PUT" : "POST",
			data : $scope.fase,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			if(finalizar){
				AlertService.addWithTimeout('success','Internalização finalizada com sucesso');
			}else{
				AlertService.addWithTimeout('success','Internalização salva com sucesso');
			}
			//$location.path('/pesquisa/fases/3');
		}).error( function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warning',data.message);
				//$location.path('/analise');
			} else if(status == 412){
				ValidationService.registrarViolacoes(data);
			}else{
				AlertService.addWithTimeout('danger','Não foi possível executar a operação');
			}
		});

	};

	$scope.aprovar = function(aprovado) {
		$scope.fase.situacao = aprovado ? 'Aprovado' : 'Reprovado';
	};
	
	$scope.finalizar = function() {
		$scope.salvar(true);
	};
	
	function listarDocumentos(){
		DocumentoService.getDocumentos($scope.fase.id).then(
		function(data){
			$scope.documentos = data;
		});
	}
	

	
	$scope.adicionarDocumento = function() {
		$scope.documento.fase = {id: $scope.fase.id};		
		DocumentoService.inserir($scope.documento).then(
		function(){
			listarDocumentos();
			$scope.documento = {};
		}, function(reason) {
    		console.log('Failed: ' + reason);
  		}, function(update) {
    		console.log('Got notification: ' + update);
  		});
	};
	
	$scope.removerDocumento = function() {
		
	};
	
	$scope.excluir = function(id) {
		FaseService.excluir(id).then(
			function(data) {
				AlertService.addWithTimeout('success','Análise excluída com sucesso');
				$location.path('/pesquisa/fases');
			},function(data) {
				ValidationService.registrarViolacoes(data);				
			}
		);
	};
	
	$scope.criarProximaFase = function() {
		console.log($scope.fase);
		ValidationService.clear();
		$http({
			url : 'api/fase/proximafase/internalizacao',
			method : "POST",
			data : $scope.fase ,
			headers : {
				'Content-Type' : 'application/json;charset=utf8'
			}
		}).success(function(data) {
			AlertService.addWithTimeout('success','Próxima fase criada com sucesso');
			var url = $filter("faseUrl")(data.fase);
			$location.path(url+'/'+data.id);
		}).error(function(data, status) {
			if (status == 401) {
				AlertService.addWithTimeout('warning',data.message);
			} else if(status == 412){
				ValidationService.registrarViolacoes(data);
			}else{
				AlertService.addWithTimeout('danger','Não foi possível executar a operação');
			}
		});
	};

});
