package com.java.mmzsit.framework.mybatisStrategy.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：mmzsit
 * @description：
 * @date ：2019/6/13 10:32
 */
public class DateUtil {
    private static SimpleDateFormat sdf;

    /**
     * 获取当前时间yyyyMMddHHmm
     */
    public static String getCurrentDate(String pattern) {
        sdf = new SimpleDateFormat(pattern);
        String curDate = sdf.format(new Date());
        return curDate;
    }

    /**
     * 获取昨天的时间yyyyMMdd
     */
    public static String getLastDate() {
        sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(new Date()); //设置时间为当前时间
        ca.add(Calendar.DATE, -1); //年份减1
        Date lastDay = ca.getTime(); //结果
        return sdf.format(lastDay);
    }

    /**
     * 获取当前时间
     */
    public static Date getCurrentDateAndTime() {
        sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String curDate = sdf.format(new Date());
        try {
            return sdf.parse(curDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间并转化为类似20190601的格式
     * @param dataStr
     * @return
     * @throws ParseException
     */
    public static String get_MM01Str(String dataStr) throws ParseException {
        return getStrByDateFormat("yyyyMM01", getDatebyFormaterStr("yyyyMMdd", dataStr));
    }
    public static Date getDatebyFormaterStr(String formater, String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.parse(dateStr);
    }

    public static String getStrByDateFormat(String formater, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(date);
    }


    /**
     * 测试
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {

        String currentTime = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm");
        System.out.println("current:" + currentTime);
        String currentDate = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        System.out.println("currenDate:" + currentDate);
    }
}
