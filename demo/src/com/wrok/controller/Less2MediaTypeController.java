package com.wrok.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wrok.bean.Pet;

@Controller
@RequestMapping("/media")
public class Less2MediaTypeController {
	private static final Logger log = LogManager.getLogger();	
	//也可使用MediaType.APPLICATION_JSON指定类型


	//consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	@RequestMapping(path = "/pets", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void addPet(@RequestBody Pet pet, Model model) {
	    log.info("-------------"+pet);
	}
	

	
	//params： 指定request中必须包含某些参数值是，才让该方法处理。
	//GET http://localhost:8080/spring/media/pets2/2?p1=3
    @RequestMapping(path = "/pets2/{petId}", method = RequestMethod.GET, params="p1=3")
    public void findPet(@PathVariable String petId, Model model) {
        // implementation omitted
    	log.info("-------------"+petId);
    }
	
    
    //仅处理request的header中包含了指定“Refer”请求头和对应值为“http://www.ifeng.com/”的请求；
    @RequestMapping(path = "/pets2", method = RequestMethod.GET, headers="Referer=http://www.ifeng.com/")
    public void findPet2(Model model) {
        // implementation omitted
    }
}
