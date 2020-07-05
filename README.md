# BilibiliUnit
Bilibili工具箱

# 傻瓜式用法
>将你要处理的文件夹拖到xxx.bat文件上（相当于输入了一遍代码，xxx是你要选择的函数名称）。
 唯一需要注意的是：BilibiliUnit.jar需要放置指定处理文件夹的同级目录
（如要处理E:\video\70856556，位置为‪E:\video\BilibiliUnit.jar）

# 高级用法
参照源代码或者BAT文件

# 支持的功能
## extract(缓存提取)
> 使用官方APP缓存实现下载，解决唧唧下载器等第三方下载器被封导致的下载缓慢的问题。
> 使用ffmpeg合成bililbili缓存音视频文件（在手机\内部存储设备\Android\data\tv.danmaku.bili\download）
>（需要配置好ffmpeg的环境变量）

注：传入的文件路径最后名称是数字，如95051759。在同级目录生成文件夹。

## extractP（分P提取）
> 要求同extract，但是生成的文件名前有P1，P2之类的，便于文件名排序

## combine(小文件合成)
> 一些视频只有几分钟的，可以使用该命令将小视频合成为一个大视频提高专注。
>命令执行后，会文件夹下生成.bat(for windows)文件，双击运行等待即可

注：会在同目录下生成`xxx.mkv`文件

## combines(指定小文件合成)
> combine默认是合成目录下所有视频文件，combines则可以合成指定视频文件。
> 将选定好的视频文件拖到combines.bat即可

注：会在同目录下生成`c-xxx.mkv`文件
