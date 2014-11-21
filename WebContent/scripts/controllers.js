var festivalCtrls = angular.module('festivalCtrls', []);

festivalCtrls.controller('FestivalListCtrl', ['$scope', '$timeout', 'FsConfService', 'FsContentService', 'CtxLocalCache',
	function($scope, $timeout, FsConfService, FsContentService, CtxLocalCache) {

		if (CtxLocalCache.get('months') == undefined || CtxLocalCache.get('months') == null)
			$scope.months = FsConfService.getMonths();
		else
			$scope.months = CtxLocalCache.get('months');

		if (CtxLocalCache.get('seasons') == undefined || CtxLocalCache.get('seasons') == null)
			$scope.seasons = FsConfService.getSeasons();
		else
			$scope.seasons = CtxLocalCache.get('seasons');
		if (CtxLocalCache.get('countries') == undefined || CtxLocalCache.get('countries') == null)
			$scope.countries = FsConfService.getCountries();
		else
			$scope.countries = CtxLocalCache.get('countries');
		if (CtxLocalCache.get('contents') == undefined || CtxLocalCache.get('contents') == null)
			$scope.contents = FsContentService.getContents();
		else
			$scope.contents = CtxLocalCache.get('contents');

		$scope.getMonth = function(monNumber) {
			return $scope.months[monNumber - 1];
		};
		//get festival by month
		$scope.getFestival = function(monNumber) {
			var monKey = $scope.getMonth(monNumber).key;
			return $scope.contents[monKey];
		};
		$scope.getMainImage = function(fsitem) {
			if (fsitem.images && fsitem.images.length > 0) {
				return fsitem.images[0].url;
			}
			return null;
		};

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

		};

		$scope.closeModal = function() {
			$('#view-container').foundation('reveal', 'close');
		}


	}
]);

festivalCtrls.controller('FestivalDetailsCtrl', ['$rootScope', '$scope', '$routeParams', 'FsContentService', 'CtxLocalCache',
	function($rootScope, $scope, $routeParams, FsContentService, CtxLocalCache) {
		var mon = $routeParams.mon;
		var date = $routeParams.date;
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
			data.date = $scope.date + '';
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
			var ctx_data = data;
			$.post("/api/contents/add", data, function(data) {
				console.log(data.msg + "[" + data.type + "]");
				if (data && data.type && data.type == 'SUCESS') {
					var objCtx = CtxLocalCache.get('contents');
					objCtx[ctx_data.mon].push(ctx_data);
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


festivalCtrls.controller('UserLoginCtrl', function($scope, $location) {
	$scope.$on('$viewContentLoaded', function() {
		setTimeout(function() {
			//			$(document).foundation();
		}, 150);
	});

	//control singup or singin form display or hide
	$scope.islogin = true;
	$scope.singup = function() {
		$scope.islogin = false;
	}
	$scope.singin = function() {
		$scope.islogin = true;
		console.log('Go to signin view');
	}

	$scope.submitSingin = function() {
		var data = {};
	}

	$scope.submitSingup = function() {
		var post_data = {};
		post_data.name = $scope.upname;
		post_data.password = $scope.uppass;
		post_data.password2 = $scope.uprepass;
		post_data.email = $scope.upemail;

		$.post('/api/users/add', post_data, function(data) {
			if (data && data.type && data.type == 'SUCESS') {
				$scope.singin();
//				$('#tab-reg').addClass('ng-hide');
//				$('#tab-login').removeClass();
//				console.log(data.msg);
			}else{
				
			}
			
		});
	}


});