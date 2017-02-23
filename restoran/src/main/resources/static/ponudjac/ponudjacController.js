var app = angular.module('ponudjac.controllers', []);
 
app.controller('ponudjacController', ['$scope','$window','ponudjacService', '$location',
  	function ($scope,$window, ponudjacService, $location) {
		
		$scope.getLoggedUser = function() {
			ponudjacService.getLoggedUser().then(
				function (response) {
					$scope.loggedUser = response.data;
	            }	
				
				
			);
			
		}
		
		$scope.update = function() {
			ponudjacService.updatePonudjacProfile($scope.loggedUser).then(
				function (response) {
                    $scope.state = undefined;
                    $location.path('/ponudjac/profili');
				}
			);
		}
		
		
		
}]);
