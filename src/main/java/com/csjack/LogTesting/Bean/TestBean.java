package com.csjack.LogTesting.Bean;

public class TestBean {
    private String username;

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
}
