# BilibiliUnit
Bilibili工具箱

# 支持的功能
## combine(类似唧唧下载器，但是比唧唧下载器要快10x)
> 使用ffmpeg合成bililbili缓存音视频文件（在device\内部存储设备\Android\data\tv.danmaku.bili\download）（需要配置好ffmpeg的环境变量），会在上一级目录生成一个视频名称文件夹，同时文件夹下有.bat(for windows)文件，双击运行等待即可


command

`combine[dir:numerical_name]       combine video with audio to runnable ffmpeg command line`

example

`BilibiliUnit.jar combine E:\video\95051759`

注：传入的文件路径最后名称是数字，如95051759
