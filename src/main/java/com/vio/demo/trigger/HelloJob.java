package com.vio.demo.trigger;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {

        Date date =new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间"+sf.format(date));

      Trigger currentTrigger=context.getTrigger();
        System.out.println("start time is： "+sf.format(currentTrigger.getStartTime()));
        System.out.println("end time is ："+sf.format(currentTrigger.getEndTime()));
        JobKey jobKey=currentTrigger.getJobKey();
        System.out.println("job name:"+jobKey.getName());
        System.out.println("/////////////////stop///////////////////");
    }
}
