package com.archer.mirror.mirrorservice.service;


import com.archer.mirror.mirrorservice.utils.FileUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.Future;


@Service
public class FileProcess implements FileProcessService{

    @Override
    public String uploadFile(MultipartFile[] files, Model model) {
        try {
            // 1. upload file
            for (MultipartFile file : files) {
                if (file.getSize() <= 0) {
                    continue;
                }
                final String newFileName = FileUtils.save(file, "");
            }

            // 2. create json prop and send to mirror-core

            // 3. asynchronous wait for result
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }



    @Async
    public Future<String> sendTo() throws InterruptedException {
        Thread.sleep(5000);
        return new AsyncResult<>("task Done");
    }




}
