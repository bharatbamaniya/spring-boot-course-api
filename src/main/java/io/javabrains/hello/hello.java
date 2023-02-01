package io.javabrains.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {

    @Value("${spring.application.name}")
    private String appName;
//    @Value("${spring.application.version}")
//    private String version;
//    @Value("${spring.application.description}")
//    private String description;


    @GetMapping("/")
    public String hello(){
        return "Application Name: " + appName;
//                "version: " + version +
//                "description: " + description;
    }
}
