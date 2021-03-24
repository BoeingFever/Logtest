package com.csjack.LogTesting.Bean;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
@Data
@Builder
public class TestBean {
    private String username;
    private Map<String, String> resourcesPath;
    public void sayHello(String temp) {
        System.out.println("TestBean sayHello...");
    }

    public String mycallback(){
        System.out.println("this is my callback");
        return "this is my callback";
    }
    public void start() {
        System.out.println("TestBean init");
    }

    public void cleanUp() {
        System.out.println("TestBean destroy");
    }

    public Map<String, String> getResourcesPath() {
        return resourcesPath;
    }

    public void setResourcesPath(Map<String, String> resourcesPath) {
        this.resourcesPath = resourcesPath;
    }
}
