$(document).ready(function(){
	caricaEventi();
})


function caricaEventi() {
	
	$("#tableEventi").DataTable({
//
//		paging : true,
//		lengthChange : false,
//		searching : false,
//		ordering : false,
//		info : false,
//		autoWidth : false,
//		autoWidth : false,
//		destroy : true,
//		
		//data : dataSet,
		ajax: {
			url: 'ws/resources/Eventi',
			dataSrc: ''
		},
		columns : [
			{data : 'tStampEvento', className : 'col-md-2', defaultContent : '' },
			{data : 'tipoEvento.codice', className : 'col-md-2', defaultContent : '' },
			{data : 'entita.codice', className : 'col-md-2', defaultContent : ''}, 
			{data : 'entita.acronimo', className : 'col-md-2', defaultContent : ''},
			{data : 'tag', className : 'col-md-3', defaultContent : ''}
			//La 'descrizione tag' non esiste se non esiste la milestone
			]
	});
	

}