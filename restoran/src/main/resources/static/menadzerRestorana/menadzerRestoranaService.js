var services = angular.module('menadzerRestorana.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('menadzerRestoranaService', ['$http', function($http){
	
	this.list = function(men){
		return $http.get("/menadzer/"+men.id);
	}
	
	this.getLoggedUser = function(loggedUser){
		return $http.get("/loginController/getLoggedUser");
	}

}]);