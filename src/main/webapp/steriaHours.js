// Declare global variables
var favLabelVar = null;
var hoursLabelVar = null;
var hoursVar = null;
var dateCounter = 0;
var deleted = false;

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
var pageVars = {};
var favList = [];
var favMap = {};
var favourite = 1;

// Constants
var MISSING = "missing";
var NO_FAV = "NO_FAV";
var ZERO = 0;
var today = "today";

//Constructor to make a Favourite object
function Favourite (projectnumber, activitycode, description, projectname, customername, billable, internalproject) {
    this.projectnumber;
    this.activitycode = activitycode;
    this.description = description;
    this.projectname = projectname;
    this.customername = customername;
    this.billable = billable;
    this.internalproject = internalproject;
}

$(document).ready(function() {
	
	favLabelVar = $('#favLabel');
	hoursLabelVar = $('#hoursLabel');
	var favVar = $('#fav');
	hoursVar = $('#hours');
	
	
	/*
	 * #loginPage
	 * Loginform submit 
	 * Sends the input username and password to the servlet(url: hours/login).
	 * If the username and password is approved, the function SuccessLogin is executed.
	 */
	$('#loginForm').submit(function(){
		var loginErr = false;
		var jsonLogin = {username: $('[name=username]').val() , password: $('[name=password]').val(), country: $('input[name=radioCountry]:checked').val()}	
		
		
		$.ajax({
			type:"POST",
			url: 'hours/login',
			data: jsonLogin,
			success: function(data){
				SuccessLogin(data);
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
		var today = "today"
		getDayList(today);
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
		hourForm = $("#hours").val();
		console.log("HOURSFORM: "+hourForm);
		if(favVar.val()==NO_FAV){
			favLabelVar.addClass(MISSING);
			err = true;
		}
		if(hourForm == ZERO){
			$('#hoursLabel').addClass(MISSING);
			err = true;
		}
		
		
		// If validation fails, show contentDialog
		if(err == true){
			console.log("Validation failed");
			return false;
		}
			
		var dateForm = $('#hdrDay').children('h1').text();
		favForm = $("#fav").val();
		hourForm = $("#hours").val();
		lunchForm = $("#lunch").val();
		projectNr = favForm.split(':')[0];
		activityCode = favForm.split(':')[1]
		description = favForm.split(':')[2];
		personId = 1;
		
		console.log('Fav: '+ favForm + ' Projectnr: ' + projectNr + ' actCode: ' + activityCode + ' description: ' + description);
		//updateDayList(favForm, hourForm, lunchForm);
			
		if (lunchForm == 1) {
			var myData = {'projectNr': projectNr, 'description': description, 'hours': hourForm, 'date': dateForm, 'lunchNumber': "1"};		
			postHourRegistration(myData);
		}else{
			var myData = {'projectNr': projectNr, 'description': description, 'hours': hourForm, 'date': dateForm, 'lunchNumber': "0"};		
			postHourRegistration(myData);
		}
		
		return false;
	});
	
	$('#searchForm').submit(function(){
		var inputSearch = $("#favSearch").val();
		console.log("FAV SEARCH: "+inputSearch);
		var search = {search: inputSearch}
		$.ajax({
			type:"POST",
			url: 'hours/searchFavourites',
			data: search,
			success: function(data){
				console.log(data);
				for (var key in data) {
					if (data.hasOwnProperty(key)) {
						console.log(key);
					}
				}
				
			}
		});
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
	    console.log(mySplitResult[0]);
	    if(mySplitResult[0] == "Total hours"){
	    	console.log("Cannot delete this");
	    }else{
	    	deleteRegistration(mySplitResult[0]);
			if(deleted){
				$(this).remove();
				resetDay();
				getDayList("today");
			}else{
				$.mobile.changePage($("#dialogPopUpNoDelete"));
			}
	    }
		
	});
	
	$('#weekList').on('click', 'li', function() {
	    var dayString = $(this).html();
	    var index = dayString.indexOf('<p class="ui-li-desc">')+'<p class="ui-li-desc">'.length;
	    var dateString = dayString.substring(index, index+10); 
	    
		$.mobile.changePage($("#dayPage"));
		resetDay();
		getDayList(dateString);
	});
	
	
	/*
	 * #weekPage
	 * Period submit 
	 * Submits the period for approval
	 */
	$('#submitPeriod').click(function(){
	});
	
	$('.dayLink').bind('click', function() {
		  console.log('User clicked on "dayLink."');
		  resetDay();
		  getDayList(today);
	});
	
});

/*
 * #dayPage
 * This will be run each time a dayPage is initiated
 */
$('#dayPage' ).live( 'pageinit',function(event){
	getFavouriteList(fillSelectMenuInDayPage);
	getDayList(today);
});

/*
 * #weekPage
 * This will be run each time a weekPage is initiated
 */
$('#weekPage' ).live( 'pageinit',function(event){
	//fill in code here
});

/*
 * #favPage
 * This will be run each time a favPage is initiated
 */
$('#favPage' ).live( 'pageinit',function(event){
	getFavouriteList(fillListInFavPage);
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
    			console.log("HAS COOKIES" +localStorage.getItem("UNameLSKey"));
    			//setUsername(localStorage.getItem("UNameLSKey"));
    		}
    	}
    }
    if (typeof data.toPage != "string" && data.toPage.attr("id") == "weekPage") {
    	var thisWeek = "thisWeek";
    	getWeekList(thisWeek);
    }
});



