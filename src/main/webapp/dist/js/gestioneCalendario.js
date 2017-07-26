
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
function selezionaCalendario_gestCal(selectIndex){
	var idx = selectIndex.selectedIndex;
	var idCalendario = selectIndex.options[idx].value;
	getDettaglioCalendarioMilestoneEditabile(idCalendario);
	rowCounter = 0;
}


/**
 * Funzione che al richiamo effettua l'eliminazione dal DB di uno specifico calendario, ovvero quello
 * selezionato attravero la comboBox in 'gestioneCalendario.jsp'
 * @returns
 */
function deleteCalendar(){
	var idSelect = $("#select_elenco_calendari").val();
	if(idSelect !== '') {
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
				$.ajax({
					type : "DELETE",
					url : "ws/resources/Calendari(" + idSelect + ")",
					dataType : "json",
					success : function(dataSet) {		
						location.reload(true);
					}
				});
		      }
		    }
		  });
	} else customAlertError("Selezionare un calendario per poterlo eliminare");
}


var counterRicorsione = 0;
function saveEditedCalendar(){
	updateCalendarData();
	if(counterRicorsione === 0) {
		alert("fine ricorsione funzione di aggiornamento");
		console.log("FINE AGGIORNAMENTO DATI");
		insertMilestoneInCalendar();
		if(counterRicorsione === 0) {
			alert("fine ricorsione funzione di inserimento");
			console.log("FINE INSERIMENTO DATI");
//			location.reload(true);
			counterRicorsione = 0;
		}
	}
}


function updateCalendarData(){
	if(counterRicorsione === rowCounterFromDBData) return;
	var idCalendarioMilestone = $("#idCalendarioMilestone"+counterRicorsione).text();
	var dataList = [];
	dataList.push([$("#selectMilestoneCalEdit"+counterRicorsione).val(), convertDataOraToTimestamp($("#dataCalEdit"+counterRicorsione).val(), $("#oraCalEdit"+counterRicorsione).val()), $("#tagsCalEdit"+counterRicorsione).val(), $("#descTagsCalEdit"+counterRicorsione).val()]);
	
	for(var i = 0; i<dataList.length; i++){
		var data = dataList[i];
		var request = {            
			_milestone:data[0],
			_dataOrePreviste:data[1],
			_tag:data[2],
			_descrizione:data[3]
		};
	}
	request = JSON.stringify(request); 
	console.log("idCalendarioMilestone: "+idCalendarioMilestone);
	console.log(request);
	//recupero dati prima riga
//	 $.ajax({
//		  type: "PUT",
//		  url: "CalendarioMilestones("+idCalendarioMilestone+")",
//		  data: request,
//		  contentType: "application/json; charset=utf-8",
//		  dataType: "json",
//		  success: function(res) {
		  counterRicorsione++;
		  updateCalendarData();			  
//		  }
//		 });
	  counterRicorsione--;
}

