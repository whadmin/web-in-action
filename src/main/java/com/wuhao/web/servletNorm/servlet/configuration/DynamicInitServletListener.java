package com.wuhao.web.servletNorm.servlet.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener(value ="dynamicInitServletListener")
public class DynamicInitServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ServletRegistration.Dynamic dynamic1 = servletContext.addServlet("configurationServletForAPI", ConfigurationServletForAPI.class);
        dynamic1.setLoadOnStartup(1);
        dynamic1.addMapping("/configurationServletForAPI");
        System.out.println("add configurationServletForAPI ");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
