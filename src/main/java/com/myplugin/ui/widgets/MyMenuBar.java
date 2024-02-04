package com.myplugin.ui.widgets;

import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.myplugin.application.commands.OpenFileCommand;
import com.myplugin.application.services.DecompressTask;
import com.myplugin.application.services.FileDecompressionService;
import com.myplugin.ui.UIUpdater;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyMenuBar extends JMenuBar {
    private Project project;
    private final UIUpdater uiUpdater;
    public MyMenuBar(Project project, UIUpdater uiUpdater) {
        this.project = project;
        this.uiUpdater = uiUpdater;

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(this::openActionPerformed);
        fileMenu.add(openItem);

        JMenu editMenu = new JMenu("Edit");
        // Add edit menu items here

        JMenu helpMenu = new JMenu("Help");
        // Add help menu items here

        this.add(fileMenu);
        this.add(editMenu);
        this.add(helpMenu);
    }

    private void openActionPerformed(ActionEvent e) {
        new OpenFileCommand(project, file -> {
            FileDecompressionService decompressionService = new FileDecompressionService();
            Task.Backgroundable task = new DecompressTask(project, "Decompressing File", file.toNioPath().toFile(), decompressionService, uiUpdater);
            task.queue(); // 将任务加入队列以异步执行
        }).execute();
    }
}
