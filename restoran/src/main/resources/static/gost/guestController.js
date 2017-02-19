var app = angular.module('guest.controllers', []);
 
app.controller('guestController', ['$scope','$window','guestService', '$location',
  	function ($scope,$window, guestService, $location) {
		
		$scope.getLoggedUser = function() {
			guestService.getLoggedUser().then(
				function (response) {
					$scope.loggedUser = response.data;
	            }	
				
				
			)
			// ucitaj();
		}
		
		$scope.update = function() {
			guestService.updateGuestProfile($scope.loggedUser).then(
				function (response) {
                    $scope.state = undefined;
                    $location.path('gost');
				}
			);
		}
		
		$scope.zaRezervaciju = function(restoran){
			guestService.find(restoran.id).then(
					function(response){
						$scope.resttt = response.data;
						$location.path('gost');
					}
					)
		
		}
		
		// function ucitaj(){
		$scope.sviRestorani = function(){
			guestService.sviRestorani().then(
					function(response){
						$scope.restorani = response.data;
					}
				);
		}
		
		$scope.potvrdaRezervacije = function() {
			guestService.potvrda($scope.loggedUser,$scope.resttt).then(
				function (response) {
                    //$scope.state = undefined;
                    $location.path('gost');
				}
			);
		}
		
}]);
