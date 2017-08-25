
/**
 * Funzione document.ready di jQuery che carica la datatable contenente l'elenco dei tipi di eventi
 */
$(document).ready(function(){
	getListaTipiEventi();
});


var mainDatatable = null;

/**
 * Funzione che elimina una tipologia di evento dalla datatable e dal DB chiedendone conferma prima
 */
function removeTipoEvento(row) {
	
	var idTipoEvento = mainDatatable.row(row).data().idTipoEvento;
	
	commonAskAndDelete(row, "ws/resources/TipiEvento(" + idTipoEvento + ")");
	
/*
	bootbox.confirm({
	    title: "Eliminare tipolgia di evento",
	    message: "Si \u00E8 sicuri di voler eliminare questo tipologia di evento?<br/>L'operazione \u00E8 irreversibile!",
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
			idTipoEvento = $("#idTipoEvento"+row).text();
				$.ajax({
					type : "DELETE",
					url : "ws/resources/TipiEvento(" + idTipoEvento + ")",
					dataType : "json",
					success : function(dataSet) {		
						$("#idTipoEvento"+row).parent().parent().remove();
						rowCounter = rowCounter - 1;
					},
					error : function(errore) {
						customAlertError("Errore: probabile utilizzo di questa tipologia");
					}
				});
			$('body>.tooltip').remove();
	      }
	    }
	  }); */
}

/**
 * Funzione che viene richiamata al premere del mouse sull'icona della matita di una determinata row, la quale rende editabili i campi 'descrizione' così
 * da poter essere editati dall'utente
 * @param row
 * @returns
 */
