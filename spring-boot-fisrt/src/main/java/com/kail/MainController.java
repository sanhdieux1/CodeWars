package com.kail;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/hello")
    @Authentication(anyPermission = {"ADMIN", "USER"})
    public Object sayHello(String name){
        return "Hello ".concat(name);
    }
}
