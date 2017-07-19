<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	  <jsp:attribute name="head_area">
	  	<!-- Select2 -->
		<link rel="stylesheet" href="plugins/select2/select2.min.css">
		<!-- DataTables -->
	    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
	  </jsp:attribute>
	
	  <jsp:attribute name="body_area">
		<section class="content-header">
			<h1>{{labels.menu.eventi}}</h1>
		</section>
		
		<section class="content">
			<div class="row">
				<div id="divDettagliCalendarioMilestone" >
					<div class="col-md-12">
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title">{{labels.global.eventi}}</h3>
							</div>
							<div class="box-body">
								<div class="table-responsive">
									<table id="tableEventi" class="table no-margin">
							        	<thead>
								          <tr role="row">
								           <th class="col-md-2">{{labels.eventi.dataOra}}</th>
								           <th class="col-md-2">{{labels.eventi.codTipoEv}}</th>
								           <th class="col-md-2">{{labels.eventi.codEnt}}</th>
								           <th class="col-md-2">{{labels.eventi.acronimo}}</th>
								           <th class="col-md-3">{{labels.eventi.tag}}</th>
								          </tr>
								        </thead>          
							        </table>
								</div>
							</div>
						</div>
					</div>
				</div>
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
		
		<script src="dist/js/eventi.js"></script>
		<script>
			attivaWidgetSelect2();
		</script>
	  </jsp:attribute>

</t:template>