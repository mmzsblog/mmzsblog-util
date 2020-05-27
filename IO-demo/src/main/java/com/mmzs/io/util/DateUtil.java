package com.mmzs.io.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：mmzsit
 * @description：时间工具类
 * @date ：2018/12/29 16:46
 */
public class DateUtil {

    private static SimpleDateFormat sdf;

    /**
     * description: 获取当前时间
     * author: mmzsit
     * date: 2018/12/29 16:49
     *
     * @return 指定格式的时间
     * @Param: 指定格式
     */
    public static String getCurrentDate(String pattern) {
        sdf = new SimpleDateFormat(pattern);
        String curDate = sdf.format(new Date());
        return curDate;
    }
}
