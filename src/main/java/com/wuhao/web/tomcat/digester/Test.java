package com.wuhao.web.tomcat.digester;

public class Test {

    @org.junit.Test
    public void testJavaRule() throws Exception {
        Department department = new DigesterRule().execute("tomcat/department.xml");
        System.out.println(department);
    }
}
