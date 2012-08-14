// Declare global variables
var pageVar = "";
var pageVars = {};

//favlist contains a list of Strings to displau favourites in lists, it's filled in getFavourites
var favList = [];
/**
 * favMap: In the function getFavourites this is set to contain all the favourites
 * as Favourite objects, stored with incremental numbers as keys
 */
var favMap = {}; 

/**
 * favDescription is used to map projects and activitycodes with a projectdescription. 
 * It's set in the function getFavourites and used to display projectdescription for Hour Registrations in dayList
 */
var favDescriptions = {};
//regMap is filled with Hour Registration objects in getDayList
var regMap = {};
var editTaskNumber = null;

// Constants
var MISSING = "missing";
var NO_FAV = "NO_FAV";
var ZERO = 0;
var EMPTY = "";
var today = "today";

//Constructor to make a Favourite object
function Favourite (projectnumber, activitycode, description, projectname, customername, billable, internalproject) {
    this.projectnumber = projectnumber;
    this.activitycode = activitycode;
    this.description = description;
    this.projectname = projectname;
    this.customername = customername;
    this.billable = billable;
    this.internalproject = internalproject;
}

function HourRegistration (tasknumber, projectnumber, activitycode, description, hours, submitted, approved, projectDescription) {
	this.tasknumber = tasknumber;
	this.projectnumber = projectnumber;
	this.activitycode = activitycode;
	this.description = description; //The description of the Time Entry
	this.hours = hours;
	this.submitted = submitted;
	this.approved = approved;
	this.projectDescription = projectDescription; //The decription of the project
}

function Project (projectnumber, activitycode, description){
	this.projectnumber = projectnumber;
	this.activitycode = activitycode;
	this.description = description;
}

