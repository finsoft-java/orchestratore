<%@page contentType="text/html"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	
		<section class="content-header">
			<h1>Gestione Calendario</h1>
		</section>

		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="box box-warning">
						<div class="box-header with-border">
							<h3 class="box-title">Info</h3>
						</div>
			              <div class="box-body">
			              	<div class="row">
								<div class="col-md-10">
           	                  		<label for="denomNuovoCal">Denominazione nuovo calendario:</label><br/>
					                <div class="form-group">
									  <select id="select_elenco_calendari" onchange="selezionaCalendario_gestCal(this)" class="form-control select2">
									  	<option></option>
									  </select>
									</div>
					            </div>
								<div class="col-md-2">
				                	<div class="form-group">
								  		<a id="id_button_elimina_calendario" onclick="deleteCalendar()" type="button" class="btn btn-danger" style="margin-top: 25px;"><i class="fa fa-trash-o"></i> Elimina</a>
								  	</div>
								</div>
							</div>					
			            </div>
	            	</div>
	            </div>
	        </div>

			<div id="div_tabella_milestone_editabile" class="row hide">
				<div class="col-md-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Milestones</h3>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table id="tableCalendarioEditabile" class="table no-margin" style="margin-bottom: 120px !important">
									<thead>
										<tr role="row">
											<th style="text-align: center">Opz.</th>
											<th style="text-align: center">Milestones</th>
											<th style="text-align: center">Data cut-off</th>
											<th style="text-align: center">Ora cut-off</th>
											<th style="text-align: center">TAGs</th>
											<th style="text-align: center">TAGs attese</th>
										</tr>
									</thead>
								</table>
							</div>
							<br/>
							<a id="id_button_inserisci_calendario" type="button" class="btn btn-success"><i class="fa fa-save"></i> &nbsp;Salva</a>							
						</div>
					</div>
				</div>
			</div>
			
		</section>

</t:template>