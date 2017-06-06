<%@page contentType="text/html"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	<div class="content-wrapper">
		<section class="content-header">
			<h1>Monitor Calendario</h1>
		</section>
		<section class="content">


	      <div class="box box-primary">
	        <div class="box-header with-border">
	          <h3 class="box-title">Seleziona calendario</h3>
	        </div>
	        <div class="box-body">
	          <div class="row">
	            <div class="col-md-12">
	              <div class="form-group">
	                <select id="select_elenco_calendari" class="form-control select2" style="width: 100%;" onchange="selezionaCalendario(this)">
				  		<option></option>
				  	</select>
	              </div>
	             </div>
	            </div>
	          </div>
	        </div>

			<div class="row">
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
											<th class="col-md-2" style="text-align: center">Data cut-off</th>
											<th class="col-md-2" style="text-align: center">Ora cut-off</th>
											<th class="col-md-1" style="text-align: center">Stato</th>
											<th class="col-md-5">TAG</th>
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
						<div class="box-body">
							<p>Lorem ipsum dolor sit amet, consectetuer adipism.</p>
						</div>
					</div>
				</div>
			</div>
			
		</section>
	</div>

</t:template>