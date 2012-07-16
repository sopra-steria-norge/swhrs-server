// Declare global variables
// Remember 
/*
 	$('#hdrDay').children('h1').text('new text');
  
 */
	var hdrDayVar = null;
	var contentDayVar = null;
	var ftrDayVar = null;
	var favLabelVar = null;
	var hoursLabelVar = null;
	var dayformVar = null;
	var favVar = null;
	var hoursVar = null;
	
	var date = new Date();
	var day = date.getDay();
	var month = date.getMonth();
	var year = date.getFullYear();
	

	var contentTransitionVar = null;
	var confirmationVar = null;
	var contentDialogVar = null;
	var hdrConfirmationVar = null; 
	var contentConfirmationVar = null;
	var ftrConfirmationVar = null;
	
	var favForm = null;
	var hourForm = null;
	var lunchForm = null;
	
	var myDate = null;
	var dates = null;
	
	var username = null;
	var password = null;
	
	var pageVar = "";
	var pageVar2 = "weekPage";
	var pageVars = {}
	
	
	// Constants
	var MISSING = "missing";
	var NO_FAV = "10 : ZZ";
	var EMPTY = "";
	var ZERO = 0;	

$(document).ready(function() {
	//Assign gloabal variables
	hdrDayVar = $('#hdrDay');
	contentDayVar = $('#contentDay');
	ftrDayVar = $('#ftrDay');
	favLabelVar = $('#favLabel');
	hoursLabelVar = $('#hoursLabel');
	dayformVar = $('#dayForm');
	favVar = $('#fav');
	hoursVar = $('#hours');

	//var dayArray=new Array("Monday","Tuesday","Wednesday", "Thursday");
	//var dateArray=new Array("02.07.2012","03.07.2012","04.07.2012", "06.07.2012");
	//var hourArray=new Array(2,7,8, 8);
	//updateWeekList(dayArray, dateArray, hourArray);
	
	hdrDayVar.children('h1').text('13.07.' + year);
	
	
	/*
	 * Loginform submit 
	 * Sends the input username and password to the servlet(url: hours/login).
	 * If the username and password is approved, the function SuccessLogin is executed.
	 */
	$('#loginForm').submit(function(){
		var loginErr = false;
		var jsonLogin = {username: $('[name=username]').val() , password: $('[name=password]').val(), country: $('[name=radioCountry]').val()}	
		console.log(jsonLogin);
		
		$.ajax({
			type:"POST",
			url: 'hours/login',
			data: jsonLogin,
			success: function(data){
				SuccessLogin(data);
				console.log(data);
			},
			error: function(data){
				$('#loginErr').text("Wrong username/password");
			}
		});
		return false;
	});
	
	$("#updateBtn").click(function() {
		var today = $('#hdrDay').children('h1').text();
		console.log(today);
		getDayList();
	});
	
	$('#dayForm').submit(function(){
		var err = false;
		// Reset highlighted form elements
		favLabelVar.removeClass(MISSING);
		hoursLabelVar.removeClass(MISSING);
		$.mobile.silentScroll(100);

		
		if(favVar.val()==NO_FAV){
			favLabelVar.addClass(MISSING);
			err = true;
		}
		if(hoursVar.val()==ZERO){
			hoursLabelVar.addClass(MISSING);
			err = true;
		}
		
		// If validation fails, show contentDialog
		if(err == true){
			console.log("Validation failed")
			return false;
		}
			
		var dateForm = $('#hdrDay').children('h1').text();
		favForm = $("#fav").val();
		hourForm = $("#hours").val();
		lunchForm = $("#lunch").val();
		projectNr = favForm.split(':')[0];
		personId = 1;
		
		updateDayList(favForm, hourForm, lunchForm);		
		
		if (lunchForm == 1) {
			var myData = {'personId': personId, 'projectNr': "1", 'hours': "0.5", 'date': dateForm};		
			postHourRegistration(myData);
		}
		var myData = {'personId': personId, 'projectNr': projectNr, 'hours': hourForm, 'date': dateForm};		
		postHourRegistration(myData);
		
		return false;
	});
	
	$('#resetDayBtn').click(function(){
		resetDay2();
	});
	
	
	
});


