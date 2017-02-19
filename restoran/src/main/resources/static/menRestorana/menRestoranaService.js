var services = angular.module('menRestorana.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('menRestoranaService', ['$http', function($http){
	
	this.list = function(men){
		return $http.get("/menadzer/"+men.id);
	}
	
	this.getLoggedUser = function(loggedUser){
		return $http.get("/loginController/getLoggedUser");
	}

}]);