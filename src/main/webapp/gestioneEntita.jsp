
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

		<section class="content-header">
			<h1>Gestione Entità</h1>
		</section>
		
		<section class="content">
		
<!-- 	      <div class="box box-warning"> -->
<!-- 	        <div class="box-header with-border"> -->
<!-- 	          <h3 class="box-title">Seleziona calendario</h3> -->
<!-- 	        </div> -->
<!-- 	        <div class="box-body"> -->
<!-- 	          <div class="row"> -->
<!-- 	            <div class="col-md-12"> -->
<!-- 	              <div class="form-group"> -->
<!-- 	                <select id="select_elenco_calendari" class="form-control select2" style="width: 100%;" onchange="selezionaCalendario_gestEntita(this)"> -->
<!-- 				  		<option></option> -->
<!-- 				  	</select> -->
<!-- 	              </div> -->
<!-- 	             </div> -->
<!-- 	            </div> -->
<!-- 	          </div> -->
<!-- 	        </div> -->

			<div id="div_tabella_entita_editabile" class="row">
				<div class="col-md-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Entità</h3>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table id="tableGestioneEntita" class="table no-margin">
						        	<thead>
							          <tr role="row">
							           <th class="col-md-1"></th>
							           <th class="col-md-1">Id</th>
							           <th class="col-md-2">Codice</th>
							           <th class="col-md-4">Descrizione</th>
							           <th class="col-md-4">Acronimo</th>
							          </tr>
							        </thead>          
						        </table>
							</div>
							<br/>
							<a id="id_button_inserisci_calendario" onclick="saveEditedEntita()" type="button" class="btn btn-success"><i class="fa fa-save"></i> Salva</a>							
						</div>
					</div>
				</div>
			</div>
			
		</section>

		<script src="dist/js/gestioneEntita.js"></script>

</t:template>