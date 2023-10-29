package com.archer.mirror.mirrorservice.service;

import com.archer.mirror.mirrorservice.bean.DataCompareMeta;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.Future;

public interface FileProcessService {

    DataCompareMeta uploadFile(MultipartFile[] files, Model model);
    Future<String> sendToMirror() throws InterruptedException;
}
