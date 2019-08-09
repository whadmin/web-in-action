package com.wuhao.web.http;

import com.wuhao.web.StreamTool;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionTest {

    @Before
    public void init() {

    }


    @Test
    public void sendEms() throws Exception {
        String wen = "MS2201828";
        String btnSearch = "EMS快递查询";
        URL url = new URL("http://localhost:8080/web/LocationServlet");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 提交模式
        conn.setRequestMethod("GET");
        //连接超时 单位毫秒
        conn.setConnectTimeout(10000);
        //读取超时 单位毫秒
        conn.setReadTimeout(2000);
        // 是否输入参数
        conn.setDoOutput(true);

        // 将产生写入body
//        StringBuffer params = new StringBuffer();
//        params.append("wen").append("=").append(wen).append("&").append("btnSearch").append("=").append(btnSearch);
//        byte[] bypes = params.toString().getBytes();
//        conn.getOutputStream().write(bypes);

        //读取服务器返回input
        InputStream inStream = conn.getInputStream();
        System.out.println(new String(StreamTool.readInputStream(inStream), "gbk"));
    }
}
