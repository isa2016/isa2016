var services = angular.module('loginRegistration.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('loginRegistrationService', ['$http', function($http){
	
	this.logIn = function(korisnik){
		return $http.post("/loginController/logIn",korisnik);
	}
	
	this.logOut = function(){
		return $http.get("/loginController/logOut");
	}
	
	this.save = function(gost){
		return $http.post("/loginController/registration",gost);
	}
	
	
}]);