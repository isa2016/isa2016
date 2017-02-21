var app = angular.module('kuv.controllers', []);

app.controller('kuvController', [
		'$scope',
		'$window',
		'kuvService',
		'$location',
		function($scope, $window, kuvService, $location) {

			$scope.getLoggedUser = function() {
				kuvService.getLoggedUser().then(function(response) {
					$scope.loggedUser = response.data;
				})
			}

			$scope.update = function() {
				kuvService.updateKuvarProfile($scope.loggedUser).then(
						function(response) {
							$scope.state = undefined;
							$location.path('/kuvar/profil');
						});
			}

		} ]);
