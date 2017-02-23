'use strict';

angular.module(
		'routerApp',
		[ 'ui.router', 'loginRegistration.services',
				'loginRegistration.controllers', 'guest.services',
				'guest.controllers', 'menadzerSistema.services',
				'menadzerSistema.controllers', 'menadzerRestorana.services',
				'menadzerRestorana.controllers', 'kon.services',
				'kon.controllers', 'sank.services', 'sank.controllers', 'kuv.services', 'kuv.controllers','ponudjac.controllers','ponudjac.services' ])
		.config(
				function($stateProvider, $urlRouterProvider) {

					$urlRouterProvider.otherwise('/login');

					$stateProvider

					.state('login', {
						url : '/login',
						templateUrl : 'loginRegistration/login.html',
						controller : 'loginRegistrationController'
					})

					.state('firstLogin', {
						url : '/firstLogin',
						templateUrl : 'loginRegistration/prviLogin.html',
						controller : 'loginRegistrationController'
					})

					.state('registration', {
						url : '/registration',
						templateUrl : 'loginRegistration/registration.html',
						controller : 'loginRegistrationController'
					})

					.state('gost', {
						url : '/gost',
						templateUrl : 'gost/navBar.html',
						controller : 'guestController'
					})

					.state('gost.profil', {
						url : '/profil',
						templateUrl : 'gost/profil.html',
						controller : 'guestController'
					})

					.state('gost.izmena', {
						url : '/izmena',
						templateUrl : 'gost/izmena.html',
						controller : 'guestController'
					})

					.state('gost.prijatelji', {
						url : '/prijatelji',
						templateUrl : 'gost/prijatelji.html',
						controller : 'guestController'
					})
					
					

					.state('gost.restorani', {
						url : '/restorani',
						templateUrl : 'gost/restorani.html',
						controller : 'guestController'
					})
					
					.state('gost.restorani.detalji', {
						url : '/detalji',
						templateUrl : 'gost/detalji.html',
						controller : 'guestController'
					})

					.state('gost.restorani.rezervisi', {
						url : '/rezervisi',
						templateUrl : 'gost/rezervisi.html',
						controller : 'guestController'
					})

					.state('gost.rezervacije', {
						url : '/rezervacije',
						templateUrl : 'gost/rezervacije.html',
						controller : 'guestController'
					})
					
					.state('gost.rezervacije.detalji', {
						url : '/detalji',
						templateUrl : 'gost/detaljiRez.html',
						controller : 'guestController'
					})
					
					.state('gost.restorani.rezervisi2', {
						url : '/rezervisi2',
						templateUrl : 'gost/rezervisi2.html',
						controller : 'guestController'
					})

					.state('konobar', {
						url : '/konobar',
						templateUrl : 'kon/navBarKonobar.html',
						controller : 'konController'
					})

					.state('konobar.raspored', {
						url : '/raspored',
						templateUrl : 'kon/rasporedKonobar.html',
						controller : 'konController'
					})

					.state('konobar.kalendar', {
						url : '/kalendar',
						templateUrl : 'kon/kalendarKonobar.html',
						controller : 'konController'
					})

					.state('konobar.porudzbine', {
						url : '/porudzbine',
						templateUrl : 'kon/porudzbineKonobar.html',
						controller : 'konController'
					})

					.state('konobar.profil', {
						url : '/profil',
						templateUrl : 'kon/profilKonobar.html',
						controller : 'konController'
					})

					.state('konobar.izmena', {
						url : '/izmena',
						templateUrl : 'kon/izmenaKonobar.html',
						controller : 'konController'
					})

					.state('kuvar', {
						url : '/kuvar',
						templateUrl : 'kuv/navBarKuvar.html',
						controller : 'kuvController'
					})

					.state('kuvar.raspored', {
						url : '/raspored',
						templateUrl : 'kuv/rasporedKuvar.html',
						controller : 'kuvController'
					})

					.state('kuvar.kalendar', {
						url : '/kalendar',
						templateUrl : 'kuv/kalendarKuvar.html',
						controller : 'kuvController'
					})

					.state('kuvar.porudzbine', {
						url : '/porudzbine',
						templateUrl : 'kuv/porudzbineKuvar.html',
						controller : 'kuvController'
					})

					.state('kuvar.profil', {
						url : '/profil',
						templateUrl : 'kuv/profilKuvar.html',
						controller : 'kuvController'
					})

					.state('kuvar.izmena', {
						url : '/izmena',
						templateUrl : 'kuv/izmenaKuvar.html',
						controller : 'kuvController'
					})

					.state('sanker', {
						url : '/sanker',
						templateUrl : 'sank/navBarSanker.html',
						controller : 'sankController'
					})

					.state('sanker.raspored', {
						url : '/raspored',
						templateUrl : 'sank/rasporedSanker.html',
						controller : 'sankController'
					})

					.state('sanker.kalendar', {
						url : '/kalendar',
						templateUrl : 'sank/kalendarSanker.html',
						controller : 'sankController'
					})

					.state('sanker.porudzbine', {
						url : '/porudzbine',
						templateUrl : 'sank/porudzbineSanker.html',
						controller : 'sankController'
					})

					.state('sanker.profil', {
						url : '/profil',
						templateUrl : 'sank/profilSanker.html',
						controller : 'sankController'
					})

					.state('sanker.izmena', {
						url : '/izmena',
						templateUrl : 'sank/izmenaSanker.html',
						controller : 'sankController'
					})

					
					.state('menadzerSistema', {
						url : '/menadzerSistema',
						templateUrl : 'menadzerSistema/navBarMS.html',
						controller : 'menadzerSistemaController'
					})

					.state('menadzerSistema.menadzeri', {
						url : '/menadzeri',
						templateUrl : 'menadzerSistema/menadzeri.html',
						controller : 'menadzerSistemaController'
					})

					.state('menadzerSistema.restorani', {
						url : '/restorani',
						templateUrl : 'menadzerSistema/restorani.html',
						controller : 'menadzerSistemaController'
					})

					.state('menadzerSistema.noviMenadzerRestorana', {
						url : '/noviMenadzerRestorana',
						templateUrl : 'menadzerSistema/noviMR.html',
						controller : 'menadzerSistemaController'
					})

					.state('login/logOut', {
						url : '/logout',
						// templateUrl : 'loginRegistration/login.html',
						resolve : {
							promiseObj : function($http, $location) {
								$location.path('login');
								return $http.get("/loginController/logOut");
							}
						}
					})
                    
						.state('ponudjac', {
						url : '/ponudjac',
						templateUrl : 'ponudjac/ponudjac.html',
						controller : 'ponudjacController'
					})
			
					.state('ponudjac.profili', {
						url : '/profili',
						templateUrl : 'ponudjac/profilPonudjac.html',
						controller : 'ponudjacController'
					})
			
			
					.state('ponudjac.izmene', {
						url : '/izmene',
						templateUrl : 'ponudjac/izmenaPonudjac.html',
						controller : 'ponudjacController'
					})
					
					
					
					.state('menadzerRestorana', {
						url : '/menadzerRestorana',
						templateUrl : 'menadzerRestorana/navBarMR.html',
						controller : 'menadzerRestoranaController'
					})

					.state('menadzerRestorana.restorani', {
						url : '/restorani',
						templateUrl : 'menadzerRestorana/listaRestorana.html',
						controller : 'menadzerRestoranaController'
					})

					.state('menadzerRestorana.jelovnik', {
						url : '/jelovnik',
						templateUrl : 'menadzerRestorana/jelovnik.html',
						controller : 'menadzerRestoranaController'
					})

					.state('menadzerRestorana.kartapica', {
						url : '/kartapica',
						templateUrl : 'menadzerRestorana/kartapica.html',
						controller : 'menadzerRestoranaController'
					})

					.state('menadzerRestorana.kuvari', {
						url : '/kuvari',
						templateUrl : 'menadzerRestorana/kuvari.html',
						controller : 'menadzerRestoranaController'
					})

					.state('menadzerRestorana.konobari', {
						url : '/konobari',
						templateUrl : 'menadzerRestorana/konobari.html',
						controller : 'menadzerRestoranaController'
					})

					.state('menadzerRestorana.sankeri', {
						url : '/sankeri',
						templateUrl : 'menadzerRestorana/sankeri.html',
						controller : 'menadzerRestoranaController'
					})

					.state('menadzerRestorana.izmeni', {
						url : '/izmeni',
						templateUrl : 'menadzerRestorana/izmeniRestoran.html',
						controller : 'menadzerRestoranaController'
					})

					.state(
							'activation/:reg',
							{
								url : '/activation/:reg',
								templateUrl : 'gost/aktivaciona.html',
								resolve : {
									promiseObj : function($http, $stateParams) {
										return $http.put("/guest/activate/"
												+ $stateParams.reg);
									}
								}
							})

				});