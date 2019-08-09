package com.wuhao.web.servletNorm.http.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "FormServlet",urlPatterns = {"/servlet/FormServlet"})
public class FormServlet extends HttpServlet {
    private static final long serialVersionUID = -884689940866074733L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = TokenProccessor.getInstance().makeToken();//创建令牌
        System.out.println("在FormServlet中生成的token："+token);
        request.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)
        request.getRequestDispatcher("/html/Form.jsp").forward(request, response);//跳转到form.jsp页面
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
