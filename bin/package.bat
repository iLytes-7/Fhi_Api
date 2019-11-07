@echo off
echo [INFO] Packaging a executable production war.
cd %~dp0
cd ..
set MAVEN_OPTS=%MAVEN_OPTS%
call mvn clean package -Dmaven.test.skip=true -Pprd
cd bin