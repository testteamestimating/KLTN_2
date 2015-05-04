app.controller('projectCtrl', function($scope, $mdDialog, $rootScope, $http, $stateParams, $state, $mdSidenav, $timeout, $mdSidenav, $mdUtil, $log) {
	$scope.$on('event:dataTableLoaded', function(event, loadedDT) {
	    // loadedDT === {"id": "foobar", "DataTable": oTable, "dataTable": $oTable}

	    // loadedDT.DataTable is the DataTable API instance
	    // loadedDT.dataTable is the jQuery Object
	    // See http://datatables.net/manual/api#Accessing-the-API
	});
	
	
	$scope.data = {};
	$scope.data.usecasePoint = "no";
	$scope.data.functionPoint = "no";
	$scope.data.usecaseCost = "no";
	$scope.data.functionCost = "no";
	$scope.alert1 = "";
	$scope.alert2 = "";
	$scope.alert3 = "";
	$scope.alert4 = "";
	$scope.alert5 = "";
	$scope.alert6 = "";
	$scope.alert7 = "";
	$scope.alert8 = "";
	$scope.alertFunctionCost = "";
	$scope.alertUsecaseCost = "";
	$scope.alertFunctionPoint = "";
	$scope.alertUsecasePoint = "";
	$scope.alertRoot = "";
	
	$scope.projects;
	$scope.projectTypes;
	$scope.project;
	$scope.projectType;
	$scope.projectsSearch;
	$scope.state = $state.current;
	$rootScope.projectId = $scope.projectId ;
	
	$scope.uSimples = [
	   "long", "hung"
	                   ];
	
	 $scope.toggleLeft = buildToggler('left');
	 $scope.toggleRight = buildToggler('right');
	 
	 function buildToggler(navID) {
	      var debounceFn =  $mdUtil.debounce(function(){
	            $mdSidenav(navID)
	              .toggle()
	              .then(function () {
	                $log.debug("toggle " + navID + " is done");
	              });
	          },300);
	      return debounceFn;
	    };
	  

	$scope.close = function () {
	    $mdSidenav('left').close()
	      .then(function () {
	        $log.debug("close LEFT is done");
	      });
	  };
  
  $scope.close = function () {
      $mdSidenav('right').close()
        .then(function () {
          $log.debug("close RIGHT is done");
        });
    };
	
	if($scope.state.name == "usecase_cal" && $scope.projects == null && $scope.projectTypes == null) {
		$http.get("project/findall")
		.success(function(response) {
			$scope.projects = response["projects"];
			$scope.projectTypes = response["projectTypes"];
			$scope.projectsSearch = response["projectsSearch"];
		}).error(function(){
			throw new Error("Can't find the object");
	    });
	};
	
	$scope.openLeftMenu = function() {
		    $mdSidenav('left').toggle();
		  };
	
	if($scope.state.name == "myproject" && $scope.projectsSearch == null) {
		$http.get("project/findall")
		.success(function(response) {
			$scope.projectsSearch = response["projectsSearch"];
			//$('#example').dataTable();
			$('#example').dataTable({
			    "aaData": $scope.projectsSearch,
			        "aoColumns": [{
			        "mDataProp": "projectName"
			    }, {
			        "mDataProp": "usecaseTotalPoint"
			    }, {
			        "mDataProp": "usecaseTotalCost"
			    }, {
			        "mDataProp": "usecaseVersion"
			    }, {
			        "mDataProp": "functionTotalPoint"
			    }, {
			        "mDataProp": "functionTotalCost"
			    }, {
			        "mDataProp": "functionVersion"
			    }]
			});
		}).error(function(){
			throw new Error("Can't find the object");
	    });
	}
	
	$scope.load = function() {
		$http.get("project/findall")
		.success(function(response) {
			$scope.projects = response["projects"];
			$scope.projectTypes = response["projectTypes"];
		}).error(function(){
			throw new Error("Can't find the object");
	    });
	}
	
	$scope.searchAll = function() {
		$http.get("project/searchAll")
		.success(function(response) {
			$scope.projectsSearch = response;
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
		var check =  $scope.validateSearch();
		if( check == false ) {
			return;
		}
		var data = $scope.getvalues();
		$http.post(url, data).
		success(function(response) {
			$scope.projectsSearch = response;
			$('#example').dataTable({
				destroy: true,
			    "aaData": $scope.projectsSearch,
			        "aoColumns": [{
			        "mDataProp": "projectName"
			    }, {
			        "mDataProp": "usecaseTotalPoint"
			    }, {
			        "mDataProp": "usecaseTotalCost"
			    }, {
			        "mDataProp": "usecaseVersion"
			    }, {
			        "mDataProp": "functionTotalPoint"
			    }, {
			        "mDataProp": "functionTotalCost"
			    }, {
			        "mDataProp": "functionVersion"
			    }]
			});
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
	
	$scope.validateSearch = function() {
		var result = true;
		var resultAlert1 = true;
		var resultAlert2 = true;
		var resultAlert3 = true;
		var resultAlert4 = true;
		var resultAlert5 = true;
		var resultAlert6 = true;
		var resultAlert7 = true;
		var resultAlert8 = true;
		var resultAlertUsecasePoint = true;
		var resultAlertFunctionPoint = true;
		var resultAlertUsecaseCost = true;
		var resultAlertFunctionCost = true;
		var alertRool = true;
		
		
		
	/*	if(isNaN($scope.usecaseMin) || isNaN($scope.usecaseMax) || isNaN( $scope.functionMin) || isNaN( $scope.functionMax) 
			|| isNaN($scope.usecaseCostMin) || isNaN( $scope.usecaseCostMax)|| isNaN($scope.functionCostMin) || isNaN($scope.functionCostMax)) {
			result = false;
		}*/
		if($scope.data.usecasePoint == "yes") {
			if(isNaN($scope.usecaseMin) || $scope.usecaseMin == "" ) {
				$scope.alert1 = "Phai nhap so";
				resultAlert1 = false;
			}
			else if (! isNaN($scope.usecaseMin)) {
				$scope.alert1 = "";
				resultAlert1 = true;
			}
			if(isNaN($scope.usecaseMax) || $scope.usecaseMax == "") {
				$scope.alert2 = "Phai nhap so";
				resultAlert2 = false;
			}
			else if (! isNaN($scope.usecaseMax)) {
				$scope.alert2 = "";
				resultAlert2 = true;
			}
			if(resultAlert1 == true && resultAlert2 == true) {
				if($scope.usecaseMin >= $scope.usecaseMax) {
					$scope.alertUsecasePoint = "min phai nho hon max";
					resultAlertUsecasePoint = false;
				}
				else {
					$scope.alertUsecasePoint = "";
					resultAlertUsecasePoint = true;
				}
			}
		}
		if($scope.data.functionPoint == "yes") {
			if(isNaN($scope.functionMin) || $scope.functionMin == "") {
				$scope.alert3 = "Phai nhap so";
				resultAlert3 = false;
			}
			else if (! isNaN( $scope.functionMin)) {
				$scope.alert3 = "";
				resultAlert3 = true;
			}
			if(isNaN($scope.functionMax) || $scope.functionMax == "") {
				$scope.alert4 = "Phai nhap so";
				resultAlert4 = false;
			}
			else if (! isNaN( $scope.functionMax)) {
				$scope.alert4 = "";
				resultAlert4 = true;
			}
			if(resultAlert3 ==  true && resultAlert4 == true) {
				if($scope.functionMin >= $scope.functionMax) {
					$scope.alertFunctionPoint = "min phai nho hon max";
					resultAlertFunctionPoint = false;
				}
				else {
					$scope.alertFunctionPoint = "";
					resultAlertFunctionPoint = true;
				}
			}
		}
		if($scope.data.usecaseCost == "yes") {
			if(isNaN($scope.usecaseCostMin) || $scope.usecaseCostMin == "") {
				$scope.alert5 = "Phai nhap so";
				resultAlert5 = false;
			}
			else if (! isNaN($scope.usecaseCostMin)) {
				$scope.alert5 = "";
				resultAlert5 = true;
			}
			if(isNaN($scope.usecaseCostMax) || $scope.usecaseCostMax == "") {
				$scope.alert6 = "Phai nhap so";
				resultAlert6 = false;
			}
			else if (! isNaN($scope.usecaseCostMax)) {
				$scope.alert6 = "";
				resultAlert6 = true;
			}
			if(resultAlert5 == true && resultAlert6 == true) {
				if($scope.usecaseCostMin >= $scope.usecaseCostMax) {
					$scope.alertUsecaseCost = "min phai nho hon max";
					resultAlertUsecaseCost = false;
				}
				else {
					$scope.alertUsecaseCost = "";
					resultAlertUsecaseCost = true;
				}
			}
		}
		if($scope.data.functionCost == "yes") {
			if(isNaN($scope.functionCostMin) || $scope.functionCostMin == "") {
				$scope.alert7 = "Phai nhap so";
				resultAlert7 = false;
			}
			else if (! isNaN($scope.functionCostMin)) {
				$scope.alert7 = "";
				resultAlert7 = true;
			}
			if(isNaN($scope.functionCostMax) || $scope.functionCostMax == "") {
				$scope.alert8 = "Phai nhap so";
				resultAlert8 = false;
			}
			else if (! isNaN($scope.functionCostMax)) {
				$scope.alert8 = "";
				resultAlert8 = true;
			}
			if(resultAlert7 == true && resultAlert8 == true) {
				if($scope.functionCostMin >= $scope.functionCostMax) {
					$scope.alertFunctionCost = "min phai nho hon max";
					resultAlertFunctionCost = false;
				}
				else {
					$scope.alertFunctionCost = "";
					resultAlertFunctionCost = true;
				}
			}
		}
		if($scope.data.usecasePoint == "no" && $scope.data.functionPoint == "no" && $scope.data.usecaseCost == "no" && $scope.data.functionCost == "no" ) {
			$scope.alertRoot = "chon 1 kieu tim kiem";
			alertRool = false;
		}
		else {
			$scope.alertRoot = "";
			alertRool = true;
		}
		if( resultAlert1 == false || resultAlert2 == false || resultAlert3 == false|| resultAlert4 == false
				|| resultAlert5 == false || resultAlert6 == false || resultAlert7 == false || resultAlert8 == false
				|| resultAlertFunctionCost == false || resultAlertFunctionPoint == false 
				|| resultAlertUsecaseCost == false || resultAlertUsecasePoint == false || alertRool == false) {
			result = false;
		}
		return result;
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
	$scope.addRowSimple = function(){
		console.log("uSimpleValue: " + $rootScope.uSimpleValue);
		$scope.uSimples.push($rootScope.uSimpleValue);
		//$scope.uSimpleValue='';
	};
	
	$scope.removeRowSimple = function(uSimpleValue){				
		var index = -1;		
		var comArr = eval( $scope.uSimples );
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].uSimpleValue === uSimpleValue ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.uSimples.splice( index, 1 );		
	};
	
	
	
	
});

