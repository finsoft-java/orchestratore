<%@page contentType="text/html"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Test page</h1><br />

			URL per ripopolare il database (DELETE+INSERT): <a href="ws/Reset" target="_blank">click
				here</a><br /> URL per visualizzare gli eventi (JSON): <a
				href="ws/resources/Eventi" target="_blank">click here</a><br /> URL per visualizzare
			i tipi di evento (JSON): <a href="ws/resources/TipiEvento" target="_blank">click
				here</a><br /> URL per visualizzare le entità (JSON): <a
				href="ws/resources/Entita" target="_blank">click here</a><br /> <br /> FORM per
			inserire un evento:
			<form action="ws/Collector" target="_blank" method="get">
				Codice evento: <input name="tipiEvento" value="DISDATI" /><br />
				Codice entit&agrave;: <input name="entita" value="U7SC0_HER" /><br />
				Tag: <input name="tag" value="201705" /><br /> <input
					type="submit" />
			</form>
			<br />
			<br /> FORM per recuperare lo stato di una milestone (Restituisce TRUE o FALSE):
			<form action="ws/Polling" target="_blank" method="get">
				Codice milestone: <input name="milestone" value="ELABORAZIONE ACQUISIZIONE CRM FV" /><br />
				Tag1: <input name="tag" value="201705" /><br /> Tag2: <input
					name="tag" value="20170623ABC" /><br /> Tag3: <input name="tag"
					value="20170623ABC" /><br /> Tag4: <input name="tag"
					value="20170623ABC" /><br /> Tag5: <input name="tag"
					value="20170623ABC" /><br /> <input type="submit" />
			</form>
			
			<br />
			<br /> FORM per recuperare lo stato di una milestone (Albero):
			<form action="ws/Polling/testTree" target="_blank" method="get">
				Codice milestone: <input name="milestone" value="ELABORAZIONE ACQUISIZIONE CRM FV" /><br />
				Tag1: <input name="tag" value="20170623ABC" /><br /> Tag2: <input
					name="tag" value="20170623ABC" /><br /> Tag3: <input name="tag"
					value="20170623ABC" /><br /> Tag4: <input name="tag"
					value="20170623ABC" /><br /> Tag5: <input name="tag"
					value="20170623ABC" /><br /> <input type="submit" />
			</form>
			
			<br />
			<br /> FORM per recuperare lo stato di una milestone (Foglie):
			<form action="ws/Polling/testLeaf" target="_blank" method="get">
				Codice milestone: <input name="milestone" value="ELABORAZIONE ACQUISIZIONE CRM FV" /><br />
				Tag1: <input name="tag" value="20170623ABC" /><br /> Tag2: <input
					name="tag" value="20170623ABC" /><br /> Tag3: <input name="tag"
					value="20170623ABC" /><br /> Tag4: <input name="tag"
					value="20170623ABC" /><br /> Tag5: <input name="tag"
					value="20170623ABC" /><br /> <input type="submit" />
			</form>
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
</t:template>