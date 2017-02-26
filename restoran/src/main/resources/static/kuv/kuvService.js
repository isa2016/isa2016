var services = angular.module('kuv.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('kuvService', ['$http', function($http){
	this.getLoggedUser = function() {
		return $http.get("/loginController/getLoggedUser");
	}
	
	this.updateKuvarProfile = function(konobar) {
		return $http.put("/kuvar/" + konobar.id, konobar);
	}
	
	this.porudzbineZaKuvara = function() {
		return $http.get("/kuvar/porudzbine");
	}
	this.porudzbinePriprema = function() {
		return $http.get("/kuvar/porudzbinePriprema");
	}
	
	this.prihvati = function(porudzbina){
		return $http.post("/kuvar/prihvati/" + porudzbina.id);
	}
	
	this.zavrsi = function(porudzbina){
		return $http.post("/kuvar/zavrsi/" + porudzbina.id);
	}
}]);