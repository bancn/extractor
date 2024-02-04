package com.myplugin.ui.panels;

import com.intellij.openapi.project.Project;
import com.myplugin.domain.model.DecompressedDirectory;
import com.myplugin.domain.model.FileNode;
import com.myplugin.ui.UIUpdater;
import com.myplugin.ui.widgets.MyMenuBar;
import com.myplugin.ui.widgets.MySimpleTree;
import com.myplugin.ui.widgets.MyTabbedPane;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements UIUpdater {
    private JTabbedPane tabbedPane;
    private Project project;

    public MainPanel(Project project) {
        this.project = project;
        setLayout(new BorderLayout());
        tabbedPane = new MyTabbedPane();
        add(new MyMenuBar(project, this), BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    @Override
    public void updateUIWithDecompressedData(DecompressedDirectory decompressedDirectory, String tabTitle) {
        SwingUtilities.invokeLater(() -> {
            FileNode root = decompressedDirectory.getRoot();
            MySimpleTree tree = new MySimpleTree(root);
            JScrollPane scrollPane = new JScrollPane(tree);
            tabbedPane.addTab(tabTitle, scrollPane);
            tabbedPane.setSelectedComponent(scrollPane);
        });
    }
}
