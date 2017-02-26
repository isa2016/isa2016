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
					findAll();
				})
			}

			$scope.update = function() {
				kuvService.updateKuvarProfile($scope.loggedUser).then(
						function(response) {
							$scope.state = undefined;
							$location.path('/kuvar/profil');
						});
			}

			function findAll() {
				kuvService.porudzbineZaKuvara().then(
						function(response) {
							$scope.porudzbine = response.data;
						});
			
				kuvService.porudzbinePriprema().then(
						function(response) {
							$scope.porudzbinePriprema = response.data;
						});
			}
			
			$scope.prihvati = function(porudzbina) {
				kuvService.prihvati(porudzbina).then(function(response) {
					findAll();
					$location.path('/kuvar/porudzbine');
					
				});
			}
			
			$scope.zavrsi = function(porudzbina) {
				kuvService.zavrsi(porudzbina).then(function(response) {
					findAll();
					$location.path('/kuvar/porudzbine');
					
				});
			}
			
		} ]);
