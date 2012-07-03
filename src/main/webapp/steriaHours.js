// Declare global variables

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
