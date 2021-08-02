package com.example.demo;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class testController {

    @CrossOrigin("*")
    @GetMapping(value = "/")
    public String hello(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        return "Hello World!!";
    }
}
