var app = angular.module('menadzerSistema.controllers', []);

app.controller('menadzerSistemaController', [
		'$scope',
		'$window',
		'menadzerSistemaService',
		'$location',
		function($scope, $window, menadzerSistemaService, $location) {

			$scope.getLoggedManager = function() {
				menadzerSistemaService.getLoggedManager().then(
						function(response) {
							findAll();
							$scope.loggedManager = response.data;
						});
			}

			function findAll() {

				menadzerSistemaService.sviMR().then(function(response) {
					$scope.sviMR = response.data;
				});
				
				menadzerSistemaService.sviMR2().then(function(response) {
					$scope.sviMR2 = response.data;
				});

				menadzerSistemaService.sviRestorani().then(function(response) {
					$scope.restorani = response.data;
				});

				menadzerSistemaService.sviMS().then(function(response) {
					$scope.MSa = response.data;
				});

			}

			$scope.dodajRest = function() {

				menadzerSistemaService.save($scope.rest,$scope.manager).then(
						function(response) {
							findAll();
							$location.path('/menadzerSistema/restorani');
						});
				
			}

			$scope.dodajMR = function() {

				menadzerSistemaService.saveMR($scope.menadzerR).then(
						function(response) {
							findAll();
							$location.path('/menadzerSistema/restorani');
						});
				
			}
			
			$scope.dodajMS = function() {

				menadzerSistemaService.saveMS($scope.ms).then(
						function(response) {
							findAll();
							$location.path('/menadzerSistema/menadzeri');
						});
			}
		} ]);
