var app = angular.module('kon.controllers', []);

app.controller('konController', [
		'$scope',
		'$window',
		'konService',
		'$location',
		function($scope, $window, konService, $location) {

			$scope.getLoggedUser = function() {
				konService.getLoggedUser().then(
						function(response) {
							$scope.loggedUser = response.data;
						})
			}
			
			$scope.porudzbineZaRest = function() {
				konService.porudzbineZaRest().then(function(response) {
					$scope.porudzbine = response.data;
				});
			}
			
			$scope.update = function() {
				konService.updateKonobarProfile($scope.loggedUser).then(
						function(response) {
							$scope.state = undefined;
							$location.path('/konobar/profil');
						});
			}

		} ]);
