package com.myplugin.domain.model;

public class FileNode {
    private final String name;
    private final boolean isDirectory;
    private final FileNode[] children;

    public FileNode(String name, boolean isDirectory, FileNode[] children) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.children = children;
    }

    // Getters
    public String getName() {
        return name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public FileNode[] getChildren() {
        return children;
    }
}
