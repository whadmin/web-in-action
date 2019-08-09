package com.wuhao.web.servletNorm.http.head.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 通过setStatus(状态码)和setHeader("Location",location)来实现http的重定向功能；
 * <p>
 * 服务器响应状态码(在HttpServletResponse类中有定义)
 * 301 永久性重定向；
 * 302表示资源临时被重定向, 可以直接通过sendRedirect(location)方法来实现302的重定向功能。
 * 303表示客户端应当采用GET方法获取资源,
 * 307代表临时重定向，不会更改请求方法；HttpServletResponse.SC_TEMPORARY_REDIRECT=307
 *
 * @author xiao
 */
@WebServlet(
        name = "LocationServlet",
        urlPatterns = {"/LocationServlet"}
)

public class LocationServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 设置服务器的响应状态码,307也可用HttpServletResponse.SC_TEMPORARY_REDIRECT来表示;
         */
        resp.setStatus(307);

        /**
         *设置响应头，服务器通过 Location这个头，来告诉浏览器跳到哪里，这就是所谓的请求重定向
         */
        /**此处路径中的文件，是返回给客户端浏览器重定向的，故此处的"/"表示webapp的目录，故要加上web工程的名称
         * */
//        resp.setHeader("Location", "/web/307.jsp");
        /**当使用上面的方法表示绝对路径时，若项目部署的名称发生变化时，此处的路径也要跟着修改，无法灵活应用；
         * 故可以将项目名称(/web)用req.getContextPath()方法来替换；
         * req.getContextPath()是返回当前
         * */
        resp.setHeader("Location",req.getContextPath() + "/307.jsp");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * 调用sendRedirect方法实现请求重定向,
         * sendRedirect内部的实现原理：使用response设置302状态码和设置location响应头实现重定向
         */
//        resp.sendRedirect("/web/302.jsp");
        resp.sendRedirect(req.getContextPath()+"/302.jsp");

        /**
         //         *  sendRedirect(location)方法，实际就是调用了setStatus(302)和setHeader("Location", location)这两个方法来实现的。
         *  resp.setStatus(HttpServletResponse.SC_FOUND);
         *  resp.setHeader("Location", "/web/302.jsp");
         */


    }
}
