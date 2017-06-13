var rowCounter = 1
function addRiga() {
	var row = '<tr><td style="text-align: center"><a style="cursor: pointer;" onclick="eliminaRiga()" id="buttonToDeleteRiga'
			+ rowCounter
			+ '" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;<a style="margin-left: 15px; cursor: pointer;" onclick="addRiga()" id="buttonToAddRiga'
			+ rowCounter
			+ '" data-toggle="tooltip" title="Aggiungi" data-placement="bottom"><i style="color:green" class="fa fa-plus-circle"></i></a></td><td style="text-align: center">'
			+ (rowCounter + 1)
			+ '</td><td><div class="form-group"><select id="milestoneNuovoCal'
			+ rowCounter
			+ '" class="form-control select2" style="width: 100%;"><option></option></select></div></td><td><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-calendar"></i></div><input onkeydown="return false" type="text" placeholder="Data" class="form-control pull-right datepicker" id="dataNuovoCal'
			+ rowCounter
			+ '"></div></div></td><td><div class="bootstrap-timepicker"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-clock-o"></i></div><input onkeydown="return false" id="oraNuovoCal'
			+ rowCounter
			+ '" placeholder="Ora" type="text" class="form-control timepicker"></div></div></div></td><td><input type="text" class="form-control" id="tagNuovoCal'
			+ rowCounter + '" placeholder="TAG"></td></tr>';
	$('#tableNuovoCalendario').append(row);

	attivaWidget();

	document.getElementById("buttonToAddRiga" + (rowCounter - 1)).className = "disabled";
	
	getListaMilestone(rowCounter);
	
	
	rowCounter++;
	getListaMilestone(rowCounter-1);
}

function eliminaRiga() {

}

$(document).ready(function() {
	getListaCalendari();
	getListaMilestone(rowCounter-1);
})

/**
 * Funzione che effettua una chiamata AJAX all'apposito ws, il quale restituisce
 * una lista contenente tutti i calendari e i relativi id
 * 
 * @returns
 */
function getListaCalendari() {
	$.getJSON("ws/resources/Calendari", function(dataSet) {
		for (i in dataSet) {
			var opt = "<option value='" + dataSet[i].idCalendario + "'>"
					+ dataSet[i].descrizione + "</option>";
			$("#select_elenco_calendari").append(opt);
		}
	});
}

/**
 * Funzione che effettua una chiamata AJAX all'apposito ws, il quale restituisce
 * una lista contenente tutte le milestone e i relativi id
 * 
 * @returns
 */
function getListaMilestone(i) {
	$.getJSON("ws/resources/Milestones", function(dataSet) {
		for (i in dataSet) {
			var opt = "<option value='" + dataSet[i].idMilestone + "'>"
					+ dataSet[i].descrizione + "</option>";
			$("#milestoneNuovoCal" + (i)).append(opt);
		}
	});
}

function selezionaCalendario(selectIndex) {
	var idx = selectIndex.selectedIndex;
	var idCalendario = selectIndex.options[idx].value;
	getDettaglioCalendarioMilestone(idCalendario);
}

function getDettaglioCalendarioMilestone(idCalendario) {
	
	$.ajax({
		type : "GET",
		url : "ws/resources/Calendari(" + idCalendario + ")/Milestone",
		dataType : "json",
		success : function(dataSet) {
//	console.log("dataSet.milestone.descrizione: " + dataSet[0].milestone.descrizione);
//	console.log("dataSet.dataOraPreviste: " + dataSet[0].dataOraPreviste);
//	console.log("dataSet.dataOraPreviste: " + dataSet[0].dataOraPreviste);
//	console.log("dataSet.tags: " + dataSet[0].tags);
//	console.log("dataSet.tags: " + dataSet[0].tags);
			
			$("#divDettagliCalendarioMilestone").removeClass("hide");
			$("#tableDettaglioCalendarioMilestone").DataTable({
				paging : false,
				lengthChange : false,
				searching : false,
				ordering : false,
				info : false,
				autoWidth : false,
				data : dataSet,
				autoWidth : false,
				destroy : true,
				columns : [ {
					data : 'milestone.descrizione'
				}, {
					data : 'dataOraPreviste'
				}, {
					data : 'dataOraPreviste'
				}, {
					data : 'semaforo', defaultContent: ''
				}, {
					data : 'tags'
				}, ]
			});
			
			richiamaPolling($("#tableDettaglioCalendarioMilestone").DataTable());
		}
	});
}

function attivaWidget() {
	$(".select2").select2({
		placeholder : "Seleziona",
		minimumResultsForSearch : 10,
	});

	// Date picker
	$('.datepicker').datepicker({
		language : 'it',
		orientation : "auto",
		format : 'dd/mm/yyyy',
		todayHighlight : true,
		autoclose : true,
		todayBtn : "linked",
		toggleActive : true
	});

	// Timepicker
	$(".timepicker").timepicker({
		showInputs : false,
		showMeridian : false,
		defaultTime : false,
		minuteStep : 1,
		autoclose : true
	});
}

// Richiama il servizio di polling su tutte le righe di una datatable data
function richiamaPolling(dataTable) {
	for (var rowNum = 0; rowNum < dataTable.rows().length; ++rowNum) {
		richiamaPollingRiga(dataTable, dataTable.row(rowNum));
	}
}

// Richiama il servizio di polling relativo a una specifica riga di tabella
// ATTENZIONE, il servizio Polling richiede tanti parametri tutti con chiave 'tag'.
function richiamaPollingRiga(dataTable, row) {
	
	var milestone = row.data().milestone.descrizione;
	var tags = row.data().tags;
	var endpoint = "ws/Polling?milestone=" + milestone + "&tag=" + tags.split(",").join("&tag=");
	
	alert(endpoint);
	
	$.ajax({
		type : "GET",
		url : endpoint,
		dataType : "json",
		success : function(dataSet) {
			//alert(dataSet);
			row.data().semaforo = dataSet;
		}
	});
}