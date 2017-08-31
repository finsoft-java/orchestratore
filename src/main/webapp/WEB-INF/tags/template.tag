<%@tag description="Orchestratore" language="java"%>

<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" required="true" %>
<%@attribute name="footer_area" fragment="true" %>

<!DOCTYPE html>

<html data-ng-app="app" data-ng-controller="labelsController">	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>OrchestratoreRADAR</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		
		<!-- Bootstrap 3.3.6 -->
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<!-- Font Awesome -->		
		<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
		<!-- nell'head ci andranno i fogli di stile necessari per ogni pagina -->
		<jsp:invoke fragment="head_area"/>
		<!-- Theme style -->
		<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
		<link rel="stylesheet" href="dist/css/skin-green.css">

     	<!-- Google Font -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
		<!-- foglio di stile generico condiviso -->
		<link rel="stylesheet" href="dist/css/orchestratoreStyleSheet.css">
		<!-- Favicon -->
    	<link rel="shortcut icon" type="image/png" href="dist/img/favicon.ico"/>
	</head>
	<body class="hold-transition skin-green sidebar-mini">
	
		<script>
			var wsPath = '${sessionBean.wsPath}';
			var userName = '${sessionBean.userName}';
			var ambiente = '${sessionBean.ambiente}';
		</script>

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
		
			<!-- Left side column -->
			<aside class="main-sidebar">
				<section class="sidebar">
					<ul class="sidebar-menu treeview-menu">
						<li>
							<a href="index.jsp"> <i class="fa fa-calendar"></i><span>${labels.menu_monitor}</span></a>
						</li>
         			 	<li>
          					<a href="gestioneCalendario.jsp"> <i class="fa fa-edit"></i><span>${labels.menu_gestioneCalendario}</span></a>
						</li>
						<li>
          					<a href="eventi.jsp"> <i class="fa fa-list-alt"></i><span>${labels.menu_eventi}</span></a>
						</li>
						<li>
							<a href="#"><i class="fa fa-cogs"></i><span>${labels.menu_amministrazione}</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
							<ul class="treeview-menu">
								<li><a href="gestioneEntita.jsp"><i class="fa fa-edit"></i> ${labels.menu_gestioneEntita}</a></li>
								<li><a href="gestioneTipiEvento.jsp"><i class="fa fa-edit"></i> ${labels.menu_gestioneTipiEvento}</a></li>
								<li><a href="gestioneMilestone.jsp"><i class="fa fa-edit"></i> ${labels.menu_gestioneMilestones}</a></li>
							</ul>
						</li>
					</ul>
				</section>
			</aside>
	
			<!-- Nel doBody ci andrà il contenuto di ogni pagina -->
			<div class="content-wrapper">
				<jsp:invoke fragment="body_area"/>
			</div>
	
			<footer class="main-footer">
				<div class="pull-right hidden-xs">
					<b>${labels.global_versione}</b> Beta 0.1
				</div>
				<strong>Copyright &copy; 2017 <a href="http://www.finsoft.it">Finsoft S.r.l.</a></strong> ${labels.global_dirittiRiservati}
			</footer>
		</div>		
	</body>
	 <footer>
	 	<!-- jQuery 3.1.1 -->
		<script src="plugins/jQuery/jquery-3.1.1.min.js"></script>
		<!-- Bootstrap 3.3.6 -->
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<!-- AdminLTE App -->
		<script src="dist/js/app.min.js"></script>
		<script type="text/javascript" src="dist/js/adminlte.min.js"></script>		
		<!-- sidebar remember state -->
		<script src="dist/js/sidebarState.js"></script>
		<!-- bootbox widget for all notification -->
		<script src="plugins/bootbox/js/bootbox.min.js"></script>	
		<!-- funzioni terze -->
		<script src="dist/js/sharedFunctions.js"></script>
		<!-- nel footer ci andranno le funzioni js necessarie per ogni pagina -->
	    <jsp:invoke fragment="footer_area"/>
	    <!-- script for all page -->
		<script>
			activeDeactiveNavBarTab();
		</script>
	 </footer>
</html>