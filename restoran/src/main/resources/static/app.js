'use strict';

angular.module(
		'routerApp',
		[ 'ui.router', 'loginRegistration.services',
				'loginRegistration.controllers', 'guest.services',
				'guest.controllers', 'menadzerSistema.services',
				'menadzerSistema.controllers' ]).config(
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
			
			.state('gost.restoran', {
				url : '/gost/restoran',
				templateUrl : 'gost/restoran.html',
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
				url : '/menadzerSistema/menadzeri',
				templateUrl : 'menadzerSistema/menadzeri.html',
				controller : 'menadzerSistemaController'
			})

			.state('menadzerSistema.restorani', {
				url : '/menadzerSistema/restorani',
				templateUrl : 'menadzerSistema/restorani.html',
				controller : 'menadzerSistemaController'
			})
			
			.state('logovan/menadzerRestorana/pocetna', {
				url : '/logovan/menadzerRestorana/pocetna',
				templateUrl : 'zaposleni/menadzerRestorana.html',

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