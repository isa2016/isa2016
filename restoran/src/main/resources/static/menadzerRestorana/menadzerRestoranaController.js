var app = angular.module('menadzerRestorana.controllers', []);

app.controller('menadzerRestoranaController', ['$scope','menadzerRestoranaService', '$location',
                                             	function ($scope, menadzerRestoranaService, $location) {
	
	       
	$scope.getLoggedUser = function() {
		menadzerRestoranaService.getLoggedUser($scope.loggedUser).then(
			function (response) {
				$scope.loggedUser = response.data;
            }	
			
			
		)
		
	}
	
	$scope.list = function () {            
		menadzerRestoranaService.list($scope.loggedUser).then(
				function (response) {
					$scope.lista = response.data;
				$location.path('/menadzerRestorana/restorani');
			
				}
				);
	}
	
	
}]);
 