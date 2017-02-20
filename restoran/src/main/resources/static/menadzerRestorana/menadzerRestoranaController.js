var app = angular.module('menadzerRestorana.controllers', []);

app.controller('menadzerRestoranaController', [
		'$scope',
		'menadzerRestoranaService',
		'$location',
		function($scope, menadzerRestoranaService, $location) {

			$scope.getLoggedUser = function() {
				menadzerRestoranaService.getLoggedUser().then(
						function(response) {
							$scope.loggedUser = response.data;
							findRest($scope.loggedUser);
						})
			}

			function findRest(loggedUser) {
				menadzerRestoranaService.list($scope.loggedUser).then(
						function(response) {
							$scope.restoran = response.data;
						});
			}
			
			$scope.jelovnik = function() {
				menadzerRestoranaService.jelovnik($scope.restoran).then(
						function(response) {
							$scope.jela = response.data;
						});
			}
			
			$scope.pica = function() {
				menadzerRestoranaService.pica($scope.restoran).then(
						function(response) {
							$scope.picaa = response.data;
						});
			}
			
			function jelovnik2(){
				menadzerRestoranaService.jelovnik($scope.restoran).then(
						function(response) {
							$scope.jela = response.data;
						});
			}
			
			function pica2(){
				menadzerRestoranaService.pica($scope.restoran).then(
						function(response) {
							$scope.picaa = response.data;
						});
			}
			
			$scope.zaIzmenu = function(restoran){
				menadzerRestoranaService.find(restoran.id).then(
						function(response){
							$scope.prikazi = "true";
							$scope.resttt = response.data;
							$location.path('/menadzerRestorana/restorani');
						}
						)
			
			}
			
			$scope.update = function() {
				menadzerRestoranaService.updateRest($scope.resttt).then(
					function (response) {
						$scope.prikazi = "false";
	                    $scope.state = undefined;
	                    findRest($scope.loggedUser);
	                    $location.path('/menadzerRestorana/restorani');
					}
				);
			}
			
			
			$scope.dodajJelo = function() {
				menadzerRestoranaService.dodajJelo($scope.restoran,$scope.jeloo).then(
					function (response) {
						jelovnik2();
	                    $location.path('/menadzerRestorana/jelovnik');
					}
				);
			}
			
			$scope.dodajPice = function() {
				menadzerRestoranaService.dodajPice($scope.restoran,$scope.picee).then(
					function (response) {
						pica2();
	                    $location.path('/menadzerRestorana/kartapica');
					}
				);
			}
			
			
			
		} ]);
