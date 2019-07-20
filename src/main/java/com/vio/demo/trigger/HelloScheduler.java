package com.vio.demo.trigger;

import com.vio.demo.trigger.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloScheduler {

    public static void main(String[] args) throws SchedulerException {
        //创建JobDetail实例与helloJob类绑定
        JobDetail jobDetail= JobBuilder.newJob(HelloJob.class)
                .withIdentity("HelloJob","group1").build();
        //通过使用usingJobData方法类似于map使用，可添加一些必要参数
        jobDetail.getKey().getName();
        //获取距离当前时间3秒后的时间
        Date startData=new Date();
        startData.setTime(startData.getTime()+3000);
        //获取距离当前时间6秒后的时间
        Date endDate=new Date();
        endDate.setTime(endDate.getTime()+6000);

        //创建一个Trigger实例，定义该job立即执行，没两秒重复执行一次，直到永远
        Trigger trigger= TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","group1")
                .startAt(startData)
                .endAt(endDate)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2).repeatForever()
                ).build();
        //创建Scheduler实例
        SchedulerFactory factory=new StdSchedulerFactory();
        Scheduler scheduler=factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
