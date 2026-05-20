@echo off
setlocal
title Java EE - Clone repo and init DB

REM Этот скрипт работает на ноуте при первичном развёртывании.
REM Папка persons\ создастся рядом с этим .bat.

set BASE=%~dp0

echo [1/2] Cloning repo (skip if persons\ folder already exists)...
cd /d "%BASE%"
if exist "%BASE%persons" (
    echo [INFO] persons\ already exists, doing git pull...
    cd "%BASE%persons"
    git pull
) else (
    git clone https://github.com/Hipao/java-ee-persons.git "%BASE%persons"
)

echo.
echo [2/2] Initializing database persons_jee...
set PGPASSWORD=postgres
set PSQL="C:\Program Files\PostgreSQL\16\bin\psql.exe"
%PSQL% -U postgres -h localhost -c "DROP DATABASE IF EXISTS persons_jee;"
%PSQL% -U postgres -h localhost -c "CREATE DATABASE persons_jee;"
%PSQL% -U postgres -h localhost -d persons_jee -f "%BASE%persons\src\main\java\config\init_db.sql"

echo.
echo Done!
echo Now run: start.bat
echo URL: http://localhost:8080/persons/
pause
endlocal
