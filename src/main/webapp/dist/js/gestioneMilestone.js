
/**
 * Funzione document.ready di jQuery
 */
$(document).ready(function(){
	getListaMilestones();
})


/**
 * Funzione che elimina la riga selezionata dalla tabella 'Milestones' presente in gestioneMilestones.jsp e nel DB (chiedendone conferma)
 * @returns
 */
function removeMilestone(row) {
	bootbox.confirm({
	    title: "Eliminare entità",
	    message: "Si \u00E8 sicuri di voler eliminare questa milestone?<br/>L'operazione \u00E8 irreversibile!",
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
			idMilestone = $("#idMilestone"+row).text();
				$.ajax({
					type : "DELETE",
					url : "ws/resources/Milestones(" + idMilestone + ")",
					dataType : "json",
					success : function(dataSet) {		
						$("#idMilestone"+row).parent().parent().remove();
						rowCounter = rowCounter - 1;
					},
					error : function(errore) {
						customAlertError("Errore: probabile utilizzo di questa milestone");
					}
				});
			$('body>.tooltip').remove();
	      }
	    }
	  });	
}

/**
* Funzione che come prima cosa ottiene la lista delle tipologie di entità (tramite chiamata ad opportuno servizio) ed infine ne popola le opzioni di uno specifico tag <select>
* @param row
* @returns
*/
function getListaEntitaForUpdate(row, selectedEntita){
	$.getJSON("ws/resources/Entita", function(dataSet){
		 for(i in dataSet){
		    var opt = "<option value='"+dataSet[i].idEntita+"'>"+dataSet[i].codice+"</option>";
		    $("#selectCodiceEntita_rowEdit_"+row).append(opt);
	      }
		 
			$("#selectCodiceEntita_rowEdit_"+row+" option").each(function(){
				if ($(this).text() == selectedEntita)
				    $(this).attr("selected","selected");
				});

	  });
}

/**
* Funzione che come prima cosa ottiene la lista delle tipologie di evento (tramite chiamata ad opportuno servizio) ed infine ne popola le opzioni di uno specifico tag <select>
* @param row
* @returns
*/
function getListaTipiEventiForUpdate(row, selectedTipoEvento){
	 $.getJSON("ws/resources/TipiEvento", function(dataSet){
	   for(i in dataSet){
	    var opt = "<option value='"+dataSet[i].idTipoEvento+"'>"+dataSet[i].codice+"</option>";
	    $("#selectCodiceTipoEvento_rowEdit_"+row).append(opt);
	      }
	   
		$("#selectCodiceTipoEvento_rowEdit_"+row+" option").each(function(){
			if ($(this).text() == selectedTipoEvento)
			    $(this).attr("selected","selected");
			});
		
	  });
}


function addEditForm(row) {	

	var selectedEntita = $("#codiceEntita"+row).html();
	var selectedTipoEvento = $("#codiceTipoEvento"+row).html();
	console.log(selectedEntita);
	console.log("selectedTipoEvento:" + selectedTipoEvento);
	check = '<a style="cursor:pointer" onclick="back('+row+')" id="buttonToCancelRigaEdit'+row+'" data-toggle="tooltip" title="Annulla" data-placement="left"><i style="color:black" class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="update('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Segnala fine modifiche" data-placement="right"><i style="color:green" class="fa fa-check"></i></a>';	

//	check = '<a style="cursor:pointer" onclick="back('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Annulla" data-placement="left"><i style="color:black" class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="update('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Salva" data-placement="right"><i style="color:green" class="fa fa-check"></i></a>';	

	
	
	sCodiceMilestone = '<input style="width:100%" placeholder="Codice Milestone" id="codiceMilestone_rowEdit_'+row+'" type="text" class="form-control" value="'+$("#codiceMilestone"+row).text()+'"/>'; 
	sDescrizioneMilestone = '<input style="width:100%" placeholder="Descrizione" id="descrizioneMilestone_rowEdit_'+row+'" type="text" class="form-control" value="'+$("#descrizioneMilestone"+row).text()+'"/>'; 
	sDescrizioneTagMilestone = '<input style="width:100%" placeholder="Descrizione Tag" id="descrizioneTagMilestone_rowEdit_'+row+'" type="text" class="form-control" value="'+$("#descrizioneTagMilestone"+row).text()+'"/>'; 
	sCodiceTipoEvento = '<div class="form-group" style="width:100%;"><select style="width:100%;" id="selectCodiceTipoEvento_rowEdit_'+row+'" class="form-control select2"><option></option></select></div>'; 
	sCodiceEntita = '<div class="form-group" style="width:100%;"><select style="width:100%;" id="selectCodiceEntita_rowEdit_'+row+'" class="form-control select2"><option></option></select></div>'; 
	sPredecessori = '<input style="width:100%" placeholder="Predecessori" id="predecessori_rowEdit_'+row+'" type="text" class="form-control" value="'+$("#predecessori"+row).text()+'"/>'; 
	
	$("#codiceMilestone"+row).parent().html(sCodiceMilestone);
	$("#descrizioneMilestone"+row).parent().html(sDescrizioneMilestone);
	$("#descrizioneTagMilestone"+row).parent().html(sDescrizioneTagMilestone);
	$("#codiceTipoEvento"+row).parent().html(sCodiceTipoEvento);
	$("#codiceEntita"+row).parent().html(sCodiceEntita);
	$("#predecessori"+row).parent().html(sPredecessori);
	$("#buttonToUpdateRigaEdit"+row).parent().html(check);
	
	getListaEntitaForUpdate(row, selectedEntita);
	getListaTipiEventiForUpdate(row, selectedTipoEvento);	
	
	$('body>.tooltip').remove();
}


