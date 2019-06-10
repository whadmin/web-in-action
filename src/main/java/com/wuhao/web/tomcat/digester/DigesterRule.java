package com.wuhao.web.tomcat.digester;

import org.apache.tomcat.util.digester.Digester;

import java.io.File;
import java.net.URL;

public class DigesterRule {

    public Department execute(String filePath) throws Exception {
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.setRulesValidation(true);

        // addObjectCreate方法的意思是碰到xml文件中的department节点则创建一个Department对象
        digester.addObjectCreate("department", "com.wuhao.web.tomcat.digester.Department");
        // addSetProperties方法的意思是根据department节点中的属性信息调用相应属性的setter方法
        digester.addSetProperties("department");
        // addObjectCreate方法的意思是碰到xml文件中的department节点则创建一个Department对象
        digester.addObjectCreate("department/user", "com.wuhao.web.tomcat.digester.User");
        digester.addSetProperties("department/user");

        digester.addSetNext("department/user", "addUser", "com.wuhao.web.tomcat.digester.User");
        digester.addCallMethod("department/extension", "putExtension", 2);
        digester.addCallParam("department/extension/property-name", 0);
        digester.addCallParam("department/extension/property-value", 1);
        URL url = this.getClass().getClassLoader().getResource(filePath);
        return (Department) digester.parse(new File(url.getFile()));
    }
}
