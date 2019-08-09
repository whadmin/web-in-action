package com.wuhao.web.servletNorm.http.head.common;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Cache-Control字段是用来控制缓存的行为，可以用于请求和响应。
 * Pragma是HTTP1.1之前的历史遗留字段，属于首部通用字段，但只用在客户端发送的请求中。客户端会要求所有的中间服务器不返回缓存的资源。
 * pragma:no-cache 一般与Cache-Control:no-cache一起使用，指定所有的服务器不返回缓存的资源。
 *
 * @author xiao
 */

@WebServlet(
        name = "CacheControlServlet",
        urlPatterns = {"/CacheControlServlet"}
)

public class CacheControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.在内存中创建一张图片
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
        //2.得到图片
        //Graphics g = image.getGraphics();
        Graphics2D g = (Graphics2D) image.getGraphics();
        //设置图片的背景色
        g.setColor(Color.WHITE);
        //填充背景色
        g.fillRect(0, 0, 80, 20);
        //3.向图片上写数据
        g.setColor(Color.BLUE);//设置图片上字体的颜色
        g.setFont(new Font(null, Font.BOLD, 20));
        g.drawString(makeNum(), 0, 20);
        //4.设置响应头控制浏览器浏览器以图片的方式打开
        resp.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
        //5.设置响应头控制浏览器不缓存图片数据
        resp.setDateHeader("expries", -1);
        resp.setHeader("Cache-Control", "no-cache");
//        resp.setHeader("Cache-Control","");
        resp.setHeader("Pragma", "no-cache");
        //6.将图片写给浏览器
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    /**
     * 生成随机数字
     *
     * @return
     */
    private String makeNum() {
        Random random = new Random();
        String num = random.nextInt(9999999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7 - num.length(); i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
