package com.archer.mirror.mirrorservice.controller.server;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {


    @GetMapping("/connect")
    public String connect() {
        return "success";
    }

}
