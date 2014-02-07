
var diretivas = angular.module('catalogo.directives', []);

diretivas.directive('ngAnaliseSituacao', function() {

	  return {
		//restrict: 'C',
	    //require: '^ngSituacao',
	    scope: {
	      situacao: '@'
	    },	
	    template: '<span class="label"><i class="fa"> </i> {{situacao}}</span>',
	    
	    link: function(scope, elem, $attrs) {
	    	
	    	$attrs.$observe('situacao', function(situacao) {
	    		var labelType = 'label-primary';
	    		var icon = 'fa-pencil-square-o';
		    	if (situacao == 'Aprovado'){
		    		var labelType = 'label-success';
		    		var icon = 'fa-thumbs-o-up';
		    	} else 
		    	if (situacao == 'Reprovado') {
		    		var labelType = 'label-danger';
		    		var icon = 'fa-thumbs-o-down';
		    	}
	    		$(elem).children().first().addClass(labelType).children().first().addClass(icon);
    		});

	    }
	  };
	});
