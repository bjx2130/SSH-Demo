package com.wrok.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.wrok.bean.Pet;

/**
 * 
 * @author Administrator
 *
 * @RestController 等同于 [@ResponseBody+@Controller]
 *
 */
@Controller
@RequestMapping("/reset")
public class Less4MessageCvnController {
	private static final Logger log = LogManager.getLogger();	
	
	/**
         * 如果Controller中所有的方法返回结果都需要MassageConverter转换
         * 就可以使用 @RestController   (@RestController =@ResponseBody + @Controller)
	 	 * [@RestController 或 @ControllerAdvice] 必须和@RequestMapping(path = "/reset")同时使用否则扫描不到
         * 
	 * 访问 @RequestBody  通过指定 consumes="application/json" 类型寻找对应的类型转换器--->读取信息
	 * 访问 @ResponseBody 通过指定produces="image/jpeg"类型寻找对应的类型转换器--->写出信息
         * HttpEntity<?> 类似于 @RequestBody 
         * ResponseEntity<?> 类似于 @ResponseBody
         * 
         * String s = HttpClient.postJson("http://localhost:8084/spring/reset/reqbody","{\"name\":\"小动物\",\"age\":2}");
	 */
	@RequestMapping(path = "/reqbody", consumes="application/json", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Pet reqbody(@RequestBody Pet pet) throws IOException { 
		log.info(ReflectionToStringBuilder.toString(pet));
                return pet;
	}

	//produces:指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
	@RequestMapping(path = "/pets/{petId}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Pet getPet(@PathVariable String petId, Model model) {
		// implementation omitted
		Pet pet =new Pet();
		pet.setAge(2);
		pet.setName("小动物");
		return pet;
	}

	
	@RequestMapping(path = "/rspImg" ,produces="image/jpeg")
	public @ResponseBody BufferedImage rspImg(@RequestHeader("Accept")String accept) throws IOException {	
		log.info("读取图片:"+accept);
		BufferedImage bufImage = ImageIO.read(new File("F:\\img\\red.jpg"));
	    return bufImage;
	}
        
        	
	
        /**
         * String s = HttpClient.postJson("http://localhost:8084/spring/reset/entity","{\"name\":\"小动物\",\"age\":2}");
         * @param reqEntity
         * @return
         * @throws UnsupportedEncodingException 
         */
	@RequestMapping(path = "/entity",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pet> handle(HttpEntity<Pet> reqEntity) throws UnsupportedEncodingException {
	    String requestHeader = reqEntity.getHeaders().getFirst("Content-Type");
	    Pet pet = reqEntity.getBody();
            log.info("requestHeader:{}",requestHeader);
            log.info("requestBody:{} {}",pet.getAge() , pet.getName());

	    HttpHeaders responseHeaders = new HttpHeaders();
	    return new ResponseEntity<Pet>(pet, responseHeaders, HttpStatus.CREATED);
	}
	

}
