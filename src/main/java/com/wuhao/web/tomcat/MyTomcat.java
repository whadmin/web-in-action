package com.wuhao.web.tomcat;

import com.wuhao.web.servletNorm.http.request.RequestGetHttpInfo;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class MyTomcat {

    public static void main(String[] args) throws LifecycleException {
        //1.把目录的绝对路径获取到
        String classPath = System.getProperty("user.dir");

        //2.新建一个Tomcat对象
        Tomcat tomcat = new Tomcat();

        //3.创建一个连接器
        Connector connector = tomcat.getConnector();
        //4.连接器有一个端口属性
        connector.setPort(9090);

        //5.设置Host
        Host host = tomcat.getHost();
        //6.设置Host的属性，可以参照Server.xml来进行理解
        host.setName("localhost");
        host.setAppBase("webapps");
        /**
         * tomcat.start();
         * 到上面这一步其实已经可以把tomcat起动了，只是现在启动里面没啥东西
         *
         * 八月 28, 2018 3:31:02 下午 org.apache.coyote.AbstractProtocol init
         * 信息: Initializing ProtocolHandler ["http-bio-9090"]
         * 八月 28, 2018 3:31:02 下午 org.apache.catalina.core.StandardService startInternal
         * 信息: Starting service Tomcat
         * 八月 28, 2018 3:31:02 下午 org.apache.catalina.core.StandardEngine startInternal
         * 信息: Starting Servlet Engine: Apache Tomcat/7.0.47
         * 八月 28, 2018 3:31:02 下午 org.apache.coyote.AbstractProtocol start
         * 信息: Starting ProtocolHandler ["http-bio-9090"]
         */

        //7.把class加载进来，把启动的工程加入进来了
        Context context = tomcat.addContext(host, "/", classPath);

        if (context instanceof StandardContext) {
            StandardContext standardContext = (StandardContext) context;
            //要给一个默认的web.xml文件
            standardContext.setDefaultContextXml("D:\\soft\\tomcat\\apache-tomcat-8.0.30-windows-x64\\apache-tomcat-8.0.30\\conf\\web.xml");
            //把server设置进去
            Wrapper wrapper = tomcat.addServlet("/", "DemoServlet", new RequestGetHttpInfo());
            wrapper.addMapping("/nelson");
        }
        tomcat.start();
        //强制Tomcat Server等待，避免main线程执行结束后关闭
        tomcat.getServer().await();
    }
}
