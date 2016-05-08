package com.sf.threadtest.unit4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by CodeMonkey on 2016/4/10.
 */
public class TimerTest {

    public static void main(String[] args) throws ParseException {


        Timer timer = new Timer();
        String dateFormat = "yyyy-MM-dd HH-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date date1 = simpleDateFormat.parse("2016-4-10 15-21-20");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("nihao zhangsan");
            }
        };
        timer.schedule(timerTask, date1, 1000);

    }
}


