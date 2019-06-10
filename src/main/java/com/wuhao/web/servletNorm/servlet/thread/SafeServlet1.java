package com.wuhao.web.servletNorm.servlet.thread;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SafeServlet1 extends HttpServlet {

    int i = 1;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /**
         * 当多线程并发访问这个方法里面的代码时，会存在线程安全问题吗
         * i变量被多个线程并发访问，但是没有线程安全问题，因为i是doGet方法里面的局部变量，
         * 当有多个线程并发访问doGet方法时，每一个线程里面都有自己的i变量，
         * 各个线程操作的都是自己的i变量，所以不存在线程安全问题
         * 多线程并发访问某一个方法的时候，如果在方法内部定义了一些资源(变量，集合等)
         * 那么每一个线程都有这些东西，所以就不存在线程安全问题了
         */
        synchronized (this) {
            i++;
            try {
                Thread.sleep(1000 * 4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response.getWriter().write(i + "");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
