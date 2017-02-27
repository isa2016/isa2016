var app = angular.module('guest.controllers', []);

app.controller('guestController', [
		'$scope',
		'$window',
		'guestService',
		'$location',
		function($scope, $window, guestService, $location) {

			function sviRestorani() {
				guestService.sviRestorani().then(function(response) {
					$scope.restorani = response.data;
				});
			}

			$scope.getLoggedUser = function() {
				guestService.getLoggedUser().then(function(response) {
					$scope.loggedUser = response.data;
				});
				$scope.prikazi = "true";
				$scope.prikazi2 = "false";
				$scope.prikazi3 = "false";
				sviRestorani();
			}

			$scope.update = function() {
				guestService.updateGuestProfile($scope.loggedUser).then(
						function(response) {
							$scope.state = undefined;
							$location.path('/gost/profil');
						});
			}

			$scope.zaRezervaciju = function(restoran) {
				guestService.find(restoran.id).then(function(response) {
					$scope.resttt = response.data;
					$scope.prikazi = "false";
					$scope.prikazi2 = "true";
					$scope.prikazi3 = "false";
					// $location.path('/gost/restorani/rezervisi');
				})

			}

			$scope.detalji = function(restoran) {
				guestService.find(restoran.id).then(function(response) {
					$scope.resttt = response.data;
					$location.path('/gost/restorani/detalji');
				})
			}

			$scope.izbrisi = function(jelo, porudzbina) {
				guestService.izbrisi(jelo, porudzbina).then(function(response) {
					$scope.porudzbina = response.data;
				});
			}

			$scope.izbrisiPice = function(pice, porudzbina) {
				guestService.izbrisiPice(pice, porudzbina).then(
						function(response) {
							$scope.porudzbina = response.data;
						});
			}

			$scope.detaljiRez = function(rez) {
				guestService.findRez(rez.id).then(function(response) {
					$scope.porr = response.data;
					$location.path('/gost/rezervacije/detalji');
				})
			}

			function sveRez() {
				guestService.sveRez($scope.loggedUser).then(function(response) {
					$scope.rezervacije = response.data;
				});
			}

			$scope.sveRez2 = function() {
				guestService.sveRez($scope.loggedUser).then(function(response) {
					$scope.rezervacije = response.data;
				});
			}

			$scope.potvrdaRezervacije = function() {
				$scope.prikazi = "false";
				$scope.prikazi2 = "false";
				$scope.prikazi3 = "true";
			}

			$scope.dodajJelo = function(jelo) {
				if ($scope.porudzbina !== undefined) {
					guestService.dodajJelo(jelo, $scope.porudzbina).then(
							function(response) {
								$scope.porudzbina = response.data;
							});
				} else {
					guestService.napraviP(jelo).then(function(response) {
						$scope.porudzbina = response.data;
					});
				}

			}

			$scope.dodajPice = function(pice) {
				if ($scope.porudzbina !== undefined) {
					guestService.dodajPice(pice, $scope.porudzbina).then(
							function(response) {
								$scope.porudzbina = response.data;
							});
				} else {
					guestService.napraviP2(pice).then(function(response) {
						$scope.porudzbina = response.data;
					});
				}

			}

			$scope.rezervisi = function(porudzbina) {
				if (porudzbina !== undefined) {
					guestService.potvrda(porudzbina, $scope.rezervacija,
							$scope.resttt).then(function(response) {
						$location.path('/gost/rezervacije');
					});
				} else {
					guestService.potvrda2($scope.rezervacija, $scope.resttt)
							.then(function(response) {
								$location.path('/gost/rezervacije');
							});
				}
			}

		} ]);
