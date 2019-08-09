package com.wuhao.web.servletNorm.servlet.servletcontext;

import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ServletContextRequestForward",urlPatterns = {"/ServletContextRequestForward"})
public class ServletContextRequestForward extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String data = "<h2><font color='red'>abcdefghjkl</font></h2>";
//      //获取ServletContext对象
        ServletContext context = this.getServletContext();
        //获取请求转发对象(RequestDispatcher)
        /**
         * 2.forward
         * 客户端请求某个web资源，服务器跳转到另外一个web资源，这个forward也是给服务器用的，
         * 那么这个"/"就是给服务器用的，所以此时"/"代表的就是web工程
         */
        RequestDispatcher rd = context.getRequestDispatcher("/ServletContextGetInitParam");
        //设置request的name属性
        request.setAttribute("name",data);
        //调用forward方法实现请求转发
        rd.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
