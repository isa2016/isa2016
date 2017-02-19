var app = angular.module('loginRegistration.controllers', []);
 
var firstLoginId = null;

app.controller('loginRegistrationController', ['$scope','loginRegistrationService', '$location',
  	function ($scope, loginRegistrationService, $location) {
	
		$scope.submitLogin = function () {            
			loginRegistrationService.logIn($scope.korisnik).then(
				function (response) {
                    $scope.state = undefined;
                    
                    if(response.data === "boss")
                    	$location.path('loggedIn/bossManager/list');
                    else if(response.data === "menadzerSistema")
                    	$location.path('menadzerSistema');
                    else if(response.data === "menadzerRestorana")
                    	$location.path('logovan/menadzerRestorana/pocetna');
                    else if(response.data === "gost")                 	
                    	$location.path('gost');
                    else if(response.data === "ponudjac")
                    	$location.path('logovan/ponudjac/pocetna');
                    else if(response.data === "sanker")
                    	$location.path('logovan/sanker/pocetna');
                    else if(response.data === "kuvar")
                    	$location.path('logovan/kuvar/pocetna');
                    else if(response.data === "konobar")
                    	$location.path('logovan/konobar/pocetna');
                    else {
                    	firstLoginId = response.data;
                    	$location.path('firstLogin');
                    }
				},
                function (response) {
                    alert("Ne postoji korisnik sa tim parametrima.");
                }
			);
		}
		
		$scope.submitRegistration = function () {  
			if($("#pass").val() != $("#passRepeat").val()){
				alert("Password does not match the confirm password");
				return;
			}
			
			loginRegistrationService.save($scope.gost).then(
				function (response) {
                    $location.path('login');
                }
            ); 	
		};
		
		
		$scope.submitLogout = function() {
			loginRegistrationService.logOut().then(
				function (response) {
					$location.path('login/logout');
	            }		
			)
		}	
		
}]);