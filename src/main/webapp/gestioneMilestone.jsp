
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	  <jsp:attribute name="head_area">
		<!-- DataTables -->
	    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
	  </jsp:attribute>
	
	  <jsp:attribute name="body_area">
		<section class="content-header">
			<h1>{{labels.menu.gestioneMilestones}}</h1>
		</section>
		
		<section class="content">
			<div id="div_tabella_gestMS_editabile" class="row">
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
							           <th class="col-md-1">{{labels.gestioneMilestones.opz}}</th>
							           <th class="col-md-1">{{labels.gestioneMilestones.tipo}}</th>
							           <th class="hide">{{labels.global.id}}</th>
							           <th class="col-md-1">{{labels.global.codice}}</th>
							           <th class="col-md-3">{{labels.global.descrizione}}</th>
							           <th class="col-md-2">{{labels.global.descrizioneTag}}</th>
							           <th class="col-md-1">{{labels.global.tipoEvento}}</th>
							           <th class="col-md-1">{{labels.global.entita}}</th>
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
		<!-- bootstrap Datatable -->
		<script src="plugins/datatables/jquery.dataTables.js"></script>
		<script src="plugins/datatables/dataTables.bootstrap.js"></script>
		
		<script src="dist/js/gestioneMilestone.js"></script>
	  </jsp:attribute>			
		

</t:template>