
/**
 * Questa costante dice che il JSON passa il timestamp come numero e non come stringa (dipende da quale versione di JAX-RS si usa)
 */
var USE_TIMESTAMP = true;

//FIXME i18n?

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
		return "" + date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear();
		
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
	
	/* VECCHIO CODICE non pu� e non deve stare dentro la Datatable!!!
	
	var row = '<tr id="aggiungiButtonRow" role="row">'
		+'	<td class="tdCenter col-md-1"><a style="cursor: pointer;" onclick="addInputForm()" data-toggle="tooltip" title="Aggiungi" data-placement="bottom"><i style="color:green" class="fa fa-plus-circle"></i></a></td>'
		+'	</tr>';
	$('#'+table).append(row);
	*/
	var btn = '<div id="aggiungiButtonRow">' +
		'<a style="cursor: pointer;" onclick="addInputForm()" data-toggle="tooltip" title="Aggiungi" data-placement="bottom"><i style="color:green" class="fa fa-plus-circle"></i></a>' +
		'&nbsp;<a style="cursor: pointer;" onclick="mainDatatable.ajax.reload()" data-toggle="tooltip" title="Reload" data-placement="bottom"><i style="color:blue" class="fa fa-refresh"></i></a>' +
		'</div>';
	$('#'+table).parent().append(btn);
	
	$('body>.tooltip').remove();
}

/**
 * Funzione che rimuove le row inserite dall'utente attraverso il bottone verde '+' a fondo tabella, il quale serve per inserire
 * una nuova riga vuota per inserire una nuova entry all'interno della tabella. Questa row viene eliminata solo dalla
 * tabella vista sulla pagina, poiche' non e' ancora stata inserita nel DB
 * @param row
 * @returns
 */
function removeInputForm(row) {
	$(row).parent().parent().remove();
	$('body>.tooltip').remove();
}

/**
 * Inserisce un DIV intorno al testo dato in input
 * @param divIdPrefix obbligatorio, es. "idEntita"
 * @param rowNum obbligatorio, viene concatenato all'ID
 * @param data dati che verranno messi dentro il DIV
 * @returns {String}
 */
function wrapDiv(divIdPrefix, rowNum, data) {
	if (data == null || data == undefined) data = "";
	return '<div id="' + divIdPrefix + rowNum + '">' + data + '</div>';
}

/**
 * Funzione che richiama un'url per eseguire una UPDATE, e in caso di successo aggiorna anche la Datatable
 * @param rowNum
 * @param url
 * @param requestJSON
 */
function commonUpdate(rowNum, url, requestJSON) {
	
	var request = JSON.stringify(requestJSON); 
	$.ajax({
		type: "PUT",
		url: url,
		data: request,
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(res) {
			
			var oldData = mainDatatable.row(rowNum).data();
			for (var attrName in res) {
				oldData[attrName] = res[attrName];
			}
			mainDatatable.row(rowNum).data(oldData).draw(); 
			
			$('body>.tooltip').remove();
		}
	});
}

/**
 * Funzione che richiama un'url per recuperare i dati di una singola riga.
 * Da usare quando l'utente preme "annulla", in fase di modifica.
 * 
 * @param rowNum
 * @param url
 * @param requestJSON
 */
function commonGoBack(rowNum, url) {
	
	//FIXME a essere precisi forse AJAX non serve, i dati vecchi sono conservati nella Datatable
	// Quindi forse basta richiamare il metodo draw() ?
	
	$.ajax({
		type: "GET",
		url: url,
		success: function(res) {
			
			var oldData = mainDatatable.row(rowNum).data();
			for (var attrName in res) {
				oldData[attrName] = res[attrName];
			}
			mainDatatable.row(rowNum).data(oldData).draw();
		
			$('body>.tooltip').remove();
		}
	});
}

/**
 * Funzione che richiama un'url per eseguire una INSERT, e in caso di successo aggiorna anche la Datatable
 * @param rowNum
 * @param url
 * @param requestJSON
 */
function commonInsert(rowNum, url, requestJSON) {
	
	var request = JSON.stringify(requestJSON); 
	$.ajax({
		type: "POST",
		url: url,
		data: request,
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(res) {
			
			mainDatatable.row.add(res).draw();

			$('body>.tooltip').remove();

		}
	});
}

/**
 * Funzione che richiama un'url per eseguire una DELETE, e in caso di successo aggiorna anche la Datatable
 * @param rowNum
 * @param url
 * @param requestJSON
 */
function commonDelete(rowNum, url) {
	
	$.ajax({
		type : "DELETE",
		url :  url,
		dataType : "json",
		success : function(dataSet) {
			mainDatatable.row(rowNum).remove().draw();
		},
		error : function(errore) {
			customAlertError("Errore nell'eliminazione: probabilmente l'oggetto e' utilizzato altrove�");
		}
	});
	
	$('body>.tooltip').remove();
}

/**
 * Funzione che elimina un oggetto dalla datatable e dal DB chiedendone conferma prima
 */
function commonAskAndDelete(rowNum, url) {
	bootbox.confirm({
		title: "Eliminare oggetto",
		message: "Si \u00E8 sicuri di voler eliminare questo oggetto?<br/>L'operazione \u00E8 irreversibile!",
		buttons: {
			cancel: {
				label: '<i class="fa fa-times"></i> Annulla',
				className: 'btn-danger'
			},
			confirm: {
				label: '<i class="fa fa-trash-o"></i> Conferma',
				className: 'btn-success'
			}
		},
		callback: function (result) {
			if (result) {
				commonDelete(rowNum, url);
			}
		}
	});	
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











