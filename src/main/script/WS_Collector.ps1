param (
[Parameter(Mandatory=$True,Position=1)]
                [string]$ent,
                [Parameter(Mandatory=$True)]
                [string]$evn,
		[string]$tag
)
$evento = @{
    entita=$ent
    tipiEvento=$evn
    tag=$tag
}

$response = Invoke-RestMethod 'http://localhost:8080/orchestratoreRADAR/ws/collector' -Body $evento 


