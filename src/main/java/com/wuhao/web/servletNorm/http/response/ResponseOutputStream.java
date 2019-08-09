package com.wuhao.web.servletNorm.http.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 使用OutputStream流输出中文，数字;
 * OutputStream处理字节流，可以处理任意类型的数据；
 * @author wuhao1
 */
@WebServlet(name = "responseOutputStream", urlPatterns = {"/responseOutputStream"}, loadOnStartup = 1)
public class ResponseOutputStream extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用OutputStream流输出中文
        outputChineseByOutputStream(resp);

        //使用OutputStream流输出数字
//        outputOneByOutputStream(resp);
    }

    /**
     * 使用OutputStream流输出中文
     * @param response
     * @throws IOException
     */
    public void outputChineseByOutputStream(HttpServletResponse response) throws IOException{
        /**使用OutputStream输出中文注意问题：
         * 在服务器端，数据是以哪个码表输出的，那么就要控制客户端浏览器以相应的码表打开，
         * 比如：outputStream.write("中国".getBytes("UTF-8"));//使用OutputStream流向客户端浏览器输出中文，以UTF-8的编码进行输出
         * 此时就要控制客户端浏览器以UTF-8的编码打开，否则显示的时候就会出现中文乱码，那么在服务器端如何控制客户端浏览器以以UTF-8的编码显示数据呢？
         * 可以通过设置响应头控制浏览器的行为，例如：
         * response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据
         */
        String data = "中国";
        //获取OutputStream输出流，此处为字节流
        OutputStream outputStream = response.getOutputStream();
        //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        response.setHeader("content-type", "text/html;charset=UTF-8");
        /**
         * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表，
         * 如果是中文的操作系统环境，默认就是查找查GB2312的码表，
         * 将字符转换成字节数组的过程就是将中文字符转换成GB2312的码表上对应的数字
         * 比如： "中"在GB2312的码表上对应的数字是98
         *         "国"在GB2312的码表上对应的数字是99
         */
        /**
         * getBytes()方法如果不带参数，那么就会根据操作系统的语言环境来选择转换码表，如果是中文操作系统，那么就使用GB2312的码表
         */
        //将字符转换成字节数组，指定以UTF-8编码进行转换
        byte[] dataByteArr = data.getBytes("UTF-8");
        //使用OutputStream流向客户端输出字节数组
        outputStream.write(dataByteArr);
    }


    /**
     * 使用OutputStream流输出数字1
     * 在开发过程中，如果希望服务器输出什么浏览器就能看到什么，那么在服务器端都要以字符串的形式进行输出。
     * @param response
     * @throws IOException
     */
    public void outputOneByOutputStream(HttpServletResponse response) throws IOException{
        response.setHeader("content-type", "text/html;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write("使用OutputStream流输出数字1：".getBytes("UTF-8"));
//        //使用如下代码，无法输出数字1
//        outputStream.write(1);
        //将数字转换为字符后，通过getBytes()方法将内容以字符串的形式输出
        outputStream.write((1+"").getBytes());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
