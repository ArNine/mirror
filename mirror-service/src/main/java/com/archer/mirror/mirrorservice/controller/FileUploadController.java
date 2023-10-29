package com.archer.mirror.mirrorservice.controller;

import com.archer.mirror.mirrorservice.service.FileProcess;
import com.archer.mirror.mirrorservice.service.FileProcessService;
import com.archer.mirror.mirrorservice.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@EnableAsync
public class FileUploadController {


    @Autowired
    private FileProcessService fileService;

    /**
     *
     * @param files
     * @param model
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile[] files, Model model) throws IOException {

        StringBuilder sb = new StringBuilder();
        for (MultipartFile file : files) {
            if (file.getSize() <= 0) {
                continue;
            }
            final String newFileName = FileUtils.save(file, "");
            final String msg = String.format("uploaded file %s, and new filename is %s%n", file.getOriginalFilename(), newFileName);
            sb.append(msg);
        }
        model.addAttribute("msg", sb.toString());

        return "upload/index";
    }




}
