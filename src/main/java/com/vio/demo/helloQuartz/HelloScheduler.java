package com.vio.demo.helloQuartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloScheduler {


    public static void main(String[] args) throws SchedulerException{
        //创建JobDetail实例与helloJob类绑定
        JobDetail jobDetail= JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob","group1")
                .usingJobData("message","hahaha").build();
        //通过使用usingJobData方法类似于map使用，可添加一些必要参数
        jobDetail.getKey().getName();

        //创建一个Trigger实例，定义该job立即执行，没两秒重复执行一次，直到永远
        Trigger trigger= TriggerBuilder.newTrigger()
                .usingJobData("message","triggerxixi")
                .withIdentity("myTrigger","group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).repeatForever()
                ).build();
        //创建Scheduler实例
        SchedulerFactory factory=new StdSchedulerFactory();
        Scheduler scheduler=factory.getScheduler();
        scheduler.start();
        Date date =new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间"+sf.format(date));
        scheduler.scheduleJob(jobDetail,trigger);
    }

}
