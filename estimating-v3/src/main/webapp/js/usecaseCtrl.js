app.controller('usecaseCtrl', function($scope, $rootScope, $http) {
	
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
    /** @namespace $scope.project */
    $scope.project;
    /** @namespace $scope.projectType */
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
	/*
	if($scope.state.name == "myproject") {
		$scope.uSimples = [
		     "long", "hung"           
		 ];
	}
	*/
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
	
	/*
	 * add user simple
	 */
	$scope.uSimples = [];
	
	$scope.addRowSimple = function(){	
		$scope.uSimples.push({ 'uSimpleValue': $scope.uSimpleValue });
		$scope.uSimpleValue='';
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
	
	/*
	 * add user average
	 */
	$scope.uAverages = [];
	
	$scope.addRowAverage = function(){	
		$scope.uAverages.push({ 'uAverageValue': $scope.uAverageValue });
		$scope.uAverageValue='';
	};
	
	$scope.removeAverage = function(uAverageValue){				
		var index = -1;		
		var comArr = eval( $scope.uAverages );
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].uAverageValue === uAverageValue ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.uAverages.splice( index, 1 );		
	};
	/*
	 * add user complex
	 */
	$scope.uComplexs = [];
	
	$scope.addRowuComplex = function(){	
		$scope.uComplexs.push({ 'uComplexValue': $scope.uComplexValue });
		$scope.uComplexValue='';
	};
	
	$scope.removeuComplex = function(uComplexValue){				
		var index = -1;		
		var comArr = eval( $scope.uComplexs );
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].uComplexValue === uComplexValue ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.uComplexs.splice( index, 1 );		
	};
	/*
	 * add actor simple
	 */
	$scope.aSimples = [];
	
	$scope.addRowaSimple = function(){
		$scope.aSimples.push({ 'asimpleValue': $scope.asimpleValue });
		$scope.asimpleValue='';
	};
	
	$scope.removeaSimple = function(asimpleValue){				
		var index = -1;		
		var comArr = eval( $scope.aSimples );
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].asimpleValue === asimpleValue ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.aSimples.splice( index, 1 );		
	};
	/*
	 * add actor average
	 */
	$scope.aAverages = [];
	
	$scope.addRowaAverage = function(){	
		$scope.aAverages.push({ 'aAverageValue': $scope.aAverageValue });
		$scope.aAverageValue='';
	};
	
	$scope.removeaAverage = function(aAverageValue){
		var index = -1;		
		var comArr = eval( $scope.aAverages );
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].aAverageValue === aAverageValue ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.aAverages.splice( index, 1 );		
	};
	/*
	 * add actor comlpex
	 */
	$scope.aComplexs = [];
	
	$scope.addRowaComlpex = function(){	
		$scope.aComplexs.push({ 'aComlpexValue': $scope.aComlpexValue });
		$scope.aComlpexValue='';
	};
	
	$scope.removeaComplex = function(aComlpexValue){				
		var index = -1;		
		var comArr = eval( $scope.aComplexs );
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].aComlpexValue === aComlpexValue ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.aComplexs.splice( index, 1 );		
	};
	
	

	
	/*
	 * usecase point review
	 */
	$scope.review = function() {
		var data = $scope.getUsecasePointData();
		var url = "usecasepoint/review"
		$http.post(url, data).
		success(function(response) {
			$scope.totalPoint = response["totalPoint"];
			$scope.totalHour = response["totalHour"];
			$scope.totalCost = response["totalCost"];
		}).
		error(function() {
			console.log("Error");
		});
	};
	
	/*
	 * Usecasepoint finish
	 */
	$scope.finish = function() {
		var data = $scope.getUsecasePointData();
		var url = "usecasepoint/finish"
		$http.post(url, data).
		success(function(response) {
			$scope.totalPoint = response.totalPoint;
			$scope.totalHour = response.totalHour;
			$scope.totalCost = response.totalCost;
		}).
		error(function() {
			console.log("Error");
		});
	};
	
	// Get all data to send server
	$scope.getUsecasePointData = function() {
		var data = {};
		
		// get usecase
		data.usimple = null;
		var usimples = [];
		for (var i = 0; i < $scope.uSimples.length; i++) {
			usimples.push($scope.uSimples[i].uSimpleValue);
		}
		data.usimple = usimples;
		
		
		data.uaverage = null;
		var uaverages = [];
		for (var i = 0; i < $scope.uAverages.length; i++) {
			uaverages.push($scope.uAverages[i].uAverageValue);
		}
		data.uaverage = uaverages;
		
		data.ucomplex = null;
		var ucomlpexs = [];
		for (var i = 0; i < $scope.uComplexs.length; i++) {
			ucomlpexs.push($scope.uComplexs[i].uComplexValue);
		}
		data.ucomplex = ucomlpexs;
		
		// actor
		data.asimple = null;
		var asimples = [];
		for (var i = 0; i < $scope.aSimples.length; i++) {
			asimples.push($scope.aSimples[i].asimpleValue);
		}
		data.asimple = asimples;
		
		
		data.aaverage = null;
		var aaverages = [];
		for (var i = 0; i < $scope.aAverages.length; i++) {
			aaverages.push($scope.aAverages[i].aAverageValue);
		}
		data.aaverage = aaverages;
		
		data.acomplex = null;
		var acomlpexs = [];
		for (var i = 0; i < $scope.aComplexs.length; i++) {
			acomlpexs.push($scope.aComplexs[i].aComlpexValue);
		}
		data.acomplex = acomlpexs;
		
		//TCF variable
		data.distributed = $scope.distributedId;
		data.performance = $scope.performanceId;
		data.endUserefficiency = $scope.endUserefficiencyId;
		data.complexProcessing = $scope.complexProcessingId;
		data.easeofInstallation = $scope.easeofInstallationId;
		data.easeofUse = $scope.easeofUseId;
		data.portable = $scope.portableId;
		data.easeofChange = $scope.easeofChangeId;
		data.concurrentUse = $scope.concurrentUseId;
		data.specialSecurity = $scope.specialSecurityId;
		data.accessforThirdParties = $scope.accessforThirdPartiesId;
		data.trainingNeeds = $scope.trainingNeedsId;
		data.reusableCode = $scope.reusableCodeId;

		//Efc variable
		data.familiarwithDevelopmentProcess = $scope.familiarwithDevelopmentProcessId;
		data.applicationExperience = $scope.applicationExperienceId;
		data.objectOrientedExperience = $scope.objectOrientedExperienceId;
		data.leadAnalystCapability = $scope.leadAnalystCapabilityId;
		data.motivation = $scope.motivationId;
		data.stableRequirements = $scope.stableRequirementsId;
		data.partTimeStaff = $scope.partTimeStaffId;
		data.difficulProgrammingLanguage = $scope.difficulProgrammingLanguageId;
		
		data.hourPerTask = $scope.hourPerTask;
		data.payment = $scope.payment;
		data.projectId = $rootScope.projectId;
		
		return data;
	};
	
});