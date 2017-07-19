

/**
 * Funzione che splitta e converte la data passata come parametro, dal formato timeStamp a quello dd/MM/yyyy.
 * In più viene anche eliminata l'ora dal parametro così da restituire solamente la data
 * @param data
 * @returns
 */
function convertTimestampToData(data){
	var firstSplit = data.split("T");
	var secondSplit = firstSplit[0].split("-");
	return secondSplit[2]+"/"+secondSplit[1]+"/"+secondSplit[0];
}

/**
 * Funzione che splitta e converte l'ora passata come parametro, dal formato timeStamp a quello hh:mm.
 * In più viene anche eliminata la data dal parametro così da restituire solamente l'ora
 * @param data
 * @returns
 */
function convertTimestampToTime(ora){
	var firstSplit = ora.split("T");
	var secondSplit = firstSplit[1].split("+");
	return secondSplit[0];
}


/**
 * Funzione che passata data e ora in formato dd/MM/yyyy e hh:mm rispettivamente, li unisce e li converte in una stringa
 * in formato compatibile con timestamp
 * @param data
 * @param ora
 * @returns
 */
function convertDataOraToTimestamp(data, ora){
	var splitDate = data.split("/");
	var sqlDate = splitDate[2] +"-"+ splitDate[1] +"-"+ splitDate[0];
	return sqlDate +"T"+ ora + ":00+02:00"; 
}


/**
 * Funzione che inizializza il widget Select2
 * @returns
 */
function attivaWidgetSelect2(){
	$(function () {
		  //Initialize Select2 Elements
		  $("select").select2({
		  	placeholder: "Seleziona",
		    minimumResultsForSearch: 10,
		    language: 'it'
		  });
	});
}

/**
 * Funzione che inizializza il widget datapicker
 * @returns
 */
function attivaWidgetDatepicker(){
	$(function () {
		  //Date picker
		  $('.datepicker').datepicker({
		 	language: 'it',
		    orientation: "auto",
		    format: 'dd/mm/yyyy',
		    todayHighlight: true,
		    autoclose: true,
		    todayBtn: "linked",
		    toggleActive: true
		  });
	});
}

/**
 * Funzione che inizializza il widget timepicker
 * @returns
 */
function attivaWidgetTimepicker(){
	$(function () {
		  //Timepicker
		  $(".timepicker").timepicker({
		    showInputs: false,
		    showMeridian: false,
		    defaultTime: false,
		    minuteStep: 1,
		    autoclose: true,
		    //appendWidgetTo: '.table-responsive'
		  });
	});
}

/**
 * Funzione che aggiunge/rimuove la classe 'active' dai tab laterali della nav bar del template, in modo che solo
 * il tab contente la pagina vista attualmente dall'utente abbia la classe 'active'
 * @returns
 */
function activeDeactiveNavBarTab(){
	var url = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
	$('.treeview-menu li a[href="' + url + '"]').parent().addClass('active');
	$('.treeview-menu li a').filter(function() {
	    return this.href === url;
	}).parent().parent().parent().addClass('active');
}

/**
 * Funzione utilizzata per mandare un messaggio di errore all'utente che si dissolve automaticamente dopo 2,5 secondi
 * @param message
 * @returns
 */
function customAlertError(message) {
	  var dialog = bootbox.dialog({
	    message: '<i class="fa fa-times"></i>   ' + message
	  });
	  dialog.init(function () {
	    setTimeout(function () {
	      dialog.modal('hide');
	    }, 2500);
	  });
	}

