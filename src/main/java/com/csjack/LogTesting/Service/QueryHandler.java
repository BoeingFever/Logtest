package com.csjack.LogTesting.Service;

import com.csjack.LogTesting.DB.DBManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service("QueryHandler")
public class QueryHandler implements ApiHandler{
//    private static final Logger log = LoggerFactory.getLogger(QueryHandler.class);
    @Autowired
    private DBManager myDBmanager;

    public void HelloWorld(){
        log.trace("method entry");
        Collections.emptyList();
        log.info("Helloworld name of this log is {}", log.getName());

        log.trace("method exit");
    }

    public String selectRecord(String SCAC, String convertTypeId, String TP_ID){
        log.trace("method entry");
        String ext_cde = null;
        try {
            log.info("why select record method can't show error logs");
            ext_cde = myDBmanager.dummySelect(SCAC, convertTypeId, TP_ID);
        } catch (NullPointerException ex){
            log.error("abc",ex);
        }
        log.trace("method exit");
        return ext_cde;
    }

    public Integer toDivide(int dividend, int divisor){
        log.trace("method entry");
        try {
            Integer result = dividend / divisor;
            log.debug("division result is {}", result);

            log.trace("method exit");
            return result;
        } catch (ArithmeticException ex) {
            log.error("division {} / {} failed due to", dividend, divisor, ex);

            log.trace("method exit");
            return null;
        }
    }
}
