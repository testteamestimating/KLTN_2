 var app = angular.module('signup', ['ui.router'])
.controller('ngClickController', function($scope, $http){
	$scope.addNewUser = function() {
		var url = "signup";
		var requestObject = $scope.initUserObject();
		$http.post(url, requestObject).
		success(function(response) {
			alert("OK");
		}).
		error(function(response) {
			alert("bf");
		});
	};
	
	$scope.initUserObject = function() {
		var object = {
			"username" : $scope.username,
			"password" : $scope.password,
		};
		alert("fef");
		return object;
	};
});
 app.config(function($stateProvider, $urlRouterProvider) {
	 $urlRouterProvider.otherwise('login');
	    
	    $stateProvider
		    .state('/', {
	            url: '/',
	            templateUrl: 'views/login.html',
	            controller: 'projectCtrl'
	        })
	        /*.state('usecase_cal', {
	            url: '/login',
	            templateUrl: 'views/login.html',
	            controller: 'projectCtrl'
	        })
	        .state('myproject', {
	            url: '/singup',
	            templateUrl: 'views/signup.html',
	            controller: 'projectCtrl'
	        })
	        .state('share_project', {
	            url: '/reset',
	            templateUrl: 'views/reset_password.html' 
	        })*/
	});