function back(row){
	idMilestone = $("#idMilestone"+row).text();
	console.log("idMilestone: "+idMilestone);
	$.ajax({
	  type: "GET",
	  url: "ws/resources/Milestones("+idMilestone+")",
	  success: function(res) {
			console.log("res.idMilestone: " + res.idMilestone);
		  
		  	//boolAggregato = '<div id="boolAggregato'+row+'">'+res.boolAggregato+'</div>';
			idMilestone = '<div id="idMilestone'+row+'">'+res.idMilestone+'</div>';
			codiceMilestone = '<div id="codiceMilestone'+row+'">'+res.codice+'</div>';
			descrizioneMilestone = '<div id="descrizioneMilestone'+row+'">'+res.descrizione+'</div>';
			descrizioneTagMilestone = '<div id="descrizioneTagMilestone'+row+'">'+res.descrizioneTag+'</div>';
			codiceTipoEvento = '<div id="codiceTipoEvento'+row+'">'+res.tipoEvento.codice+'</div>';
			codiceEntita = '<div id="codiceEntita'+row+'">'+res.entita.codice+'</div>';
			predecessori = '<div id="predecessori'+row+'">'+res.predecessori+'</div>';
		
		check = '<a style="cursor:pointer" onclick="removeMilestone('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="addEditForm('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
					
		$("#codiceMilestone_rowEdit_"+row).parent().parent().find(".idMilestone").html(idMilestone);
		
		$("#codiceMilestone_rowEdit_"+row).parent().html(codiceMilestone);
		$("#descrizioneMilestone_rowEdit_"+row).parent().html(descrizioneMilestone);
		$("#descrizioneTagMilestone_rowEdit_"+row).parent().html(descrizioneTagMilestone);
		$("#selectCodiceTipoEvento_rowEdit_"+row).parent().html(codiceTipoEvento);
		$("#selectCodiceEntita_rowEdit_"+row).parent().html(codiceEntita);
		$("#predecessori_rowEdit_"+row).parent().html(predecessori);
		
		$("#buttonToCancelRigaEdit"+row).parent().html(check);
		
		$('body>.tooltip').remove();
		
		  
//		descrizione = '<div id="descrizioneMilestone'+row+'">'+res.descrizione+'</div>';
//		check = '<a style="cursor:pointer" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="addEditForm('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
//		
//		$("#descrizioneMilestone"+row).parent().html(descrizione);
//		$("#buttonToUpdateRigaEdit"+row).parent().html(check);
//		$('body>.tooltip').remove();
	  }
	 });
}



