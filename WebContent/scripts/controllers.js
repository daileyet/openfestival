var festivalCtrls = angular.module('festivalCtrls', []);

festivalCtrls.controller('FestivalListCtrl', ['$rootScope', '$scope', '$timeout', 'FsConfService', 'FsContentService', 'CtxLocalCache', 'localStorageService',
	function($rootScope, $scope, $timeout, FsConfService, FsContentService, CtxLocalCache, localStorageService) {
//		if ($rootScope.months == undefined) {
//			$rootScope.months = FsConfService.getMonths();
//		}
//		if ($rootScope.seasons == undefined) {
//			$rootScope.seasons = FsConfService.getSeasons();
//		}
//		if ($rootScope.countries == undefined) {
//			$rootScope.countries = FsConfService.getCountries();
//		}
//		if ($rootScope.contents == undefined) {
//			$rootScope.contents = FsContentService.getContents();
//		}

		if (CtxLocalCache.get('months') == undefined) {
			CtxLocalCache.put('months', FsConfService.getMonths());
		}
		if (CtxLocalCache.get('seasons') == undefined) {
			CtxLocalCache.put('seasons', FsConfService.getSeasons());
		}
		if (CtxLocalCache.get('countries') == undefined) {
			CtxLocalCache.put('countries', FsConfService.getCountries());
		}
		if (CtxLocalCache.get('contents') == undefined) {
			CtxLocalCache.put('contents', FsContentService.getContents());
		}

		$rootScope.months = CtxLocalCache.get('months');
		$rootScope.seasons = CtxLocalCache.get('seasons');
		$rootScope.countries = CtxLocalCache.get('countries');
		$rootScope.contents = CtxLocalCache.get('contents');

		$scope.getMonth = function(monNumber) {
				return CtxLocalCache.get('months')[monNumber - 1]
			}
			//get festival by month
		$scope.getFestival = function(monNumber) {
			var monKey = $scope.getMonth(monNumber).key;
			return CtxLocalCache.get('contents')[monKey];
		}
		$scope.getMainImage = function(fsitem) {
			if (fsitem.images && fsitem.images.length > 1) {
				return fsitem.images[0].url;
			}
			return null;
		}

		$scope.ctrlMonthBar = function(mouseoverEvent, toShow, isTarget) {
			if (toShow) {
				if (isTarget) {
					$('.months-block-bar').hide();
					$(mouseoverEvent.target).show();
				} else {
					$('.months-block-bar').hide();
					$(mouseoverEvent.target).next('.months-block-bar').show();
					$(mouseoverEvent.target).next('.months-block-bar').find('i').show();
				}
			} else {
				if (isTarget) {
					$(mouseoverEvent.target).hide();
				} else {
					$(mouseoverEvent.target).next('.months-block-bar').hide();
				}
			}

		}


	}
]);

festivalCtrls.controller('FestivalDetailsCtrl', ['$rootScope', '$scope', '$routeParams', 'FsContentService', 'CtxLocalCache',
	function($rootScope, $scope, $routeParams, FsContentService, CtxLocalCache) {
		var mon = $routeParams.mon;
		var date = $routeParams.date;
		if (CtxLocalCache.get('contents') == undefined) {
			CtxLocalCache.put('contents', FsContentService.getContents());
		}
		var fss = CtxLocalCache.get('contents')[mon];
		var i = 0;
		var j = fss.length;
		for (; i < j; i++) {
			var fs = fss[i];
			if (fs.date === date) {
				$scope.fsi = fs;
				$scope.existImg = (fs.images && fs.images.length > 0);
				break;
			}
		}
		$scope.$on('$viewContentLoaded', function() {
			setTimeout(function() {
				$(document).foundation();
			}, 150);
		});

	}
]);

festivalCtrls.controller('FestivalAddCtrl', ['$rootScope', '$scope', '$routeParams', 'FsContentService', 'CtxLocalCache',
	function($rootScope, $scope, $routeParams, FsContentService, CtxLocalCache) {
		$scope.$on('$viewContentLoaded', function() {
			setTimeout(function() {
				$(document).foundation();
			}, 150);
		});

		$scope.mon = $routeParams.mon;
		$scope.submit = function() {

			var data = {};
			data.mon = $scope.mon;
			data.name = $scope.name;
			data.date = $scope.date;
			data.desc = $scope.desc;
			data.images = [];

			$(".click-add-success").each(function() {
				var $item = $(this);
				var imgObj = {};
				imgObj.url = $item.find('img').attr('src');
				imgObj.caption = $item.find('img').attr('alt');
				//validate
				data.images.push(imgObj);
			});
			//submit form data
			var add_data = data;
			$.post("/api/contents/add", data, function(data) {
				alert(data.msg + "[" + data.type + "]");
				if (data && data.type && data.type == 'SUCESS') {
					var objCtx = CtxLocalCache.get('contents');
					objCtx[add_data.mon].push(data);
					CtxLocalCache.put('contents', objCtx);
				}
			});

		}

		$scope.showAddPanel = function() {
			$('.click-add-action a').hide();
			$('.click-add-action .click-add-panel').show();
			$('.click-add-action .click-add-panel *').show();
		}

		$scope.hideAddPanel = function() {
			$('.click-add-action a').show();
			$('.click-add-action .click-add-panel').hide();
		}

		$scope.createImage = function() {
			var desc = $('.click-add-action .click-add-panel input[name="fsi-picture-name"]').val();
			var url = $('.click-add-action .click-add-panel input[name="fsi-picture-url"]').val();
			//validate
			var e = '<li class="click-add-success"><a class="th" href="' + url + '" target="_blank">' + '<img src="' + url + '" alt="' + desc + '">'; + '</a>';
			$(e).insertBefore($('.click-add-action'));
			$scope.hideAddPanel();
		}







	}
]);