var services = angular.module('konobar.services', [ 'ngResource' ]);

var baseUrl = 'http://localhost\\:8080';

services.service('konobarService', [ '$http', function($http) {

	this.getLoggedUser = function() {
		return $http.get("/loginController/getLoggedUser");
	}

	this.updateKonobarProfile = function(konobar) {
		return $http.put("/konobar/" + konobar.id, konobar);
	}
} ]);