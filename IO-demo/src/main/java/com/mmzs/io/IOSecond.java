package com.mmzs.io;

import com.mmzs.io.util.DateUtil;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.*;

/**
 * @description：测试1
 */
public class IOSecond {


    /**
     * description: 此种方式比较理想
     * author: mmzsit
     * date: 2018/12/27 17:41
     */
    public static void main(String[] args) {

        File filePath = new File("d:\\" + DateUtil.getCurrentDate("yyyyMMdd") + ".txt");

        //将数据保存到StringBuffer中后再存储到文件中
        List<String> list = new ArrayList();
        list.add("hello");
        list.add("second");
        list.add("method");
        list.add("io");
        list.add("util");
        //因为此处不涉及线程安全问题，所以用了StringBuilder
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            sb.append(item).append("\t");
        }
        String newTxt = sb.deleteCharAt(sb.length()-1).append("\n").toString();

        BufferedWriter bw = null;
        try {
            //true表示文件写入方式为追加；flase表示是覆盖
            bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(newTxt);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != bw) {
                try {
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
