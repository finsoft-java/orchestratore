@echo off
set WGET=c:\Users\Finsoft-PC\Desktop\Progetto\VisualWGet\wget.exe
set ENDPOINT=http://localhost:8080/orchestratoreRADAR/ws/collector

rem ...

%WGET% -qO- "%ENDPOINT%?entita=%1&tipiEvento=%2&tag=%3"
