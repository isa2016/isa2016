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
	
	this.find = function(id){
		return $http.get("/restoran/"+id)
	}

	this.potvrda = function(rezervacija,restoran){
		return $http.post("/guest/rezervisi/"+restoran.id,rezervacija)
	}

}]);