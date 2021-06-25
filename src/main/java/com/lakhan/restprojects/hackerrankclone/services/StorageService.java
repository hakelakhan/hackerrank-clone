package com.lakhan.restprojects.hackerrankclone.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    void init() throws IOException;
    void store(MultipartFile file, String targetFilename) throws IOException;
}
