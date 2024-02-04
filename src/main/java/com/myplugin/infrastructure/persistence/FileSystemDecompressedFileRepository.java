package com.myplugin.infrastructure.persistence;

import com.myplugin.domain.model.DecompressedDirectory;
import com.myplugin.domain.repository.DecompressedFileRepository;

public class FileSystemDecompressedFileRepository implements DecompressedFileRepository {
    @Override
    public DecompressedDirectory saveDecompressedFiles(DecompressedDirectory directory) {
        // 实现文件存储逻辑
        return directory; // 示例代码，实际可能会复杂
    }
}
