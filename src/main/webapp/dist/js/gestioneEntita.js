
/**
 * Funzione document.ready di jQuery che carica la datatable contenente l'elenco delle entità
 */
$(document).ready(function(){
	getListaEntita();
})

/**
 * Funzione che elimina una entità dalla datatable e dal DB chiedendone conferma prima
 */
function removeEntita(row) {
	bootbox.confirm({
	    title: "Eliminare entità",
	    message: "Si \u00E8 sicuri di voler eliminare questa entità?<br/>L'operazione \u00E8 irreversibile!",
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
				$.ajax({
					type : "DELETE",
					url : "ws/resources/Entita(" + idEntita + ")",
					dataType : "json",
					success : function(dataSet) {		
						$("#idEntita"+row).parent().parent().remove();
						rowCounter = rowCounter - 1;
					},
					error : function(errore) {
						customAlertError("Errore: probabile utilizzo di questa entità");
					}
				});
			$('body>.tooltip').remove();
	      }
	    }
	  });	
}

/**
 * Funzione che viene richiamata al premere del mouse sull'icona della matita di una determinata row, la quale rende editabili i campi 'descrizione' e 'acronimo' così
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
		descrizione = '<div id="descrizioneEntita_rowNumber_'+row+'">'+res.descrizione+'</div>';
		acronimo = '<div id="acronimoEntita_rowNumber_'+row+'">'+ res.acronimo +'</div>';
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
 * di una determinata row. Essa recupera i dati modificati dell'entità inseriti dall'utente e richiamando un'opportuno WS effettua un'update sul DB. Infine
 * riporta i campi sulla datatable in modalità non editabile 
 * @param row
 * @returns
 */
function update(row){
	var idEntita = $("#idEntita"+row).text();
	var dataList = [];
	dataList.push([$("#descrizioneEntita_rowNumber_"+row).val(), $("#acronimoEntita_rowNumber_"+row).val(),  $("#idCodice"+row).text()]);
	
	for(var i = 0; i<dataList.length; i++){
		var data = dataList[i];
		var request = {            
			acronimo:data[1],
			codice:data[2],
			descrizione:data[0]
		};
	}
	
	request = JSON.stringify(request); 
	 $.ajax({
		  type: "PUT",
		  url: "ws/resources/Entita("+idEntita+")",
		  data: request,
		  contentType: "application/json; charset=utf-8",
		  dataType: "json",
		  success: function(res) {
			descrizione = '<div id="descrizioneEntita_rowNumber_'+row+'">'+$("#descrizioneEntita_rowNumber_"+row).val()+'</div>';
			acronimo = '<div id="acronimoEntita_rowNumber_'+row+'">'+ $("#acronimoEntita_rowNumber_"+row).val()+'</div>';
			check = '<a style="cursor:pointer" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateEntita('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
			
			$("#descrizioneEntita_rowNumber_"+row).parent().html(descrizione);
			$("#acronimoEntita_rowNumber_"+row).parent().html(acronimo);
			$("#buttonToUpdateRigaEdit"+row).parent().html(check);
			$('body>.tooltip').remove();
		  }
		 });
}

/**
 * Funzione che viene richiamata al premere del mouse sull'icona della spunta verde di una determinata row comparsa al momento della scelta di aggiungere una 
 * nuova entità. Essa recupera i nuovi dati dell'entità inseriti dall'utente e richiamando un'opportuno WS effettua un'operazione di insert sul DB. Infine
 * inserisce i campi sulla datatable in modalità non editabile 
 * @param row
 * @returns
 */
