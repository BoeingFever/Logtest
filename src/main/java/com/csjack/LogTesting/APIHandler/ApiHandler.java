package com.csjack.LogTesting.APIHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ApiHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiHandler.class);

    abstract public void HelloWorld();
    abstract public Integer toDivide(int dividend, int divisor);
}