/*
 * Checks if the page is secured, if so checks if the user is authenticated.
 */
$(document).bind("pagebeforechange", function (event, data) {
    if (typeof data.toPage == 'object' && data.toPage.attr('data-needs-auth') == 'true') {
    	console.log("data-needs-auth");
    	if (!sessionStorage.getItem("UNameLSKey")) {
    		console.log("hasnosessionstorage");
    		if (!localStorage.getItem("UNameLSKey")) {
    			console.log("hasnolocalstorage");
    			console.log(data.toPage.attr('id'));
    			pageVar = data.toPage.attr('id');
    			pageVars.returnAfterLogin = data;
    			event.preventDefault();
    			$.mobile.changePage("#loginPage", { changeHash: false });
    		}else{
    			sessionStorage.setItem('UNameLSKey', localStorage.getItem("UNameLSKey"));
    		}
    	}
    }
    if (typeof data.toPage != "string" && data.toPage.attr("id") == "weekPage") {
    	console.log("to weekPage");
    	loadWeekList();
    }
    
});

/*
 * SuccessLogin - Executed after a successful login response
 * Checks if the response data is null, if not return to the page requested before login.
 */
function SuccessLogin(data) {
    if (data != null) {
    	localStorage.setItem('UNameLSKey', "Steriatime");
    	
    	if (pageVars && pageVars.returnAfterLogin) {
            	$.mobile.changePage(pageVars.returnAfterLogin.toPage);
            }
            else {
                $.mobile.changePage("#weekPage", { changeHash: false });
            }
	}
}
    

function postHourRegistration(myData) {
	$.ajax({
		type:"POST",
		url: 'hours/registration',
		data: myData,
		success: function(){
			console.log(myData);
		}
	});
}


function prevDay(){
	var currDay = $('#hdrDay').children('h1').text();
	currDay = parseInt(currDay);
	var prevDay = currDay - 1;
	//$('#hdrH1').text(dates+1);
	//$('#hdrH1').text(prevDay+"/"+myDate.getMonth()+"/"+myDate.getFullYear());
	$('#hdrDay').children('h1').text(prevDay+".07.2012");
}

function nextDay(){
	var currDay = $('#hdrDay').children('h1').text();
	currDay = parseInt(currDay);
	var nextDay = currDay + 1;
	$('#hdrDay').children('h1').text(nextDay+".07.2012");
	resetDay();
	
}

function prevWeek(){
	var currDay = $('#hdrWeek').children('h1').text();
	currDay = parseInt(currDay);
	var prevDay = currDay - 1;
	$('#hdrWeek').children('h1').text(prevDay);
}

function nextWeek(){
	var currDay = $('#hdrWeek').children('h1').text();
	currDay = parseInt(currDay);
	var prevDay = currDay + 1;
	$('#hdrWeek').children('h1').text(prevDay);
	
}

function loadWeekList(){
	var week = {week: $('#hdrWeek').children('h1').text()}
	$('#weekList').children().remove('li');
	console.log(week);
	$.ajax({
		type: "POST",
		url: 'hours/week',
		data: week,
		success: function(data){
			console.log('loadWeekList success');
			console.log(data);
			var dateArray = new Array();
			var hoursArray = new Array();
			var dayArray = new Array("Monday","Tuesday","Wednesday","Thursday","Friday");
			
			for (var key in data) {
				if (data.hasOwnProperty(key)) {
					dateArray.push(key);
					hoursArray.push(data[key]);
					console.log("dato"+key);
					console.log("timer"+data[key]);
				} 
			}
			hoursArray.sort();
			dateArray.sort();
			splitArray(hoursArray, dayArray, dateArray);
		}
		
	});
}

