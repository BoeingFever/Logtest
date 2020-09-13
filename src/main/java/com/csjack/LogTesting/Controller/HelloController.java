package com.csjack.LogTesting.Controller;

import com.csjack.LogTesting.Service.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private QueryHandler myhandler;

    private class TESTB{
        Integer ab;
        public List<String> tempstr;

    }
//    @RequestMapping("/")
    @GetMapping(value = "/greeting", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGreetings() {
        logger.trace("method entry");

        TESTB a = new TESTB();
        a.ab = 5; a.tempstr = new ArrayList<>();
        a.tempstr.add("Mum");a.tempstr.add("Dad");
        TESTB bc = new TESTB();
        bc.tempstr = new ArrayList<>(); bc.tempstr.add("Son");
        System.out.println(bc.tempstr.toString());

        bc.tempstr.addAll(a.tempstr);
        bc.tempstr.set(2, "gram"); bc.tempstr.add("Son");

        System.out.println(bc.tempstr.toString());
        System.out.println(a.tempstr.toString());
        //Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ERROR);
        String returnStr = "Greetings from Spring Boot!";
        logger.debug("String to return : {}", returnStr);

        logger.trace("method exit");
        return returnStr;
    }

//    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap createDummy(@RequestBody Map<String, String> body){
        logger.trace("method entry");

        String SCAC = body.get("SCAC");
        String convertTypeId = body.get("convertTypeId");
        String TP_ID = body.get("TP_ID");

        logger.info("param1 is {}, param2 is {}, param3 is {}", SCAC, convertTypeId, TP_ID);

        //myhandler.HelloWorld();

        //logger.trace("This logger name is {}", logger.getName());
        //myhandler.toDivide(3,0);

        myhandler.selectRecord(SCAC, convertTypeId, TP_ID);

        HashMap<String, String> result = new HashMap<String, String>();
        result.put("say", "S");
        result.put("it", "T");
        result.put("loud", "D");

        logger.info("Result to return : {}", result.toString());
        logger.trace("method exit");
        return result;
    }
}