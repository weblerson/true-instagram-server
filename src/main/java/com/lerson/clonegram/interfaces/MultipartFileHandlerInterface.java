package com.lerson.clonegram.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MultipartFileHandlerInterface {

    String saveFile(MultipartFile file, String uploadDir) throws IOException;
}
