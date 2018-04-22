/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrok.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wrok.thread.MyRun;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/bigPipe")
public class Less9BigPipeController {
    

    @RequestMapping(path = "index",method = RequestMethod.GET)
    public void  bigPipe(HttpServletRequest req,HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            MyRun r1 = new MyRun(out);
            MyRun r2 = new MyRun(out);
            
            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);
            
            t1.start();
            t2.start();
            
            t1.join();
            t2.join();
            out.print("-------------------主线程执行完毕---------------------");
        }
        
        
    }   
}
