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
							$scope.loggedUser;
							findRest($scope.loggedUser);
						})
			}

			function findRest(loggedUser) {
				menadzerRestoranaService.list($scope.loggedUser).then(
						function(response) {
							$scope.restoran = response.data;
							//$location.path('/menadzerRestorana/restorani');
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
			
			
			
		} ]);
