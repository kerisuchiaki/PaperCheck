package com.yuyonghai.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//文件读写的IO类
public class FileUtils {
    //从指定路径的文件中读取文本内容并返回文本字符串
    public static String readFile(String path) throws IOException {
        StringBuilder builder = new StringBuilder(); //用于构建最终的文本内容
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        return builder.toString(); //转换为字符串返回
    }

    public static void writeFile(String path, String text) throws IOException {
        //创建文件输出流，文件不存在则新建
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(text);
        }
    }
}