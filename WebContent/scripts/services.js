var festivalServices = angular.module('festivalServices', ['ngResource']);

festivalServices.factory('FsConfService', ['$resource','CtxLocalCache',
	//	function($http) {
	//		return {
	//			fetchMonths: function(existcache, callback) {
	//				if (!existcache)
	//					$http.get('data/fsconf-months.json').then(function(response) {
	//						callback.call(this, angular.copy(response.data))
	//					});
	//			},
	//			fetchSeasons: function(existcache, callback) {
	//				if (!existcache)
	//					$http.get('data/fsconf-seasons.json').then(function(response) {
	//						callback.call(this, angular.copy(response.data))
	//					});
	//			},
	//			fetchCountries: function(existcache, callback) {
	//				if (!existcache)
	//					$http.get('data/fsconf-countries.json').then(function(response) {
	//						callback.call(this, angular.copy(response.data))
	//					});
	//			}
	//		};
	//	}
	function($resource,CtxLocalCache) {
		return $resource('data/:fsconfPart.json', {}, {
			getMonths: {
				method: 'GET',
				params: {
					fsconfPart: 'fsconf-months'
				},
				isArray: true,
				transformResponse:function(data){
					CtxLocalCache.put('months',data);
					return CtxLocalCache.get('months');
				}
			},
			getSeasons: {
				method: 'GET',
				params: {
					fsconfPart: 'fsconf-seasons'
				},
				isArray: true,
				transformResponse:function(data){
					CtxLocalCache.put('seasons',data);
					return  CtxLocalCache.get('seasons');
				}
			},
			getCountries: {
				method: 'GET',
				params: {
					fsconfPart: 'fsconf-countries'
				},
				isArray: true,
				transformResponse:function(data){
					CtxLocalCache.put('countries',data);
					return  CtxLocalCache.get('countries');
				}
			}
		});
	}

]);

festivalServices.factory('FsContentService', ['$resource','CtxLocalCache',
	//	function($http) {
	//		return {
	//			fetchContents: function(existcache, callback) {
	//				if (!existcache)
	//					$http.get('data/fs-content.json').then(function(response) {
	//						callback.call(this, angular.copy(response.data))
	//					});
	//			}
	//		}
	//	}
	function($resource,CtxLocalCache) {
		return $resource('data/:fs.json', {}, {
			getContents: {
				method: 'GET',
				params: {
					fs: 'fs-content'
				},
				isArray: false,
				transformResponse:function(data){
					CtxLocalCache.put('contents',data);
					return CtxLocalCache.get('contents');
				}
			}
		});
	}
]);