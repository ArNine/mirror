package com.archer.mirror.mirrorservice.controller.compare;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/compare")
public class CompareController {

    @PostMapping("/file")
    public String fileCompare(@RequestParam String baseFileName, @RequestParam String compareFileName, @RequestParam List<String> primary) {

        return null;
    }



}
