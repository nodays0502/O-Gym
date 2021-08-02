package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class testController {

    @CrossOrigin("*")
    @GetMapping(value = "/")
    public String hello() {
        return "Hello World!!";
    }
}
