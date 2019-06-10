package com.wuhao.web.servletNorm.http.coding;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet(name = "requestCoding", urlPatterns = {"/requestCoding/*"}, loadOnStartup = 1)
public class RequestCoding extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter1 = req.getParameter("key1");
        String parameter2 = req.getParameter("key2");
        String pathInfo = req.getPathInfo();
        System.out.println(parameter1);
        System.out.println(parameter2);
        System.out.println(pathInfo);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
