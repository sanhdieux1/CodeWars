package com.kail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private MyHandler myHandler;

    @RequestMapping("/test")
    public Object test(){
        return myHandler.exampleClientAPI(AccessActivityLoggingService.class).getMessage();
    }

}
