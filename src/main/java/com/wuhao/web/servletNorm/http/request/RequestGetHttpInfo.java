package com.wuhao.web.servletNorm.http.request;

import com.wuhao.web.util.GzipUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.zip.GZIPInputStream;

@WebServlet(name = "requestGetHttpInfo", urlPatterns = {"/requestGetHttpInfo"}, loadOnStartup = 1
)
public class RequestGetHttpInfo extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 1.获得http连接信息
         */
        //返回请求的URL地址
        String requestUrl = request.getRequestURL().toString();
        //返回请求URL中的资源部分，如访问当前Servlet的http请求的资源部分就为/web/requestGetHttpInfo
        String requestUri = request.getRequestURI();
        //返回context根路径
        String contextPath = request.getContextPath();
        //返回请求的URL地址中附带的参数
        String queryString = request.getQueryString();
        //返回请求URL地址时使用的方法
        String method = request.getMethod();
        //返回协议名称和版本号
        String protocol = request.getProtocol();
        //返回回请求URL中的额外路径信息。
        // 额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
        String pathInfo = request.getPathInfo();
        /**
         * 1.获得http连接客户端机器信息
         */
        //返回来访者的IP地址
        String remoteAddr = request.getRemoteAddr();
        //返回发出请求的客户机的完整主机名
        String remoteHost = request.getRemoteHost();
        //返回客户机所使用的网络端口号
        int remotePort = request.getRemotePort();
        String remoteUser = request.getRemoteUser();
        /**
         * 1.获得http连接服务端机器信息
         */
        //返回WEB服务器的IP地址
        String localAddr = request.getLocalAddr();
        //返回WEB服务器的主机名
        String localName = request.getLocalName();
        //返回WEB服务器的网络端口号
        int localPort = request.getLocalPort();


        String character = request.getCharacterEncoding();//设置将字符以"UTF-8"编码输出到客户端浏览器
//        //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
//        ServletInputStream ris = request.getInputStream();
//        StringBuilder content = new StringBuilder();
//        byte[] b = new byte[1024];
//        int lens = -1;
//        while ((lens = ris.read(b)) > 0) {
//            content.append(new String(b, 0, lens));
//        }



        response.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("获取到的客户机信息如下：");
        out.write("<hr/>");
        out.write("请求的URL地址：" + requestUrl);
        out.write("<br/>");
        out.write("请求的资源：" + requestUri);
        out.write("<br/>");
        out.write("请求的URL地址中附带的参数：" + queryString);
        out.write("<br/>");
        out.write("Context根路径"+contextPath);
        out.write("<br/>");
        out.write("pathInfo："+pathInfo);
        out.write("<br/>");
        out.write("来访者的IP地址：" + remoteAddr);
        out.write("<br/>");
        out.write("来访者的主机名：" + remoteHost);
        out.write("<br/>");
        out.write("remoteUser："+remoteUser);
        out.write("<br/>");
        out.write("使用的端口号：" + remotePort);
        out.write("<br/>");
        out.write("请求使用的方法：" + method);
        out.write("<br/>");
        out.write("localAddr：" + localAddr);
        out.write("<br/>");
        out.write("localName：" + localName);
        out.write("<br/>");
        out.write("localPort：" + localPort);
        out.write("<br/>");
        //out.write("content：" + GzipUtils.uncompress(content.toString()));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
