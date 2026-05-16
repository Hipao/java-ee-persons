@echo off
title Java EE - Stop

set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot
set CATALINA_HOME=D:\dev\apache-tomcat-10.0.27

echo [INFO] Sending shutdown to Tomcat...
call "%CATALINA_HOME%\bin\shutdown.bat" 2>nul

timeout /t 4 /nobreak >nul

netstat -ano | findstr ":8080 " >nul
if %errorlevel% == 0 (
    echo [WARN] Tomcat still alive, killing java processes...
    powershell -NoProfile -Command "Get-WmiObject Win32_Process | Where-Object { $_.CommandLine -like '*apache-tomcat*' } | ForEach-Object { Stop-Process -Id $_.ProcessId -Force }"
)

echo [INFO] Tomcat stopped.
timeout /t 2 /nobreak >nul
