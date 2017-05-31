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
											<th style="text-align: center">#</th>
											<th style="text-align: center">Milestones</th>
											<th style="text-align: center">Data cut-off</th>
											<th style="text-align: center">Ora cut-off</th>
											<th style="text-align: center">Dettagli</th>
											<th style="text-align: center">Opz.</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td style="text-align: center">1</td>
											
											<td>
								              <div class="form-group">
								                <select class="form-control select2" style="width: 100%;">
								                  <option></option>
								                  <option>Milestone 4</option>
								                  <option>Milestone 4 3</option>
								                  <option>Milestone 4 2</option>
								                  <option>Milestone 4 1</option>
								                </select>
								              </div>
											</td>
											
											<td>
									        	<div class="form-group">
									                <div class="input-group date">
									                  <div class="input-group-addon">
									                    <i class="fa fa-calendar"></i>
									                  </div>
									                  <input onkeydown="return false" type="text" placeholder="Data" class="form-control pull-right" id="datepicker" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask>
									                </div>
									            </div>
								            </td>
								            
											<td>
												<div class="bootstrap-timepicker">
									                <div class="form-group">
									                  <div class="input-group">
									                  	<div class="input-group-addon">
									                    	<i class="fa fa-clock-o"></i>
									                    </div>
									                    <input onkeydown="return false" placeholder="Ora" type="text" class="form-control timepicker">
									                  </div>
									                </div>
									            </div>
											</td>
											
											<td>
												<input type="text" class="form-control" id="tagNuovoCal0" placeholder="TAG">
											</td>
											
											<td style="text-align: center">
											    <a style="cursor: pointer;"><i style="color:green" class="fa fa-plus-circle"></i></a>
											</td>
											
										</tr>
									</tbody>
								</table>
							</div>
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