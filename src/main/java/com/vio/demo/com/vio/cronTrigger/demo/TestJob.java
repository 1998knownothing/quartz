package com.vio.demo.com.vio.cronTrigger.demo;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {


        Date date =new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间:  "+sf.format(date));


    }
}
