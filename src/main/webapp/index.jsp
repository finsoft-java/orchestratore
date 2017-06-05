<%@page contentType="text/html"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Monitor Calendario
				<!--<small>Version 2.0</small>-->
			</h1>
		</section>
		<!-- Main content -->
		<section class="content">



	      <div class="box box-primary">
	        <div class="box-header with-border">
	          <h3 class="box-title">Seleziona calendario</h3>
	        </div>
	        <div class="box-body">
	          <div class="row">
	            <div class="col-md-12">
	              <div class="form-group">
	                <select id="select_elenco_calendari" class="form-control select2" style="width: 50%;">
				  		<option></option>
				  	</select>
	              </div>
	             </div>
	            </div>
	          </div>
	        </div>
      

			<div class="row">
				<!-- TABLE: LATEST ORDERS -->
				<div class="col-md-8">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Milestones</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr role="row">
											<th class="col-md-2">Milestones</th>
											<th class="col-md-2" style="text-align: center">Data cut-off</th>
											<th class="col-md-2" style="text-align: center">Ora cut-off</th>
											<th class="col-md-1" style="text-align: center">Stato</th>
											<th class="col-md-5">TAG</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Ricezione dati da Hermione</td>
											<td style="text-align: center">05/05/2017</td>
											<td style="text-align: center">13:00</td>
											<td style="text-align: center"><span
												class="btn btn-success btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>Lorem ipsum dolor sit amet adipiscing
												elit.</td>
										</tr>
										<tr>
											<td>Preprocesamento Hermione</td>
											<td style="text-align: center">06/05/2017</td>
											<td style="text-align: center">13:00</td>
											<td style="text-align: center"><span
												class="btn btn-success btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>Aenean commodo ligor. Aenean massa.</td>
										</tr>
										<tr>
											<td>Invio dati MACS1</td>
											<td style="text-align: center">06/05/2017</td>
											<td style="text-align: center">18:00</td>
											<td style="text-align: center"><span
												class="btn btn-success btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>Donec quam felem.</td>
										</tr>
										<tr>
											<td>Caricamento MACS1</td>
											<td style="text-align: center">07/05/2017</td>
											<td style="text-align: center">09:00</td>
											<td style="text-align: center"><span
												class="btn btn-success btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>Nulla consequat massau.</td>
										</tr>
										<tr>
											<td>Certificazione MACS1</td>
											<td style="text-align: center">10/05/2017</td>
											<td style="text-align: center">18:00</td>
											<td style="text-align: center"><span
												class="btn btn-danger btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>Cum sociis natoque penr ridiculus mus.</td>
										</tr>
										<tr>
											<td>Invio rettifiche</td>
											<td style="text-align: center">10/05/2017</td>
											<td style="text-align: center">19:00</td>
											<td style="text-align: center"><span
												class="btn btn-default btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>regergre</td>
										</tr>
										<tr>
											<td>Preprocessamento rettifiche</td>
											<td style="text-align: center">11/05/2017</td>
											<td style="text-align: center">13:00</td>
											<td style="text-align: center"><span
												class="btn btn-default btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>ergreg</td>
										</tr>
										<tr>
											<td>Invio dati U7B20</td>
											<td style="text-align: center">12/05/2017</td>
											<td style="text-align: center">18:00</td>
											<td style="text-align: center"><span
												class="btn btn-default btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>greegreg</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.box-body -->
						<%--                         <div class="box-footer clearfix">
                                <a href="javascript:void(0)" class="btn btn-sm btn-info btn-flat pull-left">Place New Order</a>
                                <a href="javascript:void(0)" class="btn btn-sm btn-default btn-flat pull-right">View All Orders</a>
                            </div>--%>
						<!-- /.box-footer -->
					</div>
				</div>
				<div class="col-md-4">
					<div class="box box-danger">
						<div class="box-header">
							<h3 class="box-title">Dettagli Evento</h3>
						</div>
						<!-- /.box-header -->

						<div class="box box-solid">
							<div class="box-header with-border">
								<h3 class="box-title">Evt033_MOLP_20170519</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<p>Lorem ipsum dolor sit amet, consectetuer adipism.</p>
							</div>
							<!-- /.box-body -->
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->

</t:template>