<%@page contentType="text/html"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Test page</h1><br />

			URL per ripopolare il database (DELETE+INSERT): <a href="ws/reset" target="_blank">click
				here</a><br /> URL per visualizzare gli eventi (JSON): <a
				href="ws/resources/eventi" target="_blank">click here</a><br /> URL per visualizzare
			i tipi di evento (JSON): <a href="ws/resources/tipievento" target="_blank">click
				here</a><br /> URL per visualizzare le entità (JSON): <a
				href="ws/resources/entita" target="_blank">click here</a><br /> <br /> FORM per
			inserire un evento:
			<form action="ws/collector" target="_blank" method="get">
				Codice evento: <input name="tipiEvento" value="LOAD" /><br />
				Codice entit&agrave;: <input name="entita" value="U7SC0_BO" /><br />
				Tag: <input name="tag" value="20170523U" /><br /> <input
					type="submit" />
			</form>
			<br />
			<br /> FORM per recuperare lo stato di una milestone (Restituisce TRUE o FALSE):
			<form action="ws/polling" target="_blank" method="get">
				Codice milestone: <input name="milestone" value="milestone 5" /><br />
				Tag1: <input name="tag" value="20170523U" /><br /> Tag2: <input
					name="tag" value="20170523U" /><br /> Tag3: <input name="tag"
					value="20170523U" /><br /> Tag4: <input name="tag"
					value="20170523U" /><br /> Tag5: <input name="tag"
					value="20170523U" /><br /> <input type="submit" />
			</form>
			
			<br />
			<br /> FORM per recuperare lo stato di una milestone (Albero):
			<form action="ws/polling/testTree" target="_blank" method="get">
				Codice milestone: <input name="milestone" value="milestone 5" /><br />
				Tag1: <input name="tag" value="20170523U" /><br /> Tag2: <input
					name="tag" value="20170523U" /><br /> Tag3: <input name="tag"
					value="20170523U" /><br /> Tag4: <input name="tag"
					value="20170523U" /><br /> Tag5: <input name="tag"
					value="20170523U" /><br /> <input type="submit" />
			</form>
			
			<br />
			<br /> FORM per recuperare lo stato di una milestone (Foglie):
			<form action="ws/polling/testLeaf" target="_blank" method="get">
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