$(document).ready(function() {
	
	var favLabelVar = $('#favLabel');
	var hoursLabelVar = $('#hoursLabel');
	var favVar = $('#fav');
	var hoursVar = $('#hours');
	
	var myDate=new Date();
	dateT = myDate.getDate();
	monthT = myDate.getMonth();
	if(monthT < 10){
		monthT = "0"+monthT;
	}
	yearT = myDate.getFullYear();
	
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
	 * Editing of registrations in dayList
	 * Listens to click of a list element in dayList
	 */
	$('#editReg').click(function(){
		var err = false;
		
		descLabelVar = $('#descEditLabel');
		hourEditVar = $('#hoursEditLabel');
		// Reset highlighted form elements
		descLabelVar.removeClass(MISSING);
		hourEditVar.removeClass(MISSING);
		
		var editHours = $('#editHours').val();
		var editDesc = $('#editDesc').val();
		
		if(editDesc==EMPTY){
			descLabelVar.addClass(MISSING);
			err = true;
		}
		if(editHours == ZERO){
			hourEditVar.addClass(MISSING);
			err = true;
		}
		
		// Validation error of input fields
		if(err == true){
			return false;
		}
		
		var edit = {'taskNumber': editTaskNumber, 'hours': editHours, 'description': editDesc};
		$.ajax({
			type:"POST",
			url: 'hours/updateRegistration',
			data: edit,
			success: function(data){
				resetDay();
				getDayList(today);
			}
		});
	});
	
	/*
	 * #dayPage
	 * Daypageform submit
	 * Sends the input to the servlet(url: hours/registration)
	 * Stores the input as a HourRegistration object in the database
	 */
	$('#dayForm').submit(function() {
		var err = false;
		// Reset highlighted form elements
		favLabelVar.removeClass(MISSING);
		hoursLabelVar.removeClass(MISSING);
		$.mobile.silentScroll(100);
		hourForm = $("#hours").val();
		
		if(favVar.val()==NO_FAV){
			favLabelVar.addClass(MISSING);
			err = true;
		}
		if(hourForm == ZERO){
			$('#hoursLabel').addClass(MISSING);
			err = true;
		}
		
		
		// Validation error of input fields
		if(err == true){
			return false;
		}
			
		var dateForm = $('#hdrDay').children('h1').text();
		var favForm = $("#fav").val();
		var hourForm = $("#hours").val();
		var lunchForm = $("#lunch").val();
		var selectedFav = favMap[favForm]; //The Favourite object selected in the select box
		
		var lunchCodeString = '';
		if (lunchForm == 1) {
			lunchCodeString = "1";
		}else{
			lunchCodeString = "0";
		}
		
		var myData = {'projectNr': selectedFav.projectnumber, 'activityCode': selectedFav.activitycode, 
				'description': selectedFav.description, 'billable': selectedFav.billable, 
				'internalproject': selectedFav.internalproject, 'hours': hourForm, 'date': dateForm, 
				'lunchNumber': lunchCodeString};
		postHourRegistration(myData);
		return false;
	});
	
	/*
	 * Listens to a click of "Search Projects" in the Fav page
	 * Gets search data from the server and displays results in a a list. SQL statement is set to return maximum 50 results
	 */
	$('#favBtn').click(function(){
		var inputSearch = $("#favSearch").val();
		var search = {search: inputSearch}
		
		$.ajax({
			type:"POST",
			url: 'hours/searchFavourites',
			data: search,
			success: function(data){
				fillProjectList(data);
			}
		});
	});
	
	/*
	 * #weekPage
	 * Listens to clicks on list elements in weekPage (Monday, Tuesday etc.)
	 * If a day is clicked it navigates to that day in dayView
	 */
	$('#weekList').on('click', 'li', function() {
	    var dayString = $(this).html();
	    var index = dayString.indexOf('<p class="ui-li-desc">')+'<p class="ui-li-desc">'.length;
	    var dateString = dayString.substring(index, index+10); 
	    
		$.mobile.changePage($("#dayPage"));
		resetDay();
		getDayList(dateString);
	});
	
	/*
	 * Listens to click on the "Day" button in the footer
	 * Resets the view and gets the list for current day
	 */
	$('.dayLink').bind('click', function() {
		  resetDay();
		  getDayList(today);
	});
	
	/*
	 * Listens to clicks on the "Fav" button in the footer
	 * Removes any previous content and displays current favourites
	 */
	$('.favLink').bind('click', function(){
		$('#favText').text("User favourites");
		$('#projectList').children().remove('li');
		$('#favList').children().remove('li');
		getFavouriteList(fillListInFavPage);
		$('#favList').listview('refresh');
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
 * Checks if the page is secured, if so checks if the user is authenticated.
 * If not, redirects to login page
 */
$(document).bind("pagebeforechange", function (event, data) {
    if (typeof data.toPage == 'object' && data.toPage.attr('data-needs-auth') == 'true') {
    	console.log("User needs to be authenticated to reach this page");
    	if (!sessionStorage.getItem("UNameLSKey")) {
    		console.log("User have no sessionstorage");
    		if (!localStorage.getItem("UNameLSKey")) {
    			console.log("User have no localstorage");
    			pageVar = data.toPage.attr('id');
    			pageVars.returnAfterLogin = data;
    			event.preventDefault();
    			$.mobile.changePage("#loginPage", { changeHash: false });
    		}else{
    			sessionStorage.setItem('UNameLSKey', localStorage.getItem("UNameLSKey"));
    			//THIS IS WHERE THE USER HAVE THEIR INFORMATION ALLREADY STORED, AND IS AUTOMATICALLY REDIRECTED TO THE DAYPAGE.
    			//THIS IS WHERE THE USERNAME NEEDS TO BE SENT TO THE SERVLET
    			//setUsername(localStorage.getItem("UNameLSKey"));
    		}
    	}
    }
    if (typeof data.toPage != "string" && data.toPage.attr("id") == "weekPage") {
    	var thisWeek = "thisWeek";
    	getWeekList(thisWeek);
    }
});

/*
 * Should set username in cookies and server. Not working properly, and is not being used
 */
function setUsername(username){
	var details = {UN: username}
	$.ajax({
		type:"POST",
		url: 'hours/setUsername',
		data: details,
		success: function(){
			var today = "today";
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
 * Used to submit a period for approval. Functionality to reopen a period has not yet been made. 
 * Change option value to 0 to reopen the period
 */
function updatePeriod(){
	options = {'option': 1};
	$.ajax({
		type:"POST",
		url: 'hours/updatePeriod',
		data: options,
		success: function(data){
		},
		async: false
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


/**
 * gets the weekList for the previous week. Used when the user navigates to previous week through the button "Prev" in header of week view
 * Updates the period in header with new dates
 */
function prevWeek(){
	var prevWeek = "prevWeek";
	getWeekList(prevWeek);
}

/**
 * gets the weekList for next week. Used when the user navigates to next week through the button "Next" in header of week view
 * Updates the period in header with new dates
 */
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
						dataArray.push(data[key][0], key, data[key][1], data[key][2]);
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
	var submitted = false;
	for (var i = 0; i < dateArray.length; i++) {
		var dayNr = data[dateArray[i]][0];
		var hours = data[dateArray[i]][1];
		dayHours.push(hours);
		if(data[dateArray[i]][2] == 1 || submitted){
			submitted = true;
			$('#weekList').append($("<li data-theme='c' data-icon='check'></li>").html('<a href=""><b>' + 
						weekDays[dayNr] + '</b><p>'+dateArray[i]+'</p></a><span class="ui-li-count">' + hours + ' hours'+'</span>')).listview('refresh');
			}else{
			$('#weekList').append($("<li data-theme='c'></li>").html('<a href=""><b>' +
					weekDays[dayNr] + '</b><p>'+dateArray[i]+'</p></a><span class="ui-li-count">' + hours + ' hours'+'</span>')).listview('refresh');
		}
	}
	var totalWeek = 0;
	$.each(dayHours,function() {
	    totalWeek += parseFloat(this);
	});
	var norm = 0;
	if(dateArray.length < 5){
		norm = dateArray.length * 8;
	}else{
		norm = 40;
	}
	$('#weekDescription').children('b').text("You have logged "+totalWeek+" hours this week");
	$('#hdrDia').children('h1').text("Do you want to Submit?");
	$('#contentDia').children('p').text("You have registered "+totalWeek+" hours in period. Norm time is "+norm+" hours.");
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
		url: 'hours/deleteRegistration',
		data: delreg,
		success: function(data){
			if (data.indexOf('Already submitted') != -1) {
				$.mobile.changePage($("#dialogPopUpNoDelete"));
			} else {
				$('#reg' + taskNr).remove();
				resetDay();
				getDayList("today");
			}
		},
		async: false
	});
}

/**
 * This method is called when the user clicks a list element in the dayList which can
 * @param tasknumber The tasknumber of the time entry in the database
 */
function editRegistration(tasknumber) {
	editTaskNumber = tasknumber;
	$.mobile.changePage($("#dialogEditReg"));
	$('#editDesc').val(regMap[tasknumber].description);
	$('#editHours').val(regMap[tasknumber].hours);
	$('#editHours').slider('refresh');
}

function getFavouriteList(addToPage){
	favMap = {};
	favList = [];
	favDescription = {};
	$.ajax({
		type: "POST",
		url: 'hours/favourite',
		success: function(data){
			for(var key in data){
				var jsonMap = data[key];
				var newFav = new Favourite(jsonMap['projectnumber'], jsonMap['activitycode'], jsonMap['description'], jsonMap['projectname'], jsonMap['customername'], jsonMap['billable'], jsonMap['internalproject']);
				
				favMap[key] = newFav;
				favDescription[jsonMap['projectnumber'] + '<:>' + jsonMap['activitycode']] = newFav;
				var favtext = newFav.projectname + " ("+ newFav.activitycode + ") " + newFav.description;
				favList.push(favtext);
			}
			addToPage(favList);
		},
		error: function(data){
		},
		async: false
	});
}

function fillListInFavPage(favlist) {
	for (var i = 0; i < favList.length; i++) {
		var favs = favList[i];
		$('#favList').append($('<li id="fav:' + i +'"></li>').html('<a href="#" data-split-theme="c" data-split-icon="delete"><b>' +
	            favs + ' </b></a><a href="javascript:deleteFavourite('+i+')"></a>'));
	}
	$('#favList').listview('refresh');
	$('#favText').text("Current favourites");
	
}

function fillProjectList(data){
	$('#favList').children().remove('li');
	$('#projectList').children().remove('li');
	for (key in data) {
		var jsonMap = data[key];
		var projects = jsonMap['projectnumber'] + " ("+ jsonMap['activitycode'] + ") " + jsonMap['description'];
		var pNr = jsonMap['projectnumber'];
		var aC = jsonMap['activitycode'];
		$('#projectList').append($("<li></li>", {id:""}).html('<a href="#" data-split-theme="c"><b>' +
				projects + ' </b></a><a href="" onclick="javascript:addFavourites(\''+pNr+'\',\''+aC+'\')"></a>'));
	}
	$('#favList').listview();
	$('#favText').text("Search results");
	$('#favList').listview('refresh'); 
	$('#projectList').listview('refresh');
}

function addFavourites(pNr, aC){
	var favourite = {'projectNumber': pNr, 'activityCode': aC}
	$.ajax({
		type:"POST",
		url: 'hours/addFavourites',
		data: favourite,
		success: function(data){
			getFavouriteList(fillSelectMenuInDayPage);
			alert('Added project with nr ' + pNr + ' to favourite list');
		}
	});
}

function deleteFavourite(key) {
	var fav = favMap[key];
	var delFavourite = {'projectNumber': fav.projectnumber, 'activityCode': fav.activitycode};
	
	$.ajax({
		type:"POST",
		url: 'hours/deleteFavourite',
		data: delFavourite,
		success: function(){
			alert('Deleted project with nr ' + fav.projectnumber + ' from favourite list');
			$('#favList').children().remove('li');			
			getFavouriteList(fillListInFavPage);
			getFavouriteList(fillSelectMenuInDayPage);
		},
		async: false
	});
	
}

function fillSelectMenuInDayPage(favList){
	var options = "NO_FAV";
	var select = "Select a favourite"
	$('#fav').html('');
	$('#fav').append('<option value='+options+'>'+select+'</option>').selectmenu('refresh', true);
	for (var i = 0; i < favList.length; i++) {
		var favs = favList[i];
		$('#fav').append('<option value='+ i +'>'+favs+'</option>').selectmenu('refresh', true);
	    
	}
}

/*
 * getDayList()
 * Sends a date to the servlet to return all entries on a specific day
 */
function getDayList(newDay) {
	var weekDays = new Array("", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
	if(newDay == 1)getFavouriteList(fillSelectMenuInDayPage);
	var totalHours = 0;
	var newDay = {day: newDay};
	regMap = {};
	$.ajax({
		type: "POST",
		url: 'hours/daylist',
		data: newDay,
		success: function(data){
			for (var key in data) {
				var jsonMap = data[key];
				if (jsonMap['projectnumber'] == "LUNSJ") {
					$('#lunch').val(0);
				}
				$('#lunch').slider('refresh');
				if(key === "date"){
					var hdrDayText = data[key].split(' ')[0];
					var hdrDateText = data[key].split(' ')[1];
					var dateText = hdrDateText.split('-')[2]+"."+hdrDateText.split('-')[1]+"."+hdrDateText.split('-')[0]
					$('#hdrDay').children('h1').text(weekDays[hdrDayText]+" "+ dateText);
				}else{
					var val = data[key];
					totalHours += val['hours'];
					
					var projectKey = val['projectnumber'] + '<:>' + val['activitycode'];
					var projectdescription = val['description'];
					//super ugly hack
					if (projectKey in favDescription) {
						projectdescription = favDescription[projectKey].description;
					}
					var newhr = new HourRegistration(key, val['projectnumber'], val['activitycode'],
							val['description'], val['hours'], val['submitted'], val['approved'], projectdescription);
					regMap[key] = newhr;
					var editlink = '';
					var buttonlink = '';
					if (newhr['approved']) {
						editlink = '<a href="#">';
						buttonlink = '<a href="#" data-icon="check"></a>';
					} else if (newhr['submitted']) {
						editlink = '<a href="#">';
						buttonlink = '<a href="#" data-theme="c" data-icon="check"></a>';
					} else {
						editlink = '<a href="javascript:editRegistration('+ newhr.tasknumber +')">';
						buttonlink = '<a href="javascript:deleteRegistration(' + newhr.tasknumber +')" data-icon="delete"></a>';
					}
					$('#dayList').append($('<li id="reg:' + key +'" data-rel="popup"></li>').html(editlink +
							'<b>' + newhr['projectDescription']+' </b><span class="ui-li-count">' + newhr['hours'] + ' hours '+
							'</span></a>' + buttonlink)).listview('refresh');
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
 * resetDay()
 * Function too reset the daypage
 */
function resetDay(){
	$('#dayList').children().remove('li');
	$('#hours').val(0);
	$('#hours').slider('refresh');
}