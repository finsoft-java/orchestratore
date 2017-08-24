
/**
 * Funzione document.ready di jQuery che carica la datatable contenente l'elenco delle entita' 
 */
$(document).ready(function(){
	getListaEntita();
})

var mainDatatable = null;

/**
 * Funzione che elimina una entita'  dalla datatable e dal DB chiedendone conferma prima
 */
function removeEntita(row) {
	bootbox.confirm({
		title: "Eliminare entit\u00E0",
		message: "Si \u00E8 sicuri di voler eliminare questa entit\u00E0?<br/>L'operazione \u00E8 irreversibile!",
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
				idEntita = $("#idEntita"+row).text();
				
				//TODO usare commonDelete(row, "ws/resources/Entita(" + idEntita + ")")
				
				$.ajax({
					type : "DELETE",
					url : "ws/resources/Entita(" + idEntita + ")",
					dataType : "json",
					success : function(dataSet) {
						// modifica per miglior gestione datatable
						// $("#idEntita"+row).parent().parent().remove();
						mainDatatable.row(row).remove().draw();
					},
					error : function(errore) {
						//FIXME i18n?
						customAlertError("Errore: probabile utilizzo di questa entita' ");
					}
				});
			$('body>.tooltip').remove();
		  }
		}
	});	
}

/**
 * Funzione che viene richiamata al premere del mouse sull'icona della matita di una determinata row, la quale rende editabili i campi 'descrizione' e 'acronimo' cosi'
 * da poter essere editati dall'utente
 * @param row
 * @returns
 */
