
/**
 * Funzione document.ready di jQuery
 */
$(document).ready(function(){
	getListaTipiEventi();
})


/**
 * Funzione che elimina la riga selezionata dalla tabella 'Milestone' presente in gestioneCalendario.jsp per aggiungere una nuova milestone
 * a un determinato calendario
 * @returns
 */
function removeTipoEvento(row) {
	idTipoEvento = $("#idTipoEvento"+row).text();
	console.log(idTipoEvento);
	if(confirm("Sicuro di voler eliminare questo tipologia di evento?")){
		$.ajax({
			type : "DELETE",
			url : "ws/resources/TipiEvento(" + idTipoEvento + ")",
			dataType : "json",
			success : function(dataSet) {		
				$("#idTipoEvento"+row).parent().parent().remove();
				rowCounter = rowCounter - 1;
			},
			error : function(errore) {
				alert("Errore: probabile utilizzo di questa tipologia");
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


function updateTipoEvento(row) {	
	descrizione = '<input style="width:100%" placeholder="Descrizione" id="descrizioneTipoEvento_rowNumber_'+row+'" type="text" class="form-control" value="'+$("#descrizioneTipoEvento_rowNumber_"+row).text()+'"/>'; 
	check = '<a style="cursor:pointer" onclick="back('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Annulla" data-placement="left"><i style="color:black" class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="update('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Segnala fine modifiche" data-placement="right"><i style="color:green" class="fa fa-check"></i></a>';	
	$("#descrizioneTipoEvento_rowNumber_"+row).parent().html(descrizione);
	$("#buttonToUpdateRigaEdit"+row).parent().html(check);
	
	$('body>.tooltip').remove();
}


function back(row){
	idTipoEvento = $("#idTipoEvento"+row).text();
	$.ajax({
	  type: "GET",
	  url: "ws/resources/TipiEvento("+idTipoEvento+")",
	  success: function(res) {
		descrizione = '<div id="descrizioneTipoEvento_rowNumber_'+row+'">'+res.descrizione+'</div>';
		check = '<a style="cursor:pointer" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateTipoEvento('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
		
		$("#descrizioneTipoEvento_rowNumber_"+row).parent().html(descrizione);
		$("#buttonToUpdateRigaEdit"+row).parent().html(check);
		$('body>.tooltip').remove();
	  }
	 });
}

/**
 * Funzione che il valore della chiave, di un option scelto da una combobox, catturato attraverso il metodo onchange 
 * @param selectIndex
 * @returns
 */
//function selezionaCalendario_gestEntita(selectIndex){
//	var idx = selectIndex.selectedIndex;
//	var idCalendario = selectIndex.options[idx].value;
//	getDettaglioCalendarioEntitaEditabile(idCalendario);
//	rowCounter = 0;
//}


function update(row){
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
	console.log("idEntita: "+idTipoEvento);
	console.log(request);
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
			
			//alert("Aggiornamento dati avvenuto correttamente!");
		  }
		 });
}


function insert(row){
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
	console.log(request);
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
			
			//alert("Inserimento dati avvenuto correttamente!");
		  }
		 });
}

$(document).ready(function(){getListaEntita()});



/**
 * Funzione che popola la tabella presente nella pagina 'gestioneCalendario.jsp' visualizzando le milestone del relativo
 * calendario selezionato
 * @param idCalendario
 * @returns
 */
var rowCounter = 0
var rowCounterFromDBData = 0;
function getListaEntita(){
	$.getJSON("ws/resources/TipiEvento", function(dataSet){
				for (i in dataSet){

					dataSet[i].deleteRowButton = '<a href="#" onclick="removeMilestone('+rowCounter+')" id="buttonToDeleteRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>';
						
					if (dataSet[i].descrizione != null)
						dataSet[i].descrizione = '<input style="width:100%" placeholder="idEntita" id="descrizioneEntita_rowNumber_'+rowCounter+'" type="text" class="form-control" value="'+dataSet[i].descrizione+'"/>'; 

					if (dataSet[i].codice != null)
						dataSet[i].codice = '<input style="width:100%" placeholder="idEntita" id="acronimoEntita_rowNumber_'+rowCounter+'" type="text" class="form-control" value="'+dataSet[i].codice+'"/>'; 
					
					rowCounter++;
					
				}
				//$("#div_tabella_entita_editabile").removeClass("hide");
				
				$("#tableGestioneTipiEvento").DataTable({
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
						{data : 'idEntita', className : 'col-md-1 idTipoEvento', defaultContent : '' },
						{data : 'codice', className : 'col-md-2', defaultContent : ''}, 
						{data : 'descrizione', className : 'col-md-4', defaultContent : ''},
						]
				});
				
				$('.idTipoEvento').each(function(){
					if($(this).html() == ''){
						$($(this).parent().remove());
					}
					
				})
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
	+'	<td class="tdCenter col-md-1"><a id="buttonToDeleteRigaNew'+rowCounter+'" style="cursor: pointer;" onclick="removeInputForm(this)" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a></td>'
	+'	<td class="idEntita col-md-1"></td>'
	+'	<td class="col-md-2"><input style="width:100%" placeholder="Codice" id="codiceEntita'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-4"><input style="width:100%" placeholder="Descrizione" id="descrizioneEntita'+rowCounter+'" type="text" class="form-control"/></td>'
//	+'	<td class="col-md-2"><input style="width:100%" placeholder="Acronimo" id="acronimoEntita'+rowCounter+'" type="text" class="form-control"/></td>'
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
		+'	<td class="tdCenter col-md-2"></td>'
		+'	<td class="tdCenter col-md-2"></td>'
		+'	<td class="tdCenter col-md-2"></td>'
		+'	<td class="tdCenter col-md-1"></td>'
		+'	</tr>';
	$('#tableGestioneEntita').append(row);
	$('body>.tooltip').remove();
}


