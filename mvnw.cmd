@REM Apache Maven Wrapper startup batch script, version 3.3.2
@REM Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
@echo off
set ERROR_CODE=0
set MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%
IF NOT "%MAVEN_PROJECTBASEDIR%"=="" GOTO endDetectBaseDir
set EXEC_DIR=%CD%
set WDIR=%EXEC_DIR%
:findBaseDir
IF EXIST "%WDIR%"\.mvn GOTO baseDirFound
cd ..
IF "%WDIR%"=="%CD%" GOTO baseDirNotFound
set WDIR=%CD%
GOTO findBaseDir
:baseDirFound
set MAVEN_PROJECTBASEDIR=%WDIR%
cd "%EXEC_DIR%"
GOTO endDetectBaseDir
:baseDirNotFound
set MAVEN_PROJECTBASEDIR=%EXEC_DIR%
cd "%EXEC_DIR%"
:endDetectBaseDir

IF NOT EXIST "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar" (
  echo Maven Wrapper jar no encontrado. Ejecute ejecutar.bat para descargarlo.
  set ERROR_CODE=1
  goto error
)

SET MAVEN_JAVA_EXE=java.exe
if NOT "%JAVA_HOME%"=="" (
  SET MAVEN_JAVA_EXE="%JAVA_HOME%\bin\java.exe"
)

set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain
%MAVEN_JAVA_EXE% ^
  -classpath "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar" ^
  "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" ^
  %WRAPPER_LAUNCHER% %*
if ERRORLEVEL 1 goto error
goto end
:error
set ERROR_CODE=1
:end
cmd /C exit /B %ERROR_CODE%
