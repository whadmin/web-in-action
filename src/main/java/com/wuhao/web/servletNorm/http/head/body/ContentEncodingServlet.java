package com.wuhao.web.servletNorm.http.head.body;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @author wuhao1
 */

@WebServlet(name="ContentEncoding", urlPatterns = {"/ContentEncodingServlet"})
public class ContentEncodingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = "abcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabc" +
                "dabcdabcdabcdabcdabcdabcdabcdabc" +
                "dabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdabcd";
        System.out.println("原始数据的大小为：" + data.getBytes().length);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        //buffer
        GZIPOutputStream gout = new GZIPOutputStream(bout);
        gout.write(data.getBytes());
        gout.close();
        //得到压缩后的数据
        byte g[] = bout.toByteArray();
        resp.setHeader("Content-Encoding", "gzip");
        resp.setHeader("Content-Length",g.length +"");
        resp.getOutputStream().write(g);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
