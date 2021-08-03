package com.example.demo;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class testController {

    @CrossOrigin("*")
    @GetMapping(value = "/hello")
    public String hello(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        return "Hello World!!";
    }

//    @CrossOrigin("*")
//    @GetMapping(value = "/")
//    public String proxyHello() {
//        String url = "http://ec2-13-124-95-248.ap-northeast-2.compute.amazonaws.com:30044/hello";
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(url, String.class);
//    }
}
