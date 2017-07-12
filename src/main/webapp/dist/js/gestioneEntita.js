
/**
 * Funzione document.ready di jQuery
 */
$(document).ready(function(){
	getListaEntita();
})


/**
 * Funzione che elimina la riga selezionata dalla tabella 'Milestone' presente in gestioneCalendario.jsp per aggiungere una nuova milestone
 * a un determinato calendario
 * @returns
 */
function removeEntita(row) {
	idEntita = $("#idEntita"+row).text();
	console.log(idEntita);
	if(confirm("Sicuro di voler eliminare questa entità?")){
		$.ajax({
			type : "DELETE",
			url : "ws/resources/Entita(" + idEntita + ")",
			dataType : "json",
			success : function(dataSet) {		
				$("#idEntita"+row).parent().parent().remove();
				rowCounter = rowCounter - 1;
			},
			error : function(errore) {
				alert("Errore: probabile utilizzo di questa entità");
			}
		});
	}
	$('body>.tooltip').remove();
}

/**
 * Funzione che rimuove le row inserite dall'utente attraverso il bottone verde '+' a fondo tabella, il quale serve per inserire
 * una nuova riga vuota per inserire una nuova milestone all'interno del calendario. Questo row viene eliminata solo dalla
 * tabella vista sulla pagina, poichè non è ancora stata inserita nel DB
 * @param row
 * @returns
 */
function removeInputForm(row) {
	$(row).parent().parent().remove();
	rowCounter = rowCounter - 1;
	$('body>.tooltip').remove();
}


function updateEntita(row) {	
	descrizione = '<input style="width:100%" placeholder="Descrizione" id="descrizioneEntita_rowNumber_'+row+'" type="text" class="form-control" value="'+$("#descrizioneEntita_rowNumber_"+row).text()+'"/>'; 
	acronimo = '<input style="width:100%" placeholder="Acronimo" id="acronimoEntita_rowNumber_'+row+'" type="text" class="form-control" value="'+$("#acronimoEntita_rowNumber_"+row).text()+'"/>'; 
	check = '<a href="#" onclick="back('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Annulla" data-placement="left"><i style="color:black" class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="update('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Segnala fine modifiche" data-placement="right"><i style="color:green" class="fa fa-check"></i></a>';	
	$("#descrizioneEntita_rowNumber_"+row).parent().html(descrizione);
	$("#acronimoEntita_rowNumber_"+row).parent().html(acronimo);
	$("#buttonToUpdateRigaEdit"+row).parent().html(check);
	
	$('body>.tooltip').remove();
}


function back(row){
	idEntita = $("#idEntita"+row).text();
	$.ajax({
	  type: "GET",
	  url: "ws/resources/Entita("+idEntita+")",
	  success: function(res) {
		descrizione = '<div id="descrizioneEntita_rowNumber_'+row+'">'+res.descrizione+'</div>';
		acronimo = '<div id="acronimoEntita_rowNumber_'+row+'">'+ res.acronimo +'</div>';
		check = '<a href="#" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="updateEntita('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
		
		$("#descrizioneEntita_rowNumber_"+row).parent().html(descrizione);
		$("#acronimoEntita_rowNumber_"+row).parent().html(acronimo);
		$("#buttonToUpdateRigaEdit"+row).parent().html(check);
		$('body>.tooltip').remove();
	  }
	 });
}



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
	console.log("idEntita: "+idEntita);
	console.log(request);
	 $.ajax({
		  type: "PUT",
		  url: "ws/resources/Entita("+idEntita+")",
		  data: request,
		  contentType: "application/json; charset=utf-8",
		  dataType: "json",
		  success: function(res) {
			descrizione = '<div id="descrizioneEntita_rowNumber_'+row+'">'+$("#descrizioneEntita_rowNumber_"+row).val()+'</div>';
			acronimo = '<div id="acronimoEntita_rowNumber_'+row+'">'+ $("#acronimoEntita_rowNumber_"+row).val()+'</div>';
			check = '<a href="#" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="updateMilestone('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
			
			$("#descrizioneEntita_rowNumber_"+row).parent().html(descrizione);
			$("#acronimoEntita_rowNumber_"+row).parent().html(acronimo);
			$("#buttonToUpdateRigaEdit"+row).parent().html(check);
			$('body>.tooltip').remove();
			
			//alert("Aggiornamento dati avvenuto correttamente!");
		  }
		 });
}


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
	console.log(request);
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
			check = '<a href="#" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="updateEntita('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
						
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
 * Funzione che popola la tabella presente nella pagina 'gestioneCalendario.jsp' visualizzando le milestone del relativo
 * calendario selezionato
 * @param idCalendario
 * @returns
 */
var rowCounter = 0
var rowCounterFromDBData = 0;
function getListaEntita(){
	$.getJSON("ws/resources/Entita", function(dataSet){
		for (i in dataSet){
			dataSet[i].deleteRowButton = '<a href="#" onclick="removeEntita('+rowCounter+')" id="buttonToDeleteRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="updateEntita('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
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
		addButtonInputForm();
		attivaWidget();
	 });
}


/**
 * Funzione che aggiunge una nuova riga alla tabella 'Milestone' presente in gestioneCalendario.jsp per aggiungere una nuova milestone
 * a un determinato calendario
 * @returns
 */
function addInputForm(){	
	var row = '<tr role="row">'
	+'	<td class="tdCenter col-md-1"><a id="buttonToDeleteRigaNew'+rowCounter+'" style="cursor: pointer;" onclick="removeInputForm(this)" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="insert('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Inserisci dati" data-placement="right"><i style="color:green" class="fa fa-check"></i></a></td>'
	+'	<td class="idEntita hide"></td>'
	+'	<td class="col-md-3"><input style="width:100%" placeholder="Codice" id="codiceEntita_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-4"><input style="width:100%" placeholder="Descrizione" id="descrizioneEntita_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-4"><input style="width:100%" placeholder="Acronimo" id="acronimoEntita_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'</tr>';
	
	//getListaMilestone_gestCal(rowCounter);
	$('#tableGestioneEntita').append(row);
	addButtonInputForm();
	attivaWidget();
	rowCounter++;
}

/**
 * Funzione che aggiunge una nuova riga alla tabella 'Milestone' presente in gestioneCalendario.jsp per aggiungere una nuova milestone
 * a un determinato calendario
 * @returns
 */
function addButtonInputForm(){	
	$("#aggiungiButtonRow").remove();
	var row = '<tr id="aggiungiButtonRow" role="row">'
		+'	<td class="tdCenter col-md-1"><a style="cursor: pointer;" onclick="addInputForm()" data-toggle="tooltip" title="Aggiungi" data-placement="bottom"><i style="color:green" class="fa fa-plus-circle"></i></a></td>'
		+'	<td class="hide"></td>'
		+'	<td class="tdCenter col-md-3"></td>'
		+'	<td class="tdCenter col-md-4"></td>'
		+'	<td class="tdCenter col-md-4"></td>'
		+'	</tr>';
	$('#tableGestioneEntita').append(row);
	$('body>.tooltip').remove();
}


