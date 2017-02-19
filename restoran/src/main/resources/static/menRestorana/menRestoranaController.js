var app = angular.module('menRestorana.controllers', []);

app.controller('menRestoranaController', ['$scope','menRestoranaService', '$location',
                                             	function ($scope, menRestoranaService, $location) {
	
	       
	$scope.getLoggedUser = function() {
		menRestoranaService.getLoggedUser($scope.loggedUser).then(
			function (response) {
				$scope.loggedUser = response.data;
            }	
			
			
		)
		
	}
	
	
	$scope.list = function () {            
		menRestoranaService.list($scope.loggedUser).then(
				function (response) {
					$scope.lista = response.data;
					$scope.lista;
				$location.path('menadzerRestorana/restorani');
			
				}
				);
	}
	
	
}]);
 