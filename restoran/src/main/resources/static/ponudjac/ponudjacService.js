var services = angular.module('ponudjac.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('ponudjacService', ['$http', function($http){
	
	
	this.getLoggedUser = function(){
		return $http.get("/loginController/getLoggedUser");
	}
	
	
	this.updatePonudjacProfile = function(ponudjac){
		return $http.put("/ponudjac/ponudjacUpdate/"+ponudjac.id,ponudjac);
	}
	
	this.sveObjavee = function(ponudjac){
		return $http.get("/ponudjac/ponudjacObjave/"+ponudjac.id);
	}
	
	this.ponudaKreiraj = function(ponudjac){
		return $http.get("/ponudjac/kreirajPonudu/"+ponudjac.restoranID);
	}
	
	this.ponuda = function(restoran,ponudjac,cena,garancija,isporuka){
		return $http.put("/ponudjac/ponudjacPonuda/"+restoran.restoranID+"/"+restoran.id+"/"+ponudjac.id+"/"+cena+"/"+garancija+"/"+isporuka);
	}

	this.ponudaMenjanje = function(restoran,ponudjac,garancija,cena,isporuka){
		return $http.put("/ponudjac/menjanjePonuda/"+restoran.id+"/"+garancija+"/"+cena+"/"+isporuka);
	}
	
	this.ponudaDetalji = function(ponuda){
		return $http.get("/ponudjac/detaljiPonuda/"+ponuda.id);
	}
	
	this.svePonude2 = function(ponudjac){
		return $http.get("/ponudjac/ponudjacPonude/"+ponudjac.id);
	}
}]);
