
/**
 * Load some JSON data into $scope.labels (intended for translations).
 * 
 * @param url
 * @param $http
 * @param $scope
 * @returns
 */
function loadContext(url, $http, $scope) {
	$http.get(url).then(function(res) {
		$scope.labels = res.data;
	});
};

/**
 * AngularJS main module
 */
var app = angular.module('app', []);

app.controller('labelsController', function($scope, $http, $sce, $compile) {

	loadContext('labels_it.json', $http, $scope);

});
