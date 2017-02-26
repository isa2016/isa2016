var services = angular.module('sank.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('sankService', ['$http', function($http){
	this.getLoggedUser = function() {
		return $http.get("/loginController/getLoggedUser");
	}
	
	this.updateSankerProfile = function(sanker) {
		return $http.put("/sanker/" + sanker.id, sanker);
	}
	
	this.porudzbineZaSankera = function() {
		return $http.get("/sanker/porudzbine");
	}
}]);