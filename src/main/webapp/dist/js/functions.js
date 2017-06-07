
var rowCounter = 1
function addRiga(){
	var row = '<tr><td style="text-align: center"><a style="cursor: pointer;" onclick="eliminaRiga()" id="buttonToDeleteRiga'+rowCounter+'" data-toggle="tooltip" title="Elimina" data-placement="left"><i style="color:red" class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;<a style="margin-left: 15px; cursor: pointer;" onclick="addRiga()" id="buttonToAddRiga'+rowCounter+'" data-toggle="tooltip" title="Aggiungi" data-placement="bottom"><i style="color:green" class="fa fa-plus-circle"></i></a></td><td style="text-align: center">'+(rowCounter+1)+'</td><td><div class="form-group"><select id="milestoneNuovoCal'+rowCounter+'" class="form-control select2" style="width: 100%;"><option></option><option>Milestone 4</option><option>Milestone 4 3</option><option>Milestone 4 2</option><option>Milestone 4 1</option></select></div></td><td><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-calendar"></i></div><input onkeydown="return false" type="text" placeholder="Data" class="form-control pull-right datepicker" id="dataNuovoCal'+rowCounter+'"></div></div></td><td><div class="bootstrap-timepicker"><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-clock-o"></i></div><input onkeydown="return false" id="oraNuovoCal'+rowCounter+'" placeholder="Ora" type="text" class="form-control timepicker"></div></div></div></td><td><input type="text" class="form-control" id="tagNuovoCal'+rowCounter+'" placeholder="TAG"></td></tr>';
	$('#tableNuovoCalendario').append(row);
	
	attivaWidget();
		
	document.getElementById("buttonToAddRiga"+(rowCounter-1)).className = "disabled";
	rowCounter++;
}

function eliminaRiga(){
	
}





$(document).ready(function(){
	getDescCalendario();
})


function getDescCalendario(){
	 $.getJSON("ws/resources/Calendari", function(dataSet){
		 for(i in dataSet){
			 var opt = "<option value='"+dataSet[i].idCalendario+"'>"+dataSet[i].descrizione+"</option>";
			 $("#select_elenco_calendari").append(opt);
	     }
	 });
}





function selezionaCalendario(selectIndex){
	var idx = selectIndex.selectedIndex;
	var which = selectIndex.options[idx].value;
	getDettaglioCalendarioMilestone(which);
	$("#divDettagliCalendarioMilestone").removeClass("hide");
}


function getDettaglioCalendarioMilestone(idCalendario){
		$.ajax({
		    type : "GET",
			url : "ws/resources/CalendariMilestone("+idCalendario+")",
			dataType : "json",
			success : function(dataSet) {
				
			 $("#tableDettaglioCalendarioMilestone").DataTable({
			  paging : false,
		      lengthChange: false,
		      searching: false,
		      ordering: false,
		      info: false,
		      autoWidth: false,
			  data : dataSet,
			  autoWidth : false,
			  destroy: true,
			  columns : [ 
				   { data : dataSet.milestone.descrizione}, 
				   { data : dataSet.dataOraPreviste},
				   { data : dataSet.dataOraPreviste},
				   { data : null},
				   { data : dataSet.tags},
			       ]});
			}
		});
}




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

