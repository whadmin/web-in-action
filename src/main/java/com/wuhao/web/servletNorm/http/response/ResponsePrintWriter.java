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
 * 使用PrintWriter方法来输出中文；
 * PrintWrite处理字符流，只能处理字符类的数据，处理字节流的数据时，会导致数据丢失（譬如图片、视频会无法正常显示）。
 * getOutputStream和getWriter方法分别用于得到输出二进制数据、输出文本数据的ServletOuputStream、Printwriter对象。
 * getOutputStream和getWriter这两个方法互相排斥，调用了其中的任何一个方法后，就不能再调用另一方法。
 *
 * @author wuhao1
 */
@WebServlet(name = "responsePrintWriter", urlPatterns = {"/responsePrintWriter"}, loadOnStartup = 1)
public class ResponsePrintWriter extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = "中国";
        //设置将字符以"UTF-8"编码输出到客户端浏览器
//        resp.setCharacterEncoding("UTF-8");
        //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        /**
         * PrintWriter out = response.getWriter();这句代码必须放在response.setCharacterEncoding("UTF-8");之后
         * 否则response.setCharacterEncoding("UTF-8")这行代码的设置将无效，浏览器显示的时候还是乱码
         */
        //获取PrintWriter输出流，此处为字符流
        PrintWriter out = resp.getWriter();
        out.write(data);

//        outputChineseByPrintWriter(resp);
    }


    /**
     * 使用PrintWriter流输出中文,使用HTML语言里面的<meta>标签来控制浏览器行为
     *
     * @param response
     * @throws IOException
     */
    public void outputChineseByPrintWriter(HttpServletResponse response) throws IOException {
        String data = "中国";

        //设置将字符以"UTF-8"编码输出到客户端浏览器
        response.setCharacterEncoding("UTF-8");
        /**
         * PrintWriter out = response.getWriter();这句代码必须放在response.setCharacterEncoding("UTF-8");之后
         * 否则response.setCharacterEncoding("UTF-8")这行代码的设置将无效，浏览器显示的时候还是乱码
         */
        //获取PrintWriter输出流
        PrintWriter out = response.getWriter();
        /**
         * 多学一招：使用HTML语言里面的<meta>标签来控制浏览器行为，模拟通过设置响应头控制浏览器行为
         * out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'/>");
         * 等同于response.setHeader("content-type", "text/html;charset=UTF-8");
         */
        out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'/>");
        //使用PrintWriter流向客户端输出字符
        out.write(data);
    }

    /**
     * 使用PrintWriter流输出数字1
     *
     * @param response
     * @throws IOException
     */
    public void outputOneByPrintWriter(HttpServletResponse response) throws IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获取PrintWriter输出流
        PrintWriter out = response.getWriter();
        out.write("使用PrintWriter流输出数字1：");
        out.write(1 + "");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
