var app = angular.module('konobar.controllers', []);

app.controller('konobarController', [
		'$scope',
		'$window',
		'konobarService',
		'$location',
		function($scope, $window, konobarService, $location) {

			$scope.getLoggedUser = function() {
				konobarService.getLoggedUser().then(
						function(response) {
							$scope.loggedUser = response.data;
						});
			}
			
			$scope.update = function() {
				konobarService.updateKonobarProfile($scope.loggedUser).then(
						function(response) {
							$scope.state = undefined;
							$location.path('/konobar/profil');
						});
			
		} ]);