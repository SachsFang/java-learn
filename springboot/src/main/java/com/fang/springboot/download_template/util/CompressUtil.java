package com.fang.springboot.download_template.util;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author shaobin
 * @date 2022/11/18 11:17
 */
public class CompressUtil {

    static final int BUFFER = 8192;

    /**
     * 根据文件路径进行压缩
     * @param sourcePath 文件源
     * @param targetPath 压缩目标路径
     * @param compressBaseDir 压缩包基础路径
     * @throws IOException
     */
    public static void compress(String sourcePath, String targetPath, String compressBaseDir) throws IOException {
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        if (!sourceFile.exists()) {
            throw new FileNotFoundException(sourcePath + "不存在！");
        }
        FileOutputStream out = null;
        ZipOutputStream zipOut = null;
        try {
            out = new FileOutputStream(targetFile);
            CheckedOutputStream cos = new CheckedOutputStream(out, new CRC32());
            zipOut = new ZipOutputStream(cos);
            compressBaseDir = Objects.nonNull(compressBaseDir) ? compressBaseDir : "";
            compress(sourceFile, zipOut, compressBaseDir);
        } finally {
            if (null != zipOut) {
                zipOut.close();
            }
            if (null != out) {
                out.close();
            }
        }
    }

    private static void compress(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        if (file.isDirectory()) {
            compressDirectory(file, zipOut, baseDir);
        } else {
            compressFile(file, zipOut, baseDir);
        }
    }

    /**
     * 压缩一个目录
     */
    private static void compressDirectory(File dir, ZipOutputStream zipOut, String baseDir) throws IOException {
        File[] files = dir.listFiles();
        if (Objects.nonNull(files)) {
            for (File file : files) {
                if (StringUtils.isEmpty(baseDir)) {
                    baseDir = dir.getName();
                }
                compress(file, zipOut, baseDir + "/");
            }
        }
    }

    /**
     * 压缩一个文件
     */
    private static void compressFile(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        if (!file.exists()) {
            return;
        }
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            ZipEntry entry = new ZipEntry(baseDir + file.getName());
            zipOut.putNextEntry(entry);
            int count;
            byte[] bytes = new byte[BUFFER];
            while ((count = bis.read(bytes, 0, BUFFER)) != -1) {
                zipOut.write(bytes, 0, count);
            }
        }
    }
}
