package com.csjack.LogTesting.Controller;

import com.csjack.LogTesting.Service.ApiHandler;
import com.csjack.LogTesting.Service.QueryHandler;
import com.sun.tools.javac.util.DefinedBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    @Qualifier("QueryHandler")
    private ApiHandler myhandler;

    public class TESTB{
        Integer ab;
        public List<String> tempstr;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String initialize(){
        myhandler.HelloWorld();
        return "initialized";
    }

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

        logger.info("ApiHandler.class.getSimpleName() : {}", ApiHandler.class.getSimpleName());
        logger.info("ApiHandler.class.getCanonicalName() : {}", ApiHandler.class.getCanonicalName());
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