function insertMilestoneInCalendar() {
    var temp = rowCounterFromDBData+counterRicorsione;
	if(counterRicorsione === (rowCounter - rowCounterFromDBData)) return;
	var dataList = [];
	dataList.push([$("#selectMilestoneCalNew"+temp).val(), convertDataOraToTimestamp($("#dataCalNew"+temp).val(), $("#oraCalNew"+temp).val()), $("#tagsCalNew"+temp).val(), $("#descrizioneCalNew"+temp).val()]);
	
	for(var i = 0; i<dataList.length; i++){
		var data = dataList[i];
		var request = {            
			_milestone:data[0],
			_dataOrePreviste:data[1],
			_tag:data[2],
			_descrizione:data[3]
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
		  insertMilestoneInCalendar();			  
	//	  }
	//	 });
	  counterRicorsione--;
}


/**
 * Funzione che popola la tabella presente nella pagina 'gestioneCalendario.jsp' visualizzando le milestone del relativo
 * calendario selezionato
 * @param idCalendario
 * @returns
 */
var rowCounter = 0
var rowCounterFromDBData = 0;
function getDettaglioCalendarioMilestoneEditabile(idCalendario){
	$.getJSON("ws/resources/Milestones", function(dataSet2){
		$.ajax({
			type : "GET",
			url : "ws/resources/Calendari(" + idCalendario + ")/Milestone",
			dataType : "json",
			success : function(dataSet) {		
				
				for (i in dataSet){
					dataSet[i].deleteRowButton = '<a onclick="removeMilestone('+rowCounter+')" id="buttonToDeleteRigaEdit'+rowCounter+'" style="cursor:pointer" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>';
					 var opt = "<div style='width:100%' class='form-group'><select class='form-control select2' id='selectMilestoneCalEdit"+rowCounter+"'>";
					 for(j in dataSet2){
						 if(dataSet[i].milestone.descrizione === dataSet2[j].descrizione) opt += "<option selected value='"+dataSet2[j].idMilestone+"'>"+dataSet2[j].descrizione+"</option>";
					     else opt += "<option select2' value='"+dataSet2[j].idMilestone+"'>"+dataSet2[j].descrizione+"</option>";
				     }
					opt += "</select></div>";
					
					dataSet[i].selectMilestones = opt;
					dataSet[i].idCalendarioMilestone = '<div id="idCalendarioMilestone'+rowCounter+'">'+dataSet[i].idCalendarioMilestone+'</div>';
					 
					dataSet[i].dataOraPreviste1 = '<div style="width:100%" class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-calendar"></i></div><input id="dataCalEdit'+rowCounter+'" value="'+convertTimestampToData(dataSet[i].dataOraPreviste)+'" onkeydown="return false" type="text" placeholder="Data" class="form-control pull-right datepicker"></div></div>';
					dataSet[i].dataOraPreviste2 = '<div style="width:100%" class="bootstrap-timepicker"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-clock-o"></i></div><input id="oraCalEdit'+rowCounter+'" value="'+convertTimestampToTime(dataSet[i].dataOraPreviste)+'" placeholder="Ora" type="text" class="form-control timepicker"/></div></div></div>';
					
					dataSet[i].tag = '<input style="width:100%" placeholder="Tag" id="tagsCalEdit'+rowCounter+'" type="text" class="form-control" value="'+dataSet[i].tag+'"/>';
					
					if(dataSet[i].milestone.descrizioneTag != null){
						dataSet[i].milestone.descrizioneTag = '<input style="width:100%" placeholder="Descrizione" id="descTagsCalEdit'+rowCounter+'" type="text" class="form-control" value="'+dataSet[i].milestone.descrizioneTag+'"/>';
					}else{
						dataSet[i].milestone.descrizioneTag = '<input style="width:100%" placeholder="Descrizione" id="descTagsCalEdit'+rowCounter+'" type="text" class="form-control"/>';
					}
					
					rowCounter++;
				}
				$("#div_tabella_milestone_editabile").removeClass("hide");
				$("#tableCalendarioEditabile").DataTable({
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
						{data : 'deleteRowButton', className : 'tdCenter col-md-1'},
						{data : 'idCalendarioMilestone', className : 'hide'},
						{data : 'selectMilestones', className : 'tdCenter col-md-2'}, 
						{data : 'dataOraPreviste1', className : 'tdCenter col-md-2'}, 
						{data : 'dataOraPreviste2', className : 'tdCenter col-md-2'}, 
						{data : 'tag', className : 'tdCenter col-md-2'}, 
						{data : 'milestone.descrizioneTag', className : 'hide', defaultContent : ''}
						]
				});
				rowCounterFromDBData = rowCounter;
				addButtonInputForm();
				attivaWidgetSelect2();
				attivaWidgetDatepicker();
				attivaWidgetTimepicker();
			}
		});	
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
	+'	<td class="tdCenter col-md-2"><div class="form-group" style="width:100%;"><select style="width:100%;" id="selectMilestoneCalNew'+rowCounter+'" class="form-control select2"><option></option></select></div></td>'
	+'	<td class="tdCenter col-md-2"><div style="width:100%" class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-calendar"></i></div><input placeholder="Data" id="dataCalNew'+rowCounter+'" onkeydown="return false" type="text" class="form-control pull-right datepicker"></div></div></td>'
	+'	<td class="tdCenter col-md-2"><div style="width:100%" class="bootstrap-timepicker"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-clock-o"></i></div><input id="oraCalNew'+rowCounter+'" placeholder="Ora" type="text" class="form-control timepicker"/></div></div></div></td>'
	+'	<td class="tdCenter col-md-2"><input style="width:100%" placeholder="Tag" id="tagsCalNew'+rowCounter+'" type="text" class="form-control"/></td>'
	+'</tr>';
	
	getListaMilestone_gestCal(rowCounter);
	$('#tableCalendarioEditabile').append(row);
	addButtonInputForm();
	attivaWidgetSelect2();
	attivaWidgetDatepicker();
	attivaWidgetTimepicker();
	rowCounter++;
}

/**
 * Funzione che aggiunge una nuova riga alla tabella 'Milestone' presente in gestioneCalendario.jsp per aggiungere una nuova milestone
 * a un determinato calendario
 * @returns
 */
function addButtonInputForm(){	
	$("#aggiungiButtonRow").remove();
	var row = '<tr id="aggiungiButtonRow" role="row"><td class="tdCenter col-md-1"><a style="cursor: pointer;" onclick="addInputForm()" data-toggle="tooltip" title="Aggiungi" data-placement="bottom"><i style="color:green" class="fa fa-plus-circle"></i></a></td><td class="tdCenter col-md-2"></td><td class="tdCenter col-md-2"></td><td class="tdCenter col-md-2"></td><td class="tdCenter col-md-1"></td><td class="hide"></td></tr>';
	$('#tableCalendarioEditabile').append(row);
	$('body>.tooltip').remove();
}





