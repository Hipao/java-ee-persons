@echo off
setlocal EnableDelayedExpansion
title Java EE - Setup on new PC

echo ============================================================
echo  Установка окружения для Java EE labs persons
echo  Студент: Демидко М.Д., ПИZ-331
echo ============================================================
echo.

REM === Проверка winget ===
where winget >nul 2>nul
if errorlevel 1 (
    echo [ERROR] winget не найден. Установи App Installer из Microsoft Store.
    pause
    exit /b 1
)

echo [1/6] Установка JDK 21 (Adoptium Temurin)...
winget install --id EclipseAdoptium.Temurin.21.JDK --accept-source-agreements --accept-package-agreements --silent

echo.
echo [2/6] Установка Git...
winget install --id Git.Git --accept-source-agreements --accept-package-agreements --silent

echo.
echo [3/6] Установка GitHub CLI...
winget install --id GitHub.cli --accept-source-agreements --accept-package-agreements --silent

echo.
echo [4/6] Установка PostgreSQL 16...
winget install --id PostgreSQL.PostgreSQL.16 --accept-source-agreements --accept-package-agreements --silent --override "--mode unattended --superpassword postgres --servicename postgresql-x64-16 --serverport 5432"

echo.
echo [5/6] Скачивание Apache Tomcat 10.0.27...
if not exist D:\dev mkdir D:\dev
if not exist D:\dev\downloads mkdir D:\dev\downloads
if not exist D:\dev\apache-tomcat-10.0.27 (
    powershell -NoProfile -Command "$ProgressPreference='SilentlyContinue'; Invoke-WebRequest -Uri 'https://archive.apache.org/dist/tomcat/tomcat-10/v10.0.27/bin/apache-tomcat-10.0.27-windows-x64.zip' -OutFile 'D:\dev\downloads\tomcat-10.0.27.zip'; Expand-Archive -Path 'D:\dev\downloads\tomcat-10.0.27.zip' -DestinationPath 'D:\dev\' -Force"
)

echo.
echo [6/6] Скачивание Eclipse IDE for Enterprise Java (~540MB, ~5-10 мин)...
if not exist D:\dev\eclipse (
    powershell -NoProfile -Command "$ProgressPreference='SilentlyContinue'; Invoke-WebRequest -Uri 'https://download.eclipse.org/technology/epp/downloads/release/2024-12/R/eclipse-jee-2024-12-R-win32-x86_64.zip' -OutFile 'D:\dev\downloads\eclipse-jee.zip'; Expand-Archive -Path 'D:\dev\downloads\eclipse-jee.zip' -DestinationPath 'D:\dev\' -Force"
)

echo.
echo ============================================================
echo  Окружение установлено. Дальше:
echo    1. clone-and-run.bat  - клонировать репо и развернуть БД
echo    2. ..\start.bat       - запустить Tomcat
echo ============================================================
pause