function splitArray(hourArray, dayArray, dateArray){
	var hSortArray = new Array();
	console.log(hourArray.length);
	for(i=0; i<hourArray.length; i++){
		hSortArray.push(hourArray[i].substring(2));
	}
	updateWeekList(dayArray, dateArray, hSortArray);
}


function updateWeekList(day, date, dayHours){
	console.log($('#weekList'));
	for(i=0; i<day.length; i++){
		console.log("Dag: "+day[i]+" - Dato: "+date[i]+" - dayHours: "+dayHours[i]);
	}
	
	for(i=0; i<day.length; i++){
		if(dayHours[i] < 8){
			$('#weekList').append($("<li data-theme='c'></li>").html('<a href="#dayPage"><b>' + 
						day[i] + '</b><p>'+date[i]+'</p></a><span class="ui-li-count">' + dayHours[i] + ' timer'+'</span>')).listview('refresh');
			}else{
			$('#weekList').append($("<li></li>").html('<a href="#dayPage"><b>' + 
					day[i] + '</b><p>'+date[i]+'</p></a><span class="ui-li-count">' + dayHours[i] + ' timer'+'</span>')).listview('refresh');
		}
	}
	
	var total = 0;
	$.each(dayHours,function() {
	    total += parseFloat(this);
	});
	$('#weekDescription').children('b').text("You have logged "+total+" hours this week");
	
}

function updateDayList(fav, hour, lunch){
	var lunchText = "Lunch";
	if(lunch == 1){
		$('#dayList').append($("<li></li>").html('<a href="" data-split-theme="c" data-split-icon="delete"><b>' +
	            lunchText + '</b><span class="ui-li-count"> 0.5 timer '+'</span></a><a href="#weekPage"></a>')).listview('refresh');	
		$('#lunch').val(0);
		$('#lunch').slider('refresh');		
	}
	$('#dayList').append($("<li></li>").html('<a href="" data-split-theme="c" data-split-icon="delete"><b>' +
            fav + '</b><span class="ui-li-count">' + hour + ' timer '+'</span></a><a href="#weekPage"></a>')).listview('refresh');
//	$('.deleteMe').live('click', function(){
//	    $(this).parent().remove();
//	    $('#dayList').listview('refresh');
//	});
}


function getDayList() {
	//Hardkoder inn prosjektene her for aa printe ut prosjektnavn, fjern dette naar vi har databaseoppslag
	var projects = {'10': 'ZZ', '1093094': 'LARM', '1112890': 'OSL CDM', '19': 'Javazone', '1337': 'Timeforing app', '19': 'Steria Intern', '1': 'Lunch'};
	
	/*$.ajax(function(){
		type: "POST",
		url: 'hours/list',
		data: week,
		success: function(data){
			
		}
	});*/	
	
	$.getJSON("hours/list", function(json) {
		for(var key in json) {
			if (key === "1") {
				$('#lunch').val(0);
				$('#lunch').slider('refresh');	
			}
			$('#dayList').append($("<li></li>").html('<a href="#dialogPopUp" data-rel="dialog" data-transition="pop" data-role="button"><b>' +
					key+" : "+projects[key] + '</b><span class="ui-li-count">' + json[key] + ' timer '+'</span></a>')).listview('refresh');
		}
	});
}


function resetDay(){
	$('#dayList').children().remove('li');
//	$('#lunch').val(1);
	$('#lunch').slider('refresh');
	$('#hours').val(0);
	$('#hours').slider('refresh');
	$('#fav').val('').removeAttr('selected').find('option:first');
//	$('#fav').val('').removeAttr('checked').removeAttr('selected');
//	$('#fav').val('10 : ZZ').removeAttr('checked').removeAttr('selected');
}

function resetDay2(){
	$('#lunch').val(1);
	$('#lunch').slider('refresh');
	$('#hours').val(0);
	$('#hours').slider('refresh');
	//$('#fav').val('').removeAttr('checked').removeAttr('selected');
}

/*document.ontouchmove = function(e){
    e.preventDefault();
}*/	
