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
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                    <li class="active">Monitor Calendario</li>
                </ol>
            </section>
            <!-- Main content -->
            <section class="content">
                <!-- Info boxes -->
                <div class="row">
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-aqua"><i class="icon fa fa-info"></i></span>
                            <div class="info-box-content">
                                <span class="info-box-text">Eventi in programmazione</span>
                                <span class="info-box-number">37</span>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <!-- /.col -->
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-red"><i class="icon fa fa-warning"></i></span>
                            <div class="info-box-content">
                                <span class="info-box-text">Eventi bloccati</span>
                                <span class="info-box-number">3<small>/37</small></span>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <!-- /.col -->
                    <!-- fix for small devices only -->
                    <div class="clearfix visible-sm-block"></div>
                    <!-- /.col -->
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-yellow"><i class="icon fa fa-warning"></i></span>
                            <div class="info-box-content">
                                <span class="info-box-text">Eventi in pending</span>
                                <span class="info-box-number">6<small>/37</small></span>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <!-- /.col -->
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-green"><i class="icon fa fa-check"></i></span>
                            <div class="info-box-content">
                                <span class="info-box-text">Eventi Terminati</span>
                                <span class="info-box-number">27<small>/37</small></span>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                </div>
                <!-- /.row -->
                <div class="row">
                    <!-- TABLE: LATEST ORDERS -->
                    <div class="col-md-9">
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title">Milestones</h3>
                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse">
                                        <i class="fa fa-minus"></i>
                                    </button>
                                    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">
                                <div class="table-responsive">
                                    <table class="table no-margin">
                                        <thead>
                                            <tr role="row">
                                                <th class="col-md-2">Milestones</th>
                                                <th class="col-md-1" style="text-align:center">Data cut-off</th>
                                                <th class="col-md-1" style="text-align:center">Ora cut-off</th>
                                                <th class="col-md-1" style="text-align:center">Stato</th>
                                                <th class="col-md-7">Dettagli</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Ricezione dati da Hermione</td>
                                                <td style="text-align:center">05/05/2017</td>
                                                <td style="text-align:center">13:00</td>
                                                <td style="text-align:center"><span class="btn btn-success btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; cursor:default !important;"></span></td>
                                                <td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. </td>
                                            </tr>
                                            <tr>
                                                <td>Preprocesamento Hermione</td>
                                                <td style="text-align:center">06/05/2017</td>
                                                <td style="text-align:center">13:00</td>
                                                <td style="text-align:center"><span class="btn btn-success btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; cursor:default !important;"></span></td>
                                                <td>Aenean commodo ligula eget dolor. Aenean massa. </td>
                                            </tr>
                                            <tr>
                                                <td>Invio dati MACS1</td>
                                                <td style="text-align:center">06/05/2017</td>
                                                <td style="text-align:center">18:00</td>
                                                <td style="text-align:center"><span class="btn btn-success btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; cursor:default !important;"></span></td>
                                                <td>Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. </td>
                                            </tr>
                                            <tr>
                                                <td>Caricamento MACS1</td>
                                                <td style="text-align:center">07/05/2017</td>
                                                <td style="text-align:center">09:00</td>
                                                <td style="text-align:center"><span class="btn btn-success btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; cursor:default !important;"></span></td>
                                                <td>Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.</td>
                                            </tr>
                                            <tr>
                                                <td>Certificazione MACS1</td>
                                                <td style="text-align:center">10/05/2017</td>
                                                <td style="text-align:center">18:00</td>
                                                <td style="text-align:center"><span class="btn btn-danger btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; cursor:default !important;"></span></td>
                                                <td>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</td>
                                            </tr>
                                            <tr>
                                                <td>Invio rettifiche</td>
                                                <td style="text-align:center">10/05/2017</td>
                                                <td style="text-align:center">19:00</td>
                                                <td style="text-align:center"><span class="btn btn-default btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; cursor:default !important;"></span></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td>Preprocessamento rettifiche</td>
                                                <td style="text-align:center">11/05/2017</td>
                                                <td style="text-align:center">13:00</td>
                                                <td style="text-align:center"><span class="btn btn-default btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; cursor:default !important;"></span></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td>Invio dati U7B20</td>
                                                <td style="text-align:center">12/05/2017</td>
                                                <td style="text-align:center">18:00</td>
                                                <td style="text-align:center"><span class="btn btn-default btn-circle btn-sm btn-semaforo" style="width:15px; height: 15px; cursor:default !important;"></span></td>
                                                <td></td>
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
                    <div class="col-md-3">
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
                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. 
                                        Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, 
                                        imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. 
                                        Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim.</p>

                                </div>
                                <!-- /.box-body -->
                            </div>
                        </div>
                    </div>
            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

</t:template>