package com.kail.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @RequestMapping("/authen")
    public Object testAuthen(){
        return "inside";
    }

    @RequestMapping("/noauthen")
    public Object testNoAuthen(){
        return "inside";
    }
}
