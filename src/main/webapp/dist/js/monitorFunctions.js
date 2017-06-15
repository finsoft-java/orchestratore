
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
	    		dataSet[i].data = convertTimestampToData(dataSet[i].dataOraPreviste);
		        dataSet[i].ora = convertTimestampToTime(dataSet[i].dataOraPreviste);
	    	}
	    	
	    	var dataTable = $("#tableDettaglioCalendarioMilestone").DataTable({
	             paging : false,
	             lengthChange : false,
	             searching : false,
	             ordering : false,
	             info : false,
	             data: dataSet,
	             autoWidth : false,
	             destroy : true,
	             columns : [
	               { data : 'milestone.descrizione' },
	               { data : 'data', className: 'tdCenter' },
	               { data : 'ora', className: 'tdCenter' },
	               { data : 'semaforo', className: 'tdCenter', defaultContent:'' },
	               { data : 'tag' },
	               { data : null, defaultContent : '' } 
	              ]
	           });
	    	 
		     for (j in dataSet) {
		    	 polling(dataTable, j, dataSet[j]);
		     }
		     
		     $("#divDettagliCalendarioMilestone").removeClass("hide");
	     
	     }
	   });
}
function pad(numb) {
    return (numb < 10 ? '0' : '') + numb;
}
function polling(datatable, numRiga, data) {
    
	var milestone = data.milestone.descrizione;
    var tags = data.tag;    
    var endpoint = "ws/Polling?milestone=" + milestone + "&tag=" + tags.split(",").join("&tag=");

    var _ora = data.ora;
    
    var _now = new Date($.now());
    var nYear  = pad(_now.getFullYear());
    var nMonth = pad(_now.getMonth() + 1);
    var nDay   = pad(_now.getDate());
    var nHours = pad(_now.getHours()); //returns 0-23
    var nMinutes = pad(_now.getMinutes()); //returns 0-59
    var nSeconds = pad(_now.getSeconds()); //returns 0-59    
      
    var _data = new Date(data.dataOraPreviste);
    var dYear  = pad(_data.getFullYear());
    var dMonth = pad(_data.getMonth() + 1);
    var dDay   = pad(_data.getDate());
    var dHours = pad(_data.getHours()); //returns 0-23
    var dMinutes = pad(_data.getMinutes()); //returns 0-59
    var dSeconds = pad(_data.getSeconds()); //returns 0-59    
   

    _now = parseInt(nYear+nMonth+nDay+nHours+nMinutes+nSeconds);
    _data = parseInt(dYear+dMonth+dDay+dHours+dMinutes+dSeconds);
    

    var diff = _now - _data;    
    
     $.ajax({
       type : "GET",
       url : endpoint,
       dataType : "json",
       success : function(dataSet) {
    	   
           if (dataSet != null) {
               if (dataSet == 0 && diff < 0) {
            	   dataSet = '<span class="btn btn-primary btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; font-size:0%; cursor: default;">3 blu</span>';
               } else if (dataSet == 0) {
            	   dataSet = '<span class="btn btn-danger btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; font-size:0%; cursor: default;">0 rosso</span>';
               } else if (dataSet == 1) {
            	   dataSet = '<span class="btn btn-success btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; font-size:0%; cursor: default;">1 verde</span>';
               } else {
            	   dataSet = '';
               }
           }
    	   datatable.cell(numRiga, 3).data(dataSet).draw();
       }
     })
}
     
     
     
     