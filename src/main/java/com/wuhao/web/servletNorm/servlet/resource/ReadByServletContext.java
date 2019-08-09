package com.wuhao.web.servletNorm.servlet.resource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * 利用ServletContext对象读取资源文件(resource)
 *
 * @author xiao
 */
@WebServlet(
        name = "ReadByServletContext",
        urlPatterns = "/ReadByServletContext"
)
public class ReadByServletContext extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /**
         * response.setContentType("text/html;charset=UTF-8");目的是控制浏览器用UTF-8进行解码；
         * 这样就不会出现中文乱码了
         */
        response.setHeader("content-type", "text/html;charset=UTF-8");
        //读取src目录(即class目录）下的properties配置文件
        readSrcDirPropCfgFile(response);
        response.getWriter().println("<hr/>");
        //读取Webapp目录下的properties配置文件
        readWebRootDirPropCfgFile(response);
        response.getWriter().println("<hr/>");
        //读取src目录下的mysql包中的db.properties配置文件
        readPropCfgFile(response);
        response.getWriter().println("<hr/>");


    }


    /**
     * 读取src目录（即class目录）下的mysql包中的db.properties配置文件
     *
     * @param response
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void readPropCfgFile(HttpServletResponse response)
            throws FileNotFoundException, IOException {
        //通过ServletContext获取web资源的绝对路径
        String path = this.getServletContext().getRealPath("/WEB-INF/classes/mysql/db.properties");
        InputStream in = new FileInputStream(path);
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        response.getWriter().println("读取src目录下的mysql包中的db.properties配置文件：<br>");
        response.getWriter().println(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}",
                        driver, url, username, password));
    }

    /**
     * 通过ServletContext对象读取Webapp目录下的properties配置文件
     *
     * @param response
     * @throws IOException
     */
    private void readWebRootDirPropCfgFile(HttpServletResponse response)
            throws IOException {
        /**
         * 通过ServletContext对象读取Webapp目录下的properties配置文件
         * “/”代表的是项目根目录
         */
        InputStream in = this.getServletContext().getResourceAsStream("/db2.properties");
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        response.getWriter().println("读取Webapp目录下的db2.properties配置文件：<br>");
        response.getWriter().print(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}",
                        driver, url, username, password));
    }

    /**
     * 通过ServletContext对象读取src目录（即class根目录）下的properties配置文件
     * 项目中资源文件均放置在resource目录下，该目录也是class的根目录，故配置文件在/resource/db1.properties
     *
     * @param response
     * @throws IOException
     */
    private void readSrcDirPropCfgFile(HttpServletResponse response) throws IOException {
        /**
         * 通过ServletContext对象读取src目录下的db1.properties配置文件
         */
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db1.properties");
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        response.getWriter().println("读取src目录下的db1.properties配置文件：<br>");
        response.getWriter().println(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}",
                        driver, url, username, password));
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
