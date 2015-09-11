'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'einheitview/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', ['$scope', '$http', function($scope, $http) {
    $scope.name = "Stefanie";
        $scope.einheitArr = 'Hallo';

    $http.get('/einheit/').success(function(data) {
        $scope.einheitArr = data;
        $scope.name = "Virginia";
    });

    }]);
