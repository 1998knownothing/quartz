import com.vio.demo.helloQuartz.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class Test {


    public static void main(String[] args) throws SchedulerException {

        JobDetail jobDetail= JobBuilder.newJob(HelloJob.class)
                .withIdentity("hellojob","1").build();
        Date start=new Date();
        start.setTime(start.getTime()+2000);
        CronTrigger cronTrigger= (CronTrigger) TriggerBuilder.newTrigger()
                .withIdentity("hellotrigger","2")
                .startAt(start)
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("* * * * * ?")
                ).build();//漏加build 和 表达式错误

        SchedulerFactory schedulerFactory=new StdSchedulerFactory();

        Scheduler scheduler=schedulerFactory.getScheduler();

        scheduler.start();

        scheduler.scheduleJob(jobDetail,cronTrigger);





    }


}
