package com.wuhao.web.servletNorm.servlet.configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfigurationServletForXml extends HttpServlet {
    private String msg;

    public ConfigurationServletForXml() {
        System.out.println("load on ConfigurationServletForXml startup ");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        msg = this.getInitParameter("msg");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws
            ServletException, IOException {
        System.out.println("ConfigurationServletForXml Servlet正在提供服务");
        System.out.println(msg);
    }
}