function updateTipoEvento(row) {	
	descrizione = '<input style="width:100%" placeholder="{{labels.gestioneTipiEvento.descrizione}}" id="descrizioneTipoEvento_rowNumber_'+row+'" type="text" class="form-control" value="'+$("#descrizioneTipoEvento_rowNumber_"+row).text()+'"/>'; 
	check = '<a style="cursor:pointer" onclick="back('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Annulla" data-placement="left"><i style="color:black" class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="update('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Salva" data-placement="right"><i style="color:green" class="fa fa-check"></i></a>';	
	$("#descrizioneTipoEvento_rowNumber_"+row).parent().html(descrizione);
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
	
	var idTipoEvento = mainDatatable.row(row).data().idTipoEvento;
	
	commonGoBack(row, "ws/resources/TipiEvento(" + idTipoEvento + ")");
	
	/*
	idTipoEvento = $("#idTipoEvento"+row).text();
	$.ajax({
	  type: "GET",
	  url: "ws/resources/TipiEvento("+idTipoEvento+")",
	  success: function(res) {
		descrizione = '<div id="descrizioneTipoEvento_rowNumber_'+row+'">'+res.descrizione+'</div>';
		check = '<a style="cursor:pointer" onclick="removeTipoEvento('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateTipoEvento('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
		
		$("#descrizioneTipoEvento_rowNumber_"+row).parent().html(descrizione);
		$("#buttonToUpdateRigaEdit"+row).parent().html(check);
		$('body>.tooltip').remove();
	  }
	 });*/
}

/**
 * Funzione che viene richiamata al premere del mouse sull'icona della spunta verde di una determinata row comparsa al momento della scelta di editare i campi
 * di una determinata row. Essa recupera i dati modificati della tipologia di evento inseriti dall'utente e richiamando un'opportuno WS effettua un'update sul DB. Infine
 * riporta i campi sulla datatable in modalità non editabile 
 * @param row
 * @returns
 */
function update(row){
	
	var idTipoEvento = mainDatatable.row(row).data().idTipoEvento;
	
	var request = {
		codice: mainDatatable.row(row).data().codice,
		descrizione: $("#descrizioneTipoEvento_rowNumber_"+row).val()
	};
		
	commonUpdate(row, "ws/resources/TipiEvento("+idTipoEvento+")", request);
		
	/*
	var idTipoEvento = $("#idTipoEvento"+row).text();
	var dataList = [];
	dataList.push([$("#descrizioneTipoEvento_rowNumber_"+row).val(),  $("#idCodice"+row).text()]);
	
	for(var i = 0; i<dataList.length; i++){
		var data = dataList[i];
		var request = {            
			codice:data[1],
			descrizione:data[0]
		};
	}
	
	request = JSON.stringify(request); 

	 $.ajax({
		  type: "PUT",
		  url: "ws/resources/TipiEvento("+idTipoEvento+")",
		  data: request,
		  contentType: "application/json; charset=utf-8",
		  dataType: "json",
		  success: function(res) {
			descrizione = '<div id="descrizioneTipoEvento_rowNumber_'+row+'">'+$("#descrizioneTipoEvento_rowNumber_"+row).val()+'</div>';
			check = '<a style="cursor:pointer" onclick="removeTipoEvento('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateTipoEvento('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
			
			$("#descrizioneTipoEvento_rowNumber_"+row).parent().html(descrizione);
			$("#buttonToUpdateRigaEdit"+row).parent().html(check);
			$('body>.tooltip').remove();
		  }
		 });*/
}

/**
 * Funzione che viene richiamata al premere del mouse sull'icona della spunta verde di una determinata row comparsa al momento della scelta di aggiungere una 
 * nuova tipologia di evento. Essa recupera i nuovi dati della tipologia di evento inseriti dall'utente e richiamando un'opportuno WS effettua un'operazione di insert sul DB. Infine
 * inserisce i campi sulla datatable in modalità non editabile 
 * @param row
 * @returns
 */
function insert(row){
	
	var request = {
			codice: $("#codiceTipoEvento_New"+row).val(),
			descrizione: $("#descrizioneTipoEvento_New"+row).val()
	};
		
	commonInsert(row, "ws/resources/TipiEvento", request);
	
	/*
	var dataList = [];
	dataList.push([$("#descrizioneTipoEvento_New"+row).val(), $("#codiceTipoEvento_New"+row).val()]);
	
	for(var i = 0; i<dataList.length; i++){
		var data = dataList[i];
		var request = {            
			codice:data[1],
			descrizione:data[0]
		};
	}
	request = JSON.stringify(request); 
	 $.ajax({
		  type: "POST",
		  url: "ws/resources/TipiEvento",
		  data: request,
		  contentType: "application/json; charset=utf-8",
		  dataType: "json",
		  success: function(res) {
			idTipoEvento = '<div id="idTipoEvento'+row+'">'+res.idTipoEvento+'</div>';
			codice = '<div id="idCodice'+row+'">'+res.codice+'</div>';
			descrizione = '<div id="descrizioneTipoEvento_rowNumber_'+row+'">'+res.descrizione+'</div>';
			check = '<a style="cursor:pointer" onclick="removeTipoEvento('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateTipoEvento('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
						
			$("#codiceTipoEvento_New"+row).parent().parent().find(".idTipoEvento").html(idTipoEvento);
			
			$("#codiceTipoEvento_New"+row).parent().html(codice);
			$("#descrizioneTipoEvento_New"+row).parent().html(descrizione);
			
			$("#buttonToDeleteRigaNew"+row).parent().html(check);
			$('body>.tooltip').remove();
		  }
		 });*/
}

function renderIdTipoEvento(data, type, row, meta) {
	return wrapDiv("idTipoEvento", meta.row, data);
}

function renderCodice(data, type, row, meta) {
	return wrapDiv("idCodice", meta.row, data);
}

function renderDescrizione(data, type, row, meta) {
	return wrapDiv("descrizioneTipoEvento_rowNumber_", meta.row, data);
}

function renderRowButtons(data, type, row, meta) {
	
	return '<a style="cursor:pointer" onclick="removeTipoEvento('+meta.row+')" id="buttonToDeleteRigaEdit'+meta.row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>'
		+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
		+ '<a style="cursor:pointer" onclick="updateTipoEvento('+meta.row+')" id="buttonToUpdateRigaEdit'+meta.row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
}

/**
 * Funzione che popola la tabella presente nella pagina 'gestioneTipiEvento.jsp' visualizzando le tipologie di evento presenti sul DB
 * @param idCalendario
 * @returns
 */
//var rowCounter = 0
function getListaTipiEventi() {
	
	mainDatatable = $("#tableTipiEventi").DataTable({
		paging : false,
		lengthChange : false,
		searching : false,
		ordering : true,
		info : false,
		autoWidth : false,
		ajax: {
			url: 'ws/resources/TipiEvento',
			dataSrc: '',
			language: 'it'
		},
		autoWidth : false,
		destroy : true,
		columns : [
			{data : 'deleteRowButton', className : 'col-md-1 tdCenter', defaultContent : '', render: renderRowButtons },
			{data : 'idTipoEvento', className : 'hide', defaultContent : '', render: renderIdTipoEvento },
			{data : 'codice', className : 'col-md-3', defaultContent : '', render: renderCodice }, 
			{data : 'descrizione', className : 'col-md-8', defaultContent : '', render: renderDescrizione },
			]
	});
	
	addButtonInputForm("tableTipiEventi");
	
	/*
	$.getJSON("ws/resources/TipiEvento", function(dataSet){
		
		for (i in dataSet){
			dataSet[i].deleteRowButton = '<a style="cursor:pointer" onclick="removeTipoEvento('+rowCounter+')" id="buttonToDeleteRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateTipoEvento('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
			dataSet[i].idTipoEvento = '<div id="idTipoEvento'+rowCounter+'">'+dataSet[i].idTipoEvento+'</div>';
			dataSet[i].codice = '<div id="idCodice'+rowCounter+'">'+dataSet[i].codice+'</div>';
			dataSet[i].descrizione = '<div id="descrizioneTipoEvento_rowNumber_'+rowCounter+'">'+dataSet[i].descrizione+'</div>';

			rowCounter++;
		}
		
		$("#tableTipiEventi").DataTable({
			paging : false,
			lengthChange : false,
			searching : false,
			ordering : false,
			info : false,
			autoWidth : false,
			data : dataSet,
			autoWidth : false,
			destroy : true,
			columns : [
				{data : 'deleteRowButton', className : 'col-md-1 tdCenter', defaultContent : '' },
				{data : 'idTipoEvento', className : 'hide', defaultContent : '' },
				{data : 'codice', className : 'col-md-3', defaultContent : ''}, 
				{data : 'descrizione', className : 'col-md-8', defaultContent : ''},
				]
		});
		addButtonInputForm("tableTipiEventi");
	 });*/
}


/**
 * Funzione che aggiunge una nuova riga alla datatable 'Tipi evento' presente in gestioneTipiEvento.jsp per aggiungere una nuova tipologia di evento al DB
 * @returns
 */
function addInputForm() {
	
	var rowCounter = mainDatatable.rows().length;
	
	var row = '<tr role="row">'
	+'	<td class="tdCenter col-md-1"><a id="buttonToDeleteRigaNew'+rowCounter+'" style="cursor: pointer;" onclick="removeInputForm(this)" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="insert('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Inserisci" data-placement="right"><i style="color:green" class="fa fa-check"></i></a></td>'
	+'	<td class="idTipoEvento hide"></td>'
	+'	<td class="col-md-3"><input style="width:100%" placeholder="{{labels.gestioneTipiEvento.codice}}" id="codiceTipoEvento_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-8"><input style="width:100%" placeholder="{{labels.gestioneTipiEvento.descrizione}}" id="descrizioneTipoEvento_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'</tr>';
	
	$('#tableTipiEventi').append(row);
	addButtonInputForm("tableTipiEventi");
	
}


