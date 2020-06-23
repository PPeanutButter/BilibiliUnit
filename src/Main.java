import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        switch (args[0]) {
            case "extract": {
                extract(new File(args[1]));
                break;
            }
            case "combine": {
                combine(new File(args[1]));
                break;
            }
            default: {
                help();
            }
        }
        System.out.println("Process finished...");
    }

    private static void combine(File dir) {
        File[] list = dir.listFiles();
        StringBuilder bat = new StringBuilder();
        if (list == null || list.length == 0) {
            System.out.println("no valid dir found!");
            return;
        }
        bat.append("(for %%i in (*.mkv) do @echo file '%%i') > combine_task_list.txt").append(System.lineSeparator());
        bat.append(String.format("ffmpeg -f concat -safe 0 -i combine_task_list.txt -c copy %s.mkv", dir.getName()));
        bat.append(System.lineSeparator());
        bat.append("del combine_task_list.txt").append(System.lineSeparator());
        string2File(bat.toString(), dir.getPath() + "/" + "combine.bat");
    }

    private static void extract(File dir) {
        File[] list = dir.listFiles();
        StringBuilder bat = new StringBuilder();
        bat.append("chcp 65001").append(System.lineSeparator());
        if (list == null || list.length == 0) {
            System.out.println("no valid dir found!");
            return;
        }
        for (File secondaryDir : list) {
            File entryJsonFile = new File(secondaryDir.getPath() + "\\entry.json");
            JSONObject entryJson = JSONObject.parseObject(readFileContent(entryJsonFile));
            String title = entryJson.getString("title");
            String type_tag = entryJson.getString("type_tag");
            JSONObject page_data = entryJson.getJSONObject("page_data");
            String part = page_data.getString("part");
            System.out.println(part);
            String video = secondaryDir.getPath() + "\\" + type_tag + "/video.m4s";
            String audio = secondaryDir.getPath() + "\\" + type_tag + "/audio.m4s";
            //noinspection ResultOfMethodCallIgnored
            new File(dir.getParent() + "\\" + title).mkdirs();
            String output = dir.getParent() + "/" + title + "/" + part + ".mkv";
            String cmd = String.format("ffmpeg -i \"%s\" -i \"%s\" -c:v copy -c:a copy \"%s\"", video, audio, output);
            bat.append(cmd).append(System.lineSeparator());
        }
        bat.append("echo 按任意键清理临时文件...").append(System.lineSeparator());
        bat.append("pause>nul").append(System.lineSeparator());
        bat.append("del ").append(dir.getPath()).append("\\")
                .append("extract.bat").append(System.lineSeparator());
        string2File(bat.toString(), dir.getPath() + "\\" + "extract.bat");
    }

    public static void string2File(String res, String filePath) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter;
        try {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists()) //noinspection ResultOfMethodCallIgnored
                distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile,StandardCharsets.UTF_8));
            char[] buf = new char[1024];
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readFileContent(File file) {
        BufferedReader reader = null;
        StringBuilder sbf = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return "";
    }

    private static void help() {
        System.out.println("support commands:");
        System.out.println("help                              this doc");
        System.out.println("extract[dir:numerical_name]         extract video with audio(from cache directory)to runnable ffmpeg command line");
        System.out.println("combine[dir]                        combine videos to runnable ffmpeg command line");
    }
}
