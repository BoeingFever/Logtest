package com.csjack.LogTesting.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ApiHandler {
    public void HelloWorld();
    public Integer toDivide(int dividend, int divisor);
    public String selectRecord(String SCAC, String convertTypeId, String TP_ID);
}
