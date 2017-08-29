<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	  <jsp:attribute name="head_area">
	  	<!-- Select2 -->
		<link rel="stylesheet" href="plugins/select2/select2.min.css">
		<!-- DataTables -->
	    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
	  </jsp:attribute>
	
	  <jsp:attribute name="body_area">
		<section class="content-header">
			<h1>${labels.menu_monitor}</h1>
		</section>
		
		<section class="content">
	      <div class="box box-warning">
	        <div class="box-header with-border">
	          <h3 class="box-title">${labels.gestioneCalendario_selezionaCalendario}</h3>
	        </div>
	        <div class="box-body">
	          <div class="row">
	            <div class="col-md-12">
	              <div class="form-group">
	                <select id="select_elenco_calendari" class="form-control select2" style="width: 100%;" onchange="selezionaCalendarioMonitor(this)">
				  		<option></option>
				  	</select>
	              </div>
	             </div>
	            </div>
	          </div>
	        </div>

			<div id="divDettagliCalendarioMilestone" class="row hide">
				<div class="col-md-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">${labels.global_milestones}</h3>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table id="tableDettaglioCalendarioMilestone" class="table no-margin">
						        	<thead>
							          <tr role="row">
							           <th class="col-md-2">${labels.monitorCalendario_cod}</th>
							           <th class="col-md-2">${labels.monitorCalendario_milestone}</th>
							           <th class="col-md-1 tdCenter">${labels.monitorCalendario_data}</th>
							           <th class="col-md-1 tdCenter">${labels.monitorCalendario_ora}</th>
							           <th class="col-md-1 tdCenter">${labels.monitorCalendario_stato}</th>
							           <th class="col-md-1">${labels.monitorCalendario_tag}</th>
							           <th class="col-md-4">${labels.monitorCalendario_descrTag}</th>
							          </tr>
							        </thead>          
						        </table>
							</div>
						</div>
					</div>
				</div>
<!-- 				<div class="col-md-3"> -->
<!-- 					<div class="box box-danger"> -->
<!-- 						<div class="box-header with-border"> -->
<!-- 							<h3 class="box-title">Dettagli Evento</h3> -->
<!-- 						</div>							 -->
<!-- 						<div class="box-body detailMilestone"> -->
						
<!-- 							<p>Lorem ipsum dolor sit amet, consectetuer adipism.</p> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
			
		</section>
	  </jsp:attribute>
	
	  <jsp:attribute name="footer_area">
		<!-- Select2 -->
		<script src="plugins/select2/select2.full.min.js"></script>
		<script src="plugins/select2/i18n/it.js"></script>
		<!-- bootstrap Datatable -->
		<script src="plugins/datatables/jquery.dataTables.js"></script>
		<script src="plugins/datatables/dataTables.bootstrap.js"></script>
		
		<script>
			var wsPath = '${sessionBean.wsPath}';
			var userName = '${sessionBean.userName}';
			var ambiente = '${sessionBean.ambiente}';
		</script>

		<script src="dist/js/monitorFunctions.js"></script>
		<script>
			attivaWidgetSelect2();
		</script>
	  </jsp:attribute>
	  
</t:template>