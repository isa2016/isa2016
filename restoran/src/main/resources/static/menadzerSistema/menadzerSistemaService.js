var services = angular.module('menadzerSistema.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('menadzerSistemaService', ['$http', function($http){
	
	this.getLoggedManager = function(){
		return $http.get("/loginController/getLoggedUser");
	}
	
	this.sviMR = function(){
		return $http.get("/menadzerSistemaController/sviMR");
	}
	
	this.sviMR2 = function(){
		return $http.get("/menadzerSistemaController/nezaposleni");
	}
	
	this.save = function(rest,mr){
		return $http.post("/menadzerSistemaController/addRest/"+mr.id,rest);
	}
	
	this.saveMS = function(rest){
		return $http.post("/menadzerSistemaController/addMS",rest);
	}
	
	this.saveMR = function(rest){
		return $http.post("/menadzerSistemaController/addMR",rest);
	}
	
	this.sviRestorani = function(){
		return $http.get("/guest/restorani");
	}
	
	this.sviMS = function(){
		return $http.get("/menadzerSistemaController/sviMS");
	}
	
	
}]);