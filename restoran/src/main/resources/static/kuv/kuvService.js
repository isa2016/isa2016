var services = angular.module('kuv.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('kuvService', ['$http', function($http){
	this.getLoggedUser = function() {
		return $http.get("/loginController/getLoggedUser");
	}
	
	this.updateKuvarProfile = function(konobar) {
		return $http.put("/kuvar/" + konobar.id, konobar);
	}
}]);