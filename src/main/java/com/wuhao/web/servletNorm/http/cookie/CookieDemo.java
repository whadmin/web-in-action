package com.wuhao.web.servletNorm.http.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author wuhao1
 */
@WebServlet(name = "CookieDemo",urlPatterns = {"/CookieDemo"})
public class CookieDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置服务器端以UTF-8编码进行输出
        response.setCharacterEncoding("UTF-8");
        //设置浏览器以UTF-8编码进行接收,解决中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取浏览器访问访问服务器时传递过来的cookie数组
        Cookie[] cookies = request.getCookies();
        //如果用户是第一次访问，那么得到的cookies将是null
        if (cookies != null) {
            out.write("您上次访问的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("_ga") || cookie.getName().equals("JSESSIONID")) {
                    Long lastAccessTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastAccessTime);
                    out.write("dsfdsf"+date.toLocaleString());
                }
            }
        } else {
            out.write("这是您第一次访问本站！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
