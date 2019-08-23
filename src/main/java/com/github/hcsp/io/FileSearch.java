package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        int result = -1;
        try {
            List<String> lines = FileUtils.readLines(target, Charset.defaultCharset());
            for (int i = 0; i < lines.size(); i++) {
                if (text.equals(lines.get(i))) {
                    result = i;
                }
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("文件不存在或者无法被读取", e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            throw new OutOfMemoryError("文件过大");
        }
        return result;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBBB"));
    }
}
