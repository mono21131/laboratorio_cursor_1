@echo off
setlocal
cd /d "%~dp0"

echo.
echo === Solicitar saludo ===
echo Compilando y abriendo la aplicacion de escritorio...
echo La primera vez puede tardar (descarga Maven y JavaFX).
echo.

if not exist ".mvn\wrapper\maven-wrapper.jar" (
    echo Descargando Maven Wrapper...
    powershell -NoProfile -Command "Invoke-WebRequest -UseBasicParsing -Uri https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar -OutFile .mvn\wrapper\maven-wrapper.jar"
    if errorlevel 1 (
        echo ERROR: no se pudo descargar Maven Wrapper. Revise la conexion a internet.
        pause
        exit /b 1
    )
)

call mvnw.cmd -q javafx:run
if errorlevel 1 (
    echo.
    echo ERROR: no se pudo ejecutar la aplicacion.
    echo Revise COMO_EJECUTAR.txt y que Java este instalado ^(java -version^).
    pause
    exit /b 1
)
endlocal
