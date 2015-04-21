app.controller('projectCtrl', function($scope, $mdDialog, $rootScope, $http, $stateParams, $state) {
	
	$scope.projects;
	$scope.projectTypes;
	$scope.project;
	$scope.projectType;
	$scope.state = $state.current;
	if($scope.state.name == "usecase_cal" && $scope.projects == null && $scope.projectTypes == null) {
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
	
	$scope.showAdvanced = function(ev) {
	    $mdDialog.show({
	      controller: DialogController,
	      templateUrl: 'views/component/createProject_dialog.html',
	      targetEvent: ev,
	    })
	    .then(function(answer) {
	      $scope.alert = 'You said the information was "' + answer + '".';
	    }, function() {
	      $scope.alert = 'You cancelled the dialog.';
	    });
	  };
	
	function DialogController($scope, $mdDialog) {
		  $scope.hide = function() {
		    $mdDialog.hide();
		  };
		  $scope.cancel = function() {
		    $mdDialog.cancel();
		  };
		  $scope.answer = function(answer) {
		    $mdDialog.hide(answer);
		  };
		}
});