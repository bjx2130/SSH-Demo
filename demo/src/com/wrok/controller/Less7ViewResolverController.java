package com.wrok.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wrok.bean.Pet;

@Controller
@RequestMapping("/view")
public class Less7ViewResolverController {
	private static final Logger log = LogManager.getLogger();
    
    /**
     * http://localhost:8084/spring/view/user/json
     * @return {"pet":{"name":"小动物","age":2}}
     * 
     * 需要配置视图解析器
     */
    @RequestMapping(path = "/user/json", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Pet getUser() {
        Map<String,String> m=new HashMap<String,String>();
        Pet pet =new Pet();
        pet.setAge(2);
        pet.setName("小动物");
        return pet;
    }


    
    @RequestMapping(path = "/user/xml", method = RequestMethod.GET, produces=MediaType.TEXT_XML_VALUE)
    public Pet getXMl() {
        Pet pet =new Pet();
        pet.setAge(2);
        pet.setName("小动物");
        return pet;
    }
}



