package com.vio.demo.com.vio.simpleTrigger.demo;

import com.vio.demo.trigger.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class SimpleTriggerUse {

    public static void main(String[] args) throws SchedulerException {
        //创建JobDetail实例与helloJob类绑定
        JobDetail jobDetail= JobBuilder.newJob(TestJob.class)
                .withIdentity("HelloJob","group1").build();

        //获取距离当前4秒之后的具体时间
        Date date=new Date();
        date.setTime(date.getTime()+4000L);
        //创建一个Trigger实例，定义该job立即执行，没两秒重复执行一次，直到永远
        //距离当前时间4秒钟后执行且仅执行一次
        //距离当前时间4秒后2首次执行，之后每隔两秒执行一次添加withSchedule
        SimpleTrigger trigger= (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","group1")
                .startAt(date)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(3)//4秒后执行一次，再每间隔两秒执行一次总计三次为止
                )
               .build();
        //创建Scheduler实例
        SchedulerFactory factory=new StdSchedulerFactory();
        Scheduler scheduler=factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
