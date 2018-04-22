/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrok.thread;

import java.io.PrintWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MyRun implements Runnable{
    
    private PrintWriter wt;

    public MyRun( PrintWriter wt){
        this.wt=wt;
    }
    
    @Override
    public  void run() {
                try{
                    ByteBuffer buf = ByteBuffer.allocate(100);
                    
                    
                    for(int i=0;i<10;i++){
                            Thread.sleep(new Random().nextInt(1000));
                                wt.println(("$('#pie1').append('"+"我是   "+Thread.currentThread().getName()+"')<br>"));
                                wt.flush();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
    }
  
}
