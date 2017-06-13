/**
 * Funzione document.ready di jQuery
 */
$(document).ready(function(){
	getListaCalendari();
	getListaMilestone(rowCounter-1);
})


/**
 * Funzione che aggiunge una nuova riga alla tabella 'Milestone' presente in gestioneCalendario.jsp per aggiungere una nuova milestone
 * a un determinato calendario
 * @returns
 */
//function addInputForm(){
//	var row = '<tr id="tr_row_table'
//			+ rowCounter
//			+ '"><td style="text-align: center"><a style="cursor: pointer;" onclick="removeInputForm('
//			+ rowCounter
//			+ ')" id="buttonToDeleteRiga'
//			+ rowCounter
//			+ '" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;<a style="margin-left: 15px; cursor: pointer;" onclick="addInputForm()" id="buttonToAddRiga'
//			+ rowCounter
//			+ '" data-toggle="tooltip" title="Aggiungi" data-placement="bottom"><i style="color:green" class="fa fa-plus-circle"></i></a></td><td><div class="form-group"><select id="milestoneNuovoCal'
//			+ rowCounter
//			+ '" class="form-control select2" style="width: 100%;"><option></option></select></div></td><td><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-calendar"></i></div><input onkeydown="return><td><div class="bootstrap-timepicker"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-clock-o"></i></div><input onkeydown="return false" id="oraNuovoCal'
//			+ rowCounter
//			+ '" placeholder="Ora" type="text" class="form-control timepicker"></div></div></div></td><td><input type="text" class="form-control" id="tagNuovoCal'
//			+ rowCounter + '" placeholder="TAG"></td></tr>';
//	$('#tableNuovoCalendario').append(row);
//	
//	attivaWidget();
//		
////	document.getElementById("buttonToAddRiga"+(rowCounter-1)).className = "disabled";
////	document.getElementById("buttonToDeleteRiga"+(rowCounter-1)).className = "disabled";
//	
//	
//	
//	rowCounter++;
//	getListaMilestone(rowCounter-1);
//}

/**
 * Funzione che elimina la riga selezionata dalla tabella 'Milestone' presente in gestioneCalendario.jsp per aggiungere una nuova milestone
 * a un determinato calendario
 * @returns
 */
function removeInputForm(rowIndex) {
	if(rowIndex === 'null') return;
	$("#tr_row_table"+(rowIndex)).remove();
//	document.getElementById("buttonToAddRiga"+(rowCounter-1)).className = "";
//	document.getElementById("buttonToDeleteRiga"+(rowCounter-1)).className = "";
	rowCounter = rowCounter - 1;
	$('body>.tooltip').remove();
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
 * Funzione che effettua una chiamata AJAX all'apposito ws, il quale restituisce una lista
 * contenente tutte le milestone e i relativi id
 * 
 * @returns
 */
function getListaMilestone(j){
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
function selezionaCalendario(selectIndex){
	var idx = selectIndex.selectedIndex;
	var idCalendario = selectIndex.options[idx].value;
	getDettaglioCalendarioMilestone(idCalendario);
	getDettaglioCalendarioMilestoneEditabile(idCalendario);
}






















var rowCounter = 0
function getDettaglioCalendarioMilestoneEditabile(idCalendario){
	
	var selectMilestons = '<select>';
	
	$.ajax({
		type : "GET",
		url : "ws/resources/Calendari(" + idCalendario + ")/Milestone",
		dataType : "json",
		success : function(dataSet) {		
			
			for (i in dataSet){
				dataSet[i].milestone.descrizione = '<option selected>'+dataSet[i].milestone.descrizione+'</option>';
				
				dataSet[i].deleteRowButton = '<a href="#" onclick="removeInputForm('+i+')" id="buttonToDeleteRiga'+i+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>';
            
            
				dataSet[i].dataOraPreviste1 = '<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-calendar"></i></div><input id="dataCalEdit'+i+'" value="'+convertData(dataSet[i].dataOraPreviste)+'" onkeydown="return false" type="text" placeholder="Data" class="form-control pull-right datepicker"></div></div>';
				dataSet[i].dataOraPreviste2 = '<div class="bootstrap-timepicker"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-clock-o"></i></div><input onkeydown="return false" id="oraCalEdit'+i+'" value="'+convertTime(dataSet[i].dataOraPreviste)+'" placeholder="Ora" type="text" class="form-control timepicker"/></div></div></div>';
				dataSet[i].tags = '<input type="text" class="form-control" value="'+dataSet[i].tags+'"/>';
				rowCounter++;
			}
			
			selectMilestons += '</select>';
			
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
					{data : 'milestone.descrizione'}, 
					{data : 'dataOraPreviste1'}, 
					{data : 'dataOraPreviste2'}, 
					{data : 'tags'}, 
					]
			});
			
			attivaWidget();
		}
	});
}











function convertData(data){
	var firstSplit = data.split("T");
	var secondSplit = firstSplit[0].split("-");
	return secondSplit[2]+"/"+secondSplit[1]+"/"+secondSplit[0];
}

function convertTime(ora){
	var firstSplit = ora.split("T");
	var secondSplit = firstSplit[1].split("+");
	return secondSplit[0];
}


function getDettaglioCalendarioMilestone(idCalendario) {
	$.ajax({
		type : "GET",
		url : "ws/resources/Calendari(" + idCalendario + ")/Milestone",
		dataType : "json",
		success : function(dataSet) {			
			$("#divDettagliCalendarioMilestone").removeClass("hide");
			$("#tableDettaglioCalendarioMilestone").DataTable({
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
					{data : 'milestone.descrizione'}, 
					{data : 'dataOraPreviste'}, 
					{data : 'dataOraPreviste'}, 
					{data : null, defaultContent: ''}, 
					{data : 'tags'}, 
					]
			});
		}
	});
}


/**
 * Funzione che richiama i widget riattivandoli sulle righe nuove create a runtine, attraverso
 * il tasto 'aggiungi riga'
 * @returns
 */
function attivaWidget(){
	$(".select2").select2({
    	placeholder: "Seleziona",
        minimumResultsForSearch: 10,
    });
    
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
    
    //Timepicker
    $(".timepicker").timepicker({
      showInputs: false,
      showMeridian: false,
      defaultTime: false,
      minuteStep: 1,
      autoclose: true
    });
}

