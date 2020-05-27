package com.mmzs.io;

import com.mmzs.io.util.DateUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * description: 测试1
 */
public class IOFirst {

    /**
     * description: 最复杂，但也是比较考验基本功的写法
     * author: mmzsit
     * date: 2018/12/27 17:45
    */
    public static void main(String[] args)
    {
        File log=new File("d:\\"+DateUtil.getCurrentDate("yyyyMMdd") +".txt");
        List<String> list = new ArrayList();
        list.add("hello");
        list.add("first");
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
        String newLog = sb.deleteCharAt(sb.length()-1).toString();
        //调用appendLog方法执行文件写入操作
        appendLog(log,newLog);
    }

    /**
     * description: 此种方式是自己写的类，想怎么操作按自己的意思来
     * author: mmzsit
     * date: 2018/12/27 17:42
    */
    public static void appendLog(File filePath,String newTxt)
    {
        Scanner sc=null;
        PrintWriter pw=null;
        try{
            isExists(filePath);
            sc=new Scanner(filePath);
            StringBuilder sb=new StringBuilder();
            //先读出旧文件内容,并暂存sb中;
            while(sc.hasNextLine())
            {
                sb.append(sc.nextLine());
                //换行符作为间隔,扫描器读不出来,因此要自己添加.
                sb.append("\t\n");
            }
            if (0 != sb.length()) {
                //解决每次多余的空行
                sb.deleteCharAt(sb.length()-1);
            }
            sc.close();

            pw=new PrintWriter(new  FileWriter(filePath),true);
            //A、写入旧文件内容.
            pw.println(sb.toString());
            //B、写入新文件内容
            pw.println(newTxt);
            /*
             * 如果先写入A,最近写入在文件最后.
             * 如是先写入B,最近写入在文件最前.
             */
            pw.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * description: 保证文件夹的存在
     * author: mmzsit
     * date: 2018/12/27 17:42
    */
    public static void isExists(File filePath){
        //如果文件不存在,则新建.
        if(!filePath.exists())
        {
            File parentDir=new File(filePath.getParent());
            //如果所在目录不存在,则新建.
            if(!parentDir.exists()) {
                parentDir.mkdirs();
            }
            try {
                filePath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

