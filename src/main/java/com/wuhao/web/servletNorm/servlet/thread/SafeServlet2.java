package com.wuhao.web.servletNorm.servlet.thread;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 让Servlet去实现一个SingleThreadModel接口，如果某个Servlet实现了SingleThreadModel接口，
 * 那么Servlet引擎将以单线程模式来调用其service方法。
 * 　对于实现了SingleThreadModel接口的Servlet，Servlet引擎仍然支持对该Servlet的多线程并发访问，
 * 其采用的方式是产生多个Servlet实例对象，并发的每个线程分别调用一个独立的Servlet实例对象。
 * 实现SingleThreadModel接口并不能真正解决Servlet的线程安全问题，只是創建了多個servlet實例對象
 * 在Servlet API 2.4中，已经将SingleThreadModel标记为Deprecated（过时的）。
 * @author wuhao1
 */
@WebServlet(name="SafeServlet2",urlPatterns = {"/SafeServlet2"},loadOnStartup = 1)
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
