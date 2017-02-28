var app = angular.module('guest.controllers', []);

app.controller('guestController', [
		'$scope',
		'$window',
		'guestService',
		'$location',
		function($scope, $window, guestService, $location) {

			$scope.sviRestorani = function() {
				guestService.sviRestorani().then(function(response) {
					$scope.restorani = response.data;
				});
			}
			
			function brojZahteva(){
				guestService.brojZahteva().then(function(response){
					$scope.broj = response.data;
				});
			}

			$scope.getLoggedUser = function() {
				guestService.getLoggedUser().then(function(response) {
					$scope.loggedUser = response.data;
				});
				brojZahteva();
				$scope.prikazi = "true";
				$scope.prikazi2 = "false";
				$scope.prikazi3 = "false";
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

			$scope.posete = function() {
				guestService.sveRezervacije($scope.loggedUser).then(
						function(response) {
							$scope.posete = response.data;
						});
			}

			$scope.ocena = function(rez) {
				guestService.findRez(rez.id).then(function(response) {
					$scope.porr = response.data;
					$location.path('/gost/posete/ocene');
				})
			}

			$scope.ocenaKraj = function(ocena, porr) {
				guestService.setOcena(ocena, porr.id).then(function(response) {

					$location.path('/gost/profil');
				})
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

			$scope.zahtevi = function() {
				guestService.prijatelji().then(function(response) {
					$scope.prijatelji2 = response.data;
				});
				guestService.primljeniZahtevi().then(function(response) {
					$scope.prijatelji = response.data;
				});
				guestService.poslatiZahtevi().then(function(response) {
					$scope.prijatelji3 = response.data;
				});
				
				brojZahteva();

			}

			function zahtevi2() {
				guestService.primljeniZahtevi().then(function(response) {
					$scope.prijatelji = response.data;
				});
				guestService.prijatelji().then(function(response) {
					$scope.prijatelji2 = response.data;
				});
				guestService.poslatiZahtevi().then(function(response) {
					$scope.prijatelji3 = response.data;
				});
				
				brojZahteva();
			}

			$scope.prihvati = function(prijatelj) {
				guestService.prihvati(prijatelj).then(function(response) {
					zahtevi2();
					$location.path('/gost/prijatelji');
				});
			}

			$scope.odbij = function(prijatelj) {
				guestService.odbij(prijatelj).then(function(response) {
					zahtevi2();
				});
			}

			$scope.obrisi = function(prijatelj) {
				guestService.obrisi(prijatelj).then(function(response) {
					zahtevi2();
				});
			}

			$scope.pronadjiGosta = function() {
				if (($scope.firstName !== undefined && $scope.firstName!=="") && ($scope.lastName === undefined || $scope.lastName==="")) {
					guestService.pronadjiGostaPoImenu($scope.firstName).then(
							function(response) {
								$scope.gosttt = response.data;
							});
				} else if (($scope.firstName === undefined || $scope.firstName === "") && ($scope.lastName !== undefined && $scope.lastName !== "")) {
					guestService.pronadjiGostaPoPrezimenu($scope.lastName).then(
							function(response) {
								$scope.gosttt = response.data;
							});
				} else if ($scope.firstName !== undefined && $scope.firstName !== "" && $scope.lastName !== undefined &&  $scope.lastName !== "") {
					guestService.pronadjiGosta($scope.firstName,$scope.lastName).then(function(response) {
						$scope.gosttt = response.data;
					});
				}else{
					$scope.gosttt = undefined;
				}
			}
			
			$scope.dodaj = function(gost){
				guestService.dodaj(gost).then(function(response){
					$location.path('/gost/prijatelji');
				});
			}

		} ]);
