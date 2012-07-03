

$(document).ready(function() {
	//Assign gloabal variables
	hdrHourVar = $('#hdrDay');
	contentHourVar = $('#contentDay');
	ftrHourVar = $('#ftrDay');
	favLabelVar = $('#favLabel');
	myHoursLabelVar = $('#myHoursLabel');
	dateLabelVar = $('#dateLabel')
	form1Var = $('#dayForm');
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
});




function hideMain(){
	hdrHourVar.hide();
	contentHourVar.hide();
	ftrHourVar.hide();
	}

function showMain(){
	hdrHourVar.show();
	contentHourVar.show();
	ftrHourVar.show();
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
