'use strict';

angular.module(
		'routerApp',
		[ 'ui.router', 'loginRegistration.services',
				'loginRegistration.controllers', 'guest.services',
				'guest.controllers', 'menadzerSistema.services',
				'menadzerSistema.controllers', 'menadzerRestorana.services',
				'menadzerRestorana.controllers', 'kuvar.services', 'kuvar.controllers', 'kon.services', 'kon.controllers']).config(
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

			.state('konobar', {
				url : '/konobar',
				templateUrl : 'kon/navBar.html',
				controller : 'konController'
			})

			.state('konobar.raspored', {
				url : '/raspored',
				templateUrl : 'kon/raspored.html',
				controller : 'konController'
			})

			.state('konobar.kalendar', {
				url : '/kalendar',
				templateUrl : 'kon/kalendar.html',
				controller : 'konController'
			})

			.state('konobar.porudzbine', {
				url : '/porudzbine',
				templateUrl : 'kon/porudzbine.html',
				controller : 'konController'
			})

			.state('konobar.profil', {
				url : '/profil',
				templateUrl : 'kon/profil.html',
				controller : 'konController'
			})

			.state('konobar.izmena', {
				url : '/izmena',
				templateUrl : 'kon/izmena.html',
				controller : 'konController'
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