function setUsername(username){
	var details = {UN: username}
	$.ajax({
		type:"POST",
		url: 'hours/setUsername',
		data: details,
		success: function(){
			var today = "today";
			console.log("DOES IT WORK?");
		}
	});
}

/*
 * SuccessLogin(data) - Executed after a successful login response
 * Checks if the response data is null, if not return to the page requested before login.
 */
function SuccessLogin(data) {
    if (data != null) {
    	localStorage.setItem('UNameLSKey', "AK");
    	
    	if (pageVars && pageVars.returnAfterLogin) {
            	$.mobile.changePage(pageVars.returnAfterLogin.toPage);
        }else{
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
			var today = "today";
			getDayList(today);
			resetDay();
		}
	});
}


/*
 * prevDay() & nextDay()
 * Resets the dayPage and updates date and previous registrations
 */
function prevDay(){
	var prevDay = "prevDay"
	$('#lunch').val(1);
	getDayList(prevDay);
	resetDay();
}

function nextDay(){
	var nextDay = "nextDay"
	$('#lunch').val(1);
	getDayList(nextDay);
	resetDay();
}


/*
 * prevWeek & nextWeek
 * Updates the period with new dates
 */
function prevWeek(){
	var prevWeek = "prevWeek";
	getWeekList(prevWeek);
}

function nextWeek(){
	var nextWeek = "nextWeek";
	getWeekList(nextWeek);
}


/*
 * loadWeekList()
 * Passes data to the servlet(Period)
 * If success: Loads new period data.  
 */
