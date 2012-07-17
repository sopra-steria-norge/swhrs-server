// Declare global variables
var favLabelVar = null;
var hoursLabelVar = null;
var favVar = null;
var hoursVar = null;

var date = new Date();
var day = date.getDay();
var month = date.getMonth();
var year = date.getFullYear();

var favForm = null;
var hourForm = null;
var lunchForm = null;

var username = null;
var password = null;

var pageVar = "";
var pageVars = {}

// Constants
var MISSING = "missing";
var NO_FAV = "10 : ZZ";
var ZERO = 0;	

$(document).ready(function() {
	favLabelVar = $('#favLabel');
	hoursLabelVar = $('#hoursLabel');
	favVar = $('#fav');
	hoursVar = $('#hours');
	
	$('#hdrDay').children('h1').text('13.07.' + year);
	
	/*
	 * #loginPage
	 * Loginform submit 
	 * Sends the input username and password to the servlet(url: hours/login).
	 * If the username and password is approved, the function SuccessLogin is executed.
	 */
	$('#loginForm').submit(function(){
		var loginErr = false;
		var jsonLogin = {username: $('[name=username]').val() , password: $('[name=password]').val(), country: $('input[name=radioCountry]:checked').val()}	
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
	
	
	/*
	 * #dayPage
	 * Update button click
	 * Returns stored database hour registrations
	 */
	$("#updateBtn").click(function() {
		var today = $('#hdrDay').children('h1').text();
		getDayList();
	});
	
	
	/*
	 * #dayPage
	 * Daypageform submit
	 * Sends the input to the servlet(url: hours/registration)
	 * Stores the input as a HourRegistration object in the database
	 */
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
		projectNr = "2"; //favForm.split(':')[0];
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
	
	
	/*
	 * #dayPage
	 * Reset button click
	 * Clears the daypage form
	 */
	$('#resetDayBtn').click(function(){
		clearForm();
	});
	
	
	/*
	 * #dayPage
	 * Daylist click
	 * Deletes the hourRegistration entry from the database
	 */
	$('#dayList').on('click', 'li', function() {
	    var dayString = $(this).text();
	    var mySplitResult = dayString.split(":");
		$(this).remove();
		deleteRegistration(mySplitResult[0]);
	});
	
	
	/*
	 * #weekPage
	 * Period submit 
	 * Submits the period for approval
	 */
	$('#submitPeriod').click(function(){
		console.log("YEY");
	});
	
});


/*
 * Checks if the page is secured, if so checks if the user is authenticated.
 * If not, redirects to login page
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
    	loadWeekList();
    }
    
});


/*
 * SuccessLogin(data) - Executed after a successful login response
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
    

/*
 * postHourRegistration(mydata)
 * Sends hourRegistration data to the servlet
 */
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


/*
 * prevDay() & nextDay()
 * Resets the dayPage and updates date and previous registrations
 */
function prevDay(){
	var currDay = $('#hdrDay').children('h1').text();
	currDay = parseInt(currDay);
	var prevDay = currDay - 1;
	$('#hdrDay').children('h1').text(prevDay+".07.2012");
	resetDay();
}

function nextDay(){
	var currDay = $('#hdrDay').children('h1').text();
	currDay = parseInt(currDay);
	var nextDay = currDay + 1;
	$('#hdrDay').children('h1').text(nextDay+".07.2012");
	resetDay();
}


/*
 * prevWeek & nextWeek
 * Updates the period with new dates
 */
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


/*
 * loadWeekList()
 * Passes data to the servlet(Period)
 * If success: Loads new period data.  
 */
function loadWeekList(){
	var week = {week: $('#hdrWeek').children('h1').text()}
	$('#weekList').children().remove('li');
	console.log(week);
	$.ajax({
		type: "POST",
		url: 'hours/week',
		data: week,
		success: function(data){
			var dateArray = new Array();
			var hoursArray = new Array();
			var dayArray = new Array("Monday","Tuesday","Wednesday","Thursday","Friday");
			
			for (var key in data) {
				if (data.hasOwnProperty(key)) {
					dateArray.push(key);
					hoursArray.push(data[key]);
				} 
			}
			hoursArray.sort();
			dateArray.sort();
			splitArray(hoursArray, dayArray, dateArray);
		}
		
	});
}


/*
 * splitArray
 * Splits hourArray to only contain hours and be sorted.
 */
function splitArray(hourArray, dayArray, dateArray){
	var hSortArray = new Array();
	for(i=0; i<hourArray.length; i++){
		hSortArray.push(hourArray[i].substring(2));
	}
	updateWeekList(dayArray, dateArray, hSortArray);
}


/*
 * updateWeekList
 * Appends the new week entries to the weekList
 */
function updateWeekList(day, date, dayHours){
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
	$('#hdrDia').children('h1').text("Do you want to Submit?");
	$('#contentDia').children('p').text("You have registered "+total+" hours in period. Norm time is unknown.");
}


/*
 * updateDayList
 * Appends the new day entries to the dayList
 */
function updateDayList(fav, hour, lunch){
	var lunchText = "1 : Lunch";
	if(lunch == 1){
		$('#dayList').append($("<li></li>").html('<a href="" data-split-theme="c" data-split-icon="delete"><b>' +
	            lunchText + '</b><span class="ui-li-count"> 0.5 timer '+'</span></a><a href=""></a>')).listview('refresh');	
		$('#lunch').val(0);
		$('#lunch').slider('refresh');		
	}
	$('#dayList').append($("<li></li>").html('<a href="" data-split-theme="c" data-split-icon="delete"><b>' +
            fav + ' </b><span class="ui-li-count">' + hour + ' timer '+'</span></a><a href=""></a>')).listview('refresh');
}


/*
 * deleteRegistration()
 * sends an hourRegistration to the servlet to be deleted in the database
 */
function deleteRegistration(project_id){
	console.log(project_id);
	var delreg = {projectID: project_id}
	$.ajax({
		type: "POST",
		url: 'hours/delete',
		data: delreg,
		success: function(data){	
		}
	});
}


/*
 * getDayList()
 * Sends a date to the servlet to return all entries on a specific day
 */
function getDayList() {
	//Hardkoder inn prosjektene her for aa printe ut prosjektnavn, fjern dette naar vi har databaseoppslag
	var projects = {'10': 'ZZ', '1093094': 'LARM', '1112890': 'OSL CDM', '19': 'Javazone', '1337': 'Timeforing app', '19': 'Steria Intern', '1': 'Lunch'};
	
	/*$.ajax(function(){
		type: "POST",
		url: 'hours/list',
		data: week,
		success: function(data){
			for(var key in json){
				if(key === "1"){}
			}
			
		}
	});	*/
	
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


/*
 * resetDay() & clearForm()
 * Two functions to reset the daypage
 */
function resetDay(){
	$('#dayList').children().remove('li');
	$('#lunch').val(1);
	$('#lunch').slider('refresh');
	$('#hours').val(0);
	$('#hours').slider('refresh');
	$('#fav').val('').removeAttr('selected').find('option:first');
}

function clearForm(){
	$('#lunch').val(1);
	$('#lunch').slider('refresh');
	$('#hours').val(0);
	$('#hours').slider('refresh');
}