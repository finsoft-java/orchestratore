
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

		<section class="content-header">
			<h1>{{labels.menu.gestioneEntita}}</h1>
		</section>
		
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">{{labels.global.entita}}</h3>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table id="tableGestioneEntita" class="table no-margin">
						        	<thead>
							          <tr role="row">
							           <th class="col-md-1">{{labels.gestioneEntita.opz}}</th>
							           <th class="hide">Id</th>
							           <th class="col-md-3">{{labels.gestioneEntita.codice}}</th>
							           <th class="col-md-4">{{labels.gestioneEntita.descrizione}}</th>
							           <th class="col-md-4">{{labels.gestioneEntita.acronimo}}</th>
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