app.controller('projectCtrl', function($scope, $rootScope, $http) {
	
	// Find all Project
	/*$scope.projects = function(){
		$http({
		    'url' : 'project/findall',
		    'method' : 'GET',
		    'headers': {'Content-Type' : 'application/json'}
		}).success(function(data){
			alert("Success");
		})
	}*/
	
	// Declare variables
	$scope.projects;
	$scope.projectTypes;
	$scope.project;
	$scope.projectType;
	
	if($scope.projects == null && $scope.projectTypes == null) {
		$http.get("project/findall")
		.success(function(response) {
			$scope.projects = response["projects"];
			$scope.projectTypes = response["projectTypes"];
		}).error(function(){
			throw new Error("Can't find the object");
	    });
	};
	
	$scope.addNewProject = function() {
		var url = "project/add";
		var requestObject = $scope.initProjectObject();
		$http.post(url, requestObject).
		success(function(response) {
			alert("OK");
		}).
		error(function() {
		});
	};
	
	$scope.initProjectObject = function() {
		var object = {
			"projectName" : $scope.projectName,
			"description" : $scope.description,
			"projectTypeId": $scope.projectTypeId
		};
		return object;
	};
	
	$scope.initProjectObject2 = function() {
		var object = {
			"id" : $scope.id,
			"projectName" : $scope.newname,
			"description" : $scope.newdescription,
			"projectTypeId": $scope.projectTypeId
		};
		return object;
	};
	
	$scope.init2 = function() {
		var object = {
				"projectId" : $scope.id,
				"projectName" : $scope.projects[id].projectName,
				"description" : $scope.projects[id].description,
				"projectTypeId": 1
		};
		return object;
	};
	
	
	
	$scope.updateProject = function() {
		alert("abc" + $scope.projects[2].projectTypeId);
		var url = "project/update";
		var requestObject3 = $scope.initProjectObject2();
		//var requestObject2 = $scope.init2();
		$http.post(url, requestObject3).
		success(function(response) {
			alert("OK");
		}).
		error(function() {
		});
	};
});