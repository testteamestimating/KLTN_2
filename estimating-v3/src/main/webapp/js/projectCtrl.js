app.controller('projectCtrl', function($scope, $mdDialog, $rootScope, $http, $stateParams, $state) {
	
	$scope.data = {};
	$scope.data.usecasePoint = "no";
	$scope.data.functionPoint = "no";
	$scope.data.usecaseCost = "no";
	$scope.data.functionCost = "no";
	$scope.data.cb5 = false;
		
	$scope.projects;
	$scope.projectTypes;
	$scope.project;
	$scope.projectType;
	$scope.state = $state.current;
	$rootScope.projectId = $scope.projectId ;
	
	if($scope.state.name == "usecase_cal" && $scope.projects == null && $scope.projectTypes == null) {
		$http.get("project/findall")
		.success(function(response) {
			$scope.projects = response["projects"];
			$scope.projectTypes = response["projectTypes"];
		}).error(function(){
			throw new Error("Can't find the object");
	    });
	};
	
	$scope.load = function() {
		$http.get("project/findall")
		.success(function(response) {
			$scope.projects = response["projects"];
			$scope.projectTypes = response["projectTypes"];
		}).error(function(){
			throw new Error("Can't find the object");
	    });
	}
	
	$scope.addNewProject = function() {
		alert($scope.projectId);
		var url = "project/add";
		var requestObject = $scope.initProjectObject();
		$rootScope.projectId = $scope.projectId ;
		$http.post(url, requestObject).
		success(function(response) {
			alert("OK");
		}).
		error(function() {
		});
	};
	
	$scope.show = function() {
		var url = "project/search";
		var data = $scope.getvalues();
		$http.post(url, data).
		success(function(response) {
			$scope.projects = response;
			if(pro)
			alert("OK");
		}).
		error(function() {
		});
	}
	

	$scope.getvalues = function() {
		var datavalue = {};
		
		datavalue.usecaseMin = $scope.usecaseMin;
		datavalue.usecaseMax = $scope.usecaseMax;
		
		datavalue.functionMin = $scope.functionMin;
		datavalue.functionMax = $scope.functionMax;
		
		datavalue.usecaseCostMin = $scope.usecaseCostMin;
		datavalue.usecaseCostMax = $scope.usecaseCostMax;
		
		datavalue.functionCostMin = $scope.functionCostMin;
		datavalue.functionCostMax = $scope.functionCostMax;
		
		datavalue.usecasePointCheck = $scope.data.usecasePoint;
		datavalue.functionPointCheck = $scope.data.functionPoint;
		datavalue.usecaseCostCheck = $scope.data.usecaseCost;
		datavalue.functionCostCheck = $scope.data.functionCost;
		return datavalue;
	}
	
	
	
	$scope.initProjectObject = function() {
		var object = {
			"projectName" : $scope.projectName,
			"description" : $scope.description,
			"projectTypeId": $scope.projectTypeId,
			
		};
		$rootScope.projectId = $scope.projectId;
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