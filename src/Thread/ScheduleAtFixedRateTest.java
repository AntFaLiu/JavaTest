package Thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

//scheduleAtFixedRate（task，time，period）
// task-所要安排的任务 time-首次执行任务的时间 period-执行一次task的时间间隔，单位毫秒
// 作用：时间等于或超过time首次执行task，之后每隔period毫秒重复执行task
public class ScheduleAtFixedRateTest extends TimerTask {
    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        System.out.println("Current Time："+format.format(calendar.getTime()));//获取当前系统时间
        System.out.println("NO.1");
    }

    public static void main(String[] args) {
        ScheduleAtFixedRateTest task = new ScheduleAtFixedRateTest();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        System.out.println(format.format(calendar.getTime()));
        calendar.add(Calendar.SECOND,3);//获取距离当前时间3秒后的时间
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,calendar.getTime(),2000);
    }
}

//scheduleAtFixedRate(task, delay,period)
//task-所要执行的任务 delay-执行任务的延迟时间，单位毫秒 period-执行一次task的时间间隔
class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        System.out.println("Current Time：" + format.format(calendar.getTime()));//获取当前系统时间
        System.out.println("NO.1");
    }

    public static void main(String[] args) {
        MyTimerTask task = new MyTimerTask();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        System.out.println(format.format(calendar.getTime()));
        calendar.add(Calendar.SECOND, 3);//获取距离当前时间3秒后的时间
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,calendar.getTime(),2000);
        //timer.scheduleAtFixedRate(task, 1000, 2000);
    }
}