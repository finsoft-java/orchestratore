
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
			console.log(idMilestone);
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












function updateMilestone(row) {	
	descrizione = '<input style="width:100%" placeholder="Descrizione" id="descrizioneMilestone'+row+'" type="text" class="form-control" value="'+$("#descrizioneMilestone"+row).text()+'"/>'; 
	check = '<a style="cursor:pointer" onclick="back('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Annulla" data-placement="left"><i style="color:black" class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="update('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Segnala fine modifiche" data-placement="right"><i style="color:green" class="fa fa-check"></i></a>';	
	$("#descrizioneMilestone"+row).parent().html(descrizione);
	$("#buttonToUpdateRigaEdit"+row).parent().html(check);
	
	$('body>.tooltip').remove();
}


function back(row){
	idMilestone = $("#idMilestone"+row).text();
	$.ajax({
	  type: "GET",
	  url: "ws/resources/Milestones("+idMilestone+")",
	  success: function(res) {
		descrizione = '<div id="descrizioneMilestone'+row+'">'+res.descrizione+'</div>';
		check = '<a style="cursor:pointer" onclick="removeEntita('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateMilestone('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
		
		$("#descrizioneMilestone"+row).parent().html(descrizione);
		$("#buttonToUpdateRigaEdit"+row).parent().html(check);
		$('body>.tooltip').remove();
	  }
	 });
}



function update(row){
	var idMilestone = $("#idMilestone"+row).text();
	var dataList = [];
	dataList.push([$("#descrizioneMilestone"+row).val(),  $("#idCodice"+row).text()]);
	
	for(var i = 0; i<dataList.length; i++){
		var data = dataList[i];
		var request = {            
			codice:data[1],
			descrizione:data[0]
		};
	}
	
	request = JSON.stringify(request); 
	console.log("idMilestone: "+idMilestone);
	console.log(request);
	 $.ajax({
		  type: "PUT",
		  url: "ws/resources/Milestones("+idMilestone+")",
		  data: request,
		  contentType: "application/json; charset=utf-8",
		  dataType: "json",
		  success: function(res) {
			descrizione = '<div id="descrizioneMilestone'+row+'">'+$("#descrizioneMilestone"+row).val()+'</div>';
			check = '<a style="cursor:pointer" onclick="removeMilestone('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateMilestone('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
			
			$("#descrizioneMilestone"+row).parent().html(descrizione);
			$("#buttonToUpdateRigaEdit"+row).parent().html(check);
			$('body>.tooltip').remove();
			
			//alert("Aggiornamento dati avvenuto correttamente!");
		  }
		 });
}

function checkEmpty(id){
	var temp = $("#"+id).val();
	if(temp === "") temp = null;
	return temp;
}




function removeEmpty(obj) {


	var arr = JSON.parse(obj);
	arr = arr.filter(function(n){ return n }); 
	json_string = JSON.stringify(arr)
	console.log("ARR: "+arr);
	return json_string;

}

	
	
function insert(row){		
	var request = {            
		codice:$("#codiceMilestone_New"+row).val(),
		descrizione:$("#descrizioneMilestone_New"+row).val(),
		descrizioneTag:checkEmpty("descrizioneTagMilestone_New"+row),
		tipoEvento:{idTipoEvento:checkEmpty("codiceTipoEvento_New"+row)},
		entita:{idEntita:checkEmpty("codiceEntita_New"+row)},
		predecessori:checkEmpty("predecessori_New"+row)
	};

	request = JSON.stringify(request); 
	request = removeEmpty(request);
	console.log(request);
	$.ajax({
		  type: "POST",
		  url: "ws/resources/Milestones",
		  data: request,
		  contentType: "application/json; charset=utf-8",
		  dataType: "json",
		  success: function(res) {
			  
			  
//			  	boolAggregato = '<div id="boolAggregato'+row+'">'+res.boolAggregato+'</div>';
//				idMilestone = '<div id="idMilestone'+row+'">'+res.idMilestone+'</div>';
//				codiceMilestone = '<div id="codiceMilestone'+row+'">'+res.codice+'</div>';
//				descrizioneMilestone = '<div id="descrizioneMilestone'+row+'">'+res.descrizione+'</div>';
//				descrizioneTagMilestone = '<div id="descrizioneTagMilestone'+row+'">'+res.descrizioneTag+'</div>';
//				codiceTipoEvento = '<div id="codiceTipoEvento'+row+'">'+res.tipoEvento.codice+'</div>';
//				codiceEntita = '<div id="codiceEntita'+row+'">'+res.entita.codice+'</div>';
//				predecessori = '<div id="predecessori'+row+'">'+res.predecessori+'</div>';
//			
//			check = '<a style="cursor:pointer" onclick="removeMilestone('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateMilestone('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
//						
//			$("#codiceMilestone_New"+row).parent().parent().find(".idMilestone").html(idMilestone);
//			
//			$("#codiceMilestone_New"+row).parent().html(codiceMilestone);
//			$("#descrizioneMilestone_New"+row).parent().html(descrizioneMilestone);
//			$("#descrizioneTagMilestone_New"+row).parent().html(descrizioneTagMilestone);
//			$("#codiceTipoEvento_New"+row).parent().html(codiceTipoEvento);
//			$("#codiceEntita_New"+row).parent().html(codiceEntita);
//			$("#predecessori_New"+row).parent().html(predecessori);
//			
//			$("#buttonToDeleteRigaNew"+row).parent().html(check);
			$('body>.tooltip').remove();
			
			alert("Inserimento dati avvenuto correttamente!");
		  }
		 });
}


function getOptionButtons(data, type, row, meta){
	return '<a style="cursor:pointer" onclick="removeMilestone('+row.idMilestone+')" id="buttonToDeleteRigaEdit'+row.idMilestone+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateMilestone('+row.idMilestone+')" id="buttonToUpdateRigaEdit'+row.idMilestone+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
}

function getBoolAggregate(data, type, row, meta){
	console.log($(this));
	console.log("row.idMilestone: " + row.idMilestone);
	boolAggregate = false;
	
	return '<a style="cursor:pointer" onclick="removeMilestone('+row.idMilestone+')" id="buttonToDeleteRigaEdit'+row.idMilestone+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateMilestone('+row.idMilestone+')" id="buttonToUpdateRigaEdit'+row.idMilestone+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';		
}

/**
 * Funzione che popola la tabella presente nella pagina 'gestioneMilestones.jsp' visualizzando le milestone atomiche e aggregate
 * @returns
 */
var rowCounter = 0
function getListaMilestones(){
		$.getJSON("ws/resources/Milestones", function(dataSet){
		for (i in dataSet){
			dataSet[i].deleteRowButton = '<a style="cursor:pointer" onclick="removeMilestone('+rowCounter+')" id="buttonToDeleteRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="updateMilestone('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
			if(dataSet[i].idMilestone != null)
				dataSet[i].boolAggregato= 'Atomica';
			else
				dataSet[i].boolAggregato= 'Aggregata';

			dataSet[i].idMilestone = '<div id="idMilestone'+rowCounter+'">'+dataSet[i].idMilestone+'</div>';
			dataSet[i].codice = '<div id="idCodice'+rowCounter+'">'+dataSet[i].codice+'</div>';
			dataSet[i].descrizione = '<div id="descrizioneMilestone'+rowCounter+'">'+dataSet[i].descrizione+'</div>';

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

