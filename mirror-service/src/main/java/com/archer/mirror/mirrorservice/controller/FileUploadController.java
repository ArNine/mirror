package com.archer.mirror.mirrorservice.controller;

import com.archer.mirror.mirrorservice.bean.DataCompareMeta;
import com.archer.mirror.mirrorservice.common.Result;
import com.archer.mirror.mirrorservice.service.FileProcess;
import com.archer.mirror.mirrorservice.service.FileProcessService;
import com.archer.mirror.mirrorservice.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class FileUploadController {


    @Resource
    private FileProcessService fileService;

    /**
     *
     * @param files
     * @param model
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<DataCompareMeta> upload(@RequestParam("file") MultipartFile[] files, Model model) throws IOException {
        return Result.success(fileService.uploadFile(files, model));
    }




}
