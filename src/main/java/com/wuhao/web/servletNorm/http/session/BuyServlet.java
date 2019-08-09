package com.wuhao.web.servletNorm.http.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhao1
 */
@WebServlet(name = "BuyServlet",urlPatterns = {"/servlet/BuyServlet"})
public class BuyServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Book book = DB.getAll().get(id);  //得到用户想买的书
        HttpSession session = request.getSession();
        List<Book> list = (List) session.getAttribute("list");  //得到用户用于保存所有书的容器
        if(list==null){
            list = new ArrayList<Book>();
            session.setAttribute("list", list);
        }
        list.add(book);
        //response. encodeRedirectURL(java.lang.String url)用于对sendRedirect方法后的url地址进行重写
        String url = response.encodeRedirectURL(request.getContextPath()+"/servlet/ListCartServlet");
        System.out.println(url);
        response.sendRedirect(url);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
