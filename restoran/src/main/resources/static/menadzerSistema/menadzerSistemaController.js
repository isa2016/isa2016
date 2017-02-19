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
							$scope.loggedManager = response.data;
						});
				findAll();
			}
			
			function findAll() {

				menadzerSistemaService.sviMR().then(function(response) {
					$scope.sviMR = response.data;
				});

				menadzerSistemaService.sviRestorani().then(function(response) {
					$scope.restorani = response.data;
				});

				menadzerSistemaService.sviMS().then(function(response) {
					$scope.MSa = response.data;
				});

			}

			$scope.dodajRest = function() {

				menadzerSistemaService.save($scope.rest).then(
						function(response) {
							$location.path('menadzerSistema');
						});
			}

			$scope.dodajMS = function() {

				menadzerSistemaService.saveMS($scope.ms).then(
						function(response) {
							$location.path('menadzerSistema');
						});
			}

		} ]);
