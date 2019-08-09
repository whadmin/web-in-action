package com.wuhao.web.servletNorm.http.head.body;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 设置content-disposition响应头，让浏览器下载文件
 * 通过OutputStream来下载中英文名称的文件，通过PrintWrite来下载文件
 * <p>
 * 在编写下载文件功能时，要使用OutputStream流，避免使用PrintWriter流，
 * 因为OutputStream流是字节流，可以处理任意类型的数据，
 * 而PrintWriter流是字符流，只能处理字符数据，如果用字符流处理字节数据，会导致数据丢失。
 *
 * @author xiao
 */

@WebServlet(name = "ContentDisposition", urlPatterns = {"/ContentDispositionServlet"})
public class ContentDispositionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 设置content-disposition响应头，让浏览器下载文件
         * 下载英文名称的文件
         */
        downloadFileByOutputStream(resp);

////        下载中文名称的文件
//        downloadChineseFileByOutputStream(resp);
//        通过PrintWrite方法来下载文件，由于PrintWrite是处理字符流，会导致文件缺失；
//        downloadFileByPrintWriter(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    /**
     * 设置content-disposition响应头，让浏览器下载文件
     * 下载文件，通过OutputStream流
     * @param response
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void downloadFileByOutputStream(HttpServletResponse response)
            throws FileNotFoundException, IOException {
        //1.获取要下载的文件的绝对路径（这里的文件是给服务器用的，故"/"代表当前项目的根目录）
        String realPath = this.getServletContext().getRealPath("/image/MAC.jpg");
        //2.获取要下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.setHeader("content-disposition", "attachment;filename="+fileName);
        //4.获取要下载的文件输入流
        InputStream in = new FileInputStream(realPath);
        int len = 0;
        //5.创建数据缓冲区
        byte[] buffer = new byte[1024];
        //6.通过response对象获取OutputStream流
        OutputStream out = response.getOutputStream();
        //7.将FileInputStream流写入到buffer缓冲区
        while ((len = in.read(buffer)) > 0) {
            //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
            out.write(buffer,0,len);
        }
        in.close();
    }


    /**
     * 下载中文文件,中文文件下载时，文件名要经过URL编码，否则会出现文件名乱码
     *
     * @param response
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void downloadChineseFileByOutputStream(HttpServletResponse response)
            throws FileNotFoundException, IOException {
        //获取要下载的文件的绝对路径
        String realPath = this.getServletContext().getRealPath("/image/张家界国家森林公园.jpg");
        //获取要下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //设置content-disposition响应头控制浏览器以下载的形式打开文件，中文文件名要使用URLEncoder.encode方法进行编码，否则会出现文件名乱码
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //获取文件输入流
        InputStream in = new FileInputStream(realPath);
        int len = 0;
        byte[] buffer = new byte[1024];
        OutputStream out = response.getOutputStream();
        while ((len = in.read(buffer)) > 0) {
            //将缓冲区的数据输出到客户端浏览器
            out.write(buffer, 0, len);
        }
        in.close();
    }

    /**
     * 下载文件，通过PrintWriter流，虽然也能够实现下载，但是会导致数据丢失，因此不推荐使用PrintWriter流下载文件
     *
     * @param response
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void downloadFileByPrintWriter(HttpServletResponse response)
            throws FileNotFoundException, IOException {
        //获取要下载的文件的绝对路径
        String realPath = this.getServletContext().getRealPath("/image/张家界国家森林公园.jpg");
        //获取要下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //设置content-disposition响应头控制浏览器以下载的形式打开文件，中文文件名要使用URLEncoder.encode方法进行编码
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        FileReader in = new FileReader(realPath);
        int len = 0;
        char[] buffer = new char[1024];
        PrintWriter out = response.getWriter();
        while ((len = in.read(buffer)) > 0) {
            //将缓冲区的数据输出到客户端浏览器
            out.write(buffer, 0, len);
        }
        in.close();
    }
}
