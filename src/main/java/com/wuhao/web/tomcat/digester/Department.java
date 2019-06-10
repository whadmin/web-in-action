package com.wuhao.web.tomcat.digester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Department {

    private String name;

    private String code;

    private Map<String, String> extension = new HashMap<String, String>();

    private List<User> users = new ArrayList<User>();

    public void addUser(User user){
        this.users.add(user);
    }

    public void putExtension(String name,String value){
        this.extension.put(name,value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getExtension() {
        return extension;
    }

    public void setExtension(Map<String, String> extension) {
        this.extension = extension;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

