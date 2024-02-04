package com.myplugin.domain.repository;

import com.myplugin.domain.model.DecompressedDirectory;

public interface DecompressedFileRepository {
    DecompressedDirectory saveDecompressedFiles(DecompressedDirectory directory);
}
