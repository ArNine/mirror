package com.archer.mirror.mirrorservice.controller.server;


import com.archer.mirror.mirrorservice.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {


    @GetMapping("/connect")
    public Response<String> connect() {
        return Response.success("success");
    }

}
