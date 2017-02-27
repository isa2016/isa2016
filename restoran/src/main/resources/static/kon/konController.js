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
					findRest($scope.loggedUser);
					$scope.sakrij1 = false;
					$scope.sakrij2 = true;
				})
			}

			function findRest(loggedUser) {
				konService.list($scope.loggedUser).then(function(response) {
					$scope.resttt = response.data;
				});
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
			}
			
			$scope.findAll2 = function(){
				konService.porudzbineZaRest().then(function(response) {
					$scope.porudzbine = response.data;
				});
				konService.porudzbineNaCekanju().then(function(response) {
					$scope.porudzbineCekanje = response.data;
				});
				konService.porudzbineGotove().then(function(response) {
					$scope.porudzbineGotove = response.data;
				});
			}

			$scope.unesi = function(porudzbina) {
				konService.unesi(porudzbina).then(function(response) {
					findAll();
					$location.path('/konobar/porudzbine');
				});
			}

			$scope.zavrsiRacun = function(porudzbina) {
				konService.zavrsiRacun(porudzbina).then(function(response) {
					$scope.sakrij1 = true;
					$scope.sakrij2 = false;
					$scope.zaRacun = response.data;
					$scope.total = 0;
					for (var i = 0; i < $scope.zaRacun.hrana.length; i++)
						$scope.total += $scope.zaRacun.hrana[i].cena;
					for (var j = 0; j < $scope.zaRacun.pice.length; j++)
						$scope.total += $scope.zaRacun.pice[j].cena;
				});
			}

			$scope.potvrdiRacun = function(zaRacun) {
				konService.potvrdiRacun(zaRacun).then(function(response) {
					findAll();
					$scope.sakrij1 = false;
					$scope.sakrij2 = true;

				});
			}

			$scope.update = function() {
				konService.updateKonobarProfile($scope.loggedUser).then(
						function(response) {
							$scope.state = undefined;
							$location.path('/konobar/profil');
						});
			}

			$scope.izbrisi = function(jelo, porudzbina) {
				konService.izbrisi(jelo, porudzbina).then(function(response) {
					$scope.porudzbina = response.data;
				});
			}

			$scope.izbrisiPice = function(pice, porudzbina) {
				konService.izbrisiPice(pice, porudzbina).then(
						function(response) {
							$scope.porudzbina = response.data;
						});
			}

			$scope.dodajJelo = function(jelo) {
				if ($scope.porudzbina !== undefined) {
					konService.dodajJelo(jelo, $scope.porudzbina).then(
							function(response) {
								$scope.porudzbina = response.data;
							});
				} else {
					konService.napraviP(jelo).then(function(response) {
						$scope.porudzbina = response.data;
					});
				}

			}

			$scope.dodajPice = function(pice) {
				if ($scope.porudzbina !== undefined) {
					konService.dodajPice(pice, $scope.porudzbina).then(
							function(response) {
								$scope.porudzbina = response.data;
							});
				} else {
					konService.napraviP2(pice).then(function(response) {
						$scope.porudzbina = response.data;
					});
				}

			}

			$scope.naruci = function(porudzbina) {
				konService.naruci(porudzbina, $scope.resttt).then(
						function(response) {
							findAll();
							$location.path('/konobar/porudzbine');
						});

			}

		} ]);
