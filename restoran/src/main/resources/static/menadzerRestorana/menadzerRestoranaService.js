var services = angular.module('menadzerRestorana.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('menadzerRestoranaService', ['$http', function($http){
	
	this.list = function(men){
		return $http.get("/menadzer/"+men.id);
	}
	
	this.getLoggedUser = function(){
		return $http.get("/loginController/getLoggedUser");
	}

	this.find = function(id){
		return $http.get("/restoran/"+id)
	}
	
	this.updateRest = function(rest){
		return $http.put("/menadzer/updateRest/"+rest.id,rest);
	}
}]);