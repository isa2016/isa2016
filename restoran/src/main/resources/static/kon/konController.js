var app = angular.module('kon.controllers', []);

app.controller('konController', [
		'$scope',
		'$window',
		'konService',
		'$location',
		function($scope, $window, konService, $location) {

			$scope.getLoggedUser = function() {
				konService.getLoggedUser().then(function(response) {
					$scope.loggedUser = response.data;
					findAll();
				})
			}

			function findAll() {
				konService.porudzbineZaRest().then(function(response) {
					$scope.porudzbine = response.data;
				});
				konService.porudzbineNaCekanju().then(function(response) {
					$scope.porudzbineCekanje = response.data;
				});
				konService.porudzbineGotove().then(function(response) {
					$scope.porudzbineGotove = response.data;
				});
				$scope.stanje = "Hrana na cekanju"
			}

			$scope.unesi = function(porudzbina) {
				konService.unesi(porudzbina).then(function(response) {
					findAll();
					$location.path('/konobar/porudzbine');
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
