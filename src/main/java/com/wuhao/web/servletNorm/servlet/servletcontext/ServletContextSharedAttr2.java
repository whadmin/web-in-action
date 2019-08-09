package com.wuhao.web.servletNorm.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 通过ServletContext来共享属性数据
 * @author wuhao1
 */
@WebServlet(name = "ServletContextSharedAttr2",urlPatterns = {"/ServletContextSharedAttr2"})
public class ServletContextSharedAttr2 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        //从ServletContext对象中取出数据
        String data = (String) context.getAttribute("data");
        response.getWriter().print("data=" + data);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
