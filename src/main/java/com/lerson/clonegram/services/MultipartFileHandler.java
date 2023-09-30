package com.lerson.clonegram.services;

import com.lerson.clonegram.interfaces.MultipartFileHandlerInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class MultipartFileHandler implements MultipartFileHandlerInterface {

    @Value("${file.story.upload-dir}")
    private String uploadDir;

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        String fileName = this.generateUniqueFileName(file.getOriginalFilename());
        String filePath = this.generateFilePath(this.uploadDir, fileName);
        this.transferFile(file, filePath);

        return fileName;
    }

    private String generateUniqueFileName(String originalFileName) {

        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        return String.format("%s%s", UUID.randomUUID(), fileExtension);
    }

    private String generateFilePath(String uploadDir, String fileName) {

        return String.format("%s%s%s", uploadDir, File.separator, fileName);
    }

    private void transferFile(MultipartFile file, String filePath) throws IOException {

        File dest = new File(filePath);
        file.transferTo(dest);
    }
}
