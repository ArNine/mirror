package com.archer.mirror.mirrorservice.controller.file;

import com.archer.mirror.mirrorservice.common.Response;
import com.archer.mirror.mirrorservice.service.FileProcessService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileUploadController {


    @Resource
    private FileProcessService fileService;

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Response<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) throws Exception {
        // upload file
        String fileName = fileService.uploadFile(file);
        // read top 20 data
        Map<String, Object> result = fileService.readFile(fileName);

        return Response.success(result);
    }

}
