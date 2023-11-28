package com.archer.mirror.mirrorservice.service;


import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
import java.util.concurrent.Future;

public interface FileProcessService {

    String uploadFile(MultipartFile file) throws Exception;
    Map<String, Object> readFile(String filePath) throws Exception;
    Future<String> sendToMirror() throws InterruptedException;
}