function update(row){
	var idMilestone = $("#idMilestone"+row).text();
	
	var valueTipoEvento = $("#idMilestone"+row).parent().find(".tipoEvento").val();
	
	var sCodiceMilestone = $("#codiceMilestone"+row).val();
	var sDescrizioneMilestone = $("#descrizioneMilestone"+row).val();
	var sDescrizioneTagMilestone = $("#descrizioneTagMilestone"+row).val();
	
	var codiceTipoEvento = $("#codiceTipoEvento"+row).val();
	
	var selectCodiceTipoEvento = $("#selectCodiceTipoEvento"+row).val();
	
	$("div.selectCodiceTipoEvento select").val(codiceTipoEvento);
	
	var sCodiceEntita = $("#codiceEntita"+row).val();
	var sPredecessori = $("#predecessori"+row).val();
	
	
	var request = '{"codice":"'+sCodiceMilestone+'","descrizione":"'+sDescrizioneMilestone+'"';
		if(sDescrizioneTagMilestone.length  > 0) request += ',"descrizioneTag":"'+sDescrizioneTagMilestone+'"';	
		if(selectCodiceTipoEvento.length  > 0) request += ',"tipoEvento":{"idTipoEvento":'+selectCodiceTipoEvento+'}';	
		if(sCodiceEntita.length > 0) request += ',"entita":{"idEntita":'+sCodiceEntita+'}';
		request += '}';
	
		 $.ajax({
		  type: "PUT",
		  url: "ws/resources/Milestones("+idMilestone+")",
		  data: request,
		  contentType: "application/json; charset=utf-8",
		  dataType: "json",
		  success: function(res) {
			
			  	//boolAggregato = '<div id="boolAggregato'+row+'">'+res.boolAggregato+'</div>';
				idMilestone = '<div id="idMilestone'+row+'">'+res.idMilestone+'</div>';
				codiceMilestone = '<div id="codiceMilestone'+row+'">'+res.codice+'</div>';
				descrizioneMilestone = '<div id="descrizioneMilestone'+row+'">'+res.descrizione+'</div>';
				descrizioneTagMilestone = '<div id="descrizioneTagMilestone'+row+'">'+res.descrizioneTag+'</div>';
				codiceTipoEvento = '<div id="codiceTipoEvento'+row+'">'+res.tipoEvento.codice+'</div>';
				codiceEntita = '<div id="codiceEntita'+row+'">'+res.entita.codice+'</div>';
				predecessori = '<div id="predecessori'+row+'">'+res.predecessori+'</div>';
			
			check = '<a style="cursor:pointer" onclick="removeMilestone('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="addEditForm('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
						
			$("#codiceMilestone"+row).parent().parent().find(".idMilestone").html(idMilestone);
			
			$("#codiceMilestone"+row).parent().html(codiceMilestone);
			$("#descrizioneMilestone"+row).parent().html(descrizioneMilestone);
			$("#descrizioneTagMilestone"+row).parent().html(descrizioneTagMilestone);
			$("#codiceTipoEvento"+row).parent().html(codiceTipoEvento);
			$("#codiceEntita"+row).parent().html(codiceEntita);
			$("#predecessori"+row).parent().html(predecessori);
			
			//$("#buttonToDeleteRigaNew"+row).parent().html(check);
			$("#buttonToUpdateRigaEdit"+row).parent().html(check);
			$('body>.tooltip').remove();
			
			//alert("Inserimento dati avvenuto correttamente!");
			  
//			  descrizione = '<div id="descrizioneMilestone'+row+'">'+$("#descrizioneMilestone"+row).val()+'</div>';
//			check = '<a style="cursor:pointer" onclick="removeMilestone('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="addEditForm('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
//			
//			$("#descrizioneMilestone"+row).parent().html(descrizione);
//			$("#buttonToUpdateRigaEdit"+row).parent().html(check);
//			$('body>.tooltip').remove();
			
			alert("Aggiornamento dati avvenuto correttamente!");
		  }
		 });
}

