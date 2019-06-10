package com.wuhao.web.servletNorm.servlet.configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "servlet1", urlPatterns = {"/configurationServletForAnnotation"}, loadOnStartup = 1,
        initParams = {
                @WebInitParam(name = "msg", value = "hello world")
        })
public class ConfigurationServletForAnnotation extends HttpServlet {
    private String msg;

    public ConfigurationServletForAnnotation() {
        System.out.println("load on ConfigurationServletForAnnotation startup");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        msg = this.getInitParameter("msg");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ConfigurationServletForAnnotation Servlet正在提供服务");
        System.out.println(msg);
    }
}
