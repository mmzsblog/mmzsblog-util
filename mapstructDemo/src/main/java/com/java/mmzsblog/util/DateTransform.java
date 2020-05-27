package com.java.mmzsblog.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ：liuhongjiang
 * @description：时间格式转换类
 * @date ：2020/4/1 0001 10:47
 */
public class DateTransform {
    public static LocalDateTime strToDate(String str){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse("2018-01-12 17:07:05",df);
        System.out.println("String类型的时间转成LocalDateTime："+ldt);
        return ldt;
    }

}
