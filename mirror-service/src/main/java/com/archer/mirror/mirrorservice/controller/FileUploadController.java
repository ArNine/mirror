package com.archer.mirror.mirrorservice.controller;

import com.archer.mirror.mirrorservice.bean.DataCompareMeta;
import com.archer.mirror.mirrorservice.common.Result;
import com.archer.mirror.mirrorservice.service.FileProcessService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

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


    @PostMapping("/test/{name}")
    public Result<String> test(@RequestParam String id, @PathVariable String name, @RequestBody Map<String, String> map, @CookieValue(name = "myCookies") String cookies) {
        System.out.println(name);
        System.out.println(map);
        System.out.println(cookies);
        return Result.success(id);
    }




}
