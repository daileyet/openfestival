var festivalCaches = angular.module('festivalCaches', []);

festivalCaches.factory('CtxCache', ['$cacheFactory',
	function($cacheFactory) {
		return $cacheFactory('CtxCache');
	}
]);

festivalCaches.factory('CtxLocalCache', ['localStorageService',

	function(localStorageService) {
		return {
			get: function(key) {
				return localStorageService.get(key);
			},
			put: function(key, value) {
				localStorageService.set(key, value);
			},
			remove: function(key) {
				localStorageService.removeItem(key);
			}
		}
	}
]);