package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        try {
            List<String> textList = FileUtils.readLines(target, "UTF-8");
            Iterator iter = textList.iterator();
            int returnValue = 0;
            while (iter.hasNext()) {
                String textline = (String) (iter.next());
                if (textline.contains(text)) {
                    returnValue += 1;
                }
            }
            return (returnValue == 0) ? (-1) : returnValue;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
