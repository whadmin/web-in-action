package com.wuhao.web.servletNorm.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author wuhao1
 */
@WebServlet(name = "ServletContextSharedAttr1",urlPatterns = {"/ServletContextSharedAttr1"})
public class ServletContextSharedAttr1 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String data = "xdp_gacl";
        /**
         * ServletConfig对象中维护了ServletContext对象的引用，开发人员在编写servlet时，
         * 可以通过ServletConfig.getServletContext方法获得ServletContext对象。
         */
        ServletContext context = this.getServletConfig().getServletContext();
        //将data存储到ServletContext对象中
        context.setAttribute("data", data);
        response.getWriter().print("This pragram is set attribute,the data is xdp_gacl");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
