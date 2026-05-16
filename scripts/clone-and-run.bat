@echo off
setlocal
title Java EE - Clone repo and init DB

if not exist D:\work mkdir D:\work
if not exist D:\work\java-ee-labs mkdir D:\work\java-ee-labs

echo [1/3] Клонирование репозитория с GitHub...
cd /d D:\work\java-ee-labs
if exist persons (
    echo [INFO] Папка persons уже существует, делаю git pull
    cd persons
    git pull
    cd ..
) else (
    git clone https://github.com/Hipao/java-ee-persons.git persons
)

echo.
echo [2/3] Инициализация базы данных persons_jee...
set PGPASSWORD=postgres
set PSQL="C:\Program Files\PostgreSQL\16\bin\psql.exe"
%PSQL% -U postgres -h localhost -c "DROP DATABASE IF EXISTS persons_jee;"
%PSQL% -U postgres -h localhost -c "CREATE DATABASE persons_jee;"
%PSQL% -U postgres -h localhost -d persons_jee -f "D:\work\java-ee-labs\persons\src\main\java\config\init_db.sql"

echo.
echo [3/3] Готово!
echo Для запуска: D:\work\java-ee-labs\start.bat
echo URL приложения: http://localhost:8080/persons/
pause
