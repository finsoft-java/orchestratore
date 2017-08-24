<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	  <jsp:attribute name="head_area">
		<!-- DataTables -->
	    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
	  </jsp:attribute>
	
	  <jsp:attribute name="body_area">
	  	<section class="content-header">
			<h1>${labels.menu_gestioneTipiEvento}</h1>
		</section>
		
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">${labels.global_tipiEvento}</h3>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table id="tableTipiEventi" class="table no-margin">
						        	<thead>
							          <tr role="row">
							           <th class="col-md-1"></th>
							           <th class="hide">${labels.global.id}</th>
							           <th class="col-md-3">${labels.gestioneTipiEvento_codice}</th>
							           <th class="col-md-8">${labels.gestioneTipiEvento_descrizione}</th>
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
		
		<script src="dist/js/gestioneTipiEvento.js"></script>
	  </jsp:attribute>			

</t:template>