/**
 * Funzione document.ready di jQuery
 */
$(document).ready(function(){
	//getListaCalendari_gestCal();
	//getListaMilestone_gestCal(rowCounter);
})




/**
 * Funzione che elimina la riga selezionata dalla tabella 'Milestone' presente in gestioneCalendario.jsp per aggiungere una nuova milestone
 * a un determinato calendario
 * @returns
 */
function removeInputForm(row) {
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
 * Funzione che effettua una chiamata AJAX all'apposito ws, il quale restituisce una lista
 * contenente tutti i calendari e i relativi id
 * 
 * @returns
 */
function getListaCalendari_gestCal(){
	 $.getJSON("ws/resources/Calendari", function(dataSet){
		 for(i in dataSet){
			 var opt = "<option value='"+dataSet[i].idCalendario+"'>"+dataSet[i].descrizione+"</option>";
			 $("#select_elenco_calendari").append(opt);
	     }
	 });
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
 * Funzione che il valore della chiave, di un option scelto da una combobox, catturato attraverso il metodo onchange 
 * @param selectIndex
 * @returns
 */
function selezionaCalendario_gestCal(selectIndex){
	var idx = selectIndex.selectedIndex;
	var idCalendario = selectIndex.options[idx].value;
	getDettaglioCalendarioMilestone(idCalendario);
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
	alert("ciao");
}







var rowCounter = 0
function getDettaglioCalendarioMilestoneEditabile(idCalendario){
	$.getJSON("ws/resources/Milestones", function(dataSet2){
		$.ajax({
			type : "GET",
			url : "ws/resources/Calendari(" + idCalendario + ")/Milestone",
			dataType : "json",
			success : function(dataSet) {		
				
				for (i in dataSet){
					dataSet[i].deleteRowButton = '<a href="#" onclick="removeInputForm(this)" id="buttonToDeleteRiga'+i+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>';
					 var opt = "<div class='form-group'><select class='form-control select2' id='selectMilestoneCal'>";
					 for(j in dataSet2){
						 if(dataSet[i].milestone.descrizione === dataSet2[j].descrizione) opt += "<option selected value='"+dataSet2[j].idMilestone+"'>"+dataSet2[j].descrizione+"</option>";
					     else opt += "<option select2' value='"+dataSet2[j].idMilestone+"'>"+dataSet2[j].descrizione+"</option>";
				     }
					 opt += "</select></div>";
					 dataSet[i].selectMilestones = opt;
					
					dataSet[i].dataOraPreviste1 = '<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-calendar"></i></div><input id="dataCalEdit'+i+'" value="'+convertData(dataSet[i].dataOraPreviste)+'" onkeydown="return false" type="text" placeholder="Data" class="form-control pull-right datepicker"></div></div>';
					dataSet[i].dataOraPreviste2 = '<div class="bootstrap-timepicker"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-clock-o"></i></div><input id="oraCalEdit'+i+'" value="'+convertTime(dataSet[i].dataOraPreviste)+'" placeholder="Ora" type="text" class="form-control timepicker"/></div></div></div>';
					dataSet[i].tags = '<input type="text" class="form-control" value="'+dataSet[i].tags+'"/>';
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
						{data : 'deleteRowButton', className : 'tdCenter'},
						{data : 'selectMilestones', className : 'tdCenter'}, 
						{data : 'dataOraPreviste1', className : 'tdCenter'}, 
						{data : 'dataOraPreviste2', className : 'tdCenter'}, 
						{data : 'tags', className : 'tdCenter'}, 
						{data : null, className : 'tdCenter', defaultContent : ''}, 
						]
				});

				addInputForm();
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
	var row = '<tr role="row"><td class="tdCenter"><a style="cursor: pointer;" onclick="addInputForm()" data-toggle="tooltip" title="Aggiungi" data-placement="bottom"><i style="color:green" class="fa fa-plus-circle"></i></a></td><td class="tdCenter"><div class="form-group" style="width:100%;"><select style="width:100%;" id="milestoneNuovoCal'+rowCounter+'" class="form-control select2"><option></option></select></div></td><td class="tdCenter"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-calendar"></i></div><input onkeydown="return false" type="text" placeholder="Data" class="form-control pull-right datepicker"></div></div></td><td class="tdCenter"><div class="bootstrap-timepicker"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-clock-o"></i></div><input id="oraCalEdit'+i+'" placeholder="Ora" type="text" class="form-control timepicker"/></div></div></div></td><td class="tdCenter"><input type="text" class="form-control" placeholder="TAGs"/></td><td class="tdCenter"></td></tr>';
	
	getListaMilestone_gestCal(rowCounter);
	$('#tableCalendarioEditabile').append(row);
	attivaWidget();
	rowCounter++;
}





/**
 * Funzione che splitta e converte la data passata come parametro, dal formato timeStamp a quello dd/MM/yyyy.
 * In più viene anche eliminata l'ora dal parametro così da restituire solamente la data
 * @param data
 * @returns
 */
function convertData(data){
	var firstSplit = data.split("T");
	var secondSplit = firstSplit[0].split("-");
	return secondSplit[2]+"/"+secondSplit[1]+"/"+secondSplit[0];
}

/**
 * Funzione che splitta e converte l'ora passata come parametro, dal formato timeStamp a quello hh:mm.
 * In più viene anche eliminata la data dal parametro così da restituire solamente l'ora
 * @param data
 * @returns
 */
function convertTime(ora){
	var firstSplit = ora.split("T");
	var secondSplit = firstSplit[1].split("+");
	return secondSplit[0];
}


/**
 * Funzione che richiama i widget riattivandoli sulle righe nuove create a runtine, attraverso
 * il tasto 'aggiungi riga'
 * @returns
 */
function attivaWidget(){
	$(function () {
		  //Initialize Select2 Elements
		  $(".select2").select2({
		  	placeholder: "Seleziona",
		    minimumResultsForSearch: 10,
		    language: 'it'
		  });
		
		  //Datemask dd/mm/yyyy
		  $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
		  
		  //Money Euro
		  $("[data-mask]").inputmask();
		
		  //Date picker
		  $('.datepicker').datepicker({
		 	language: 'it',
		    orientation: "auto",
		    format: 'dd/mm/yyyy',
		    todayHighlight: true,
		    autoclose: true,
		    todayBtn: "linked",
		    toggleActive: true
		  });
		
		  //iCheck for checkbox and radio inputs
		  $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
		    checkboxClass: 'icheckbox_minimal-blue',
		    radioClass: 'iradio_minimal-blue'
		  });
		  
		  //Timepicker
		  $(".timepicker").timepicker({
		    showInputs: false,
		    showMeridian: false,
		    defaultTime: false,
		    minuteStep: 1,
		    autoclose: true,
		    //appendWidgetTo: '.table-responsive'
		  });		  
		});
}

