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
	
	this.jelovnik = function(rest){
		return $http.get("restoran/jelovnik/"+rest.id)
	}
	
	this.pica = function(rest){
		return $http.get("restoran/pice/"+rest.id)
	}
	
	this.sviKuvari = function(rest){
		return $http.get("restoran/kuvari/"+rest.id)
	}
	
	this.dodajJelo = function(rest,jeloo){
		return $http.post("menadzer/jelo/"+rest.id,jeloo)
	}
	
	this.dodajPice = function(rest,picee){
		return $http.post("menadzer/pice/"+rest.id,picee)
	}
	
	this.dodajKuvara = function(rest,kuv){
		return $http.post("menadzer/kuvar/"+rest.id,kuv)
	}
	
	this.updateRest = function(rest){
		return $http.put("/menadzer/updateRest/"+rest.id,rest);
	}
}]);