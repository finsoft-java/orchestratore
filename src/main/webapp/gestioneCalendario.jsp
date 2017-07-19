<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	  <jsp:attribute name="head_area">
	  	<!-- bootstrap datepicker -->
		<link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
		<!-- Bootstrap time Picker -->
		<link rel="stylesheet" href="plugins/timepicker/bootstrap-timepicker.min.css">		
		<!-- Select2 -->
		<link rel="stylesheet" href="plugins/select2/select2.min.css">
	    <!-- DataTables -->
	    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
	  </jsp:attribute>
	
	  <jsp:attribute name="body_area">
	  	<section class="content-header">
			<h1>{{labels.menu.gestioneCalendario}}</h1>
		</section>

		<section class="content">
			<div class="box box-warning">
				<div class="box-header with-border">
        			<h3 class="box-title">{{labels.gestioneCalendario.selezionaCalendario}}</h3>
				</div>
	              <div class="box-body">
	              	<div class="row">
						<div class="col-md-10">
			                <div class="form-group">
							  <select id="select_elenco_calendari" onchange="selezionaCalendario_gestCal(this)" class="form-control select2">
							  	<option></option>
							  </select>
							</div>
			            </div>
						<div class="col-md-2">
		                	<div class="form-group">
						  		<a id="id_button_elimina_calendario" onclick="deleteCalendar()" type="button" class="btn btn-danger"><i class="fa fa-trash-o"></i> Elimina</a>
						  	</div>
						</div>
					</div>					
	            </div>
           	</div>

			<div id="div_tabella_milestone_editabile" class="row hide">
				<div class="col-md-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">{{labels.gestioneCalendario.elencoMilestones}}</h3>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table id="tableCalendarioEditabile" class="table no-margin" style="margin-bottom: 120px !important">
									<thead>
										<tr role="row">
											<th class="col-md-1 tdCenter">{{labels.gestioneCalendario.opz}}</th>
											<th class='hide'>{{labels.global.id}}</th>
											<th class="col-md-2 tdCenter">{{labels.global.milestones}}</th>
											<th class="col-md-2 tdCenter">{{labels.gestioneCalendario.dataCutOff}}</th>
											<th class="col-md-2 tdCenter">{{labels.gestioneCalendario.oraCutOff}}</th>
											<th class="col-md-1 tdCenter">{{labels.global.tag}}</th>
											<th class="col-md-4 tdCenter">{{labels.global.descrizioneTag}}</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	  </jsp:attribute>
	
	  <jsp:attribute name="footer_area">
	  	<!-- bootstrap datepicker -->
		<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
		<script src="plugins/datepicker/locales/bootstrap-datepicker.it.js"></script>
		<!-- bootstrap time picker -->
		<script src="plugins/timepicker/bootstrap-timepicker.min.js"></script>
		<!-- Select2 -->
		<script src="plugins/select2/select2.full.min.js"></script>
		<script src="plugins/select2/i18n/it.js"></script>
		<!-- bootstrap Datatable -->
		<script src="plugins/datatables/jquery.dataTables.js"></script>
		<script src="plugins/datatables/dataTables.bootstrap.js"></script>
		
		<script src="dist/js/gestioneCalFunctions.js"></script>
		<script>
			attivaWidgetSelect2();
			attivaWidgetDatepicker();
			attivaWidgetTimepicker();
		</script>
	  </jsp:attribute>
		
</t:template>