package com.wuhao.web.servletNorm.http.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author wuhao1
 */
@WebServlet(name = "ListCartServlet",urlPatterns = {"/servlet/ListCartServlet"})
public class ListCartServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        List<Book> list = (List) session.getAttribute("list");
        if(list==null || list.size()==0){
            out.write("对不起，您还没有购买任何商品!!");
            return;
        }

        //显示用户买过的商品
        out.write("您买过如下商品:<br>");
        for(Book book : list){
            out.write(book.getName() + "<br/>");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
