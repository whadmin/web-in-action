package com.wuhao.web.servletNorm.servlet.configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfigurationServletForAPI extends HttpServlet {

    private String msg;

    public ConfigurationServletForAPI() {
        System.out.println("load on ConfigurationServletForAPI startup");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        msg = this.getInitParameter("msg");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ConfigurationServletForAPI Servlet正在提供服务");
        System.out.println(msg);
    }
}
