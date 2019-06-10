package com.wuhao.web.servletNorm.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet的生命周期
 */
public class LifeCycleServlet implements Servlet {

    /**
     * 定义ServletConfig对象来接收配置的初始化参数
     */
    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = config;
        System.out.println("Servlet正在初始化");
        //throw new RuntimeException();
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Servlet正在提供服务");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Servlet正在销毁");
    }
}
