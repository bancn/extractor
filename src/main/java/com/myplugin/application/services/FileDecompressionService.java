package com.myplugin.application.services;

import com.myplugin.domain.model.DecompressedDirectory;
import com.myplugin.domain.model.FileNode;
import com.myplugin.infrastructure.utility.FileDecompressor;
import com.myplugin.infrastructure.utility.TemporaryDirectoryManager;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class FileDecompressionService {
    public DecompressedDirectory decompressFile(File compressedFile) {
        try {
            Path tempDir = TemporaryDirectoryManager.createTemporaryDirectory();
            Path decompressedDir = FileDecompressor.decompressFile(compressedFile, tempDir);

            // 根据解压后的文件结构构建FileNode和DecompressedDirectory
            FileNode rootNode = scanDirectory(decompressedDir.toFile());
            return new DecompressedDirectory(rootNode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private FileNode scanDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files == null) return new FileNode(directory.getName(), true, new FileNode[0]);

        FileNode[] children = Arrays.stream(files)
                .map(file -> file.isDirectory() ?
                        scanDirectory(file) : new FileNode(file.getName(), false, new FileNode[0]))
                .toArray(FileNode[]::new);

        return new FileNode(directory.getName(), true, children);
    }

}
