package com.wuhao.web.servletNorm.http.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**通过getHeader()、getHeaderNames()、getHeaders()来获取http请求的头部信息；
 * @author wuhao1
 */
@WebServlet(
        name = "RequestGetHttpHeadInfo",
        urlPatterns = {"/RequestGetHttpHeadInfo"}
)
public class RequestGetHttpHeadInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置将字符以"UTF-8"编码输出到客户端浏览器
        response.setCharacterEncoding("UTF-8");
        //通过设置响应头控制浏览器以UTF-8的编码显示数据
        response.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取所有的请求头
        Enumeration<String> reqHeadInfos = request.getHeaderNames();
        out.write("获取到的客户端所有的请求头信息如下：");
        out.write("<hr/>");
        while (reqHeadInfos.hasMoreElements()) {
            String headName = (String) reqHeadInfos.nextElement();
            //根据请求头的名字获取对应的请求头的值
            String headValue = request.getHeader(headName);
            out.write(headName+":"+headValue);
            out.write("<br/>");
        }
        out.write("<hr/>");
        out.write("获取到的客户端Accept-Encoding请求头的值：");
        out.write("<br/>");
        //获取Accept-Encoding请求头对应的值,getHeader(String name)获取到的值为String类型
        String value = request.getHeader("Accept-Encoding");
        out.write(value);
        out.write("<hr/>");

        //获取http请求中Accept的值；getHeaders(String name)获取到的值为Enumeration<String>类型；
        Enumeration<String> e = request.getHeaders("Accept");
        while (e.hasMoreElements()) {
            String string = (String) e.nextElement();
            out.write(string);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
