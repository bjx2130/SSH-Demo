package com.wrok.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class Less8UploadController1 {
	private static final Logger log = LogManager.getLogger();
    
    /**
     * 1.需要在web.xml中 添加 DispatcherServlet 的属性 <multipart-config></multipart-config>
     * 2.在spring文件中开启     <bean id="multipartResolver"
     *                                  class="org.springframework.web.multipart.support.StandardServletMultipartResolver">
     *                             </bean>
     * 
     * @return
     * @throws IOException 
     */
    @RequestMapping
    public String  up() throws IOException {
        return "upload";
    }   
    
    
    @RequestMapping(path = "/form1", method = RequestMethod.POST)
    public String  getUser(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            log.info("上传成功1");
	    // 将上传的文件保存到服务器文件系统
            //file.transferTo("文件保存位置File类型"); 	
            return "redirect:uploadSuccess";
        }
        return "redirect:uploadFailure";
    }
    
    
    @RequestMapping(path = "/form2", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("name") String name,
            @RequestParam("file") Part file) throws IOException {

        InputStream inputStream = file.getInputStream();
        // store bytes from uploaded file somewhere
        log.info("上传成功2"+inputStream);
        return "redirect:uploadSuccess";
    }
    
    
    
//    @RequestMapping(path = "/someUrl", method = RequestMethod.POST)
//    public String onSubmit(@RequestPart("meta-data")MetaData metadata,
//            @RequestPart("file-data") MultipartFile file) {
//
//        // ...
//
//    }
    
}



