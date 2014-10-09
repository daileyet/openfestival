var festivalCaches = angular.module('festivalCaches', ['LocalStorageModule']);

festivalCaches.factory('CtxCache', ['$cacheFactory',
	function($cacheFactory) {
		return $cacheFactory('CtxCache');
	}
]);

festivalCaches.factory('CtxLocalCache', ['localStorageService',
	function(localStorageService) {
		var prefix = 'ls.';
		return {
			get:function(key){
				return localStorageService.get(key);
			},
			put:function(key,value){
				localStorageService.set(key,value);
			},
			remove:function(key){
				localStorageService.remove(key);
			}
		}
	}
]);