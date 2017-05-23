<html>
<head>
<title>Test page</title>
</head>
<body>
<h1>Test page</h1>

URL per ripopolare il database (DELETE+INSERT):
<a href="ws/reset">click here</a><br/>

URL per visualizzare gli eventi (JSON):
<a href="ws/resources/eventi">click here</a><br/>

URL per visualizzare i tipi di evento (JSON):
<a href="ws/resources/tipievento">click here</a><br/>

URL per visualizzare le entità (JSON):
<a href="ws/resources/entita">click here</a><br/>

FORM per inserire un evento:
<form action="ws/collector" method="get">
<input name="tipiEvento" value="CARICAMENTO"/>
<input name="entita" value="U7SC0_PCR_M"/>
<input type="submit"/>
</form>
</body>
</html>