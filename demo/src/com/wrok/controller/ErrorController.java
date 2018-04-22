package com.wrok.controller;

import com.wrok.bean.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 		全局异常处理类：
 * 				声明一个全局异常处理器
 *				<bean class="org.springsecurity.custom.exception.GolobFulHandlerExceptionResolver"/>
 * 				@ControllerAdvice必须和@RequestMapping(path = "/exp")同时使用否则扫描不到
 */
@ControllerAdvice
@RequestMapping(path = "/exp")
public class ErrorController {
	private static final Logger log = LogManager.getLogger();

	/**
	 * 此方法需要在web.xml中的<error-page> <location>/exp/error</location></error-page> 配置
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> handle(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", request.getAttribute("javax.servlet.error.status_code"));
		map.put("reason", request.getAttribute("javax.servlet.error.message"));
		return map;
	}

	@RequestMapping(path = "test",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public UserInfo test(HttpServletRequest request) {
		if(true)throw new RuntimeException("抛出一个一场信息");

		UserInfo u = new UserInfo();
		u.setUsername("报价新");
		u.setRemark("注释");
		u.setCreateDate(new Date());
		return u;
	}

}
