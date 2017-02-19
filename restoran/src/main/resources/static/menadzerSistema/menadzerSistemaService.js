var services = angular.module('menadzerSistema.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('menadzerSistemaService', ['$http', function($http){
	
	this.save = function(rest){
		return $http.post("/menadzerSistemaController/addRest",rest);
	}
	
	this.saveMS = function(rest){
		return $http.post("/menadzerSistemaController/addMS",rest);
	}
	
	this.sviRestorani = function(){
		return $http.get("/guest/restorani");
	}
	
	this.sviMS = function(){
		return $http.get("/menadzerSistemaController/menadzeri");
	}
	
	
}]);