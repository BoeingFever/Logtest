package com.csjack.LogTesting.Controller;

import com.csjack.LogTesting.Service.ApiHandler;
import com.csjack.LogTesting.Service.QueryHandler;
import lombok.extern.slf4j.Slf4j;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/")
public class HelloController {
//    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
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
        log.trace("method entry");

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

        log.info("ApiHandler.class.getSimpleName() : {}", ApiHandler.class.getSimpleName());
        log.info("ApiHandler.class.getCanonicalName() : {}", ApiHandler.class.getCanonicalName());
        //Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ERROR);
        String returnStr = "Greetings from Spring Boot!";
        log.debug("String to return : {}", returnStr);


        log.trace("method exit");
        return returnStr;
    }

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap getCustomer(@RequestParam(value = "search") String search) {

        // using a certain regex to match the input string, the regex needed to be compiled into object in advance
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");

        // use the regex object to match our input string, load the input into the matcher, then we can do whatever we want in the next step
        Matcher matcher = pattern.matcher(search + ",");
        log.info("fetching each key/value pair within the single request param \"search\" ");
        while(matcher.find()){
            log.info(matcher.group());
        }
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("status", "ok");

        return result;
    }
//    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @PostMapping(value = "/greeting", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
    public HashMap createDummy(@RequestBody Map<String, String> body){
        log.trace("method entry");

        String SCAC = body.get("SCAC");
        String convertTypeId = body.get("convertTypeId");
        String TP_ID = body.get("TP_ID");

        log.info("param1 is {}, param2 is {}, param3 is {}", SCAC, convertTypeId, TP_ID);

        //log.trace("This log name is {}", log.getName());
        //myhandler.toDivide(3,0);

        myhandler.selectRecord(SCAC, convertTypeId, TP_ID);

        HashMap<String, String> result = new HashMap<String, String>();
        result.put("say", "S");
        result.put("it", "T");
        result.put("loud", "D");

        log.info("Result to return : {}", result.toString());
        log.trace("method exit");
        return result;
    }
}