function getWeekList(newWeek){
	var weekDays = new Array("", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
	var week = {week: newWeek};
	$('#weekList').children().remove('li');
	$.ajax({
		type: "POST",
		url: 'hours/week',
		data: week,
		success: function(data){
			var dateArray = new Array();
			var hoursArray = new Array();
			var dayArray = new Array("Monday","Tuesday","Wednesday","Thursday","Friday");
			var dataArray = new Array();
			for (var key in data) {
				if (data.hasOwnProperty(key)) {
					if(key === "weekNumber"){	
						$('#hdrWeek').children('h1').text(data[key]);
					}else if(key === "dateHdr"){
						var hdrDayText = data[key].split(' ')[0];
						var hdrDateText = data[key].split(' ')[1];
						var dateText = hdrDateText.split('-')[2]+"."+hdrDateText.split('-')[1]+"."+hdrDateText.split('-')[0]
						$('#hdrDay').children('h1').text(weekDays[hdrDayText]+" "+ dateText);
					}else{
						dataArray.push(data[key][0], key, data[key][1]);
						dateArray.push(key);
						hoursArray.push(data[key]);
					}
				} 
			}
			updateWeekList(dateArray.sort(), data);
		}
		
	});
}

/*
 * updateWeekList
 * Appends the new week entries to the weekList
 */
function updateWeekList(dateArray, data){
	var weekDays = new Array("", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
	var dayHours = new Array();
	for (var i = 0; i < dateArray.length; i++) {
		var dayNr = data[dateArray[i]][0];
		var hours = data[dateArray[i]][1];
		dayHours.push(hours);
		if(hours < 8){
			$('#weekList').append($("<li data-theme='c'></li>").html('<a href=""><b>' + 
						weekDays[dayNr] + '</b><p>'+dateArray[i]+'</p></a><span class="ui-li-count">' + hours + ' timer'+'</span>')).listview('refresh');
			}else{
			$('#weekList').append($("<li data-theme='c'></li>").html('<a href=""><b>' +
					weekDays[dayNr] + '</b><p>'+dateArray[i]+'</p></a><span class="ui-li-count">' + hours + ' timer'+'</span>')).listview('refresh');
		}
	}
	
	var totalWeek = 0;
	$.each(dayHours,function() {
	    totalWeek += parseFloat(this);
	});
	$('#weekDescription').children('b').text("You have logged "+totalWeek+" hours this week");
	$('#hdrDia').children('h1').text("Do you want to Submit?");
	$('#contentDia').children('p').text("You have registered "+totalWeek+" hours in period. Norm time is unknown.");
}


/*
 * updateDayList
 * Appends the new day entries to the dayList
 */
function updateDayList(fav, hour, lunch){
	var lunchText = "1 : Lunch";
	var totalDay = 0;
		
	$.each(hour,function() {
	    totalDay += (this);
	});
	console.log(totalDay)
	
	$('#dayDescription').text("Total "+totalDay+" hours");
	
	if(lunch == 1){
		$('#dayList').append($("<li></li>").html('<a href="#" data-split-theme="c" data-split-icon="delete"><b>' +
	            lunchText + '</b><span class="ui-li-count"> 0.5 timer '+'</span></a><a href=""></a>')).listview('refresh');	
		$('#lunch').val(0);
		$('#lunch').slider('refresh');
		
	}
	$('#dayList').append($("<li></li>").html('<a href="#" data-split-theme="c" data-split-icon="delete"><b>' +
            fav + ' </b><span class="ui-li-count">' + hour + ' timer '+'</span></a><a href=""></a>')).listview('refresh');
	$('#hours').val(0);
	$('#hours').slider('refresh');
}


/*
 * deleteRegistration()
 * sends an hourRegistration to the servlet to be deleted in the database
 */
function deleteRegistration(taskNr, listid){
	var delreg = {taskNumber: taskNr}
	$.ajax({
		type: "POST",
		url: 'hours/delete',
		data: delreg,
		success: function(data){
			if (data.indexOf('Already submitted') != -1) {
				deleted = false;
			} else {
				deleted = true;
			}
		},
		async: false
	});
}


function getFavouriteList(addToPage){
	favMap = {};
	favList = [];
	$.ajax({
		type: "POST",
		url: 'hours/favourite',
		success: function(data){
			for(var key in data){
				var jsonMap = data[key];
				var newFav = new Favourite(jsonMap['projectnumber'], jsonMap['activitycode'], jsonMap['description'], jsonMap['projectname'], jsonMap['customername'], jsonMap['billable'], jsonMap['internalproject']);
				
				favMap[key] = newFav;
				var favtext = newFav.projectname + " ("+ newFav.activitycode + ") " + newFav.description;
				favList.push(favtext);
			}
//			favList.sort();
			addToPage(favList);
		},
		error: function(data){
			console.log("favourite list error");
		},
		async: false
	});
}

function fillListInFavPage(favlist) {
	for (var i = 0; i < favList.length; i++) {
		var favs = favList[i];
		
		$('#favList').append($("<li></li>").html('<a href="#" data-split-theme="c" data-split-icon="delete"><b>' +
	            favs + ' </b></a><a href=""></a>'));

		
	}
	
	$('#favList').listview('refresh');
}

function fillProjectList(projectList){
	$('#favList').children().remove('li');
	for(var i=0; i < projectList.length; i++){
		var projects = projectList[i];
		$('#favList').append($("<li></li>", {id:""}).html('<a href="#" data-split-theme="c" data-split-icon="add"><b>' +
				projects + ' </b></a><a href=""></a>'));
	}
	$('#favList').listview('refresh');
}

function addFavourites(pNr, aC){
	var favourite = {'projectNumber': pNr, 'activityCode': aC}
	}
	$.ajax({
		type:"POST",
		url: 'hours/addFavourites',
		data: favourite,
		success: function(data){
			console.log(data);
			for (var key in data) {
				if (data.hasOwnProperty(key)) {
					console.log(key);
				}
			}
			
		}
	});
}

function fillSelectMenuInDayPage(favList){
		var options = "NO_FAV";
		var select = "Select a favourite"
		$('#fav').append('<option value='+options+'>'+select+'</option>').selectmenu('refresh', true);
		for (var i = 0; i < favList.length; i++) {
			var favs = favList[i];
			$('#fav').append('<option value='+favs+'>'+favs+'</option>').selectmenu('refresh', true);
		    
		}
}

/*
 * getDayList()
 * Sends a date to the servlet to return all entries on a specific day
 */
function getDayList(newDay) {
	//Hardkoder inn prosjektene her for aa printe ut prosjektnavn, fjern dette naar vi har databaseoppslag
	//var projects = {'10': 'ZZ', '1093094': 'LARM', '1112890': 'OSL CDM', '19': 'Javazone', '1337': 'Timeforing app', '11': 'Steria Intern', '1': 'Lunch'};
	var weekDays = new Array("", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
	if(newDay == 1)getFavouriteList(fillSelectMenuInDayPage);
	var totalHours = 0;
	var newDay = {day: newDay};
	$.ajax({
		type: "POST",
		url: 'hours/daylist',
		data: newDay,
		success: function(data){
			for (var key in data) {
				if (key.split(':')[1] === "Lunsj") {
					$('#lunch').val(0);
				}
				$('#lunch').slider('refresh');
				if(key === "date"){
					var hdrDayText = data[key].split(' ')[0];
					var hdrDateText = data[key].split(' ')[1];
					var dateText = hdrDateText.split('-')[2]+"."+hdrDateText.split('-')[1]+"."+hdrDateText.split('-')[0]
					$('#hdrDay').children('h1').text(weekDays[hdrDayText]+" "+ dateText);
				}else{
				totalHours += data[key];	
				$('#dayList').append($("<li></li>").html('<a href="#" data-split-theme="c" data-split-icon="delete"><b>' +
			            key+' </b><span class="ui-li-count">' + data[key] + ' hours '+'</span></a><a href=""></a>')).listview('refresh');
				}
			}
			if(totalHours != 0){
				$('#dayList').prepend($("<li></li>").html('Total hours: <span class="ui-li-count">' + totalHours + ' hours '+'</span>')).listview('refresh');
			}
		},
		error: function(data){
			console.log("dayList error");
		}
	});	
}

/*
 * resetDay() & clearForm()
 * Two functions to reset the daypage
 */
function resetDay(){
	$('#dayList').children().remove('li');
	$('#hours').val(0);
	$('#hours').slider('refresh');
}

function clearForm(){
	$('#lunch').val(1);
	$('#lunch').slider('refresh');
	$('#hours').val(0);
	$('#hours').slider('refresh');
}