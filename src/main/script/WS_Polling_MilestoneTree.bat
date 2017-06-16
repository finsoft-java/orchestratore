@echo off
set WGET=c:\Users\Finsoft-PC\Desktop\Progetto\VisualWGet\wget.exe
set ENDPOINT=http://localhost:8080/orchestratoreRADAR/ws/Polling/testTree
set MILESTONE=ACQUISIZIONE DATI MACS1

rem Questo .bat richiede l'indicazione della milestone che si vuole far partire e del quale conosciamo il TAG da ricercare

%WGET% -qO- "%ENDPOINT%?milestone=%MILESTONE%&tag=%1"
pause