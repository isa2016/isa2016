var services = angular.module('kon.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('konService', ['$http', function($http){
	this.getLoggedUser = function() {
		return $http.get("/loginController/getLoggedUser");
	}
	
	this.updateKonobarProfile = function(konobar) {
		return $http.put("/konobar/" + konobar.id, konobar);
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
}]);