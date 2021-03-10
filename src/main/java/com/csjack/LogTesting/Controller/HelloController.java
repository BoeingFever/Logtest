package com.csjack.LogTesting.Controller;

import com.csjack.LogTesting.Bean.TestBean;
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

import java.time.*;
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

    @Autowired
    @Qualifier("testBean")
    private TestBean testBean;

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

    // sample query url : localhost:8083/customers?search=lastName:doe,age>25
    // this controller method try
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

    @GetMapping(value = "/javatime", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap getJavaTimeTest() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        //Current Date
        try{
            LocalDate today = LocalDate.now();
            log.info("Current Date= {} ", today);

            //Creating LocalDate by providing input arguments
            LocalDate firstDay_2014 = LocalDate.of(2020, Month.JANUARY, 5);
            log.info("Specific Date= {}", firstDay_2014);

            //Try creating date by providing invalid inputs
            //LocalDate feb29_2014 = LocalDate.of(2014, Month.FEBRUARY, 29);
            //Exception in thread "main" java.time.DateTimeException:
            //Invalid date 'February 29' as '2014' is not a leap year

            //Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
            LocalDate todayKolkata = LocalDate.now(ZoneId.of("Asia/Kolkata"));
            System.out.println("Current Date in IST="+todayKolkata);

            //java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
            //LocalDate todayIST = LocalDate.now(ZoneId.of("IST"));

            //Getting date from the base date i.e 01/01/1970
            LocalDate dateFromBase = LocalDate.ofEpochDay(365);
            System.out.println("365th day from base date= "+dateFromBase);

            LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 100);
            System.out.println("100th day of 2014="+hundredDay2014);

            //Current Time
            LocalTime time = LocalTime.now();
            System.out.println("Current Time="+time);

            //Creating LocalTime by providing input arguments
            LocalTime specificTime = LocalTime.of(12,20,25,40);
            System.out.println("Specific Time of Day="+specificTime);


            //Try creating time by providing invalid inputs
            //LocalTime invalidTime = LocalTime.of(25,20);
            //Exception in thread "main" java.time.DateTimeException:
            //Invalid value for HourOfDay (valid values 0 - 23): 25

            //Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
            LocalTime timeKolkata = LocalTime.now(ZoneId.of("Asia/Kolkata"));
            System.out.println("Current Time in IST="+timeKolkata);

            //java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
            //LocalTime todayIST = LocalTime.now(ZoneId.of("IST"));

            //Getting date from the base date i.e 01/01/1970
            LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
            System.out.println("10000th second from 01/01/1970 00:00:00 = "+specificSecondTime);

            //Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
            LocalDateTime todayKolkata_LDT = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
            System.out.println("Current Local Date Time in IST="+todayKolkata_LDT);

            //java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
            //LocalDateTime todayIST = LocalDateTime.now(ZoneId.of("IST"));

            //Getting date from the base date i.e 01/01/1970
            LocalDateTime dateFromBase_LDT = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
            System.out.println("10000th second Local Date Time from 01/01/1970= "+dateFromBase_LDT);

            //Instant class is used to work with machine readable time format, it stores date time in unix timestamp. Let’s see it’s usage with a simple program.
            //Current timestamp
            Instant timestamp = Instant.now();
            System.out.println("Current Timestamp = "+timestamp);

            //Instant from timestamp
            Instant specificTimestamp = Instant.ofEpochMilli(timestamp.toEpochMilli());
            System.out.println("Specific Time = "+specificTimestamp);

            //Duration example
            Duration thirtyDay = Duration.ofDays(30);
            System.out.println(thirtyDay);
            
        }catch(Exception ex){
            log.error(ex.getMessage(),ex);
            result.put("errormsg", ex.getMessage());
        }
        return result;
    }

//    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @PostMapping(value = "/greeting", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
    public TestBean createDummy(@RequestBody Map<String, String> body){
        log.trace("method entry");

        String SCAC = body.get("SCAC");
        String convertTypeId = body.get("convertTypeId");
        String TP_ID = body.get("TP_ID");

        log.info("param1 is {}, param2 is {}, param3 is {}", SCAC, convertTypeId, TP_ID);

        //log.trace("This log name is {}", log.getName());
        //myhandler.toDivide(3,0);

        myhandler.selectRecord(SCAC, convertTypeId, TP_ID);

//        HashMap<String, String> result = new HashMap<String, String>();
//        result.put("say", "S");
//        result.put("it", "T");
//        result.put("loud", "D");

//        log.info("Result to return : {}", result.toString());

        //
        log.info("Result to return : {}", testBean.toString());
        log.trace("method exit");
        return testBean;
    }
}