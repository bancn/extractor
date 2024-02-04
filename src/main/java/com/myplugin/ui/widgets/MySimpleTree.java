package com.myplugin.ui.widgets;

import com.intellij.ui.treeStructure.SimpleTree;
import com.myplugin.domain.model.FileNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MySimpleTree extends SimpleTree {
    public MySimpleTree(FileNode root) {
        super(new DefaultTreeModel(createTreeNode(root)));
        this.setRootVisible(true);
    }

    private static DefaultMutableTreeNode createTreeNode(FileNode node) {
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(node.getName());
        if (node.isDirectory()) {
            for (FileNode child : node.getChildren()) {
                treeNode.add(createTreeNode(child));
            }
        }
        return treeNode;
    }
}
