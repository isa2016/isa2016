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

			.state('logovan', {
				url : '/logovan',
				templateUrl : 'guestt/navBar.html',
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

			.state('logovan/menadzerSistema/pocetna', {
				url : '/logovan/menadzerSistema/pocetna',
				templateUrl : 'menadzerSistema/navBarMS.html',
				controller : 'menadzerSistemaController'
			})

			.state('logovan/menadzerRestorana/pocetna', {
				url : '/logovan/menadzerRestorana/pocetna',
				templateUrl : 'zaposleni/menadzerRestorana.html',

			})

			.state('gost/pocetna', {
				url : '/gost/pocetna',
				templateUrl : 'guestt/guestHome.html',
				controller : 'guestController'
			})

			.state('gost/izmena', {
				url : '/gost/izmena',
				templateUrl : 'guestt/izmena.html',
				controller : 'guestController'
			})

			.state('gost/prijatelji', {
				url : '/gost/prijatelji',
				templateUrl : 'guestt/prijatelji.html',
				controller : 'guestController'
			})

			.state('gost/restorani', {
				url : '/gost/restorani',
				templateUrl : 'guestt/restorani.html',
				controller : 'guestController'
			})
			
			.state('gost/restoran', {
				url : '/gost/restoran',
				templateUrl : 'guestt/restoran.html',
				controller : 'guestController'
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
						templateUrl : 'guestt/aktivaciona.html',
						resolve : {
							promiseObj : function($http, $stateParams) {
								return $http.put("/guest/activate/"
										+ $stateParams.reg);
							}
						}
					})
		});