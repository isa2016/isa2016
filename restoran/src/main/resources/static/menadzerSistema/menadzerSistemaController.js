var app = angular.module('menadzerSistema.controllers', []);
 
app.controller('menadzerSistemaController', ['$scope','$window','menadzerSistemaService', '$location',
  	function ($scope,$window, menadzerSistemaService, $location) {
		
	$scope.dodajRest = function () {  

		menadzerSistemaService.save($scope.rest).then(
			function (response) {
                $location.path('menadzerSistema');
            }
        ); 	
	}
	
	$scope.sviRestorani = function(){
		menadzerSistemaService.sviRestorani().then(
				function(response){
					$scope.restorani = response.data;
				}
			);
	}

	$scope.dodajMS = function () {  

		menadzerSistemaService.saveMS($scope.ms).then(
			function (response) {
                $location.path('menadzerSistema');
            }
        ); 	
	}
	
	$scope.sviMS = function(){
		menadzerSistemaService.sviMS().then(
				function(response){
					$scope.MSa = response.data;
				}
			);
	}

}]);
