package com.lakhan.restprojects.hackerrankclone.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageServiceImpl implements StorageService{

    @Value("${upload.dir.location}")
    private String uploadDirLocation;

    @Override
    @PostConstruct
    public void init() throws IOException{
        uploadDirLocation = getClass().getResource(".").getPath().substring(1) + File.separator + "static" + File.separator + uploadDirLocation;
        File uploadDir = Paths.get(uploadDirLocation).toFile();
        if(!uploadDir.exists())
            uploadDir.mkdirs();
    }

    @Override
    public void store(MultipartFile file, String targetFilename) throws IOException {
        System.out.println("Storing file to " + uploadDirLocation + File.separator+ targetFilename);
        file.transferTo(new File(uploadDirLocation + File.separator+ targetFilename));
    }

    @Override
    public Path load(String filename) {
        return Paths.get(uploadDirLocation +File.separator + filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

}
