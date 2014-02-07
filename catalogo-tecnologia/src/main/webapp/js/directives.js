
var diretivas = angular.module('catalogo.directives', []);

diretivas.directive('ngSparkline', function() {
  return {
    restrict: 'A',
    require: '^ngCity',
    scope: {
      ngCity: '@'
    },
    template: '<div class="sparkline"><h4>Weather for {{ngCity}}</h4></div>',
    link: function(scope, iElement, iAttrs) {
      // get weather details
    }
  }
});


diretivas.directive('ngAnaliseSituacao', function() {

	  return {
		//restrict: 'E',
	    //require: '^ngSituacao',
	    scope: {
	      ngSituacao: '@'
	    },		  
	    template: '<a ng-click="editar(a)" class="btn"><i class="fa"> </i> {{ngSituacao}}</a>',
	    
	    link: function(scope, iElement, iAttrs) {
	    	if (scope.ngSituacao == 'Aprovado'){
	    		$(iElement).children().first().addClass('btn-success');
		    	$(iElement).children().first().children().first().addClass('fa-thumbs-o-up');
	    	} else 
	    	if (scope.ngSituacao == 'Reprovado') {
	    		$(iElement).children().first().addClass('btn-danger');
		    	$(iElement).children().first().children().first().addClass('fa-thumbs-o-down');
	    	} else {
	    		$(iElement).children().first().addClass('btn-primary');
		    	$(iElement).children().first().children().first().addClass('fa-pencil-square-o');
	    	}
	    }
	  };
	});