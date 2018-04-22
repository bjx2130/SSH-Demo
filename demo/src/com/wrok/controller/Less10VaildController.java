package com.wrok.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wrok.bean.Pet;

/**
 * 使用spring-hibernate的表单验证框架
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/vaild")
public class Less10VaildController {
	private static final Logger log = LogManager.getLogger();	
	
	@RequestMapping("/pet")
	public void name(@Valid Pet pet,Errors errors) {
		if (errors.hasErrors()) {
			List<ObjectError> errorList = errors.getAllErrors();             
	        for(ObjectError error : errorList){                 
	            System.out.println(error.getDefaultMessage());             
	        } 
		}
		
		log.info("---------------"+errors.hasErrors());
		log.info(String.format("%s  ----------> %s",pet.getAge(),pet.getName()));
		
	}
	
}
