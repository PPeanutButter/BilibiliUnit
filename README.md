# BilibiliUnit
Bilibili工具箱

# 支持的功能
## extract(类似唧唧下载器，但是比唧唧下载器要快10x)
> 使用ffmpeg合成bililbili缓存音视频文件（在device\内部存储设备\Android\data\tv.danmaku.bili\download）（需要配置好ffmpeg的环境变量），会在上一级目录生成一个视频名称文件夹，同时文件夹下有.bat(for windows)文件，双击运行等待即可


command

`extract[dir:numerical_name]       extract video with audio(from cache directory)to runnable ffmpeg command line`

example

`java -jar BilibiliUnit.jar extract E:\video\95051759`

注：传入的文件路径最后名称是数字，如95051759

## combine(小文件合成)
> 一些视频只有几分钟的，可以使用该命令将小视频合成为一个大视频提高专注。命令执行后，会文件夹下生成.bat(for windows)文件，双击运行等待即可

command

`combine[dir]                        combine videos to runnable ffmpeg command line`

example

`java -jar BilibiliUnit.jar extract E:\video\吴恩达团队Tensorflow2.0实践系列课程第一课`

会在同目录下生成`吴恩达团队Tensorflow2.0实践系列课程第一课.mkv`文件
