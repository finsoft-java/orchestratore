$(document).ready(function(){
	caricaEventi();
})

function renderTimestamp(data, type, row, meta) {
	if (data == null) return null;
	return data.substring(8,10) + '/'+data.substring(5,7)+'/'+data.substring(0,4)+ ' ' + data.substring(11,19);
}

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
			dataSrc: '',
			language: "it"
		},
		columns : [
			{data : 'tStampEvento', className : 'col-md-2', defaultContent : '', render: renderTimestamp },
			{data : 'tipoEvento.codice', className : 'col-md-2', defaultContent : '' },
			{data : 'entita.codice', className : 'col-md-2', defaultContent : ''}, 
			{data : 'entita.acronimo', className : 'col-md-2', defaultContent : ''},
			{data : 'tag', className : 'col-md-3', defaultContent : ''}
			//La 'descrizione tag' non esiste se non esiste la milestone
			]
	});
	

}