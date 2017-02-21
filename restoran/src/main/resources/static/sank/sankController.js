var app = angular.module('sank.controllers', []);

app.controller('sankController', [
		'$scope',
		'$window',
		'sankService',
		'$location',
		function($scope, $window, sankService, $location) {

			$scope.getLoggedUser = function() {
				sankService.getLoggedUser().then(
						function(response) {
							$scope.loggedUser = response.data;
						})
			}
			
			$scope.update = function() {
				sankService.updateSankerProfile($scope.loggedUser).then(
						function(response) {
							$scope.state = undefined;
							$location.path('/sanker/profil');
						});
			}

		} ]);
