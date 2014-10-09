var app = angular.module('festivalApp', ['ngRoute','festivalCaches', 'festivalCtrls', 'festivalServices','LocalStorageModule']);

app.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.when('/festivals/:mon/:date', {
			templateUrl: 'views/festival-details.html',
			controller: 'FestivalDetailsCtrl'
		}).when('/add/:mon', {
			templateUrl: 'views/festival-add.html',
			controller: 'FestivalAddCtrl'
		}).otherwise({
			redirectTo: '/'
		});
	}
]);