package com.wuhao.web.servletNorm.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class GenericServletSub extends javax.servlet.GenericServlet {

    private String msg;

    @Override
    public void init() throws ServletException {
        System.out.println("GenericServletSub init");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        msg = this.getInitParameter("msg");
        this.log("GenericServletSub 正在提供服务");
        System.out.println(msg);
    }
}
