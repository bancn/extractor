package com.myplugin.application.commands;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.function.Consumer;

public class OpenFileCommand {
    private final Project project;
    private final Consumer<File> onFileChosen;

    public OpenFileCommand(Project project, Consumer<File> onFileChosen) {
        this.project = project;
        this.onFileChosen = onFileChosen;
    }

    public void execute() {
        FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, false, false, false);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return true;
            }

            @Override
            public String getDescription() {
                return "All Files";
            }
        });
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                onFileChosen.accept(file);
            }
        }
    }
}
