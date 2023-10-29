package com.archer.mirror.mirrorservice.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtils {

    public static String save(MultipartFile file, String filePath) throws IOException {
        String oldFileName = file.getName();
        String newFileName = String.valueOf(UUID.randomUUID());
        File fileSave = new File(filePath);
        if (!fileSave.exists()) {
            fileSave.mkdirs();
        }
        file.transferTo(fileSave);
        return newFileName;
    }


}
