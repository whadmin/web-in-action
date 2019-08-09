package com.wuhao.web.servletNorm.servlet.resource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Properties;


/**
 * 通过类加载器（ClassLoader）来获取资源文件
 *
 * @author xiao
 */
@WebServlet(name = "ReadByClassLoader", urlPatterns = {"/ReadByClassLoader"})
public class ReadByClassLoader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * response.setContentType("text/html;charset=UTF-8");目的是控制浏览器用UTF-8进行解码；
         * 这样就不会出现中文乱码了
         */
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        test1(resp);
        resp.getWriter().println("<hr/>");
        test2(resp);
        resp.getWriter().println("<hr/>");
//        test3();
        test4();

    }

    /**
     * 读取类路径下的资源文件
     *
     * @param response
     * @throws IOException
     */
    private void test1(HttpServletResponse response) throws IOException {
        //获取到装载当前类的类装载器
        ClassLoader loader = ReadByClassLoader.class.getClassLoader();
        //用类装载器读取src目录下的db1.properties配置文件
        InputStream in = loader.getResourceAsStream("db1.properties");
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        response.getWriter().println("用类装载器读取src目录下的db1.properties配置文件：");
        response.getWriter().println(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}",
                        driver, url, username, password));
    }

    /**
     * 读取类路径下面、包下面的资源文件
     *
     * @param response
     * @throws IOException
     */
    private void test2(HttpServletResponse response) throws IOException {
        //获取到装载当前类的类装载器
        ClassLoader loader = ReadByClassLoader.class.getClassLoader();
        //用类装载器读取src目录下的mysql包中的db.properties配置文件
        InputStream in = loader.getResourceAsStream("mysql/db.properties");
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        response.getWriter().println("用类装载器读取src目录下的mysql包中的db.properties配置文件：");
        response.getWriter().println(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}",
                        driver, url, username, password));
    }

    /**
     * 通过类装载器读取资源文件的注意事项:不适合装载大文件，否则会导致jvm内存溢出
     */
    public void test3() {
        /**
         * 01.avi是一个150多M的文件，使用类加载器去读取这个大文件时会导致内存溢出：
         * java.lang.OutOfMemoryError: Java heap space
         */
        InputStream in = ReadByClassLoader.class.getClassLoader().getResourceAsStream("01.avi");
        System.out.println(in);
    }

    /**
     * 读取01.avi,并拷贝到e:\根目录下
     * 01.avi文件太大，只能用servletContext去读取
     *
     * @throws IOException
     */
    public void test4() throws IOException {
        // path=G:\Java学习视频\JavaWeb学习视频\JavaWeb\day05视频\01.avi
        // path=01.avi
        String path = this.getServletContext().getRealPath("/WEB-INF/classes/01.avi");
        /**
         * path.lastIndexOf("\\") + 1是一个非常绝妙的写法
         */
        String filename = path.substring(path.lastIndexOf("\\") + 1);//获取文件名
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/01.avi");
        byte buffer[] = new byte[1024];
        int len = 0;
        OutputStream out = new FileOutputStream("e:\\" + filename);
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        out.close();
        in.close();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
