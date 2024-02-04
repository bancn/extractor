package com.myplugin.infrastructure.utility;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Path;

public class FileDecompressor {
    public static Path decompressFile(File compressedFile, Path destinationDirectory) {
        // 使用第三方库如Apache Commons Compress解压文件到指定path
        String inputFilePath = compressedFile.getAbsolutePath();
        File outputDir = destinationDirectory.toFile();


        try ( InputStream is = new FileInputStream(inputFilePath);
              BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
              ArchiveInputStream ais = new ArchiveStreamFactory().createArchiveInputStream(bufferedInputStream)) {

            ArchiveEntry entry;
            while ((entry = ais.getNextEntry()) != null) {
                if (!ais.canReadEntryData(entry)) {
                    System.err.println("Entry " + entry.getName() + " cannot be read.");
                    continue;
                }

                File entryFile = new File(outputDir, entry.getName());

                if (entry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    entryFile.getParentFile().mkdirs();

                    try (OutputStream os = new FileOutputStream(entryFile)) {
                        IOUtils.copy(ais, os);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ArchiveException e) {
            throw new RuntimeException(e);
        }

        return destinationDirectory; // 返回解压后文件的路径
    }
}
