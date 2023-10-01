package com.lerson.clonegram.interfaces;

import com.lerson.clonegram.services.MultipartFileHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MultipartFileHandlerInterface {

    String saveFile(MultipartFile file, String uploadDir) throws IOException;
}
