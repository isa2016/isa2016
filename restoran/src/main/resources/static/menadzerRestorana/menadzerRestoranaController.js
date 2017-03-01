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
							svePonude($scope.loggedUser);
						})
			}

			function findRest(loggedUser) {
				menadzerRestoranaService.list($scope.loggedUser).then(
						function(response) {
							$scope.restoran = response.data;
						});
			}

			$scope.pronadjiJelo = function() {
				menadzerRestoranaService
						.jelo($scope.restoran, $scope.nazivJela).then(
								function(response) {
									$scope.j = response.data;
								});
			}

			$scope.pronadjiPice = function() {
				menadzerRestoranaService
						.pice($scope.restoran, $scope.nazivPica).then(
								function(response) {
									$scope.p = response.data;
								});
			}

			$scope.dodajNamirnicu = function() {
				menadzerRestoranaService.dodajNam($scope.restoran, jelo).then(
						function(response) {
							$scope.o = response.data;
						});
			}

			$scope.sviPonudjaci = function() {
				menadzerRestoranaService.sviPonudjaci($scope.restoran).then(
						function(response) {
							$scope.ponudjaci = response.data;
						});
				menadzerRestoranaService.sviPonudjaci2($scope.restoran).then(
						function(response) {
							$scope.ponudjaci2 = response.data;
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

			$scope.sviKuvari = function() {
				menadzerRestoranaService.sviKuvari($scope.restoran).then(
						function(response) {
							$scope.kuvari = response.data;
						});
			}

			$scope.sviKonobari = function() {
				menadzerRestoranaService.sviKonobari($scope.restoran).then(
						function(response) {
							$scope.konobari = response.data;
						});
			}

			$scope.sviSankeri = function() {
				menadzerRestoranaService.sviSankeri($scope.restoran).then(
						function(response) {
							$scope.sankeri = response.data;
						});
			}

			function jelovnik2() {
				menadzerRestoranaService.jelovnik($scope.restoran).then(
						function(response) {
							$scope.jela = response.data;
						});
			}

			function pica2() {
				menadzerRestoranaService.pica($scope.restoran).then(
						function(response) {
							$scope.picaa = response.data;
						});
			}

			function sviKuvari2() {
				menadzerRestoranaService.sviKuvari($scope.restoran).then(
						function(response) {
							$scope.kuvari = response.data;
						});
			}

			function sviKonobari2() {
				menadzerRestoranaService.sviKonobari($scope.restoran).then(
						function(response) {
							$scope.konobari = response.data;
						});
			}

			function sviSankeri2() {
				menadzerRestoranaService.sviSankeri($scope.restoran).then(
						function(response) {
							$scope.sankeri = response.data;
						});
			}

			function sviPonudjaci2() {
				menadzerRestoranaService.sviPonudjaci($scope.restoran).then(
						function(response) {
							$scope.ponudjaci = response.data;
						});
				menadzerRestoranaService.sviPonudjaci2($scope.restoran).then(
						function(response) {
							$scope.ponudjaci2 = response.data;
						});
			}

			$scope.zaIzmenu = function(restoran) {
				menadzerRestoranaService.find(restoran.id).then(
						function(response) {
							$scope.prikazi = "true";
							$scope.resttt = response.data;
							$location.path('/menadzerRestorana/restorani');
						})

			}

			$scope.update = function() {
				menadzerRestoranaService.updateRest($scope.resttt).then(
						function(response) {
							$scope.prikazi = "false";
							$scope.state = undefined;
							findRest($scope.loggedUser);
							$location.path('/menadzerRestorana/restorani');
						});
			}

			$scope.dodajJelo = function() {
				menadzerRestoranaService.dodajJelo($scope.restoran,
						$scope.jeloo).then(function(response) {
					jelovnik2();
					$location.path('/menadzerRestorana/jelovnik');
				});
			}

			$scope.dodajPice = function() {
				menadzerRestoranaService.dodajPice($scope.restoran,
						$scope.picee).then(function(response) {
					pica2();
					$location.path('/menadzerRestorana/kartapica');
				});
			}

			$scope.dodajKuvara = function() {
				menadzerRestoranaService.dodajKuvara($scope.restoran,
						$scope.kuv).then(function(response) {
					sviKuvari2();
					$location.path('/menadzerRestorana/kuvari');
				});
			}

			$scope.dodajJeloUObjavu = function(jelo) {
				if ($scope.objava !== undefined) {
					menadzerRestoranaService.dodajJeloObjava(jelo,
							$scope.objava).then(function(response) {
						$scope.objava = response.data;
					});
				} else {
					menadzerRestoranaService.napraviO(jelo).then(
							function(response) {
								$scope.objava = response.data;
							});
				}

			}

			$scope.dodajPiceUObjavu = function(pice) {
				if ($scope.objava !== undefined) {
					menadzerRestoranaService.dodajPiceObjava(pice,
							$scope.objava).then(function(response) {
						$scope.objava = response.data;
					});
				} else {
					menadzerRestoranaService.napraviOb(pice).then(
							function(response) {
								$scope.objava = response.data;
							});
				}

			}

			$scope.dodajKonobara = function() {
				menadzerRestoranaService.dodajKonobara($scope.restoran,
						$scope.kon).then(function(response) {
					sviKonobari2();
					$location.path('/menadzerRestorana/konobari');
				});
			}

			$scope.dodajSankera = function() {
				menadzerRestoranaService.dodajSankera($scope.restoran,
						$scope.san).then(function(response) {
					sviSankeri2();
					$location.path('/menadzerRestorana/sankeri');
				});
			}

			$scope.registrujPonudjaca = function(pon) {
				menadzerRestoranaService.registrujPonudjaca($scope.restoran,
						pon).then(function(response) {
					sviPonudjaci2();
					$location.path('/menadzerRestorana/ponudjaci');
				});
			}

			$scope.posaljiPonudjacima = function() {
				menadzerRestoranaService.posaljiObjavu($scope.objava,
						$scope.pocetak, $scope.kraj, $scope.restoran).then(
						function(response) {

							$location.path('/menadzerRestorana');
						});
			}

			$scope.meni = function() {
				menadzerRestoranaService.jelovnik($scope.restoran).then(
						function(response) {
							$scope.jela = response.data;
						});
				menadzerRestoranaService.pica($scope.restoran).then(
						function(response) {
							$scope.picaa = response.data;
						});
			}

			$scope.ucitajStolove = function() {
				menadzerRestoranaService.ucitajStolove($scope.restoran).then(
						function(response) {
							$scope.stolovi = response.data;
						});
			}
			$scope.prihvati = function(ponuda) {
				menadzerRestoranaService.prihvatiPonudu(ponuda,
						$scope.loggedUser).then(function(response) {
					svePonude($scope.loggedUser);
					$location.path('/menadzerRestorana/ponude');
				});

			}

			function svePonude(loggedUser) {
				menadzerRestoranaService.svePonudee($scope.loggedUser).then(
						function(response) {
							$scope.lista = response.data;

						});

			}

			$scope.totalIncome = function(){
				menadzerRestoranaService.totalIncome($scope.oddo,$scope.restoran).then(
						function (response) {
							$scope.total = response.data;
						}
					);
			}
			
		} ]);
