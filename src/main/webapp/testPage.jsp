<%@page contentType="text/html"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Test page</h1>

			URL per ripopolare il database (DELETE+INSERT): <a href="ws/reset" target="_blank">click
				here</a><br /> URL per visualizzare gli eventi (JSON): <a
				href="ws/resources/eventi" target="_blank">click here</a><br /> URL per visualizzare
			i tipi di evento (JSON): <a href="ws/resources/tipievento" target="_blank">click
				here</a><br /> URL per visualizzare le entità (JSON): <a
				href="ws/resources/entita" target="_blank">click here</a><br /> <br /> FORM per
			inserire un evento:
			<form action="ws/collector" method="get">
				Codice evento: <input name="tipiEvento" value="LOAD" /><br />
				Codice entit&agrave;: <input name="entita" value="U7RM0ESP" /><br />
				Tag: <input name="tag" value="20170523U" /><br /> <input
					type="submit" />
			</form>
			<br />
			<br /> FORM per recuperare lo stato di una milestone:
			<form action="ws/polling" method="get">
				Codice milestone: <input name="milestone" value="milestone 5" /><br />
				Tag1: <input name="tag" value="20170523U" /><br /> Tag2: <input
					name="tag" value="20170523U" /><br /> Tag3: <input name="tag"
					value="20170523U" /><br /> Tag4: <input name="tag"
					value="20170523U" /><br /> Tag5: <input name="tag"
					value="20170523U" /><br /> <input type="submit" />
			</form>
			
			<br />
			<br /> FORM per recuperare lo stato di una milestone (albero):
			<form action="ws/polling/testTree" method="get">
				Codice milestone: <input name="milestone" value="milestone 5" /><br />
				Tag1: <input name="tag" value="20170523U" /><br /> Tag2: <input
					name="tag" value="20170523U" /><br /> Tag3: <input name="tag"
					value="20170523U" /><br /> Tag4: <input name="tag"
					value="20170523U" /><br /> Tag5: <input name="tag"
					value="20170523U" /><br /> <input type="submit" />
			</form>
			
			<br />
			<br /> FORM per recuperare lo stato di una milestone (foglie):
			<form action="ws/polling/testLeaf" method="get">
				Codice milestone: <input name="milestone" value="milestone 5" /><br />
				Tag1: <input name="tag" value="20170523U" /><br /> Tag2: <input
					name="tag" value="20170523U" /><br /> Tag3: <input name="tag"
					value="20170523U" /><br /> Tag4: <input name="tag"
					value="20170523U" /><br /> Tag5: <input name="tag"
					value="20170523U" /><br /> <input type="submit" />
			</form>
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
</t:template>