function checkEmpty(id){
	var temp = $("#"+id).val();
	if(temp === "") temp = null;
	return temp;
}

	
function insert(row){		
	
	var sCodiceMilestone_New = $("#codiceMilestone_New"+row).val();
	var sDescrizioneMilestone_New = $("#descrizioneMilestone_New"+row).val();
	var sDescrizioneTagMilestone_New = $("#descrizioneTagMilestone_New"+row).val();
	var sCodiceTipoEvento_New = $("#codiceTipoEvento_New"+row).val();
	var sCodiceEntita_New = $("#codiceEntita_New"+row).val();
	var sPredecessori_New = $("#predecessori_New"+row).val();
	
	var request = '{"codice":"'+sCodiceMilestone_New+'","descrizione":"'+sDescrizioneMilestone_New+'"';
		if(sDescrizioneTagMilestone_New.length  > 0) request += ',"descrizioneTag":"'+sDescrizioneTagMilestone_New+'"';	
		if(sCodiceTipoEvento_New.length  > 0) request += ',"tipoEvento":{"idTipoEvento":'+sCodiceTipoEvento_New+'}';	
		if(sCodiceEntita_New.length > 0) request += ',"entita":{"idEntita":'+sCodiceEntita_New+'}';
		request += '}';
	
	$.ajax({
		  type: "POST",
		  url: "ws/resources/Milestones",
		  data: request,
		  contentType: "application/json; charset=utf-8",
		  dataType: "json",
		  success: function(res) {
			  
			  
			  	boolAggregato = '<div id="boolAggregato'+row+'">'+res.boolAggregato+'</div>';
				idMilestone = '<div id="idMilestone'+row+'">'+res.idMilestone+'</div>';
				codiceMilestone = '<div id="codiceMilestone'+row+'">'+res.codice+'</div>';
				descrizioneMilestone = '<div id="descrizioneMilestone'+row+'">'+res.descrizione+'</div>';
				descrizioneTagMilestone = '<div id="descrizioneTagMilestone'+row+'">'+res.descrizioneTag+'</div>';
				codiceTipoEvento = '<div id="codiceTipoEvento'+row+'">'+res.tipoEvento.codice+'</div>';
				codiceEntita = '<div id="codiceEntita'+row+'">'+res.entita.codice+'</div>';
				predecessori = '<div id="predecessori'+row+'">'+res.predecessori+'</div>';
			
			check = '<a style="cursor:pointer" onclick="removeMilestone('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="addEditForm('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
						
			$("#codiceMilestone_New"+row).parent().parent().find(".idMilestone").html(idMilestone);
			
			$("#codiceMilestone_New"+row).parent().html(codiceMilestone);
			$("#descrizioneMilestone_New"+row).parent().html(descrizioneMilestone);
			$("#descrizioneTagMilestone_New"+row).parent().html(descrizioneTagMilestone);
			$("#codiceTipoEvento_New"+row).parent().html(codiceTipoEvento);
			$("#codiceEntita_New"+row).parent().html(codiceEntita);
			$("#predecessori_New"+row).parent().html(predecessori);
			
			$("#buttonToDeleteRigaNew"+row).parent().html(check);
			$('body>.tooltip').remove();
			
			alert("Inserimento dati avvenuto correttamente!");
		  }
		 });
}


function getOptionButtons(data, type, row, meta){
	return '<a style="cursor:pointer" onclick="removeMilestone('+row.idMilestone+')" id="buttonToDeleteRigaEdit'+row.idMilestone+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="addEditForm('+row.idMilestone+')" id="buttonToUpdateRigaEdit'+row.idMilestone+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
}

/**
 * Funzione che popola la tabella presente nella pagina 'gestioneMilestones.jsp' visualizzando le milestone atomiche e aggregate
 * @returns
 */
var rowCounter = 0
function getListaMilestones(){
		$.getJSON("ws/resources/Milestones", function(dataSet){
		for (i in dataSet){
			dataSet[i].deleteRowButton = '<a style="cursor:pointer" onclick="removeMilestone('+rowCounter+')" id="buttonToDeleteRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="addEditForm('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
			if(dataSet[i].idMilestone != null)
				dataSet[i].boolAggregato= 'Atomica';
			else
				dataSet[i].boolAggregato= 'Aggregata';

			dataSet[i].idMilestone = '<div id="idMilestone'+rowCounter+'">'+dataSet[i].idMilestone+'</div>';
			dataSet[i].codice = '<div id="codiceMilestone'+rowCounter+'">'+dataSet[i].codice+'</div>';
			dataSet[i].descrizione = '<div id="descrizioneMilestone'+rowCounter+'">'+dataSet[i].descrizione+'</div>';
			
			if(dataSet[i].descrizioneTag != null)
				dataSet[i].descrizioneTag= '<div id="descrizioneTagMilestone'+rowCounter+'">'+dataSet[i].descrizioneTag+'</div>';
			else
				dataSet[i].descrizioneTag= '<div id="descrizioneTagMilestone'+rowCounter+'"></div>';

			if(dataSet[i].tipoEvento != null)
				dataSet[i].tipoEvento.codice= '<div id="codiceTipoEvento'+rowCounter+'">'+dataSet[i].tipoEvento.codice+'</div>';

			if(dataSet[i].entita != null)
				dataSet[i].entita.codice= '<div id="codiceEntita'+rowCounter+'">'+dataSet[i].entita.codice+'</div>';

			dataSet[i].predecessori= '<div id="predecessori'+rowCounter+'"></div>';

			rowCounter++;
		}		
		
		$("#tableListaMilestones").DataTable({
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
				{data : 'boolAggregato', className : 'col-md-1 boolAggregato', defaultContent : '' },
				{data : 'idMilestone', className : 'hide', defaultContent : '' },
				{data : 'codice', className : 'col-md-1', defaultContent : ''}, 
				{data : 'descrizione', className : 'col-md-3', defaultContent : ''},
				{data : 'descrizioneTag', className : 'col-md-3', defaultContent : ''},
				{data : 'tipoEvento.codice', className : 'col-md-1 tipoEvento', defaultContent : ''},
				{data : 'entita.codice', className : 'col-md-1', defaultContent : ''},
				{data : 'predecessori', className : 'col-md-1', defaultContent : 'ToDo'}
				]
		});
		getIfAggregate();
		addButtonInputForm("tableListaMilestones");
	 });
}

