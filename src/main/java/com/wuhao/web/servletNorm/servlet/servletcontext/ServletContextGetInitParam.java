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
@WebServlet(
        name = "ServletContextGetInitParam",
        urlPatterns = "/ServletContextGetInitParam"
)
public class ServletContextGetInitParam extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = this.getServletContext();
        //获取整个web站点的初始化参数
        String contextInitParam = context.getInitParameter("url");
        String forwardName=(String)request.getAttribute("name");
        if(forwardName==null){
            response.getWriter().println("The Request Forward name is null");
        }else {
            response.getWriter().println("The Request Forward name is : " + forwardName);
        }
        response.getWriter().println("<h1>This is InitParamServlet</h1> "+contextInitParam);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
