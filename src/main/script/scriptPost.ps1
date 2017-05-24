$entita = @{
    codice='VALIDAZIONE'
    acronimo='U7SC0_BO'
    descrizione='validazione U7SC0'
}
$json = $entita | ConvertTo-Json
$response = Invoke-RestMethod 'http://localhost:8080/orchestratoreRADAR/ws/collector' -Method POST -Body $json -ContentType 'application/json'