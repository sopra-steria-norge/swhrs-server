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
    contentTransitionVar = $('#contentTransition');
    confirmationVar = $('#confirmation');
    contentDialogVar = $('#contentDialog');
    hdrConfirmationVar = $('#hdrConfirmation');
    contentConfirmationVar = $('#contentConfirmation');
    ftrConfirmationVar = $('#ftrConfirmation'); 
	
	hideContentDialog();
	hideContentTransition();
	hideConfirmation();
	
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
		
		
		var reg = new Object();
		reg.favForm = $("#fav").val();
		reg.hourForm = $("#hours").val();
		reg.lunchForm = $("#lunch").val();
		
		var jsonReg = JSON.stringify(reg);
		console.log(jsonReg);
		console.log(get_type(jsonReg));
		
		var json_object = eval('('+ jsonReg +')');
		
		console.log(get_type(json_object));
		console.log(json_object);
		var jsonObjects = [{id:1, name:"amit"}, {id:2, name:"ankit"},{id:3, name:"atin"},{id:1, name:"puneet"}];
		/*
		$.post("http://localhost:8081/hours/registration",     
		           JSON.stringify({"a":"b"} ), 
		           function(data) {});*/
		
		$.ajax({
			type:"POST",
			url: 'hours/registration',
			dataType: 'json',
			data: jsonReg,
			success: function(){
				alert('response data:' +jsonReg);
			}
		})
		
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

function hideMain(){
	console.log("TRIES");
	hdrDayVar.hide();
	contentDayVar.hide();
	ftrDayVar.hide();
	}

function showMain(){
	hdrDayVar.show();
	contentDayVar.show();
	ftrDayVar.show();
	}

function hideContentTransition(){
	contentTransitionVar.hide();
	}
	
function showContentTransition(){
	contentTransitionVar.show();
	}
	
function hideContentDialog(){
	contentDialogVar.hide();
	}

function showContentDialog(){
	contentDialogVar.show();
	}
	
function showConfirmation(){
	hdrConfirmationVar.show();
	contentConfirmationVar.show();
	}

function hideConfirmation(){
	hdrConfirmationVar.hide();
	contentConfirmationVar.hide();
	
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
