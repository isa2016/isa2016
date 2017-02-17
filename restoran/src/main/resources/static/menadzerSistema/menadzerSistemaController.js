var app = angular.module('menadzerSistema.controllers', []);
 
app.controller('menadzerSistemaController', ['$scope','$window','menadzerSistemaService', '$location',
  	function ($scope,$window, menadzerSistemaService, $location) {
		
	$scope.dodajRest = function () {  

		menadzerSistemaService.save($scope.rest).then(
			function (response) {
                $location.path('logovan/menadzerSistema/pocetna');
            }
        ); 	
	}
	
	$scope.sviRestorani = function(){
		menadzerSistemaService.sviRestorani().then(
				function(response){
					$scope.restorani = response.data;
				}
			);
	}
		
}]);
