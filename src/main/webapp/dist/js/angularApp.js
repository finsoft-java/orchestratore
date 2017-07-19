
/**
 * Load some JSON data into $scope.labels (intended for translations).
 * 
 * @param url
 * @param $http
 * @param $scope
 * @returns
 */
function loadContext(url, $scope, $http) {
	$http.get(url).then(function(res) {
		$scope.labels = res.data;
	});
};

/**
 * AngularJS main module
 */
var app = angular.module('app', []);

app.controller('labelsController', function($scope, $http) {
    loadContext('labels_it.json', $scope, $http);

});