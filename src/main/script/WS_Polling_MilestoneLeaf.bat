@echo off
set WGET=c:\Users\Finsoft-PC\Desktop\Progetto\VisualWGet\wget.exe
set ENDPOINT=http://localhost:8080/orchestratoreRADAR/ws/Polling/testLeaf
set MILESTONE=ACQUISIZIONE DATI MACS1

rem Questo .bat richiede l'indicazione della milestone che si vuole far partire e del quale conosciamo il numero esatto di TAG
rem da ricercare

%WGET% -qO- "%ENDPOINT%?milestone=%MILESTONE%&tag=%1&tag=%2&tag=%3&tag=%4&tag=%5&tag=%6&tag=%7&tag=%8&tag=%9"
pause