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

	// Constants
	var MISSING = "missing";
	var NO_FAV = "ZZ";
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
	lunchVar = $('#lunch');
	hdrDayVar.children('h1').text(day + '.' + month + '.' + year)
	

	$('#loginForm').submit(function(){
		var loginErr = false;
		var postTo = 'hours/registration';
		var jsonLogin = {username: $('[name=username]').val() , password: $('[name=password]').val(), country: $('[name=radioCountry]').val()}
		console.log(jsonLogin);
		//$.post(postTo, jsonLogin, , 'json');
	});
	
	$('#dayForm').submit(function(){
		var err = false;
		// Reset highlighted form elements
		favLabelVar.removeClass(MISSING);
		hoursLabelVar.removeClass(MISSING);
		
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
		var favForm = $("#fav").val();
		var projectNr = favForm.split(':')[0];
		var hourForm = $("#hours").val();
		var lunchForm = $("#lunch").val();
		var personId = 1;
		
		var myData = {'personId': personId, 'projectNr': projectNr, 'hours': hourForm, 'lunch': lunchForm, 'date': dateForm};
		var lunchText = "";
		if(lunchForm == 1){
			lunchText = "+ Lunch";
		}
		$('#dayList').append($("<li></li>").html('<b>' +
                favForm + '</b> '+lunchText+'<span class="ui-li-count">' + hourForm + ' timer '+'</span>')).listview('refresh');
		
		$.ajax({
			type:"POST",
			url: 'hours/registration',
			data: myData,
			success: function(){
				alert('response data:' +myData);
			}
		});
		
		return false;
	});
	
	
	
});

function get_type(thing){
    if(thing===null)return "[object Null]"; // special case
    return Object.prototype.toString.call(thing);
}


function prevDay(){
	var currDay = $('#hdrDay').children('h1').text();
	currDay = parseInt(currDay);
	var prevDay = currDay - 1;
	$('#hdrDay').children('h1').text(prevDay);
}

function nextDay(){
	var currDay = $('#hdrDay').children('h1').text();
	currDay = parseInt(currDay);
	var nextDay = currDay + 1;
	$('#hdrDay').children('h1').text(nextDay);
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


function IsJsonString(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}

document.ontouchmove = function(e){
    e.preventDefault();
}	
