
/**
 * Funzione document.ready di jQuery
 */
$(document).ready(function(){
	getListaCalendari();
})

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
function selezionaCalendarioMonitor(selectIndex){
	var idx = selectIndex.selectedIndex;
	var idCalendario = selectIndex.options[idx].value;
	getDettaglioCalendarioMilestone(idCalendario);
}


/**
 * Funzione che effettua una chiamata AJAX all'apposito ws, il quale restituisce una lista
 * contenente tutti i calendari e i relativi id
 * 
 * @returns
 */
function getListaCalendariMonitor(){
  $.getJSON("ws/resources/Calendari", function(dataSet){
   for(i in dataSet){
    var opt = "<option value='"+dataSet[i].idCalendario+"'>"+dataSet[i].descrizione+"</option>";
    $("#select_elenco_calendari").append(opt);
      }
  });
}


/**
 * Funzione che effettua una chiamata ajax al relativo ws per ottenere tutti i dati di un determinato calendario (passato come parametro)
 * sotto forma di chiave. I dati restituiti popolano una relativa tabella in 'index.jsp'
 * @param idCalendario
 * @returns
 */
function getDettaglioCalendarioMilestone(idCalendario) {
	 $.ajax({
	    type : "GET",
	    url : "ws/resources/Calendari(" + idCalendario + ")/Milestone",
	    dataType : "json",
	    success : function(dataSet) {

	    	for (var i in dataSet){
	    		dataSet[i].data = convertData(dataSet[i].dataOraPreviste);
		        dataSet[i].ora = convertTime(dataSet[i].dataOraPreviste);
	    	}
	    	
	    	var dataTable = $("#tableDettaglioCalendarioMilestone").DataTable({
	             paging : false,
	             lengthChange : false,
	             searching : false,
	             ordering : false,
	             info : false,
	             autoWidth : false,
	             destroy : true,
	             columns : [
	               { data : 'milestone.descrizione' },
	               { data : 'data', className: 'tdCenter' },
	               { data : 'ora', className: 'tdCenter' },
	               { data : 'semaforo', className: 'tdCenter', defaultContent:'' },
	               { data : 'tags' },
	               { data : null, defaultContent : 'TODO' } 
	              ]
	           });
	    	 
	    	 
	     for (j in dataSet) {
	    	 polling(dataTable, j, dataSet[j]);
	     }
	     
	     }
	   });
}

function polling(datatable, numRiga, data) {
    var milestone = data.milestone.descrizione;
    var tags = data.tags;
    var endpoint = "ws/Polling?milestone=" + milestone
      + "&tag=" + tags.split(",").join("&tag=");
     $.ajax({
       type : "GET",
       url : endpoint,
       dataType : "json",
       success : function(dataSet) {
        data.semaforo = dataSet;
       }
     })
}