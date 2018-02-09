@echo off

set JCLI_DIR=D:\opt\eclipse\jee-neon\eclipse\workspace_cli\release
set TARGET_DATA_LIST=.\data.list
set PATH=%PATH%;%JCLI_DIR%
set OUTPUT_DIR=.\output

echo init...
DEL /Q %OUTPUT_DIR%\UP
DEL /Q %OUTPUT_DIR%\DOWN
DEL /Q %OUTPUT_DIR%\DRAW

for /f "tokens=1-4 delims=," %%a in (%TARGET_DATA_LIST%) do (
  .\makeChart.bat %%a %%b %%c %%d
)

