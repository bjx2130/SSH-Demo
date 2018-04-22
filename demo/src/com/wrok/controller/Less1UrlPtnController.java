package com.wrok.controller;

import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


	
@Controller
@RequestMapping("/urltmp")
public class Less1UrlPtnController {
	private static final Logger log = LogManager.getLogger();	
	
	public Less1UrlPtnController(){
		log.info("创建一个实例  Less1UrlPtnController");
	}
	
    //普通路径
    @RequestMapping(path = "/{day}/{ownerId}", method = RequestMethod.GET)	
    public void helloWorld(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date day,
    						 @PathVariable String ownerId,//@PathVariable String ownerId ==@PathVariable("ownerId") String ownerId
    						 BindingResult result) {
        System.out.println("-----------------------");
    }
    
    
    //URI Template Patterns with Regular Expressions 正则表达式匹配
    @RequestMapping("/spring-web/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
    public void handle(@PathVariable String version, @PathVariable String extension) {
        
    }
    
    //Path Patterns with Placeholders 占位符匹配
    @RequestMapping("/spring-web/${urlPath}")
    public void urlPalaceHoders(@PathVariable String version, @PathVariable String extension) {
        
    }
    
    /**
     * 1.使用 Matrix Variables映射路径时将 设置属性    enable-matrix-variables="true" 默认值false-----------------------
     * <mvc:annotation-driven enable-matrix-variables="true" />
     * 2.还需要加入 xsi:schemaLocation = http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
     * 
     */
    // GET /pets/42;q=11;r=22 		RETURN petId=42  q=11  r=22
    @RequestMapping(path = "/pets/{petId}", method = RequestMethod.GET)
    public void findPet(@PathVariable String petId, 
    					@MatrixVariable int q,
    					@MatrixVariable int r) {
    	log.info(String.format("petId=%s  q=%d  r=%d", petId,q,r));

    }
    
    
    // GET /owners/42;q=11/pets/21;q=22     RETURN q1=11  q2=22
    @RequestMapping(path = "/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
    public void findPet(
            @MatrixVariable(name="q", pathVar="ownerId") int q1,
            @MatrixVariable(name="q", pathVar="petId") int q2) {
    	log.info(String.format("q1=%d  q2=%d", q1,q2));
    }
    
    
    // GET /pets2/42     	RETURN	q=1(返回默认值)
    // GET /pets2/42;q=2     RETURN	q=2
    @RequestMapping(path = "/pets2/{petId}", method = RequestMethod.GET)
    public void findPet(@MatrixVariable(required=false, defaultValue="1") int q) {
        // q == 1
    	log.info(String.format("q=%d", q));
    }
    
    
    
    // GET /owners2/42;q=11;r=12/pets/21;q=22;s=23
    @RequestMapping(path = "/owners2/{ownerId}/pets/{petId}", method = RequestMethod.GET)
    public void findPet(
            @MatrixVariable Map<String, String> matrixVars,
            @MatrixVariable(pathVar="ownerId") Map<String, String> OwnerMatrixVars,
            @MatrixVariable(pathVar="petId") Map<String, String> petMatrixVars) {
    	
    	log.info(String.format("----------不指定pathVar则读取url中所有 [key=vale] 的参数----------"));
    	for (String key : matrixVars.keySet()) {
			log.info(String.format("matrixVars集合key：%s   value:%s", key ,matrixVars.get(key)));
		}
    	
    	log.info(String.format("----------指定pathVar=\"ownerId\"则只读取url中 ownerId 后的 [key=vale] 的参数----------"));
    	for (String key : OwnerMatrixVars.keySet()) {
			log.info(String.format("petMatrixVars集合key：%s   value:%s", key,OwnerMatrixVars.get(key)));
		}
    	
    	log.info(String.format("----------指定pathVar=\"petId\"则只读取url中 petId 后的 [key=vale] 的参数----------"));
    	for (String key : petMatrixVars.keySet()) {
			log.info(String.format("petMatrixVars集合key：%s   value:%s", key,petMatrixVars.get(key)));
		}

    }
    
    
}
