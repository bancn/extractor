package com.myplugin.application.services;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.myplugin.domain.model.DecompressedDirectory;
import com.myplugin.ui.UIUpdater;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class DecompressTask extends Task.Backgroundable {
    private final File compressedFile;
    private final FileDecompressionService decompressionService;
    private final UIUpdater uiUpdater;
    public DecompressTask(Project project, String title, File compressedFile, FileDecompressionService decompressionService, UIUpdater uiUpdater) {
        super(project, title, true);
        this.compressedFile = compressedFile;
        this.decompressionService = decompressionService;
        this.uiUpdater = uiUpdater;
    }

    @Override
    public void run(@NotNull ProgressIndicator indicator) {
        indicator.setIndeterminate(true);
        indicator.setText("Decompressing...");

        // 执行解压缩操作
        DecompressedDirectory directory = decompressionService.decompressFile(compressedFile);

        // 必要时更新UI，注意这需要在EDT执行
        if (directory != null) {
            uiUpdater.updateUIWithDecompressedData(directory, compressedFile.getName());
        }
    }
}
