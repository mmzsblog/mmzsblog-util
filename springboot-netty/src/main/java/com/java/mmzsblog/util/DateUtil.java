package com.java.mmzsblog.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：created by mmzsblog.cn
 * @description：日期工具类
 * @date ：created at 2021/04/13 10:26
 */

public class DateUtil {
    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format  格式如果null，则按照yyyy-MM-dd HH:mm:ss格式化
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {

        if (seconds.length() > 10) {
            seconds = seconds.substring(0, 10);
        }

        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳(秒级)
     *
     * @param dateStr 字符串日期
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static String timeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    /**
     * 获取默认的时间转换
     *
     * @param timeStamp
     * @return
     */
    public static String date2TimeStampDefault(String timeStamp) {
        return DateUtil.timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 计算开始时间和结束时间的时间差：s
     *
     * @param startTime 时间戳，如：1470278082
     * @param endTime   时间戳，如：1470278111
     * @return
     */
    public static Long countTime(String startTime, String endTime) {
        long second = Long.valueOf(endTime)-Long.valueOf(startTime);
        System.out.println("时间相差是："+second+"毫秒");
        return Long.valueOf(Math.toIntExact(Math.abs(second)/1000));
    }

    public static Long countTimeMinute(Long startTime, Long endTime) {
        long minute = endTime-startTime;
        System.out.println("时间相差是："+minute+"分");
        return Long.valueOf(Math.toIntExact(Math.abs(minute/60000)));
    }

    //当天开始时间毫秒值
    public static Long nowTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();

    }

    /**
     * 将给定Date，转化成指定格式日期字符串
     *
     * @param formater
     * @param date
     * @return
     */
    public static String date2FormatString(Date date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(date);
    }

    /**
     * 返回上一月
     * @return
     */
    public static String lastMonth(String formater) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        return date2FormatString(calendar.getTime(),formater);
    }

    public static void main(String[] args) {
//        String timeStamp = timeStamp();
//        System.out.println("timeStamp=" + timeStamp); //运行输出:timeStamp=1470278082
//        System.out.println(System.currentTimeMillis());//运行输出:1470278082980
//        //该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数
//
//        String date = timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");
//        System.out.println("date=" + date);//运行输出:date=2016-08-04 10:34:42
//
//        String timeStamp2 = date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");
//        System.out.println(timeStamp2);  //运行输出:1470278082

        System.out.println(countTime("1587465159000", "1587466146000"));//运行输出:200
        System.out.println(countTime("1587465146100", "1587465146000"));//运行输出:200

        System.out.println(DateUtil.date2FormatString(new Date(),"yyyyMM"));


//        System.out.println(date2FormatString(new Date(), "yyyy.MM.dd"));
    }


}