function insert(row){
	var dataList = [];
	dataList.push([$("#descrizioneEntita_New"+row).val(), $("#acronimoEntita_New"+row).val(), $("#codiceEntita_New"+row).val()]);
	
	for(var i = 0; i<dataList.length; i++){
		var data = dataList[i];
		var request = {            
			acronimo:data[1],
			codice:data[2],
			descrizione:data[0]
		};
	}
	
	request = JSON.stringify(request); 
	 $.ajax({
		  type: "POST",
		  url: "ws/resources/Entita",
		  data: request,
		  contentType: "application/json; charset=utf-8",
		  dataType: "json",
		  success: function(res) {
			idEntita = '<div id="idEntita'+row+'">'+res.idEntita+'</div>';
			codice = '<div id="idCodice'+row+'">'+res.codice+'</div>';
			descrizione = '<div id="descrizioneEntita_rowNumber_'+row+'">'+res.descrizione+'</div>';
			acronimo = '<div id="acronimoEntita_rowNumber_'+row+'">'+ res.acronimo +'</div>';
			check = '<a style="cursor:pointer" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateEntita('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
						
			$("#codiceEntita_New"+row).parent().parent().find(".idEntita").html(idEntita);
			
			$("#codiceEntita_New"+row).parent().html(codice);
			$("#descrizioneEntita_New"+row).parent().html(descrizione);
			$("#acronimoEntita_New"+row).parent().html(acronimo);
			
			$("#buttonToDeleteRigaNew"+row).parent().html(check);
			$('body>.tooltip').remove();
			
			//alert("Inserimento dati avvenuto correttamente!");
		  }
		 });
}

/**
 * Funzione che popola la tabella presente nella pagina 'gestioneEntita.jsp' visualizzando le entità presenti sul DB
 * @param idCalendario
 * @returns
 */
var rowCounter = 0
var rowCounterFromDBData = 0;
function getListaEntita(){
	$.getJSON("ws/resources/Entita", function(dataSet){
		for (i in dataSet){
			dataSet[i].deleteRowButton = '<a style="cursor:pointer" onclick="removeEntita('+rowCounter+')" id="buttonToDeleteRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateEntita('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
			dataSet[i].idEntita = '<div id="idEntita'+rowCounter+'">'+dataSet[i].idEntita+'</div>';
			dataSet[i].codice = '<div id="idCodice'+rowCounter+'">'+dataSet[i].codice+'</div>';
			dataSet[i].descrizione = '<div id="descrizioneEntita_rowNumber_'+rowCounter+'">'+dataSet[i].descrizione+'</div>';
			dataSet[i].acronimo = '<div id="acronimoEntita_rowNumber_'+rowCounter+'">'+dataSet[i].acronimo+'</div>';

			rowCounter++;
		}
		
		$("#tableGestioneEntita").DataTable({
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
				{data : 'idEntita', className : 'hide', defaultContent : '' },
				{data : 'codice', className : 'col-md-3', defaultContent : ''}, 
				{data : 'descrizione', className : 'col-md-4', defaultContent : ''},
				{data : 'acronimo', className : 'col-md-4', defaultContent : ''}
				]
		});
		
		rowCounterFromDBData = rowCounter;
		addButtonInputForm("tableGestioneEntita");
	 });
}


/**
 * Funzione che aggiunge una nuova riga alla datatable 'Entità' presente in gestioneEntita.jsp per aggiungere una nuova entità al DB
 * @returns
 */
function addInputForm(){	
	var row = '<tr role="row">'
	+'	<td class="tdCenter col-md-1"><a id="buttonToDeleteRigaNew'+rowCounter+'" style="cursor: pointer;" onclick="removeInputForm(this)" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="insert('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Inserisci" data-placement="right"><i style="color:green" class="fa fa-check"></i></a></td>'
	+'	<td class="idEntita hide"></td>'
	+'	<td class="col-md-3"><input style="width:100%" placeholder="{{labels.gestioneEntita.codice}}" id="codiceEntita_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-4"><input style="width:100%" placeholder="{{labels.gestioneEntita.descrizione}}" id="descrizioneEntita_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-4"><input style="width:100%" placeholder="{{labels.gestioneEntita.acronimo}}" id="acronimoEntita_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'</tr>';
	
	//getListaMilestone_gestCal(rowCounter);
	$('#tableGestioneEntita').append(row);
	addButtonInputForm("tableGestioneEntita");
	rowCounter++;
}



