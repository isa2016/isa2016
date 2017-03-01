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
	
	this.jelo = function(restoran,jelo){
		return $http.get("/menadzer/nadji/"+restoran.id+"/"+jelo)
	}
	
	this.pice = function(restoran,pice){
		return $http.get("/menadzer/pronadji/"+restoran.id+"/"+pice)
	}
	
	this.dodajNam = function(restoran,jelo){
		return $http.get("/restoran/"+restoran.id+"/"+jelo.id)
	}
	
	this.dodajRejon = function(rejon,restoran){
		return $http.post("/menadzer/rejon/"+rejon+"/"+restoran.id)
	}
	
	this.napraviO = function(jelo){
		return $http.get("/restoran/napraviO/"+jelo.id)
	}
	
	this.napraviOb = function(pice){
		return $http.get("/restoran/napraviOb/"+pice.id)
	}
	
	this.dodajJeloObjava = function(jelo,por){
		return $http.get("/restoran/objavaJelo/"+jelo.id+"/"+por.id)
	}
	
	this.dodajPiceObjava = function(pice,por){
		return $http.get("/restoran/objavaPice/"+pice.id+"/"+por.id)
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
	
	this.ucitajStolove = function(rest){
		return $http.get("restoran/stolovi/"+rest.id)
	}
	this.posaljiObjavu = function(objava,pocetak,kraj,resto){
		return $http.post("/restoran/restoranii/objavaPosalji/"+objava.id+"/"+pocetak+"/"+kraj+"/"+resto.id)
	}
	
	this.prihvatiPonudu = function(porudzbina,menadzer){
		return $http.post("/menadzer/prihvatiPonudu/"+porudzbina.id+"/"+menadzer.id+"/"+porudzbina.objava_ponude_Id)
	}
		
	this.svePonudee = function(menadzer){
		return $http.get("/menadzer/menadzerPonude/"+menadzer.id);
	}
	
}]);