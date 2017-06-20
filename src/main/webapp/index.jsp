<%@page contentType="text/html"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

		<section class="content-header">
			<h1>Monitor Calendario</h1>
		</section>
		
		<section class="content">
		
	      <div class="box box-warning">
	        <div class="box-header with-border">
	          <h3 class="box-title">Seleziona calendario</h3>
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
				<div class="col-md-8">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Milestones</h3>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table id="tableDettaglioCalendarioMilestone" class="table no-margin">
						        	<thead>
							          <tr role="row">
							           <th class="col-md-2">Milestones</th>
							           <th class="col-md-2 tdCenter">Data cut-off</th>
							           <th class="col-md-2 tdCenter">Ora cut-off</th>
							           <th class="col-md-1 tdCenter">Stato</th>
							           <th class="col-md-1">TAG</th>
							           <th class="col-md-4">Descrizione TAG</th>
							          </tr>
							        </thead>          
						        </table>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="box box-danger">
						<div class="box-header with-border">
							<h3 class="box-title">Dettagli Evento</h3>
						</div>							
						<div class="box-body detailMilestone">
						
							<p>Lorem ipsum dolor sit amet, consectetuer adipism.</p>
						</div>
					</div>
				</div>
			</div>
			
		</section>

		<script src="dist/js/monitorFunctions.js"></script>

</t:template>