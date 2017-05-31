#!/bin/bash
#cambiare l'endpoint, ho usato l'indirizzo poiche' passavo da virtual machine
#per indicare la posizione dello script si puo' aggiungere
#source "home/percorsoScript/nomeScript.sh"
ENDPOINT=http://192.168.1.194:8080/orchestratoreRADAR/ws/polling
wget "$ENDPOINT?semaforo=$1&tag=$2&tag=$3&tag=$4&tag=$5&tag=$6&tag=$7&tag=$8&tag=$9"
#
#come per i .bat di base si possono passare solo 9 parametri
#
