'use strict';

angular.module(
		'routerApp',
		[ 'ui.router', 'loginRegistration.services',
				'loginRegistration.controllers', 'guest.services',
				'guest.controllers', 'menadzerSistema.services',
				'menadzerSistema.controllers','menadzerRestorana.services','menadzerRestorana.controllers' ]).config(
		function($stateProvider, $urlRouterProvider) {

			$urlRouterProvider.otherwise('/login');

			$stateProvider

			.state('login', {
				url : '/login',
				templateUrl : 'loginRegistration/login.html',
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
				url : '/gost/prijatelji',
				templateUrl : 'gost/prijatelji.html',
				controller : 'guestController'
			})

			.state('gost.restorani', {
				url : '/restorani',
				templateUrl : 'gost/restorani.html',
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


			.state('logovan/kuvar/pocetna', {
				url : '/logovan/kuvar/pocetna',
				templateUrl : 'zaposleni/kuvar.html'
			})

			.state('logovan/konobar/pocetna', {
				url : '/logovan/konobar/pocetna',
				templateUrl : 'zaposleni/konobar.html'
			})

			.state('logovan/ponudjac/pocetna', {
				url : '/logovan/ponudjac/pocetna',
				templateUrl : 'zaposleni/ponudjac.html'
			})

			.state('logovan/sanker/pocetna', {
				url : '/logovan/sanker/pocetna',
				templateUrl : 'zaposleni/sanker.html'
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