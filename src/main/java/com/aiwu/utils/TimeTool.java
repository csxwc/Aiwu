package com.aiwu.utils;

import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class TimeTool {

    //获取时间  返回毫秒级时间
    public String getTime() {
        System.out.println("getTime...util...");

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Long date = calendar.getTime().getTime();            //获取毫秒时间

        //String dateStringPaString = sdf.format(date);
        //System.out.println(dateStringPaString);

        return date.toString();
    }

    public boolean cmpTime(String time) {
        System.out.println("cmpTime...util...");
        long tempTime = Long.parseLong(time);
        System.out.println("tempTime"+tempTime);

        //在获取现在的时间
        Calendar calendar = Calendar.getInstance();
        Long date = calendar.getTime().getTime();            //获取毫秒时间
        System.out.println("date"+date);

        if(date - tempTime > 600000 ) {   //10分钟
            return false;
        } else {
            return true;
        }

    }

}
