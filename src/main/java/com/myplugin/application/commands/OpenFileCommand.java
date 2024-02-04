package com.myplugin.application.commands;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.function.Consumer;

public class OpenFileCommand {
    private final Project project;
    private final Consumer<VirtualFile> onFileChosen;

    public OpenFileCommand(Project project, Consumer<VirtualFile> onFileChosen) {
        this.project = project;
        this.onFileChosen = onFileChosen;
    }

    public void execute() {
        FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, false, false, false);
        VirtualFile file = FileChooser.chooseFile(descriptor, project, null);
        if (file != null) {
            onFileChosen.accept(file);
        }
    }
}
