@echo off
java -jar "%bilibili_unit%" extractP "%1"
%1\extract.bat
pause>nul