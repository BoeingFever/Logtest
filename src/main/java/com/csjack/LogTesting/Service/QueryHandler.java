package com.csjack.LogTesting.Service;

import com.csjack.LogTesting.DB.DBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("QueryHandler")
public class QueryHandler implements ApiHandler{
    private static final Logger logger = LoggerFactory.getLogger(QueryHandler.class);
    @Autowired
    private DBManager myDBmanager;

    public void HelloWorld(){
        logger.trace("method entry");

        logger.info("Helloworld name of this logger is {}", logger.getName());

        logger.trace("method exit");
    }

    public String selectRecord(String SCAC, String convertTypeId, String TP_ID){
        logger.trace("method entry");
        String ext_cde = null;
        try {
            logger.info("why select record method can't show error logs");
            ext_cde = myDBmanager.dummySelect(SCAC, convertTypeId, TP_ID);
        } catch (NullPointerException ex){
            logger.error("abc",ex);
        }
        logger.trace("method exit");
        return ext_cde;
    }

    public Integer toDivide(int dividend, int divisor){
        logger.trace("method entry");
        try {
            Integer result = dividend / divisor;
            logger.debug("division result is {}", result);

            logger.trace("method exit");
            return result;
        } catch (ArithmeticException ex) {
            logger.error("division {} / {} failed due to", dividend, divisor, ex);

            logger.trace("method exit");
            return null;
        }
    }
}
