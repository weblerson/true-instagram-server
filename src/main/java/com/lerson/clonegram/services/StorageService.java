package com.lerson.clonegram.services;

import com.lerson.clonegram.exceptions.StorageException;
import com.lerson.clonegram.interfaces.MultipartFileHandlerInterface;
import com.lerson.clonegram.providers.FileProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class StorageService implements MultipartFileHandlerInterface {

    private final FileProvider fileProvider;

    @Autowired
    public StorageService(FileProvider fileProvider) {

        this.fileProvider = fileProvider;
    }

    @Override
    public String saveFile(MultipartFile file, String uploadDir) {

        try {

            String fileName = this.generateUniqueFileName(file.getOriginalFilename());
            String filePath = this.generateFilePath(uploadDir, fileName);
            this.transferFile(file, filePath);

            return fileName;
        } catch (IOException e) {

            throw new StorageException(e.getMessage());
        }
    }

    private String generateUniqueFileName(String originalFileName) {

        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        return String.format("%s%s", UUID.randomUUID(), fileExtension);
    }

    private String generateFilePath(String uploadDir, String fileName) {

        return String.format("%s%s%s", uploadDir, File.separator, fileName);
    }

    private void transferFile(MultipartFile file, String filePath) throws IOException {

        File dest = this.fileProvider.getFile(filePath);
        file.transferTo(dest);
    }
}
