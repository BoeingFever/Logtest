package com.csjack.LogTesting.Controller;

import com.csjack.LogTesting.APIHandler.QueryHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private static QueryHandler myhandler = new QueryHandler();

    @RequestMapping("/get")
    public String test_get() {
        logger.trace("method entry");

        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ERROR);
        String returnStr = "Greetings from Spring Boot!";
        logger.debug("String to return : {}", returnStr);


        logger.trace("method exit");
        return returnStr;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public HashMap test_post(@RequestBody Map<String, String> body){
        logger.trace("method entry");

        String SCAC = body.get("SCAC");
        String convertTypeId = body.get("convertTypeId");
        String TP_ID = body.get("TP_ID");

        logger.debug("param1 is {}, param2 is {}, param3 is {}", SCAC, convertTypeId, TP_ID);

        //myhandler.HelloWorld();

        //logger.trace("This logger name is {}", logger.getName());
        //myhandler.toDivide(3,0);

        myhandler.selectRecord(SCAC, convertTypeId, TP_ID);

        HashMap<String, String> result = new HashMap<String, String>();
        result.put("say", "S");
        result.put("it", "T");
        result.put("loud", "D");

        logger.debug("Result to return : {}", result.toString());
        logger.trace("method exit");
        return result;
    }

}


