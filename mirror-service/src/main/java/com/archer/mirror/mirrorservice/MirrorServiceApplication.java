package com.archer.mirror.mirrorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MirrorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MirrorServiceApplication.class, args);
    }

}
