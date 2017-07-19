<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	
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
							<br/>
							<a id="id_button_inserisci_calendario" onclick="saveEditedCalendar()" type="button" class="btn btn-success"><i class="fa fa-save"></i> Salva</a>							
						</div>
					</div>
				</div>
			</div>
			
		</section>
		
		<script src="dist/js/gestioneCalFunctions.js"></script>
		
</t:template>