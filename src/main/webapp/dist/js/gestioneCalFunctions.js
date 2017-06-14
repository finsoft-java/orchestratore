
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
	var idSelectMilestoneCal = $(row).parent().parent().find("#selectMilestoneCal").val();
	if(confirm("Sicuro di voler eliminare questa milestone da questo calendario?")){
		$.ajax({
			type : "DELETE",
			url : "ws/resources/CalendarioMilestones(" + idSelectMilestoneCal + ")",
			dataType : "json",
			success : function(dataSet) {		
				$(row).parent().parent().remove();
				rowCounter = rowCounter - 1;
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
	    $("#milestoneNuovoCal"+(j)).append(opt);
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





function saveEditedCalendar(){
	var dataList = [];
	for(rowCounterFromDBData; rowCounterFromDBData<rowCounter; rowCounterFromDBData++){
		
		dataList.push([$("#selectMilestoneCalNew"+rowCounterFromDBData).val(), $("#dataCalNew"+rowCounterFromDBData).val(), $("#oraCalNew"+rowCounterFromDBData).val(), $("#tagsCalNew"+rowCounterFromDBData).val()]);
		
		
		
		
		//alert("ciao");
		selectMilestoneCalEdit6 = $("#selectMilestoneCalEdit"+rowCounterFromDBData).val();
		//location.reload(true);
	}
	
	
}







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
					dataSet[i].deleteRowButton = '<a href="#" onclick="removeMilestone(this)" id="buttonToDeleteRigaEdit'+rowCounter+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>';
					 var opt = "<div class='form-group'><select class='form-control select2' id='selectMilestoneCalEdit"+rowCounter+"'>";
					 for(j in dataSet2){
						 if(dataSet[i].milestone.descrizione === dataSet2[j].descrizione) opt += "<option selected value='"+dataSet2[j].idMilestone+"'>"+dataSet2[j].descrizione+"</option>";
					     else opt += "<option select2' value='"+dataSet2[j].idMilestone+"'>"+dataSet2[j].descrizione+"</option>";
				     }
					 opt += "</select></div>";
					 dataSet[i].selectMilestones = opt;
					
					dataSet[i].dataOraPreviste1 = '<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-calendar"></i></div><input id="dataCalEdit'+rowCounter+'" value="'+convertTimestampToData(dataSet[i].dataOraPreviste)+'" onkeydown="return false" type="text" placeholder="Data" class="form-control pull-right datepicker"></div></div>';
					dataSet[i].dataOraPreviste2 = '<div class="bootstrap-timepicker"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-clock-o"></i></div><input id="oraCalEdit'+rowCounter+'" value="'+convertTimestampToTime(dataSet[i].dataOraPreviste)+'" placeholder="Ora" type="text" class="form-control timepicker"/></div></div></div>';
					dataSet[i].tags = '<input id="tagsCalEdit'+rowCounter+'" type="text" class="form-control" value="'+dataSet[i].tags+'"/>';
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
						{data : 'deleteRowButton', className : 'tdCenter class="col-md-1'},
						{data : 'selectMilestones', className : 'tdCenter class="col-md-3'}, 
						{data : 'dataOraPreviste1', className : 'tdCenter class="col-md-2'}, 
						{data : 'dataOraPreviste2', className : 'tdCenter class="col-md-2'}, 
						{data : 'tags', className : 'tdCenter class="col-md-2'}, 
						{data : null, className : 'tdCenter class="col-md-2', defaultContent : ''}, 
						]
				});
				rowCounterFromDBData = rowCounter;
				addButtonInputForm();
				attivaWidget();
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
	var row = '<tr role="row"><td class="tdCenter col-md-1 class="col-md-1"><a id="buttonToDeleteRigaNew'+rowCounter+'" style="cursor: pointer;" onclick="removeInputForm(this)" data-toggle="tooltip" title="Elimina" data-placement="bottom"><i style="color:red" class="fa fa-trash-o"></i></a></td><td class="tdCenter col-md-3"><div class="form-group" style="width:100%;"><select style="width:100%;" id="selectMilestoneCalNew'+rowCounter+'" class="form-control select2"><option></option></select></div></td><td class="tdCenter col-md-2"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-calendar"></i></div><input id="dataCalNew'+rowCounter+'" onkeydown="return false" type="text" placeholder="Data" class="form-control pull-right datepicker"></div></div></td><td class="tdCenter col-md-2"><div class="bootstrap-timepicker"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-clock-o"></i></div><input id="oraCalNew'+rowCounter+'" placeholder="Ora" type="text" class="form-control timepicker"/></div></div></div></td><td class="tdCenter col-md-2"><input id="tagsCalNew'+rowCounter+'" type="text" class="form-control" placeholder="TAGs"/></td><td class="tdCenter col-md-2"></td></tr>';
	
	getListaMilestone_gestCal(rowCounter);
	$('#tableCalendarioEditabile').append(row);
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
	var row = '<tr id="aggiungiButtonRow" role="row"><td class="tdCenter col-md-1"><a style="cursor: pointer;" onclick="addInputForm()" data-toggle="tooltip" title="Aggiungi" data-placement="bottom"><i style="color:green" class="fa fa-plus-circle"></i></a></td><td class="tdCenter col-md-3"></td><td class="tdCenter col-md-2"></td><td class="tdCenter col-md-2"></td><td class="tdCenter col-md-2"></td><td class="tdCenter col-md-2"></td></tr>';
	$('#tableCalendarioEditabile').append(row);
	$('body>.tooltip').remove();
}