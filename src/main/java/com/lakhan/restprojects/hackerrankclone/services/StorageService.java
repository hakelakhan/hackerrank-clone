package com.lakhan.restprojects.hackerrankclone.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {
    void init() throws IOException;
    void store(MultipartFile file, String targetFilename) throws IOException;
    Path load(String filename);
    Resource loadAsResource(String filename);
}
