#Questo script si aspetta 3 parametri obbligatori, ent, evn, tag
#Funziona solo su Windows 10!!!

param (
	[Parameter(Mandatory=$True)][string]$ent,
    [Parameter(Mandatory=$True)][string]$evn,
	[Parameter(Mandatory=$True)][string]$tag
)

$evento = @{
    entita=$ent
    tipiEvento=$evn
    tag=$tag
}

$endpoint = 'http://localhost:8080/orchestratoreRADAR/ws/collector'

$response = Invoke-RestMethod $endpoint -Body $evento 


