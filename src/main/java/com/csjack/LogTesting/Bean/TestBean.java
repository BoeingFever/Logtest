package com.csjack.LogTesting.Bean;

public class TestBean {
    private String username;

    public void sayHello() {
        System.out.println("TestBean sayHello...");
    }

    public void start() {
        System.out.println("TestBean init");
    }

    public void cleanUp() {
        System.out.println("TestBean destroy");
    }
}
