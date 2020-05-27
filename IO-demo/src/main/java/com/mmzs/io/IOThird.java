package com.mmzs.io;

import com.mmzs.io.util.DateUtil;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：mmzsit
 * @description：测试3
 * @date ：2018/12/28 13:10
 */
public class IOThird {

    /**
     * description: 此种方式生成的文件末尾会多一个\t
     * 此种方式更适用于大文件之间的读写，拷贝
     * author: mmzsit
     * date: 2018/12/27 17:41
     */
    public static void main(String[] args) {

        List<String> list = new ArrayList();
        list.add("hello");
        list.add("third");
        list.add("method");
        list.add("io");
        list.add("util");
        OutputStream os = null;
        File filePath = new File("d:\\" + DateUtil.getCurrentDate("yyyyMMdd") + ".txt");

        try {
            os = new FileOutputStream(filePath, true);
            //一行中的字段用tab隔开
            IOUtils.writeLines(list,"\t",os);
            //行与行之间用回车隔开
            IOUtils.write("\n", os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
