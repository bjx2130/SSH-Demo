package com.wrok.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import com.wrok.bean.Module;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wrok.service.ISecutityService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/security")
public class Less11SecurityController {
	private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private ISecutityService securityService; 
	
//	@Value("#{controller['profileStr']}")
//	private String profileStr;
//@AuthenticationPrincipal CustomUser customUser



	@RequestMapping("/welcome")
	public String welcome(){
		return "sec/welcome";
	}

	@RequestMapping("/test")
	public void name() {
		Collection< GrantedAuthority> collection = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		Iterator<GrantedAuthority> iterator=collection.iterator();
		while(iterator.hasNext()){
			GrantedAuthority authority=iterator.next();
			log.info("------------------------------------------");
			log.info("===========！"+authority.getAuthority());
		}

		log.info("前>>>>>>>>>> 认证授权===========！");
		securityService.add();
		log.info("后>>>>>>>>>> 认证授权===========！"+securityService);
	}
	
	
	@RequestMapping("/test2")
	public void name2() {
		log.info("测试一下spring 认证授权=========profile==！");
		securityService.injectTest2();
		log.info("后>>>>>>>>>> 认证授权===========！"+securityService);
	}


	@RequestMapping(value = "/getAuthen",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Module> getAuthen(HttpServletRequest httpServletRequest){
		boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

		Authentication auth = (Authentication) httpServletRequest.getUserPrincipal();
		log.info(isAdmin+"---severlet ------ >  {}", ReflectionToStringBuilder.toString(auth));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("SecurityContext ------ >  {}", ReflectionToStringBuilder.toString(authentication));

		return null;
	}
}
