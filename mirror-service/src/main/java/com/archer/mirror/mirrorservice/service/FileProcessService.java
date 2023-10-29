package com.archer.mirror.mirrorservice.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface FileProcessService {

    String uploadFile(MultipartFile[] files, Model model);

}
