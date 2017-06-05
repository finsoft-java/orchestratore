<%@tag description="Orchestratore" language="java"%>
<!DOCTYPE html>

<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>OrchestratoreRADAR</title>
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		
		<!-- Latest compiled and minified CSS -->
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> -->
		
		<!-- Bootstrap 3.3.6 -->
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
		<!-- Ionicons -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
		<!-- jvectormap -->
		<link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
		
		<link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">
		<!-- bootstrap datepicker -->
		<link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
		<!-- iCheck for checkboxes and radio inputs -->
		<link rel="stylesheet" href="plugins/iCheck/all.css">
		<!-- Bootstrap Color Picker -->
		<link rel="stylesheet" href="plugins/colorpicker/bootstrap-colorpicker.min.css">
		<!-- Bootstrap time Picker -->
		<link rel="stylesheet" href="plugins/timepicker/bootstrap-timepicker.min.css">
		<!-- Select2 -->
		<link rel="stylesheet" href="plugins/select2/select2.min.css">

		<!-- Theme style -->
		<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
		<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
		<link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		    <![endif]
		-->

     	<!-- Google Font -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
		
		<link rel="stylesheet" href="dist/css/orchestratoreStyleSheet.css">
		
		
    	<link rel="shortcut icon" type="image/png" href="dist/img/favicon.ico"/>
		
	
	</head>
	<body class="hold-transition skin-blue sidebar-mini">
	
		<div class="wrapper">
		
		
			<header class="main-header">
	
			    <a href="index.jsp" class="logo">
			      <span class="logo-mini"><b>O</b>RD</span>
			      <span class="logo-lg"><b>Orchestratore</b>RADAR</span>
			    </a>
			
			    <nav class="navbar navbar-static-top">
			      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"><span class="sr-only"></span></a>
			    </nav>
			  </header>
		
	
	
			<!-- Left side column. contains the logo and sidebar -->
			<aside class="main-sidebar">
				<!-- sidebar: style can be found in sidebar.less -->
				<section class="sidebar">
	
					<!-- sidebar menu: : style can be found in sidebar.less -->
					<ul class="sidebar-menu treeview-menu">
						<li>
							<a href="index.jsp"> <i class="fa fa-calendar"></i><span>Monitor Calendario</span></a>
						</li>
	
         			 	<li>
          					<a href="gestioneCalendario.jsp"> <i class="fa fa-edit"></i><span>Gestione Calendario</span></a>
						</li>
	
						<li>
							<a href="#"><i class="fa fa-cogs"></i><span>Amministrazione</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
							<ul class="treeview-menu">
								<li><a href="#"><i class="fa fa-edit"></i> Gestione Entità</a></li>
								<li><a href="#"><i class="fa fa-edit"></i> Gestione Eventi</a></li>
								<li><a href="#"><i class="fa fa-edit"></i> Gestione Milestone</a></li>
							</ul>
						</li>
	
						<li>
							<a href="#"> <i class="fa fa-search"></i><span>Ricerca</span></a>
						</li>
					</ul>
	
				</section>
				<!-- /.sidebar -->
	
			</aside>
	
			<!-- Nel doBody ci andra il tag content-wrapper e il suo contenuto di ogni pagina -->
			<jsp:doBody />
	
	
			<footer class="main-footer">
				<div class="pull-right hidden-xs">
					<b>Versione</b> Beta 0.1
				</div>
				<strong>Copyright &copy; 2017 <a
					href="http://www.finsoft.it">Finsoft S.r.l. </a>.
				</strong> Tutti i diritti sono riservati.
			</footer>
	
		</div>
	
	
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
	       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	
	
	
	
		<!-- jQuery 3.1.1 -->
		<script src="plugins/jQuery/jquery-3.1.1.min.js"></script>
		<!-- Bootstrap 3.3.6 -->
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<!-- FastClick -->
		<script src="plugins/fastclick/fastclick.js"></script>
		<!-- AdminLTE App -->
		<script src="dist/js/app.min.js"></script>
		<script type="text/javascript" src="dist/js/adminlte.min.js"></script>		
		<!-- Sparkline -->
		<script src="plugins/sparkline/jquery.sparkline.min.js"></script>
		<!-- jvectormap -->
		<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
		<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
		<!-- SlimScroll 1.3.0 -->
		<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
		<!-- ChartJS 1.0.1 -->
		<script src="plugins/chartjs/Chart.min.js"></script>
		<!-- AdminLTE for demo purposes -->
		<script src="dist/js/demo.js"></script>
		<!-- Select2 -->
		<script src="plugins/select2/select2.full.min.js"></script>
		<!-- InputMask -->
		<script src="plugins/input-mask/jquery.inputmask.js"></script>
		<script src="plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
		<script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>
		<!-- date-range-picker -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
		<script src="plugins/daterangepicker/daterangepicker.js"></script>
		<!-- bootstrap datepicker -->
		<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
		<script src="plugins/datepicker/locales/bootstrap-datepicker.it.js"></script>
		<!-- bootstrap color picker -->
		<script src="plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
		<!-- bootstrap time picker -->
		<script src="plugins/timepicker/bootstrap-timepicker.min.js"></script>
		<!-- iCheck 1.0.1 -->
		<script src="plugins/iCheck/icheck.min.js"></script>
		
		<!-- Page script -->
		<script>
			$(function () {
			  //Initialize Select2 Elements
			  $(".select2").select2({
			  	placeholder: "Seleziona",
			      minimumResultsForSearch: 10,
			  });
			
			  //Datemask dd/mm/yyyy
			  $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
			  
			  //Money Euro
			  $("[data-mask]").inputmask();
			
			  //Date picker
			  $('.datepicker').datepicker({
			 	  language: 'it',
			 	  orientation: "auto",
			    format: 'dd/mm/yyyy',
			    todayHighlight: true,
			    autoclose: true,
			    todayBtn: "linked",
			    toggleActive: true
			  });
			
			  //iCheck for checkbox and radio inputs
			  $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
			    checkboxClass: 'icheckbox_minimal-blue',
			    radioClass: 'iradio_minimal-blue'
			  });
			  
			  //Timepicker
			  $(".timepicker").timepicker({
			    showInputs: false,
			    showMeridian: false,
			    defaultTime: false,
			    minuteStep: 1,
			    autoclose: true,
			    //appendWidgetTo: '.table-responsive'
			  });
			
			  
			  
			});
		</script>

		<!-- funzioni terze -->
		<script src="dist/js/functions.js"></script>
		
		
		
		<script>
			var url = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
			$('.treeview-menu li a[href="' + url + '"]').parent().addClass('active');
			$('.treeview-menu li a').filter(function() {
			    return this.href === url;
			}).parent().parent().parent().addClass('active');
		</script>
		
		
		
	</body>
</html>