
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	  <jsp:attribute name="head_area">
		<!-- DataTables -->
	    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
	    <!-- Select2 -->
		<link rel="stylesheet" href="plugins/select2/select2.min.css">
	  </jsp:attribute>
	
	  <jsp:attribute name="body_area">
		<section class="content-header">
			<h1>{{labels.menu.gestioneMilestones}}</h1>
		</section>
		
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">{{labels.global.milestones}}</h3>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table id="tableListaMilestones" class="table no-margin">
						        	<thead>
							          <tr role="row">
							           <th class="col-md-1"></th>
							           <th class="col-md-1">{{labels.gestioneMilestones.tipo}}</th>
							           <th class="hide">{{labels.gestioneMilestones.id}}</th>
							           <th class="col-md-1">{{labels.gestioneMilestones.codice}}</th>
							           <th class="col-md-3">{{labels.gestioneMilestones.descrizione}}</th>
							           <th class="col-md-2">{{labels.gestioneMilestones.descrizioneTag}}</th>
							           <th class="col-md-1">{{labels.gestioneMilestones.tipoEvento}}</th>
							           <th class="col-md-1">{{labels.gestioneMilestones.entita}}</th>
							           <th class="col-md-2">{{labels.gestioneMilestones.componenti}}</th>
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
	    <!-- Select2 -->
		<script src="plugins/select2/select2.full.min.js"></script>
		<script src="plugins/select2/i18n/it.js"></script>
		<!-- bootstrap Datatable -->
		<script src="plugins/datatables/jquery.dataTables.js"></script>
		<script src="plugins/datatables/dataTables.bootstrap.js"></script>
		
		<script src="dist/js/gestioneMilestone.js"></script>
		<script>
			attivaWidgetSelect2();
		</script>
	  </jsp:attribute>			

</t:template>