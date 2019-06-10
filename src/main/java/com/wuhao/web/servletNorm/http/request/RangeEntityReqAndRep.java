package com.wuhao.web.servletNorm.http.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "rangeEntityReqAndRep", urlPatterns = {"/rangeEntityReqAndRep"}, loadOnStartup = 1
)
public class RangeEntityReqAndRep extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = "hello world";
        //获取OutputStream输出流
        OutputStream outputStream = resp.getOutputStream();
        resp.setHeader("content-type", "text/plain;charset=UTF-8");
        //将字符转换成字节数组，指定以UTF-8编码进行转换
        byte[] dataByteArr = data.getBytes("UTF-8");
        outputStream.write(dataByteArr);
    }
}
