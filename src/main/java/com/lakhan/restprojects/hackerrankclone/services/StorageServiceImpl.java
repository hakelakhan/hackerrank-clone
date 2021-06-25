package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class StorageServiceImpl implements StorageService{

    @Autowired
    private AppConfig appConfig;

    @Override
    @PostConstruct
    public void init() throws IOException{
        File uploadDir = Paths.get(appConfig.getUploadLocation()).toFile();
        if(!uploadDir.exists())
            uploadDir.mkdirs();
    }

    @Override
    public void store(MultipartFile file, String targetFilename) throws IOException {
        file.transferTo(new File(appConfig.getUploadLocation() + File.separator+ targetFilename));
    }
}
