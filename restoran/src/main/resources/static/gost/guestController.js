var app = angular.module('guest.controllers', []);

app.controller('guestController', [
		'$scope',
		'$window',
		'guestService',
		'$location',
		function($scope, $window, guestService, $location) {

			function sviRestorani() {
				guestService.sviRestorani().then(function(response) {
					$scope.restorani = response.data;
				});
			}

			$scope.getLoggedUser = function() {
				guestService.getLoggedUser().then(function(response) {
					$scope.loggedUser = response.data;
				});
				sviRestorani();
			}

			$scope.update = function() {
				guestService.updateGuestProfile($scope.loggedUser).then(
						function(response) {
							$scope.state = undefined;
							$location.path('/gost/profil');
						});
			}

			$scope.zaRezervaciju = function(restoran) {
				guestService.find(restoran.id).then(function(response) {
					$scope.resttt = response.data;
					$location.path('/gost/restorani/rezervisi');
				})

			}

			$scope.potvrdaRezervacije = function() {
				guestService.potvrda($scope.rezervacija, $scope.resttt).then(
						function(response) {
							//sveRez();
							$location.path('/gost/rezervacije');
						});
			}

			function sveRez() {
				guestService.sveRez($scope.loggedUser).then(function(response) {
					$scope.rezervacije = response.data;
				});
			}

			$scope.sveRez2 = function() {
				guestService.sveRez($scope.loggedUser).then(function(response) {
					$scope.rezervacije = response.data;
				});
			}
			

		} ]);

