package com.wuhao.web.EncoderDecoder;

import java.io.*;

/**
 * @author wuhao1
 */
public class FileEncoderDecoder {

    public static void main(String[] args) throws IOException {
        String file = "D:/a.txt";
        String charset = "UTF-8";

        /**
         * 字符转换为字节
         * */
        //获取一块输出流的空间，此处为file文件
        FileOutputStream outputStream = new FileOutputStream(file);
        //通过UTF-8的编码方式，将字符转换为字节
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
        //写入内容，将文字用UTF-8的编码方式转换为字节
        writer.write("这是要保存的中文字符");
        //关闭输出流
        writer.close();

        /**
         * 字节转换为字符
         * */
        //创建一个file的输入流
        FileInputStream inputStream = new FileInputStream(file);
        //创建一个编码方式为UTF-8的reader
        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        StringBuffer buffer = new StringBuffer();
        char[] buf = new char[64];
        int count = 0;
        //将字符读入到buf这个目的缓冲区中，如字符读取，已经达到流的结尾，则返回-1
        while ((count = reader.read(buf)) != -1) {
            //将buf中的字符串附加到buffer的序列中去
            buffer.append(buf, 0, count);

        }
        System.out.println(buffer);
        //关闭流
        reader.close();

       String str= "829766CA4C6392A5FE4C3104923D2B37";
       Long lo=Long.parseLong(str);
       System.out.println(lo);
    }

}
