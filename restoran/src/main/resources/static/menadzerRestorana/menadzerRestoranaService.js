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
	
	this.sviKonobari = function(rest){
		return $http.get("restoran/konobari/"+rest.id)
	}
	
	this.sviSankeri = function(rest){
		return $http.get("restoran/sankeri/"+rest.id)
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
	
	this.dodajKonobara = function(rest,kon){
		return $http.post("menadzer/konobar/"+rest.id,kon)
	}
	
	this.dodajSankera = function(rest,san){
		return $http.post("menadzer/sanker/"+rest.id,san)
	}
	
	this.updateRest = function(rest){
		return $http.put("/menadzer/updateRest/"+rest.id,rest);
	}
	
	this.sviPonudjaci = function(rest){
		return $http.get("/restoran/ponudjaci/"+rest.id);
	}
	
	this.sviPonudjaci2 = function(rest){
		return $http.get("/restoran/ponudjaci2/"+rest.id);
	}
	
	this.registrujPonudjaca = function(rest,pon){
		return $http.post("menadzer/ponudjac/"+rest.id,pon)
	}
}]);