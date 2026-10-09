COMO EJECUTAR EL SOFTWARE - Solicitar saludo
============================================

Este proyecto esta pensado para Windows.
En cada computadora se descarga Maven y las librerias nativas de JavaFX.
No copie la carpeta target entre equipos.

Requisito previo
----------------
Debe tener instalado Java JDK 24 (o compatible). En esta computadora se uso:

    java version "24.0.1"

Para comprobarlo, abra una terminal y ejecute:

    java -version
    javac -version


Windows
-------
1. Abra el Explorador de archivos y entre a esta carpeta:

    Documents\modeloderequerimientosclase

2. Haga doble clic en:

    ejecutar.bat

3. La primera vez puede tardar porque descarga Maven y las librerias de JavaFX.
   Luego se abrira la ventana de la aplicacion de escritorio.

Forma alternativa (PowerShell o CMD):

    cd C:\Users\ESTUDIANTE\Documents\modeloderequerimientosclase
    .\mvnw.cmd -q javafx:run


Como usar la aplicacion
-----------------------
1. Haga clic en el boton "Solicitar saludo".
2. El sistema pedira nombre, edad y hora (AM/PM).
3. Complete los datos y pulse "Enviar y saludar".
4. El sistema lo saludara por su nombre y mencionara su edad.


Si aparece un error
-------------------
- "java no se reconoce": instale JDK y vuelva a abrir la terminal.
- La ventana no abre: verifique que este en la carpeta del proyecto y que exista el archivo pom.xml.
- Si Maven no descarga: se necesita conexion a internet la primera vez.
