#!/bin/bash
#cambiare l'endpoint, ho usato l'indirizzo poiche' passavo da virtual machine
#per indicare la posizione dello script si puo' aggiungere
#source "home/percorsoScript/nomeScript.sh"
ENDPOINT=http://192.168.1.194:8080/orchestratoreRADAR/ws/collector
wget "$ENDPOINT?entita=$1&tipoEvento=$2&tag=$3&numeroErrori=333"
#
#come per i .bat di base si possono passare solo 9 parametri
#
