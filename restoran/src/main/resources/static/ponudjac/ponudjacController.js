var app = angular.module('ponudjac.controllers', []);
 
app.controller('ponudjacController', ['$scope','$window','ponudjacService', '$location',
  	function ($scope,$window, ponudjacService, $location) {
		
		$scope.getLoggedUser = function() {
			ponudjacService.getLoggedUser().then(
				function (response) {
					$scope.loggedUser = response.data;
					sveObjave2();
					svePonude2($scope.loggedUser);
					$scope.sakrijDetalje = true;
					$scope.sakrij = true;
	            }	
				
				
			);
			
		}
		
		$scope.update = function() {
			ponudjacService.updatePonudjacProfile($scope.loggedUser).then(
				function (response) {
                    $scope.state = undefined;
                    $location.path('/ponudjac/profili');
				}
			);
		}
		
	    
		
		$scope.sveObjave = function() {
			ponudjacService.sveObjavee($scope.loggedUser).then(
				function (response) {
                    $scope.list = response.data;
                    
				}
			);
		
		}

		$scope.posaljiPonudu = function(porudzba) {
			ponudjacService.ponuda(porudzba,$scope.loggedUser,$scope.garancija,$scope.cena,$scope.isporuka).then(
				function (response) {
                   
					 $location.path('/ponudjac/profili');
				}
			);
		
		}
		
		$scope.menjanjePonude = function(jelo) {
			ponudjacService.ponudaMenjanje(jelo,$scope.loggedUser,$scope.garancija,$scope.cena,$scope.isporuka).then(
				function (response) {
                     svePonude2($scope.loggedUser);
					 $location.path('/ponudjac/ponude');
				}
			);
		
		}
		
		$scope.detaljiPonudu = function(porudzba) {
			ponudjacService.ponudaDetalji(porudzba).then(
				function (response) {
                    $scope.listing = response.data;
                    $scope.sakrijDetalje = false;
					 $location.path('/ponudjac/objave');
				}
			);
		
		}
		
		$scope.izaberiPonudu = function(porudzba) {
			ponudjacService.ponudaDetalji(porudzba).then(
				function (response) {
                    $scope.porudzba = response.data;
                    $scope.sakrij = false;
					 $location.path('/ponudjac/objave');
				}
			);
		
		}
		
		$scope.izmeniPonudu = function(jelo) {
			ponudjacService.ponudaDetalji(jelo).then(
				function (response) {
                    $scope.jelo = response.data;
                    $scope.sakrij = false;
					 $location.path('/ponudjac/ponude');
				}
			);
		
		}
		
		$scope.kreirajPonudu = function(porudzba) {
			ponudjacService.ponudaKreiraj(porudzba).then(
				function (response) {
                     $scope.rest = response.data;
					 $location.path('/ponudjac/ponuda');
				}
			);
		
		}
		
		function sveObjave2(){
			ponudjacService.sveObjavee($scope.loggedUser).then(
					function (response) {
	                    $scope.list = response.data;
	                    
					}
				);
		}
		
		function svePonude2(loggedUser){
			ponudjacService.svePonude2($scope.loggedUser).then(
					function (response) {
	                    $scope.liste = response.data;
	                    
					}
				);
		}
}]);
