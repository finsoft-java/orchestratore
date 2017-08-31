
/**
 * Funzione document.ready di jQuery
 */
$(document).ready(function(){
	getListaMilestones();
});


var mainDatatable = null;

/**
 * Funzione che elimina la riga selezionata dalla tabella 'Milestones' presente in gestioneMilestones.jsp e nel DB (chiedendone conferma)
 * @returns
 */
function removeMilestone(row) {
	
	var idMilestone = mainDatatable.row(row).data().idMilestone;
	
	commonAskAndDelete(row, "ws/resources/Milestones(" + idMilestone + ")");
	
	/*
	bootbox.confirm({
		title: "Eliminare entità",
		message: "Si \u00E8 sicuri di voler eliminare questa milestone?<br/>L'operazione \u00E8 irreversibile!",
		buttons: {
		  cancel: {
			label: '<i class="fa fa-times"></i> Annulla',
			className: 'btn-default'
		  },
		  confirm: {
			label: '<i class="fa fa-trash-o"></i> Conferma',
			className: 'btn-danger'
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
	  });	*/
}

/**
* Funzione che come prima cosa ottiene la lista delle tipologie di entità (tramite chiamata ad opportuno servizio) ed infine ne popola le opzioni di uno specifico tag <select>
* @param row
* @returns
*/
function getListaEntitaForUpdate(row, selectedEntita){
	$.getJSON("ws/resources/Entita", function(dataSet){
		for(i in dataSet) {
			var opt = "<option value='"+dataSet[i].idEntita+"'>"+dataSet[i].codice+"</option>";
			$("#selectCodiceEntita_rowEdit_"+row).append(opt);
		}
		
		$("#selectCodiceEntita_rowEdit_"+row+" option").each(function() {
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

/**
* Funzione che come prima cosa ottiene la lista dei predecessori (tramite chiamata ad opportuno servizio) ed infine ne popola le opzioni di uno specifico tag <select>
* @param row
* @returns
*/
function getListaPredecessoriForUpdate(row, selectedPredecessori){
//	$.getJSON("ws/resources/MilestoneMilestones", function(dataSet){
//		for(i in dataSet){
//			var opt = "<option value='"+dataSet[i].milestoneComponente.idMilestone+"'>"+dataSet[i].milestoneComponente.descrizione+"</option>";
//			$("#selectPredecessori_rowEdit_"+(row)).append(opt);
//		}
//	   
//		$.getJSON("ws/resources/MilestoneMilestones("+row+")", function(dataSet){
//			for(i in dataSet){
//				$.fn.select2.defaults.set("val", DataSet[i].idMilestone);
//			}
//		}

	
		$.ajax({
			  type: "GET",
			  url: "ws/resources/Milestones",
			  success: function(dataSet) {
					for(var i in dataSet){
						var opt = "<option value='"+dataSet[i].idMilestone+"'>"+dataSet[i].descrizione+"</option>";
						$("#selectPredecessori_rowEdit_"+row).append(opt);
						
						
					}
					
					$("#selectPredecessori_rowEdit_"+row).select2();
					
//					
//					$("#selectPredecessori_rowEdit_"+row+" option").each(function() {
//						
//						console.log($(this).val());
//						
//					})
					
					var idMilestone = $("#idMilestone"+row).html();
					
					$.getJSON("ws/resources/MilestoneMilestones("+idMilestone+")", function(dataSet2){
					  
						//$("#selectPredecessori_rowEdit_"+row+" option").select2(selectedPredecessori);
//						
//						if ($(this).text() == selectedPredecessori)
//							$(this).attr("selected","selected");
//					  
					  
					  var arrayDegliId = [];
					  for(var j in dataSet2) arrayDegliId.push(dataSet2[j].milestoneComponente.idMilestone);
					  
					  console.log(arrayDegliId);
					  
					  $("#selectPredecessori_rowEdit_"+row).val(arrayDegliId).trigger('change');
					  
						//console.log("dataSet: "+dataSet);
						//for(var j in dataSet2){
//							$("#selectPredecessori_rowEdit_33").select2.defaults.set("val", dataSet2[j].idMilestone);
//							console.log("dataSet"+j+": "+dataSet2[j]);
							
							
							//if ($(this).text() == dataSet2[j].idMilestone)
							//	$(this).attr("selected","selected");
							
							
						//}
						
						//$("#selectPredecessori_rowEdit_"+row+" option").select2();
						
					});	
			  }
		});
		
		
		
		
//		$("#selectPredecessori_rowEdit_"+row" option").each(function(){
//			
//			$("#selectPredecessori_rowEdit_"+row+" option").select2(selectedPredecessori);
//			
//			if ($(this).text() == selectedPredecessori)
//				$(this).attr("selected","selected");
//		});
		
		//$("#mySelect2").select2("val", "0");
//	})
}


function addEditForm(row) {	

	var selectedEntita = $("#codiceEntita"+row).html();
	var selectedTipoEvento = $("#codiceTipoEvento"+row).html();
	var selectedPredecessori = $("#predecessori"+row).html();

	console.log(selectedEntita);
	console.log("predecessori: " + selectedPredecessori);
	
	check = '<a style="cursor:pointer" onclick="back('+row+')" id="buttonToCancelRigaEdit'+row+'" data-toggle="tooltip" title="Annulla" data-placement="left"><i style="color:black" class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="update('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Salva" data-placement="right"><i style="color:green" class="fa fa-check"></i></a>';	

	//sCodiceMilestone = '<input style="width:100%" placeholder="Codice Milestone" id="codiceMilestone_rowEdit_'+row+'" type="text" class="form-control" value="'+$("#codiceMilestone"+row).text()+'"/>'; 
	sDescrizioneMilestone = '<input style="width:100%" placeholder="Descrizione" id="descrizioneMilestone_rowEdit_'+row+'" type="text" class="form-control" value="'+$("#descrizioneMilestone"+row).text()+'"/>'; 
	sDescrizioneTagMilestone = '<input style="width:100%" placeholder="Descrizione Tag" id="descrizioneTagMilestone_rowEdit_'+row+'" type="text" class="form-control" value="'+$("#descrizioneTagMilestone"+row).text()+'"/>'; 
	
	if($("#codiceTipoEvento"+row).parent().hasClass("form-group")) sCodiceTipoEvento = '<select style="width:100%;" id="selectCodiceTipoEvento_rowEdit_'+row+'" class="form-control select2"><option></option></select>'; 
	else sCodiceTipoEvento = '<div class="form-group" style="width:100%;"><select style="width:100%;" id="selectCodiceTipoEvento_rowEdit_'+row+'" class="form-control select2"><option></option></select></div>'; 
	
	if($("#codiceEntita"+row).parent().hasClass("form-group")) sCodiceEntita = '<select style="width:100%;" id="selectCodiceEntita_rowEdit_'+row+'" class="form-control select2"><option></option></select>';
	else sCodiceEntita = '<div class="form-group" style="width:100%;"><select style="width:100%;" id="selectCodiceEntita_rowEdit_'+row+'" class="form-control select2"><option></option></select></div>'; 
	
//	sPredecessori = '<input style="width:100%" placeholder="Predecessori" id="predecessori_rowEdit_'+row+'" type="text" class="form-control" value="'+$("#predecessori"+row).text()+'"/>'; 

	if($("#predecessori"+row).parent().hasClass("form-group")) sPredecessori = '<select style="width:100%;" id="selectPredecessori_rowEdit_'+row+'" class="form-control select2" multiple="multiple"><option></option></select>'; 
	else sPredecessori = '<div class="form-group" style="width:100%;"><select style="width:100%;" id="selectPredecessori_rowEdit_'+row+'" class="form-control select2" multiple="multiple"><option></option></select></div>'; 
	
	
	//$("#codiceMilestone"+row).parent().html(sCodiceMilestone);
	$("#descrizioneMilestone"+row).parent().html(sDescrizioneMilestone);
	$("#descrizioneTagMilestone"+row).parent().html(sDescrizioneTagMilestone);
	$("#codiceTipoEvento"+row).parent().html(sCodiceTipoEvento);
	$("#codiceEntita"+row).parent().html(sCodiceEntita);
	$("#predecessori"+row).parent().html(sPredecessori);
	$("#buttonToUpdateRigaEdit"+row).parent().html(check);
	
	getListaEntitaForUpdate(row, selectedEntita);
	getListaTipiEventiForUpdate(row, selectedTipoEvento);
	getListaPredecessoriForUpdate(row, selectedPredecessori);
	
	attivaWidgetSelect2();
	
	$('body>.tooltip').remove();
}


function back(row) {
	
//	var idMilestone = mainDatatable.row(row).data().idEntita;
//	
//	commonGoBack(row, "ws/resources/Milestones(" + idMilestone + ")");
	
	mainDatatable.row(row).ajax.reload();
	$('body>.tooltip').remove();
	/* VECCHIO CODICE. Che non capisco.
	
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
		
		
		if($("#descrizioneTagMilestone_rowEdit_"+row).val() !== '') $("#descrizioneTagMilestone_rowEdit_"+row).parent().html(descrizioneTagMilestone);
		else $("#descrizioneTagMilestone_rowEdit_"+row).parent().html('<div id="descrizioneTagMilestone'+row+'"></div>');
		
		
		$("#selectCodiceTipoEvento_rowEdit_"+row).parent().html(codiceTipoEvento);
		$("#selectCodiceEntita_rowEdit_"+row).parent().html(codiceEntita);
		
		if($("#predecessori_rowEdit_"+row).val() !== '') $("#predecessori_rowEdit_"+row).parent().html(predecessori);
		else $("#predecessori_rowEdit_"+row).parent().html('<div id="predecessori'+row+'"></div>');
			
		$("#buttonToCancelRigaEdit"+row).parent().html(check);
		$('body>.tooltip').remove();
	  }
	 }); */
}



function update(row){
	
	var idMilestone = mainDatatable.row(row).data().idMilestone;
	
	var request = {
	//	codice: $("#codiceMilestone_rowEdit_"+row).val(),  // FIXME: modificabile?!?
		descrizione: $("#descrizioneMilestone_rowEdit_"+row).val(),
		descrizioneTag: $("#descrizioneTagMilestone_rowEdit_"+row).val(),
		tipoEvento: {
			idTipoEvento: $("#selectCodiceTipoEvento_rowEdit_"+row).val(),
		},
		entita: {
			idEntita: $("#selectCodiceEntita_rowEdit_"+row).val()
		},
		milestoneComponente: {
			idMilestone: $("#selectPredecessori_rowEdit_"+row).val()
		}
	};
	
	console.log("requestUpdate: "+ request);
	console.log("row: ", row);
	console.log("idMilestone: ", idMilestone);
	
	commonUpdate(row, "ws/resources/Milestones("+idMilestone+")", request);
	
	/*
	var idMilestone = $("#idMilestone"+row).text();
	
	var valueTipoEvento = $("#idMilestone_rowEdit_"+row).parent().find(".tipoEvento").val();
	
	var sCodiceMilestone = $("#codiceMilestone_rowEdit_"+row).val();
	var sDescrizioneMilestone = $("#descrizioneMilestone_rowEdit_"+row).val();
	var sDescrizioneTagMilestone = $("#descrizioneTagMilestone_rowEdit_"+row).val();
	
	var codiceTipoEvento = $("#codiceTipoEvento_rowEdit_"+row).val();
	
	var selectCodiceTipoEvento = $("#selectCodiceTipoEvento_rowEdit_"+row).val();
	
	//$("div.selectCodiceTipoEvento select").val(codiceTipoEvento);
	
	var selectCodiceEntita = $("#selectCodiceEntita_rowEdit_"+row).val();
	
	var sPredecessori = $("#predecessori_rowEdit_"+row).val();
	
	
	console.log("idMilestone: "+idMilestone," valueTipoEvento: "+valueTipoEvento);
	
	var request = '{"codice":"'+sCodiceMilestone+'","descrizione":"'+sDescrizioneMilestone+'"';
		if(sDescrizioneTagMilestone.length  > 0) request += ',"descrizioneTag":"'+sDescrizioneTagMilestone+'"';	
		if(selectCodiceTipoEvento.length  > 0) request += ',"tipoEvento":{"idTipoEvento":'+selectCodiceTipoEvento+'}';	
		if(selectCodiceEntita.length > 0) request += ',"entita":{"idEntita":'+selectCodiceEntita+'}';
		request += '}';
	
		
		 $.ajax({
		  type: "PUT",
		  url: "ws/resources/Milestones("+idMilestone+")",
		  data: request,
		  contentType: "application/json; charset=utf-8",
		  dataType: "json",
		  success: function(res) {
			
//			  	boolAggregato = '<div id="boolAggregato'+row+'"></div>';
//				idMilestone = '<div id="idMilestone'+row+'">'+$("#idMilestone_rowEdit_"+row).val()+'</div>';
//				codiceMilestone = '<div id="codiceMilestone'+row+'">'+$("#codiceMilestone_rowEdit_"+row).val()+'</div>';
//				descrizioneMilestone = '<div id="descrizioneMilestone'+row+'">'+$("#descrizioneMilestone_rowEdit_"+row).val()+'</div>';
//				
//				if(sDescrizioneTagMilestone === '') sDescrizioneTagMilestone = '';
//				descrizioneTagMilestone = '<div id="descrizioneTagMilestone'+row+'">'+sDescrizioneTagMilestone+'</div>';
//				
//				codiceTipoEvento = '<div id="codiceTipoEvento'+row+'">'+$("#codiceTipoEvento_rowEdit_"+row).val()+'</div>';
//				codiceEntita = '<div id="codiceEntita'+row+'">'+$("#codiceEntita_rowEdit_"+row).val()+'</div>';
//				predecessori = '<div id="predecessori'+row+'">'+$("#predecessori_rowEdit_"+row).val()+'</div>';
//			
//			check = '<a style="cursor:pointer" onclick="removeMilestone('+row+')" id="buttonToDeleteRigaEdit'+row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="addEditForm('+row+')" id="buttonToUpdateRigaEdit'+row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
//			
//			$("#codiceMilestone"+row).parent().parent().find(".idMilestone").html(idMilestone);
//			
//			$("#codiceMilestone"+row).parent().html(codiceMilestone);
//			$("#descrizioneMilestone"+row).parent().html(descrizioneMilestone);
//
//			$("#descrizioneTagMilestone"+row).parent().html(descrizioneTagMilestone);
//
//			
//			$("#codiceTipoEvento"+row).parent().html(codiceTipoEvento);
//			$("#codiceEntita"+row).parent().html(codiceEntita);
//			$("#predecessori"+row).parent().html(predecessori);
//			
//			console.log("codiceMilestone: "+$("#codiceMilestone"+row).parent());
//			
//			//$("#buttonToDeleteRigaNew"+row).parent().html(check);
//			$("#buttonToUpdateRigaEdit"+row).parent().html(check);
			$('body>.tooltip').remove();
			back(row);
			//customAlertOK("Aggiornamento avvenuto correttamente");
		  }
		 });*/
}
	
function insert(row){		

	//prima salvo la riga senza componenti, per avere l'ID
	var request = {
		codice: $("#codiceMilestone_New"+row).val(),
		descrizione: $("#descrizioneMilestone_New"+row).val(),
		descrizioneTag: $("#descrizioneTagMilestone_New"+row).val(),
		tipoEvento: {
			idTipoEvento: $("#codiceTipoEvento_New"+row).val(),
		},
		entita: {
			idEntita: $("#codiceEntita_New"+row).val()
		}
	};

	console.log(request);
	
//	console.log("row: "+ row);
//	callback(data.results[0]);
//	And if you have set multiple:true, just accept the entire results array;
//	callback(data.results);
	
	//COPIA E INCOLLA DA commonInsert(row, "ws/resources/Milestones", request);
	request = JSON.stringify(request); 
	$.ajax({
		type: "POST",
		url: "ws/resources/Milestones",
		data: request,
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(res) {
			
			var nuovaRequest = [];
			var arrMilestoneComponenti = $("#predecessori_New"+row).val();
			for (var i in arrMilestoneComponenti) {
				nuovaRequest.push({
					milestone: {
						idMilestone: res.idMilestone
					},
					milestoneComponente: {
						idMilestone: parseInt(arrMilestoneComponenti[i])
					},
					ordinamento: parseInt(i)
				});
			}
			console.log("arrMilestoneComponenti: "+ arrMilestoneComponenti);
			console.log("res.idMilestone: "+ res.idMilestone);
			console.log("nuovaRequest: ", nuovaRequest);
			//commonInsert(row, "ws/resources/MilestoneMilestones("+res.idMilestone+")", nuovaRequest);
			
			nuovaRequest = JSON.stringify(nuovaRequest); 
			$.ajax({
				type: "POST",
				url: "ws/resources/MilestoneMilestones("+res.idMilestone+")",
				data: nuovaRequest,
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			});
			
			mainDatatable.row.add(res).draw();

			$('body>.tooltip').remove();

		}
	});
		
	/*
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
		 });*/
}


function getOptionButtons(data, type, row, meta){
	return '<a style="cursor:pointer" onclick="removeMilestone('+row.idMilestone+')" id="buttonToDeleteRigaEdit'+row.idMilestone+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="addEditForm('+row.idMilestone+')" id="buttonToUpdateRigaEdit'+row.idMilestone+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
}


function renderIdMilestone(data, type, row, meta) {
	return wrapDiv("idMilestone", meta.row, data);
}

function renderAggregata(data, type, row, meta) {
	
	if(row.tipoEvento == null && row.idEntita == null)
		return '<span class="alert-info">Aggregata</span>';
	else if (row.predecessori != null) //FIXME
		return '<span class="alert-danger">Errore</span>';
	else
		return 'Atomica';
}

function renderCodice(data, type, row, meta) {
	return wrapDiv("codiceMilestone", meta.row, data);
}

function renderDescrizione(data, type, row, meta) {
	return wrapDiv("descrizioneMilestone", meta.row, data);
}

function renderDescrizioneTag(data, type, row, meta) {
	return wrapDiv("descrizioneTagMilestone", meta.row, data);
}

function renderTipoEvento(data, type, row, meta) {
	return wrapDiv("codiceTipoEvento", meta.row, data);
}

function renderEntita(data, type, row, meta) {
	return wrapDiv("codiceEntita", meta.row, data);
}

function renderPredecessori(data, type, row, meta) {
	
//	callback(data.results[0]);
//	
//	And if you have set multiple:true, just accept the entire results array;
//
//	callback(data.results);
	
	return wrapDiv("predecessori", meta.row, data);
}

function renderRowButtons(data, type, row, meta) {
	return '<a style="cursor:pointer" onclick="removeMilestone('+meta.row+')" id="buttonToDeleteRigaEdit'+meta.row+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>'+
		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
		'<a style="cursor:pointer" onclick="addEditForm('+meta.row+')" id="buttonToUpdateRigaEdit'+meta.row+'" data-toggle="tooltip" title="Modifica" data-placement="right"><i class="fa fa-pencil"></i></a>';
}


/**
 * Funzione che popola la tabella presente nella pagina 'gestioneMilestones.jsp' visualizzando le milestone atomiche e aggregate
 * @returns
 */
var rowCounter = 0
function getListaMilestones(){
	
	mainDatatable = $("#tableListaMilestones").DataTable({
		paging : false,
		lengthChange : false,
		searching : false,
		ordering : true,
		info : false,
		autoWidth : false,
		ajax: {
			url: 'ws/resources/Milestones',
			dataSrc: '',
			language: 'it'
		},
		autoWidth : false,
		destroy : true,
		columns : [
			{data : 'deleteRowButton', className : 'col-md-1 tdCenter', defaultContent : '', render: renderRowButtons },
			{data : 'boolAggregato', className : 'col-md-1 boolAggregato', defaultContent : '', render: renderAggregata },
			{data : 'idMilestone', className : 'hide', defaultContent : '', render: renderIdMilestone },
			{data : 'codice', className : 'col-md-1', defaultContent : '', render: renderCodice }, 
			{data : 'descrizione', className : 'col-md-3', defaultContent : '', render: renderDescrizione },
			{data : 'descrizioneTag', className : 'col-md-3', defaultContent : '', render: renderDescrizioneTag },
			{data : 'tipoEvento.codice', className : 'col-md-1 tipoEvento', defaultContent : '', render: renderTipoEvento},
			{data : 'entita.codice', className : 'col-md-1', defaultContent : '', render: renderEntita },
			{data : 'predecessori', className : 'col-md-1', defaultContent : '', render: renderPredecessori }
			]
	});
	

	
	addButtonInputForm("tableListaMilestones");
	
	/*
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
	 });*/
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
function addInputForm() {
	
	var rowCounter = mainDatatable.rows().length;
	
	var row = '<tr role="row">'
	+'	<td class="tdCenter"><a id="buttonToDeleteRigaNew'+rowCounter+'" style="cursor: pointer;" onclick="removeInputForm(this)" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onclick="insert('+rowCounter+')" id="buttonToUpdateRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Inserisci dati" data-placement="right"><i style="color:green" class="fa fa-check"></i></a></td>'
	+'	<td></td>'
	+'	<td class="idMilestone hide"></td>'
	+'	<td><input style="width:100%" placeholder="{{labels.gestioneMilestones_codice}}" id="codiceMilestone_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td><input style="width:100%" placeholder="{{labels.gestioneMilestones_descrizione}}" id="descrizioneMilestone_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td><input style="width:100%" placeholder="{{labels.gestioneMilestones_descrizioneTag}}" id="descrizioneTagMilestone_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'	<td><div class="form-group" style="width:100%;"><select style="width:100%;" id="codiceTipoEvento_New'+rowCounter+'" class="form-control select2"><option></option></select></div></td>'
	+'	<td><div class="form-group" style="width:100%;"><select style="width:100%;" id="codiceEntita_New'+rowCounter+'" class="form-control select2"><option></option></select></div></td>'
	+'	<td><div class="form-group" style="width:100%;"><select style="width:100%;" id="predecessori_New'+rowCounter+'" class="form-control select2" multiple="multiple"><option></option></select></div></td>'
//	+'	<td class="col-md-1"><input style="width:100%" placeholder="{{labels.gestioneMilestones_predecessori}}" id="predecessori_New'+rowCounter+'" type="text" class="form-control"/></td>'
	+'</tr>';
	
	
	
	getListaEntita(rowCounter);
	getListaTipiEventi(rowCounter);
	getListaPredecessori(rowCounter);
	
	$('#tableListaMilestones').append(row);
	addButtonInputForm("tableListaMilestones");
	
	attivaWidgetSelect2();
}

/**
 * Funzione che come prima cosa ottiene la lista delle tipologie di entità (tramite chiamata ad opportuno servizio) ed infine ne popola le opzioni di uno specifico tag <select>
 * @param row
 * @returns
 */
function getListaEntita(row) {
	$.getJSON("ws/resources/Entita", function(dataSet) {
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


/**
 * Funzione che come prima cosa ottiene la lista dei predecessori (tramite chiamata ad opportuno servizio) ed infine ne popola le opzioni di uno specifico tag <select>
 * @param row
 * @returns
 */
function getListaPredecessori(row) {
	$.getJSON("ws/resources/MilestoneMilestones", function(dataSet) {
		for(i in dataSet){
			var opt = "<option value='"+dataSet[i].milestoneComponente.idMilestone+"'>"+dataSet[i].milestoneComponente.descrizione+"</option>";
			$("#predecessori_New"+(row)).append(opt);
		}
	});
}







