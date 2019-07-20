package com.vio.demo.helloQuartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("/////////////////start///////////////////");
        Date date =new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间"+sf.format(date));
        JobKey key=context.getJobDetail().getKey();
        System.out.println("所属名组"+key.getName()+":"+key.getGroup());
        TriggerKey triggerKey=context.getTrigger().getKey();
        System.out.println("所属名组"+triggerKey.getName()+":"+triggerKey.getGroup());
        //分别获取jobDetail和trigger的JobdataMap
        JobDataMap dataMap=context.getJobDetail().getJobDataMap();
        JobDataMap tdataMap=context.getTrigger().getJobDataMap();
        //合并获取，trigger覆盖同名
        JobDataMap merge=context.getMergedJobDataMap();
        String jobMsg=dataMap.getString("message");
        String triggerMsg=tdataMap.getString("message");
        System.out.println("JobMsg is"+jobMsg);
        System.out.println("triggerMsg is"+triggerMsg);
        System.out.println("/////////////////stop///////////////////");
    }
}
