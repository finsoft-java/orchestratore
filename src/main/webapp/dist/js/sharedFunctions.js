
/**
 * Questa costante dice che il JSON passa il timestamp come numero e non come stringa
 */
var USE_TIMESTAMP = true;

/////////////////////////CONVERSIONE DATA/ORA///////////////////////////////////////////
/**
 * Funzione che splitta e converte la data passata come parametro, dal formato timeStamp a quello dd/MM/yyyy.
 * In più viene anche eliminata l'ora dal parametro così da restituire solamente la data
 * @param data
 * @returns
 */
function convertTimestampToData(data) {
	
	if (USE_TIMESTAMP && (typeof data == "number")) {
		
		var date = new Date(data);
		return "" + date.getDate() + "/" + date.getMonth()+1 + "/" + date.getFullYear();
		
	} else {
		
		var firstSplit = data.split("T");
		var secondSplit = firstSplit[0].split("-");
		return secondSplit[2] + "/" + secondSplit[1] + "/" + secondSplit[0];
	}
}

/**
 * Funzione che splitta e converte l'ora passata come parametro, dal formato timeStamp a quello hh:mm.
 * In più viene anche eliminata la data dal parametro così da restituire solamente l'ora
 * @param data
 * @returns
 */
function convertTimestampToTime(ora) {
	
	if (USE_TIMESTAMP && (typeof ora == "number")) {
		
		var date = new Date(ora);
		return "" + date.getHours() + ":" + date.getMinutes();
		
	} else {
		
		var firstSplit = ora.split("T");
		var secondSplit = firstSplit[1].split("+");
		return secondSplit[0];
	}
}

/**
 * Funzione che passata data e ora in formato dd/MM/yyyy e hh:mm rispettivamente, li unisce e li converte in una stringa
 * in formato compatibile con timestamp
 * @param data
 * @param ora
 * @returns
 */
function convertDataOraToTimestamp(data, ora) {
	if (USE_TIMESTAMP) {
		
		var splitDate = data.split("/");
		var splitHour = ora.split(":");
		var date = new Date(splitDate[2], splitDate[1], splitDate[0], splitHour[0], splitHour[1]);
		return date.getTime();
		
	} else {
		
		var splitDate = data.split("/");
		var sqlDate = splitDate[2] +"-"+ splitDate[1] +"-"+ splitDate[0];
		return sqlDate +"T"+ ora + ":00+02:00"; 
	}
}


/////////////////////////FUNZIONI CUSTOM COMUNI PER DATATABLE///////////////////////////////////////////
/**
 * Funzione cha aggiunge una row alla tabella di id passato come parametro. La row aggiunta presenta un unico pulsante, presente nella prima colonna (quella delle opzioni), che permette
 * di aggiungere un nuovo record alla tabella
 * @param table
 * @returns
 */
function addButtonInputForm(table) {	
	$("#aggiungiButtonRow").remove();
	var row = '<tr id="aggiungiButtonRow" role="row">'
		+'	<td class="tdCenter col-md-1"><a style="cursor: pointer;" onclick="addInputForm()" data-toggle="tooltip" title="Aggiungi" data-placement="bottom"><i style="color:green" class="fa fa-plus-circle"></i></a></td>'
		+'	</tr>';
	$('#'+table).append(row);
	$('body>.tooltip').remove();
}

/**
 * Funzione che rimuove le row inserite dall'utente attraverso il bottone verde '+' a fondo tabella, il quale serve per inserire
 * una nuova riga vuota per inserire una nuova entry all'interno della tabella. Questa row viene eliminata solo dalla
 * tabella vista sulla pagina, poichè non è ancora stata inserita nel DB
 * @param row
 * @returns
 */
function removeInputForm(row) {
	$(row).parent().parent().remove();
	rowCounter = rowCounter - 1;
	$('body>.tooltip').remove();
}

/////////////////////////ATTIVAZIONE MANUALE WIDGET///////////////////////////////////////////
/**
 * Funzione che inizializza il widget Select2
 * @returns
 */
function attivaWidgetSelect2() {
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
function attivaWidgetDatepicker() {
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
function attivaWidgetTimepicker() {
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
function activeDeactiveNavBarTab() {
	var url = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
	$('.treeview-menu li a[href="' + url + '"]').parent().addClass('active');
	$('.treeview-menu li a').filter(function() {
	    return this.href === url;
	}).parent().parent().parent().addClass('active');
}


////////////////////////////////////////MESSAGGI UTENTE/////////////////////////////////////////////
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

function customAlertOK(message) {
	  var dialog = bootbox.dialog({
	    message: '<i class="fa fa-check"></i>   ' + message
	  });
	  dialog.init(function () {
	    setTimeout(function () {
	      dialog.modal('hide');
	    }, 2500);
	  });
	}











