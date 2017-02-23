var services = angular.module('ponudjac.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('ponudjacService', ['$http', function($http){
	
	
	this.getLoggedUser = function(){
		return $http.get("/loginController/getLoggedUser");
	}
	
	
	this.updatePonudjacProfile = function(ponudjac){
		return $http.put("/ponudjac/ponudjacUpdate/"+ponudjac.id,ponudjac);
	}
	
	


}]);
