package com.java.mmzsblog.client;

/**
 * @author ：created by mmzsblog.cn
 * @description：netty服务测试验证
 * @date ：created at 2021/04/12 16:12
 */
public class test {

    /**
     * 单条数据发送测试
     */
//    public static void main(String[] args){
//        try {
//            Socket socket=new Socket("127.0.0.1",10001);
//            OutputStream outputStream = socket.getOutputStream();
//            PrintWriter printWriter=new PrintWriter(outputStream);
//            printWriter.write("$tmb00035ET3318/08/22 11:5804029.94,027.25,20.00,20.00$");
//            printWriter.flush();
//            socket.shutdownOutput();
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new TimeClient().connect(10001,"localhost");
    }


}
