
/**
 * Funzione document.ready di jQuery
 */
$(document).ready(function(){
	getListaCalendari();
//	getListaMilestone(rowCounter-1);
})


/**
 * Funzione che elimina la riga selezionata dalla tabella 'Milestone' presente in gestioneCalendario.jsp per aggiungere una nuova milestone
 * a un determinato calendario
 * @returns
 */
function removeMilestone(row) {
	var idSelectMilestoneCal = $("#idCalendarioMilestone"+row).text();
	console.log(idSelectMilestoneCal);
	if(confirm("Sicuro di voler eliminare questa milestone da questo calendario?")){
		$.ajax({
			type : "DELETE",
			url : "ws/resources/CalendarioMilestones(" + idSelectMilestoneCal + ")",
			dataType : "json",
			success : function(dataSet) {		
				$("#idCalendarioMilestone"+row).parent().parent().remove();
				rowCounter = rowCounter - 1;
				if(rowCounter === 0) {
					if(confirm("Il calendario ora non contiene nessuna milestone, si desidera eliminare anch'esso?")) deleteCalendar();
					else location.reload(true);
				}
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

/**
 * Funzione che effettua una chiamata AJAX all'apposito ws, il quale restituisce una lista
 * contenente tutte le milestone e i relativi id
 * 
 * @returns
 */
function getListaMilestone_gestCal(j){
	 $.getJSON("ws/resources/Milestones", function(dataSet){
	   for(i in dataSet){
	    var opt = "<option value='"+dataSet[i].idMilestone+"'>"+dataSet[i].descrizione+"</option>";
	    $("#selectMilestoneCalNew"+(j)).append(opt);
	      }
	  });
}


/**
 * Funzione che effettua una chiamata AJAX all'apposito ws, il quale restituisce una lista
 * contenente tutti i calendari e i relativi id
 * 
 * @returns
 */
function getListaCalendari(){
	 $.getJSON("ws/resources/Calendari", function(dataSet){
		 for(i in dataSet){
			 var opt = "<option value='"+dataSet[i].idCalendario+"'>"+dataSet[i].descrizione+"</option>";
			 $("#select_elenco_calendari").append(opt);
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


/**
 * Funzione che al richiamo effettua l'eliminazione dal DB di uno specifico calendario, ovvero quello
 * selezionato attravero la comboBox in 'gestioneCalendario.jsp'
 * @returns
 */
function deleteCalendar(){
	var idSelect = $("#select_elenco_calendari").val();
	if(idSelect !== '') {
		if(confirm("Sicuro di voler eliminare questo calendario?")){
			$.ajax({
				type : "DELETE",
				url : "ws/resources/Calendari(" + idSelect + ")",
				dataType : "json",
				success : function(dataSet) {		
					location.reload(true);
				}
			});
		}
	} else alert("Selezionare un calendario per poterlo eliminare");
}


var counterRicorsione = 0;
function saveEditedEntita(){
	updateEntityData();
	if(counterRicorsione === 0) {
		alert("fine ricorsione funzione di aggiornamento");
		console.log("FINE AGGIORNAMENTO DATI");
		insertEntity();
		if(counterRicorsione === 0) {
			alert("fine ricorsione funzione di inserimento");
			console.log("FINE INSERIMENTO DATI");
//			location.reload(true);
			counterRicorsione = 0;
		}
	}
}


function updateEntityData(){
	if(counterRicorsione === rowCounterFromDBData) return;
	var idCalendarioMilestone = $("#idEntita"+counterRicorsione).text();
	var dataList = [];
	dataList.push([/*$("#idCodice"+counterRicorsione).text(),*/ $("#descrizioneEntita_rowNumber_"+counterRicorsione).val(), $("#acronimoEntita_rowNumber_"+counterRicorsione).val()]);
	
	for(var i = 0; i<dataList.length; i++){
		var data = dataList[i];
		var request = {            
			//_idCodice:data[0],
			_descrizioneEntita:data[0],
			_acronimoEntita:data[1],
		};
	}
	request = JSON.stringify(request); 
	console.log("idEntita: "+idCalendarioMilestone);
	console.log(request);
	//recupero dati prima riga
//	 $.ajax({
//		  type: "PUT",
//		  url: "CalendarioMilestones("+idEntita+")",
//		  data: request,
//		  contentType: "application/json; charset=utf-8",
//		  dataType: "json",
//		  success: function(res) {
		  counterRicorsione++;
		  updateEntityData();			  
//		  }
//		 });
	  counterRicorsione--;
}

function insertEntity() {
    var temp = rowCounterFromDBData+counterRicorsione;
	if(counterRicorsione === (rowCounter - rowCounterFromDBData)) return;
	var dataList = [];
	dataList.push([$("#codiceEntita_New"+counterRicorsione).text(), $("#descrizioneEntita_New"+counterRicorsione).val(), $("#acronimoEntita_New"+counterRicorsione).val()]);	
	for(var i = 0; i<dataList.length; i++){
		var data = dataList[i];
		var request = {            
			_idCodice:data[0],
			_descrizioneEntita:data[1],
			_acronimoEntita:data[2],
		};
	}
	request = JSON.stringify(request); 
	console.log(request);
	//recupero dati prima riga
	// $.ajax({
	//	  type: "POST",
	//	  url: "CalendarioMilestones",
	//	  data: request,
	//	  contentType: "application/json; charset=utf-8",
	//	  dataType: "json",
	//	  success: function(res) {
		  counterRicorsione++;
		  insertEntity();			  
	//	  }
	//	 });
	  counterRicorsione--;
}

$(document).ready(function(){getListaEntita()});


function updateMilestone(row) {
//	$("#descrizioneEntita_New"+counterRicorsione).removeClass("disabled");
//	$("#acronimoEntita_New"+counterRicorsione).removeClass("disabled");
//	setTimeout(
//		function(){ 
//			alert("Hello"); 
//		}, 3000);
	
	
	descrizione = '<input style="width:100%" placeholder="Descrizione" id="descrizioneEntita_rowNumber_'+row+'" type="text" class="form-control" value="'+$("#descrizioneEntita_rowNumber_"+row).text()+'"/>'; 
	acronimo = '<input style="width:100%" placeholder="Acronimo" id="acronimoEntita_rowNumber_'+row+'" type="text" class="form-control" value="'+$("#acronimoEntita_rowNumber_"+row).text()+'"/>'; 
	check = '<a href="#" onclick="back('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Annulla" data-placement="left"><i style="color:black" class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="update('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Segnala fine modifiche" data-placement="right"><i style="color:green" class="fa fa-check"></i></a>';	
	$("#descrizioneEntita_rowNumber_"+row).parent().html(descrizione);
	$("#acronimoEntita_rowNumber_"+row).parent().html(acronimo);
	$("#buttonToUpdateRigaEdit"+row).parent().html(check);
	
	
//	
//	
//	var idSelectMilestoneCal = $("#idCalendarioMilestone"+row).text();
//	console.log(idSelectMilestoneCal);
//	if(confirm("Sicuro di voler eliminare questa milestone da questo calendario?")){
//		$.ajax({
//			type : "DELETE",
//			url : "ws/resources/CalendarioMilestones(" + idSelectMilestoneCal + ")",
//			dataType : "json",
//			success : function(dataSet) {		
//				$("#idCalendarioMilestone"+row).parent().parent().remove();
//				rowCounter = rowCounter - 1;
//				if(rowCounter === 0) {
//					if(confirm("Il calendario ora non contiene nessuna milestone, si desidera eliminare anch'esso?")) deleteCalendar();
//					else location.reload(true);
//				}
//			}
//		});
//	}
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
		check = '<a href="#" onclick="removeMilestone('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="updateMilestone('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
		
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
	dataList.push([$("#descrizioneEntita_rowNumber_"+row).val(), $("#acronimoEntita_rowNumber_"+row).val()]);
	
	for(var i = 0; i<dataList.length; i++){
		var data = dataList[i];
		var request = {            
			descrizione:data[0],
			acronimo:data[1],
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
//			console.log(res);
			descrizione = '<div id="descrizioneEntita_rowNumber_'+row+'">'+$("#descrizioneEntita_rowNumber_"+row).val()+'</div>';
			acronimo = '<div id="acronimoEntita_rowNumber_'+row+'">'+ $("#acronimoEntita_rowNumber_"+row).val()+'</div>';
			check = '<a href="#" onclick="removeMilestone('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="updateMilestone('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
			
			$("#descrizioneEntita_rowNumber_"+row).parent().html(descrizione);
			$("#acronimoEntita_rowNumber_"+row).parent().html(acronimo);
			$("#buttonToUpdateRigaEdit"+row).parent().html(check);
			$('body>.tooltip').remove();
			
			alert("Aggiornamento informazioni avvenuto correttamente!");
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

					dataSet[i].deleteRowButton = '<a href="#" onclick="removeMilestone('+rowCounter+')" id="buttonToDeleteRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="updateMilestone('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
					dataSet[i].idEntita = '<div id="idEntita'+rowCounter+'">'+dataSet[i].idEntita+'</div>';
					dataSet[i].codice = '<div id="idCodice'+rowCounter+'">'+dataSet[i].codice+'</div>';
					
					dataSet[i].descrizione = '<div id="descrizioneEntita_rowNumber_'+rowCounter+'">'+dataSet[i].descrizione+'</div>';
					dataSet[i].acronimo = '<div id="acronimoEntita_rowNumber_'+rowCounter+'">'+dataSet[i].acronimo+'</div>';
					
//					if (dataSet[i].descrizione != null) dataSet[i].descrizione = '<input style="width:100%" placeholder="idEntita" id="descrizioneEntita_rowNumber_'+rowCounter+'" type="text" class="form-control" value="'+dataSet[i].descrizione+'"/>'; 
//
//					if (dataSet[i].acronimo != null) dataSet[i].acronimo = '<input style="width:100%" placeholder="idEntita" id="acronimoEntita_rowNumber_'+rowCounter+'" type="text" class="form-control" value="'+dataSet[i].acronimo+'"/>'; 
					
					rowCounter++;
					
				}
				//$("#div_tabella_entita_editabile").removeClass("hide");
				
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
						{data : 'idEntita', className : 'col-md-1 idEntita', defaultContent : '' },
						{data : 'codice', className : 'col-md-2', defaultContent : ''}, 
						{data : 'descrizione', className : 'col-md-4', defaultContent : ''},
						{data : 'acronimo', className : 'col-md-4', defaultContent : ''}
						]
				});
				
				$('.idEntita').each(function(){
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
	+'	<td class="col-md-2"><input style="width:100%" placeholder="Codice" id="codiceEntita_New'+rowCounter+'" type="text" class="form-control"/></td>'
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
		+'	<td class="tdCenter col-md-2"></td>'
		+'	<td class="tdCenter col-md-2"></td>'
		+'	<td class="tdCenter col-md-2"></td>'
		+'	<td class="tdCenter col-md-1"></td>'
		+'	</tr>';
	$('#tableGestioneEntita').append(row);
	$('body>.tooltip').remove();
}





