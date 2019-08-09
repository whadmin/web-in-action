package com.wuhao.web.servletNorm.http.head.body;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author wuhao1
 */

@WebServlet(name = "ContentType", urlPatterns = {"/ContentTypeServlet"})
public class ContentTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 浏览器能接收(Accept)的数据类型有:
         * application/x-ms-application,
         * image/jpeg,
         * application/xaml+xml,
         * image/gif,
         * image/pjpeg,
         * application/x-ms-xbap,
         * application/vnd.ms-excel,
         * application/vnd.ms-powerpoint,
         * application/msword,
         */

        //使用content-type响应头指定发送给浏览器的数据类型为"image/jpeg"
        resp.setHeader("content-type", "image/jpeg");
        //这里的第一个"/"表示当前项目的根目录，读取image文件夹里面的httpServlet.jpg这张图片，返回一个输入流
        InputStream in = this.getServletContext().getResourceAsStream("/image/httpServlet.png");
        byte buffer[] = new byte[1024];
        int len = 0;
        //得到输出流
        OutputStream out = resp.getOutputStream();
        //读取输入流(in)里面的内容存储到缓冲区(buffer)
        while ((len = in.read(buffer)) > 0) {
            //将缓冲区里面的内容输出到浏览器
            out.write(buffer, 0, len);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
