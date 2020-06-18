%generate file lists%
(for %%i in (%*) do @echo file '%%~i') > combines_task_list.txt
%run ffmpeg commands%
ffmpeg -f concat -safe 0 -i combines_task_list.txt -c copy "C-%~nx1"
%delete file lists%
del combines_task_list.txt
pause>nul
