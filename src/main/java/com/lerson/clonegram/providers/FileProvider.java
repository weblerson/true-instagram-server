package com.lerson.clonegram.providers;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileProvider {

    public File getFile(String filePath) {

        return new File(filePath);
    }
}
