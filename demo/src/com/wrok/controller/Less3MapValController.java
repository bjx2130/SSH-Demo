package com.wrok.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wrok.bean.Pet;

@Controller
@RequestMapping("/mapVal")
public class Less3MapValController {
	private static final Logger log = LogManager.getLogger();	
	
	
	/**
	 *  Host                    localhost:8080
		Accept                  text/html,application/xhtml+xml,application/xml;q=0.9
		Accept-Language         fr,en-gb;q=0.7,en;q=0.3
		Accept-Encoding         gzip,deflate
		Accept-Charset          ISO-8859-1,utf-8;q=0.7,*;q=0.7
		Keep-Alive              300
	 * @param cookie
	 * @param encoding
	 * @return
	 */
	@RequestMapping(path = "/cv")
	public Pet cv(@CookieValue("JSESSIONID") String cookie,
			@RequestHeader("Accept-Encoding") String encoding) {
		return null;
	}	
	


}



