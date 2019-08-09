package com.wuhao.web.servletNorm.http.head.body;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置Servlet的缓存时间,在缓存过期之前访问该Servlet，则是从缓存中读取的；缓存过期后，会再从服务器获取。
 * 缓存文件的路径为C:\Users\wuhao1\AppData\Local\Microsoft\Windows\Temporary Internet Files,
 * 查看ExpiresServlet文件，可以看到文件的访问时间与缓存文件的过期时间。
 *
 * @author wuhao1
 */
@WebServlet(
        name = "ExpiresServlet",
        urlPatterns = {"/ExpiresServlet"}
)

public class ExpiresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = "abcddfwerwesfasfsadf";
        /**
         * 设置数据合理的缓存时间值，以避免浏览器频繁向服务器发送请求，提升服务器的性能
         * 这里是将数据的缓存时间设置为1天
         */
        resp.setDateHeader("expires", System.currentTimeMillis() + 24 * 3600 * 1000);
        resp.getOutputStream().write(data.getBytes());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
