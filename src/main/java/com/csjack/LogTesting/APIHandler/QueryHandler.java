package com.csjack.LogTesting.APIHandler;

import com.csjack.LogTesting.DB.DBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryHandler extends ApiHandler{
    private static final Logger logger = LoggerFactory.getLogger(QueryHandler.class);
    private DBManager myDBmanager = new DBManager();

    public void HelloWorld(){
        logger.trace("method entry");

        logger.trace("name of this logger is {}", logger.getName());

        logger.trace("method exit");
    }

    public String selectRecord(String SCAC, String convertTypeId, String TP_ID){
        logger.trace("method entry");
        String ext_cde = null;
//        try {
//            logger.info("why select record method can't show error logs");
//            ext_cde = myDBmanager.selectRecord(SCAC, convertTypeId, TP_ID);
//        } catch (SQLException ex){
//            logger.error("abc",ex);
//        } catch (NullPointerException ex){
//            logger.error("abc",ex);
//        }
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
