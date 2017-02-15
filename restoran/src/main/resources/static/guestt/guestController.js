var app = angular.module('guest.controllers', []);
 
app.controller('guestController', ['$scope','$window','guestService', '$location',
  	function ($scope,$window, guestService, $location) {
		
		$scope.getLoggedUser = function() {
			guestService.getLoggedUser().then(
				function (response) {
					$scope.loggedUser = response.data;
	            }		
			)
		}
		
		$scope.update = function() {
			guestService.updateGuestProfile($scope.loggedUser).then(
				function (response) {
                    $scope.state = undefined;
                    $location.path('gost/pocetna');
				}
			);
		}
		
}]);
