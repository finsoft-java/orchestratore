@echo off
set WGET=c:\Users\Finsoft-PC\Desktop\Progetto\VisualWGet\wget.exe
set ENDPOINT=http://localhost:8080/orchestratoreRADAR/ws/Collector

rem ...

%WGET% -qO- "%ENDPOINT%?entita=%1&tipiEvento=%2&tag=%3"
PAUSE
