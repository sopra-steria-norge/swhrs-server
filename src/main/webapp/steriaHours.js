$(document).ready(function() {
	//Assign gloabal variables
	hdrHourVar = $('#hdrHour');
	contentHourVar = $('#contentHour');
	ftrHourVar = $('#ftrHour');
	favLabelVar = $('#favLabel');
	dateLabelVar = $('#dateLabel');
	myHoursLabelVar = $('#myHoursLabel');
	form1Var = $('#form1');
	favVar = $('#fav');
	hoursVar = $('#myhours');
	dateVar = $('#mydate');
	inputMapVar = $('input[name*="_r"]');
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
