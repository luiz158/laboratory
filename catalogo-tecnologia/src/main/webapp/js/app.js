'use strict';

angular.module('catalogo', [
  'ngRoute',
  'catalogo.controllers',
  'catalogo.directives',
  'catalogo.services',
  'catalogo.filters',
  'angularFileUpload',
  'mgcrea.ngStrap',
  'ui.bootstrap'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {templateUrl: 'partials/login.html', controller: 'Auth'});
  $routeProvider.when('/pesquisa/fases/:fase', {templateUrl: 'partials/pesquisa-fases.html', controller: 'PesquisaFasesCtrl'});
  $routeProvider.when('/pesquisa/fases', {templateUrl: 'partials/pesquisa-fases.html', controller: 'PesquisaFasesCtrl'});
  $routeProvider.when('/analise', {templateUrl: 'partials/analise-listar.html', controller: 'AnaliseList'});
  $routeProvider.when('/analise/edit', {templateUrl: 'partials/analise-edit.html', controller: 'AnaliseEdit'});
  $routeProvider.when('/analise/edit/:id', {templateUrl: 'partials/analise-edit.html', controller: 'AnaliseEdit'});
  $routeProvider.when('/prospeccao/edit/:id', {templateUrl: 'partials/prospeccao-edit.html', controller: 'ProspeccaoCtrl'});

  $routeProvider.when('/produto', {templateUrl: 'partials/produto-listar.html', controller: 'ProdutoList'});
  $routeProvider.when('/produto/edit', {templateUrl: 'partials/produto-edit.html', controller: 'ProdutoEdit'});
  $routeProvider.when('/produto/edit/:id', {templateUrl: 'partials/produto-edit.html', controller: 'ProdutoEdit'});
  
  $routeProvider.when('/grupo', {templateUrl: 'partials/grupo-listar.html', controller: 'GrupoList'});
  $routeProvider.when('/grupo/edit', {templateUrl: 'partials/grupo-edit.html', controller: 'GrupoEdit'});
  $routeProvider.when('/grupo/edit/:id', {templateUrl: 'partials/grupo-edit.html', controller: 'GrupoEdit'});
  
  $routeProvider.when('/user', {templateUrl: 'partials/user-listar.html', controller: 'UserList'});
  $routeProvider.when('/user/edit/:id', {templateUrl: 'partials/user-edit.html', controller: 'UserEdit'});
  $routeProvider.otherwise({templateUrl: 'partials/dashboard.html'});
}]);

var controllers = angular.module('catalogo.controllers',[]);

var ModalDemoCtrl = function ($scope, $http, $modal, $log) {

	$scope.produtoParaPesquisa = "";
	
	  $scope.open = function () {
		  
		    var servico = "";  
		  
		    if($scope.produtoParaPesquisa != ""){
		    	servico = 'api/produto/listar/'+ $scope.produtoParaPesquisa;
		    }else{
		    	servico = 'api/produto';
		    }  
		   
			$http.get(servico).success(function(data) {
				$scope.produtosPesquisados = data;
				
				var modalInstance = $modal.open({
				    templateUrl: 'myModalContent.html',
				    controller: ModalInstanceCtrl,
				    resolve: {
				      items: function () {
				        return $scope.produtosPesquisados;
				      }
				    }
				});
				
				modalInstance.result.then(function (selectedItem) {
				      $scope.selected = selectedItem;
				    }, function () {
				    $log.info('Modal dismissed at: ' + new Date());
				});
				
			});
	  };
};

	// Please note that $modalInstance represents a modal window (instance) dependency.
	// It is not the same as the $modal service used above.

var ModalInstanceCtrl = function ($rootScope, $scope, $modalInstance, items) {

	$scope.produtosPesquisados = items;
	
	$scope.selected = {
	    item: $scope.produtosPesquisados[0]
	};
	
	$scope.ok = function () {
	  $modalInstance.close($scope.selected.item);
	  $rootScope.produto.produtoAnterior = $scope.selected.item.nome;
	};

	$scope.cancel = function () {
	  $modalInstance.dismiss('cancel');
	};
};