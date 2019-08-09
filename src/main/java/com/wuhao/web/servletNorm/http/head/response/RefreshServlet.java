package com.wuhao.web.servletNorm.http.head.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @author wuhao1
 */
@WebServlet(name = "RefreshServlet", urlPatterns = "/RefreshServlet")
public class RefreshServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置refresh响应头控制浏览器每隔5秒钟刷新一次
        response.setHeader("refresh", "5");

        response.getWriter().println( makeNum());
    }

    /**
     * 生成随机数字
     *
     * @return
     */
    private String makeNum() {
        Random random = new Random();
        String num = random.nextInt(999999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7 - num.length(); i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
