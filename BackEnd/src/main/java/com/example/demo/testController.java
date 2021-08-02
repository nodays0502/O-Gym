package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class testController {

    @GetMapping(value = "/")
    //@RequestMapping(value="/",method=RequestMethod.GET)
    public String hello() {
        return "Hello World!!";
    }
}
