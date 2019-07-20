package com.vio.demo.com.vio.cronTrigger.demo;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //创建JobDetail实例与helloJob类绑定
        JobDetail jobDetail= JobBuilder.newJob(TestJob.class)
                .withIdentity("HelloJob","group1").build();

        //创建一个Trigger实例，定义该job立即执行，没两秒重复执行一次，直到永远

        CronTrigger trigger= (CronTrigger) TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("* * * * * ? *")
                )
                .build();
        //创建Scheduler实例
        SchedulerFactory factory=new StdSchedulerFactory();
        Scheduler scheduler=factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);//返回值为Date类型 最近的一次即将执行时间
        Thread.sleep(5000);
        scheduler.shutdown(true);
    }
}
