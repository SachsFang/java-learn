package 后端.常用实体类;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by SachsFang on 2021/7/12 15:55
 */
public class DateDemo {
    public static void main(String[] args) {
        Date time = new Date();
        CalendarDemo(time);
        formatTime(time);
    }

    public static void CalendarDemo(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        System.out.println("NowTime:" + year + "." + month + "." + day + " " + hour + ":" + minute + ":" + second);
    }

    public static void formatTime(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        System.out.println(formatter.format(time));
    }
}
