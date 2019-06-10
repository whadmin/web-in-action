package com.wuhao.web.servletNorm.servlet.thread;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 让Servlet去实现一个SingleThreadModel接口，如果某个Servlet实现了SingleThreadModel接口，
 * 那么Servlet引擎将以单线程模式来调用其service方法。
 */
public class SafeServlet2 extends HttpServlet implements SingleThreadModel {

    int i = 1;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        i++;
        try {
            Thread.sleep(1000 * 4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().write(i + "");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
