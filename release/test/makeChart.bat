@echo off

set OUTPUT_DIR=.\output
set TMP_FILE=.\tmp.txt
set TMP_FILE2=.\tmp2.txt

set DATA_DIR=%1
set DATA_PATH=%DATA_DIR%\%2
set OUTPUT_PATH=%OUTPUT_DIR%\%3
set RATIO_DATA_PATH=%DATA_DIR%\%4
set OUTPUT_FILE_NAME=%3

echo ##################
echo INPUT_DATA:%DATA_PATH%
echo OUTPUT_DATA:%OUTPUT_PATH%
echo INPUT_DATA:%RATIO_DATA_PATH%

echo create chart...
jcat %DATA_PATH% -s 2 | jprojection -c"1,2,3,4,5" | jchart -o %OUTPUT_PATH%

echo calc ratio...
@rem 1 + {(終値-始値)/始値*100)
echo ^1+( > %TMP_FILE%
jgrep %RATIO_DATA_PATH% -p"20:00:00" | jprojection -c"2" >> %TMP_FILE%
echo ^- >> %TMP_FILE%
jgrep %RATIO_DATA_PATH% -p"08:00:00" | jprojection -c"2" >> %TMP_FILE%
echo ^)^*100^/ >> %TMP_FILE%
jgrep %RATIO_DATA_PATH% -p"08:00:00" | jprojection -c"2" >> %TMP_FILE%
jreplace %TMP_FILE% -p\n -v" " | jeval -r3 > %TMP_FILE2%

echo judge ratio...
for /f "DELIMS=" %%A in ('jcat %TMP_FILE2%') do set RATIO=%%A
if %RATIO% geq 1.02 (move %OUTPUT_PATH% %OUTPUT_DIR%\UP\%OUTPUT_FILE_NAME%_%RATIO%.gif
) else if %RATIO% leq 0.98 (move %OUTPUT_PATH% %OUTPUT_DIR%\DOWN\%OUTPUT_FILE_NAME%_%RATIO%.gif
) else (move %OUTPUT_PATH% %OUTPUT_DIR%\DRAW\%OUTPUT_FILE_NAME%_%RATIO%.gif
)

echo success.


