package com.wrok.controller;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.wrok.bean.User;

@RestController
@RequestMapping("/json")
public class Less6JacksonController {
	private static final Logger log = LogManager.getLogger();	
	
    /**
     * 使用@JsonView注解对象属性
     * @return 
     */
    @RequestMapping(path = "/user", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @JsonView(User.WithoutPasswordView.class)
    public User getUser() {
        return new User("eric", "7!jd#h23");
    }
    
    
    @RequestMapping(path = "/asyn",method=RequestMethod.POST)
    public Callable<String> processUpload() {
        
        return new Callable<String>() {
            public String call() throws Exception {

                return "someView";
            }
        };

    }
    
	
}



