@echo off
title Java EE - Start

set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot
set CATALINA_HOME=D:\dev\apache-tomcat-10.0.27

netstat -ano | findstr ":8080 " >nul
if %errorlevel% == 0 (
    echo [INFO] Tomcat already running on port 8080
) else (
    echo [INFO] Starting Tomcat...
    start "" /B "%CATALINA_HOME%\bin\startup.bat"
    timeout /t 6 /nobreak >nul
)

echo [INFO] Opening http://localhost:8080/persons/ ...
start "" "http://localhost:8080/persons/"

echo.
echo Done. To stop: stop.bat   ^|   To redeploy: deploy.bat
echo.
timeout /t 3 /nobreak >nul
