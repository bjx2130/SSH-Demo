package com.wrok.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wrok.bean.Pet;

@Controller
@RequestMapping("/bind")
@SessionAttributes
public class Less5BindingController {
	private static final Logger log = LogManager.getLogger();	
	

	//http://localhost:8084/spring/bind/setsess?age=2&name=dog
	@RequestMapping(path = "/setsess", method = RequestMethod.GET)
	public String setsess(@ModelAttribute Pet pet) {
		log.info("----3---age:"+pet.getAge()+"    name:"+pet.getName());
		return null;
	}	
	
	//http://localhost:8084/spring/bind/getsess
	@RequestMapping(path = "/getsess", method = RequestMethod.GET)
	public String getsess(@ModelAttribute("pet") Pet pet) {
		log.info("----1---age:"+pet.getAge()+"    name:"+pet.getName());
		return null;
	}

	
}



