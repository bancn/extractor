package com.myplugin.infrastructure.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TemporaryDirectoryManager {
    public static Path createTemporaryDirectory() throws IOException {
        return Files.createTempDirectory("myplugin_");
    }
}
