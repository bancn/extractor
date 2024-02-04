package com.myplugin.ui;

import com.myplugin.domain.model.DecompressedDirectory;

public interface UIUpdater {
    void updateUIWithDecompressedData(DecompressedDirectory decompressedDirectory, String tabTitle);
}
