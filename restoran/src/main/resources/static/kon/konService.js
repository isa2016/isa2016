var services = angular.module('kon.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('konService', ['$http', function($http){
	this.getLoggedUser = function() {
		return $http.get("/loginController/getLoggedUser");
	}
	
	this.updateKonobarProfile = function(konobar) {
		return $http.put("/konobar/" + konobar.id, konobar);
	}
	
	this.list = function(kon){
		return $http.get("/konobar/"+kon.id);
	}
	
	this.porudzbineZaRest = function(){
		return $http.get("/konobar/porudzbine");
	}
	this.porudzbineNaCekanju = function(){
		return $http.get("/konobar/porudzbineNaCekanju");
	}
	this.porudzbineGotove = function(){
		return $http.get("/konobar/porudzbineGotove");
	}
	
	this.unesi = function(porudzbina){
		return $http.post("/konobar/unesi/" + porudzbina.id);
	}
	
	this.potvrdiRacun = function(porudzbina){
		return $http.post("/konobar/potvrdi/" + porudzbina.id);
	}
	
	this.zavrsiRacun = function(porudzbina){
		return $http.get("/konobar/racun/" + porudzbina.id);
	}
	
	this.dodajJelo = function(jelo,por){
		return $http.get("/konobar/porudzbineJelo/"+jelo.id+"/"+por.id)
	}
	
	this.izbrisi = function(jelo,por){
		return $http.get("/konobar/izbrisiJelo/"+jelo.id+"/"+por.id)
	}
	
	this.izbrisiPice = function(pice,por){
		return $http.get("/konobar/izbrisiPice/"+pice.id+"/"+por.id)
	}
	
	this.dodajPice = function(pice,por){
		return $http.get("/konobar/porudzbinePice/"+pice.id+"/"+por.id)
	}
	
	this.napraviP = function(jelo){
		return $http.get("/konobar/napraviPJ/"+jelo.id)
	}
	
	this.napraviP2 = function(pice){
		return $http.get("/konobar/napraviPP/"+pice.id)
	}
	
	this.naruci = function(por,rest){
		return $http.post("/konobar/naruci/"+rest.id+"/"+por.id)
	}
}]);