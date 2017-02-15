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


}]);