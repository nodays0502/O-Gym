package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://ec2-13-124-95-248.ap-northeast-2.compute.amazonaws.com:32286")
@RestController
public class testController {

    @GetMapping(value = "/")
    public String hello() {
        return "Hello World!!";
    }
}
