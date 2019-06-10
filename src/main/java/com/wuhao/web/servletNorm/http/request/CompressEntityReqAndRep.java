package com.wuhao.web.servletNorm.http.request;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@WebServlet(name = "compressEntityReqAndRep", urlPatterns = {"/compressEntityReqAndRep"}, loadOnStartup = 1
)
public class CompressEntityReqAndRep extends HttpServlet {


    /**
     * 判断客户端是否要求进行gzip压缩处理
     *
     * @param request
     * @return
     */
    private boolean clientisGzipSupport(HttpServletRequest request) {
        String headEncoding = request.getHeader("accept-encoding");
        return (headEncoding != null && (headEncoding.indexOf("gzip") != -1));
    }

    /**
     * 判断服务端是否要求进行gzip压缩处理
     *
     * @param request
     * @return
     */
    private boolean serverisGzipSupport(HttpServletRequest request) {
        String contentEncoding = request.getHeader("Content-Encoding");
        return (contentEncoding != null && (contentEncoding.indexOf("gzip") != -1));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String respContent = "中文测试this is a test!";
        String reqContent = "";
        if (serverisGzipSupport(req)) {
            ServletInputStream inputStream = req.getInputStream();
            if (inputStream != null) {
                GZIPInputStream gzis = new GZIPInputStream(inputStream);
                InputStreamReader reader = new InputStreamReader(gzis);
                BufferedReader br = new BufferedReader(reader);
                StringBuffer sb = new StringBuffer();
                String temp;
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }
                br.close();
                reqContent = sb.toString();
            }
        }
        System.out.println(reqContent);

        if (clientisGzipSupport(req)) {// 支持gzip
            resp.setHeader("Content-Encoding", "gzip");
            OutputStream os = resp.getOutputStream();
            GZIPOutputStream gs = new GZIPOutputStream(os);
            gs.write(respContent.getBytes("UTF-8"));// 解决中文乱码问题
            gs.finish();
            gs.close();
            os.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
