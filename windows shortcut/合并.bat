@echo off
chcp 65001
for %%i in (%*) do call set name= -i %%i %%name%% 
set command=ffmpeg %name% -c:v copy -c:a copy -c:s copy output.mkv
echo command
%command%
pause
