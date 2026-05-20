@echo off
setlocal EnableDelayedExpansion
title Java EE - Deploy

set TOMCAT_HOME=D:\dev\apache-tomcat-10.0.27
set TOMCAT_WEBAPPS=%TOMCAT_HOME%\webapps
set PROJECT_DIR=%~dp0persons
set PROJECT_SRC=%PROJECT_DIR%\src\main\webapp
set JAVA_SRC=%PROJECT_DIR%\src\main\java
set CLASSES=%PROJECT_DIR%\build\classes
set JAVAC="C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot\bin\javac.exe"
set CP=%TOMCAT_HOME%\lib\*;%PROJECT_SRC%\WEB-INF\lib\*

if exist "%JAVA_SRC%" (
    dir /b /s "%JAVA_SRC%\*.java" >nul 2>nul
    if not errorlevel 1 (
        echo [INFO] Compiling Java sources with UTF-8 encoding...
        if exist "%CLASSES%" rmdir /S /Q "%CLASSES%"
        mkdir "%CLASSES%"

        set FILES=
        for /r "%JAVA_SRC%" %%f in (*.java) do (
            set FILES=!FILES! "%%f"
        )

        %JAVAC% -encoding UTF-8 -d "%CLASSES%" -cp "%CP%" !FILES!
        if errorlevel 1 (
            echo [ERROR] Compilation failed!
            exit /b 1
        )
        echo [INFO] Compilation OK.
    )
)

echo [INFO] Clearing old deploy...
if exist "%TOMCAT_WEBAPPS%\persons" rmdir /S /Q "%TOMCAT_WEBAPPS%\persons"

echo [INFO] Copying webapp to Tomcat...
xcopy "%PROJECT_SRC%" "%TOMCAT_WEBAPPS%\persons\" /E /I /Y /Q

if exist "%CLASSES%" (
    if not exist "%TOMCAT_WEBAPPS%\persons\WEB-INF\classes" mkdir "%TOMCAT_WEBAPPS%\persons\WEB-INF\classes"
    xcopy "%CLASSES%" "%TOMCAT_WEBAPPS%\persons\WEB-INF\classes\" /E /I /Y /Q
)

REM Copy config.properties (resource) to classes folder
if exist "%JAVA_SRC%\config\config.properties" (
    if not exist "%TOMCAT_WEBAPPS%\persons\WEB-INF\classes\config" mkdir "%TOMCAT_WEBAPPS%\persons\WEB-INF\classes\config"
    copy /Y "%JAVA_SRC%\config\config.properties" "%TOMCAT_WEBAPPS%\persons\WEB-INF\classes\config\" >nul
)

echo.
echo [INFO] Deploy done. Restart Tomcat to apply Java class changes.
echo.
timeout /t 3 /nobreak >nul
endlocal
