


/**
 * Funzione che splitta e converte la data passata come parametro, dal formato timeStamp a quello dd/MM/yyyy.
 * In più viene anche eliminata l'ora dal parametro così da restituire solamente la data
 * @param data
 * @returns
 */
function convertTimestampToData(data){
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
function convertTimestampToTime(ora){
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

/**
 * Funzione che aggiunge/rimuove la classe 'active' dai tab laterali della nav bar del template, in modo che solo
 * il tab contente la pagina vista attualmente dall'utente abbia la classe 'active'
 * @returns
 */
function activeDeactiveNavBarTab(){
	var url = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
	$('.treeview-menu li a[href="' + url + '"]').parent().addClass('active');
	$('.treeview-menu li a').filter(function() {
	    return this.href === url;
	}).parent().parent().parent().addClass('active');
}