/**
 * Funzione che definisce la tipologia di milestone (atomica/aggregata) e lo scrive sulla pagina nella secondo colonna della tabella
 * @returns
 */
function getIfAggregate(){	
	$('.boolAggregato').each(function(){
	    var value_tipoEvento = $(this).parent().find('.tipoEvento').html();
	    console.log(value_tipoEvento);
        if (value_tipoEvento != '') $(this).html('Atomica');
        else $(this).html('Aggregata');
	});
}


/**
 * Funzione che aggiunge una nuova riga alla tabella 'Milestone' presente in gestioneMilestones.jsp per aggiungere una nuova milestone
 * @returns
 */
function addInputForm(){	
	var row = '<tr role="row">'
	+'	<td class="tdCenter col-md-1"><a id="buttonToDeleteRigaNew'+rowCounter+'" style="cursor: pointer;" onclick="removeInputForm(this)" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="insert('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Inserisci dati" data-placement="right"><i style="color:green" class="fa fa-check"></i></a></td>'
	+'	<td class="col-md-1"></td>'
	+'	<td class="idMilestone hide"></td>'
	+'	<td class="col-md-1"><input style="width:100%" placeholder="{{labels.gestioneMilestones.codice}}" id="codiceMilestone_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-3"><input style="width:100%" placeholder="{{labels.gestioneMilestones.descrizione}}" id="descrizioneMilestone_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-3"><input style="width:100%" placeholder="{{labels.gestioneMilestones.descrizioneTag}}" id="descrizioneTagMilestone_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td class="col-md-1"><div class="form-group" style="width:100%;"><select style="width:100%;" id="codiceTipoEvento_New'+rowCounter+'" class="form-control select2"><option></option></select></div></td>'
	+'	<td class="col-md-1"><div class="form-group" style="width:100%;"><select style="width:100%;" id="codiceEntita_New'+rowCounter+'" class="form-control select2"><option></option></select></div></td>'
	+'	<td class="col-md-1"><input style="width:100%" placeholder="{{labels.gestioneMilestones.predecessori}}" id="predecessori_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'</tr>';
	
	getListaEntita(rowCounter);
	getListaTipiEventi(rowCounter);
	$('#tableListaMilestones').append(row);
	addButtonInputForm("tableListaMilestones");
	attivaWidgetSelect2();
	rowCounter++;
}

/**
 * Funzione che come prima cosa ottiene la lista delle tipologie di entità (tramite chiamata ad opportuno servizio) ed infine ne popola le opzioni di uno specifico tag <select>
 * @param row
 * @returns
 */
function getListaEntita(row){
	 $.getJSON("ws/resources/Entita", function(dataSet){
		 for(i in dataSet){
		    var opt = "<option value='"+dataSet[i].idEntita+"'>"+dataSet[i].codice+"</option>";
		    $("#codiceEntita_New"+(row)).append(opt);
	      }
	  });
}

/**
 * Funzione che come prima cosa ottiene la lista delle tipologie di evento (tramite chiamata ad opportuno servizio) ed infine ne popola le opzioni di uno specifico tag <select>
 * @param row
 * @returns
 */
function getListaTipiEventi(row){
	 $.getJSON("ws/resources/TipiEvento", function(dataSet){
	   for(i in dataSet){
	    var opt = "<option value='"+dataSet[i].idTipoEvento+"'>"+dataSet[i].codice+"</option>";
	    $("#codiceTipoEvento_New"+(row)).append(opt);
	      }
	  });
}

