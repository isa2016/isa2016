var services = angular.module('guest.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('guestService', ['$http', function($http){
	
	
	this.getLoggedUser = function(){
		return $http.get("/loginController/getLoggedUser");
	}
	
	this.findFriends = function(input){
		return $http.get("/guest/findByFirstAndLastName/"+input);
	}
	
	this.updateGuestProfile = function(guest){
		return $http.put("/guest/"+guest.id,guest);
	}
	
	this.sviRestorani = function(){
		return $http.get("/guest/restorani");
	}
	
	this.sveRez = function(loggedUser){
		return $http.get("/guest/rezervacije/"+loggedUser.id);
	}
	
	this.sveRez3 = function(loggedUser){
		return $http.get("/guest/rezervacijee/"+loggedUser.id);
	}
	
	this.sveRezervacije = function(loggedUser){
		return $http.get("/guest/posete/"+loggedUser.id);
	}
	
	this.find = function(id){
		return $http.get("/restoran/"+id)
	}

	
	
	this.dodajJelo = function(jelo,por){
		return $http.get("/guest/porudzbineJelo/"+jelo.id+"/"+por.id)
	}
	
	this.izbrisi = function(jelo,por){
		return $http.get("/guest/izbrisiJelo/"+jelo.id+"/"+por.id)
	}
	
	this.izbrisiPice = function(pice,por){
		return $http.get("/guest/izbrisiPice/"+pice.id+"/"+por.id)
	}
	
	this.dodajPice = function(pice,por){
		return $http.get("/guest/porudzbinePice/"+pice.id+"/"+por.id)
	}
	
	
	this.findRez = function(id){
		return $http.get("/rezervacija/"+id)
	}
	
	this.setOcena = function(ocena,rezervacija){
		return $http.post("/rezervacija/"+rezervacija+"/"+ocena)
	}
	
	this.napraviP = function(jelo){
		return $http.get("/guest/napraviPJ/"+jelo.id)
	}
	
	this.napraviP2 = function(pice){
		return $http.get("/guest/napraviPP/"+pice.id)
	}
	
	this.potvrda = function(por,rezervacija,rest){
		return $http.post("/guest/rezervisi/"+rest.id+"/"+por.id,rezervacija)
	}
	
	this.potvrda2 = function(rezervacija,rest){
		return $http.post("/guest/rezervisiBez/"+rest.id,rezervacija)
	}
	
	this.primljeniZahtevi = function(){
		return $http.get("/prijatelji/primljeni")
	}
	
	this.prijatelji = function(){
		return $http.get("/prijatelji/prihvaceni")
	}
	
	this.poslatiZahtevi = function(){
		return $http.get("/prijatelji/poslati")
	}
	
	this.prihvati = function(p){
		return $http.post("prijatelji/prihvati/"+p.id)
	}
	
	this.odbij = function(p){
		return $http.post("prijatelji/odbij/"+p.id)
	}
	
	this.obrisi = function(p){
		return $http.post("prijatelji/obrisi/"+p.id)
	}
	
	this.pronadjiGostaPoImenu = function(gost){
		return $http.get("prijatelji/poImenu/"+gost)
	}
	
	this.pronadjiGostaPoPrezimenu = function(gost){
		return $http.get("prijatelji/poPrezimenu/"+gost)
	}
	
	this.pronadjiGosta = function(ime,prezime){
		return $http.get("prijatelji/pronadji/"+ime+"/"+prezime)
	}
	
	this.dodaj = function(gost){
		return $http.post("prijatelji/dodaj/"+gost.id)
	}
	
	this.brojZahteva = function(){
		return $http.get("prijatelji/broj")
	}
	
	this.sviPrijatelji = function(){
		return $http.get("prijatelji/prijatelji")
	}
	
	this.pozovii = function(rez) {
		return $http.post("guest/pozovi",rez)
	}
	
	this.prihvatiPoziv = function(rez,por) {
		return $http.post("guest/prihvatiPoziv/"+rez.id+"/"+por.id)
	}
	
	this.odbijPoziv = function(rez) {
		return $http.post("guest/odbijPoziv/"+rez.id)
	}

}]);