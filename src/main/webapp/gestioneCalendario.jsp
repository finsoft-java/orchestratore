<%@page contentType="text/html"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Gestione Calendario
				<!--<small>Version 2.0</small>-->
			</h1>
		</section>
		<!-- Main content -->
		<section class="content">

		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal">
	              <div class="box-body">
	                <div class="form-group">
	                  <label for="denomNuovoCal" class="col-sm-2 control-label">Denominazione calendario</label>
	                  <div class="col-sm-10">
	                    <input type="text" class="form-control" id="denomNuovoCal" placeholder="Denominazione">
	                  </div>
	                </div>
	              </div>
	            </form>
            </div>
        </div>

			<div class="row">
				<!-- TABLE: LATEST ORDERS -->
				<div class="col-md-12">
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
											<th class="col-md-5">Dettagli</th>
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
											<td>Lorem ipsum dolor sit amet, consectetuer adipiscing
												elit.</td>
										</tr>
										<tr>
											<td>Preprocesamento Hermione</td>
											<td style="text-align: center">06/05/2017</td>
											<td style="text-align: center">13:00</td>
											<td style="text-align: center"><span
												class="btn btn-success btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>Aenean commodo ligula eget dolor. Aenean massa.</td>
										</tr>
										<tr>
											<td>Invio dati MACS1</td>
											<td style="text-align: center">06/05/2017</td>
											<td style="text-align: center">18:00</td>
											<td style="text-align: center"><span
												class="btn btn-success btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>Donec quam felis, ultricies nec, pellentesque eu,
												pretium quis, sem.</td>
										</tr>
										<tr>
											<td>Caricamento MACS1</td>
											<td style="text-align: center">07/05/2017</td>
											<td style="text-align: center">09:00</td>
											<td style="text-align: center"><span
												class="btn btn-success btn-circle btn-sm btn-semaforo"
												style="width: 15px; height: 15px; cursor: default !important;"></span></td>
											<td>Nulla consequat massa quis enim. Donec pede justo,
												fringilla vel, aliquet nec, vulputate eget, arcu.</td>
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
			</div>
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->

</t:template>