function updateEntita(row) {
	
	descrizione = '<input style="width:100%" placeholder="{{labels.gestioneEntita.descrizione}}" id="descrizioneEntita_rowNumber_'+row+'" type="text" class="form-control" value="'+$("#descrizioneEntita_rowNumber_"+row).text()+'"/>'; 
	acronimo = '<input style="width:100%" placeholder="{{labels.gestioneEntita.acronimo}}" id="acronimoEntita_rowNumber_'+row+'" type="text" class="form-control" value="'+$("#acronimoEntita_rowNumber_"+row).text()+'"/>'; 
	check = '<a style="cursor:pointer"="#" onclick="back('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Annulla" data-placement="left"><i style="color:black" class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="update('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Salva" data-placement="right"><i style="color:green" class="fa fa-check"></i></a>';	
	
	$("#descrizioneEntita_rowNumber_"+row).parent().html(descrizione);
	$("#acronimoEntita_rowNumber_"+row).parent().html(acronimo);
	$("#buttonToUpdateRigaEdit"+row).parent().html(check);
	
	$('body>.tooltip').remove();
}

/**
 * Funzione che viene richiamata al premere del mouse sull'icona della X di una determinata row, la quale annulla le modifiche effettuate dall'utente, riportanto 
 * la situzione a prima che l'utente volesse modificare i campi
 * @param row
 * @returns
 */
function back(row){
	idEntita = $("#idEntita"+row).text();
	$.ajax({
		type: "GET",
		url: "ws/resources/Entita("+idEntita+")",
		success: function(res) {
		  
			descrizione = wrapDiv('descrizioneEntita_rowNumber_', row, res.descrizione);
			acronimo = wrapDiv('acronimoEntita_rowNumber_', row, res.acronimo);
			check = '<a style="cursor:pointer" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateEntita('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
		
			$("#descrizioneEntita_rowNumber_"+row).parent().html(descrizione);
			$("#acronimoEntita_rowNumber_"+row).parent().html(acronimo);
			$("#buttonToUpdateRigaEdit"+row).parent().html(check);
		
			$('body>.tooltip').remove();
		}
	});
}

/**
 * Funzione che viene richiamata al premere del mouse sull'icona della spunta verde di una determinata row comparsa al momento della scelta di editare i campi
 * di una determinata row. Essa recupera i dati modificati dell'entita'  inseriti dall'utente e richiamando un'opportuno WS effettua un'update sul DB. Infine
 * riporta i campi sulla datatable in modalita'  non editabile 
 * @param row
 * @returns
 */
function update(row){
	var idEntita = $("#idEntita"+row).text();
	
	var request = {			
		acronimo: $("#acronimoEntita_rowNumber_"+row).val(),
		codice: $("#idCodice"+row).text(),
		descrizione: $("#descrizioneEntita_rowNumber_"+row).val()
	};
	
	//TODO usare commonUpdate(row, "ws/resources/Entita("+idEntita+")", request)
	
	request = JSON.stringify(request); 
	$.ajax({
		type: "PUT",
		url: "ws/resources/Entita("+idEntita+")",
		data: request,
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(res) {
			
			// modifica per miglior gestione datatable
			var oldData = mainDatatable.row(row).data();
			oldData.descrizione = res.descrizione;
			oldData.acronimo = res.acronimo;
			mainDatatable.row(row).data(oldData).draw();
			
			/*
			descrizione = wrapDiv('descrizioneEntita_rowNumber_', row, res.descrizione);
			acronimo = wrapDiv('acronimoEntita_rowNumber_', row, res.acronimo);
			check = '<a style="cursor:pointer" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateEntita('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
			
			$("#descrizioneEntita_rowNumber_"+row).parent().html(descrizione);
			$("#acronimoEntita_rowNumber_"+row).parent().html(acronimo);
			$("#buttonToUpdateRigaEdit"+row).parent().html(check);
			*/
			$('body>.tooltip').remove();
		  }
		 });
}

/**
 * Funzione che viene richiamata al premere del mouse sull'icona della spunta verde di una determinata row comparsa al momento della scelta di aggiungere una 
 * nuova entita' . Essa recupera i nuovi dati dell'entita'  inseriti dall'utente e richiamando un'opportuno WS effettua un'operazione di insert sul DB. Infine
 * inserisce i campi sulla datatable in modalitÃ  non editabile 
 * @param row
 * @returns
 */
function insert(row){
	
	var request = {
		acronimo: $("#acronimoEntita_New"+row).val(),
		codice: $("#codiceEntita_New"+row).val(),
		descrizione: $("#descrizioneEntita_New"+row).val()
	};
	
	//TODO usare commonInsert(row, "ws/resources/Entita", request)
	
	request = JSON.stringify(request);
	
	$.ajax({
		type: "POST",
		url: "ws/resources/Entita",
		data: request,
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(res) {
			
			// modifica per miglior gestione datatable
			mainDatatable.row.add(res).draw();
			
			//Cosa serve ancora e cosa no?
			/*idEntita = wrapDiv('idEntita', row, res.idEntita);
			codice = wrapDiv('idCodice', row, res.codice);
			descrizione = wrapDiv('descrizioneEntita_rowNumber_', row, res.descrizione);
			acronimo = wrapDiv('acronimoEntita_rowNumber_', row, res.acronimo);
			check = '<a style="cursor:pointer" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateEntita('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
						
			$("#codiceEntita_New"+row).parent().parent().find(".idEntita").html(idEntita);
			
			$("#codiceEntita_New"+row).parent().html(codice);
			$("#descrizioneEntita_New"+row).parent().html(descrizione);
			$("#acronimoEntita_New"+row).parent().html(acronimo);
			
			$("#buttonToDeleteRigaNew"+row).parent().html(check);
			*/
			$('body>.tooltip').remove();
			
			//alert("Inserimento dati avvenuto correttamente!");
		  }
		 });
}

function renderIdEntita(data, type, row, meta) {
	return wrapDiv("idEntita", meta.row, data);
}

function renderCodiceEntita(data, type, row, meta) {
	return wrapDiv("idCodice", meta.row, data);
}

function renderDescrizione(data, type, row, meta) {
	return wrapDiv("descrizioneEntita_rowNumber_", meta.row, data);
}

function renderAcronimo(data, type, row, meta) {
	return wrapDiv("acronimoEntita_rowNumber_", meta.row, data);
}

function renderRowButtons(data, type, row, meta) {
	return '<a style="cursor:pointer" onclick="removeEntita('+meta.row+')" id="buttonToDeleteRigaEdit'+meta.row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>'
	+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' 
	+ '<a style="cursor:pointer" onclick="updateEntita('+meta.row+')" id="buttonToUpdateRigaEdit'+meta.row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
}

/**
 * Funzione che popola la tabella presente nella pagina 'gestioneEntita.jsp' visualizzando le entita'  presenti sul DB
 * @param idCalendario
 * @returns
 */
function getListaEntita() {
	
	mainDatatable = $("#tableGestioneEntita").DataTable({
		paging : false,
		lengthChange : false,
		searching : false,
		ordering : false,
		info : false,
		autoWidth : false,
		ajax: {
			url: 'ws/resources/Entita',
			dataSrc: '',
			language: 'it'
		},
		autoWidth : false,
		destroy : true,
		columns : [
			{data : 'deleteRowButton', className : 'col-md-1 tdCenter', defaultContent : '', render: renderRowButtons },
			{data : 'idEntita', className : 'hide', defaultContent : '', render: renderIdEntita },
			{data : 'codice', className : 'col-md-3', defaultContent : '', render: renderCodiceEntita }, 
			{data : 'descrizione', className : 'col-md-4', defaultContent : '', render: renderDescrizione },
			{data : 'acronimo', className : 'col-md-4', defaultContent : '', render: renderAcronimo }
			]
	});
	
	addButtonInputForm("tableGestioneEntita");

}


/**
 * Funzione che aggiunge una nuova riga alla datatable 'Entità ' presente in gestioneEntita.jsp per aggiungere una nuova entita'  al DB
 * @returns
 */
function addInputForm(){
	
	// modifica per miglior gestione datatable
	var rowCounter = mainDatatable.rows().length;
		
	//FIXME per avere i placeholder corretti, si può creare all'inizio una riga nascosta, poi duplicarla
	var row = '<tr role="row">'
	+'	<td class="tdCenter col-md-1"><a id="buttonToDeleteRigaNew'+rowCounter+'" style="cursor: pointer;" onclick="removeInputForm(this)" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="insert('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Inserisci" data-placement="right"><i style="color:green" class="fa fa-check"></i></a></td>'
	+'	<td class="idEntita hide"></td>'
	+'	<td class="col-md-3"><input style="width:100%" placeholder="${labels.gestioneEntita_codice}" id="codiceEntita_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-4"><input style="width:100%" placeholder="${labels.gestioneEntita_descrizione}" id="descrizioneEntita_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-4"><input style="width:100%" placeholder="${labels.gestioneEntita._acronimo}" id="acronimoEntita_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'</tr>';
	
	//getListaMilestone_gestCal(rowCounter);
	$('#tableGestioneEntita').append(row);
	
	addButtonInputForm("tableGestioneEntita");
	
}



