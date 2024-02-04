package com.myplugin.domain.model;

public class DecompressedDirectory {
    private final FileNode root;

    public DecompressedDirectory(FileNode root) {
        this.root = root;
    }

    public FileNode getRoot() {
        return root;
    }
}
