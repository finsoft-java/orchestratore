
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

		<section class="content-header">
			<h1>Gestione Tipi Evento</h1>
		</section>
		
		<section class="content">
			<div id="div_tabella_tipiEvento_editabile" class="row">
				<div class="col-md-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Tipi Evento</h3>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table id="tableGestioneTipiEvento" class="table no-margin">
						        	<thead>
							          <tr role="row">
							          <th class="col-md-1">Opz.</th>
							           <th>Id</th>
							           <th class="col-md-3">Codice</th>
							           <th class="col-md-8">Descrizione</th>
							          </tr>
							        </thead>          
						        </table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<script src="dist/js/gestioneTipiEvento.js"></script>

</t:template>