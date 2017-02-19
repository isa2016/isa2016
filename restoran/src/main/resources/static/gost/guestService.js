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
	
	this.find = function(id){
		return $http.get("/restoran/"+id)
	}

	this.potvrda = function(rezervacija){
		return $http.post("/guest/dodaj",rezervacija)
	}

}]);