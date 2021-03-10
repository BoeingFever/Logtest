package com.csjack.LogTesting;

import com.csjack.LogTesting.Bean.TestBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;


import java.util.Collections;
import java.util.HashMap;
@Slf4j
@SpringBootApplication
//@PropertySource("classpath:application.yml")
public class Application {

    static Marker fatal = MarkerFactory.getMarker("FATAL");

    //private static final Logger log = LoggerFactory.getLogger(Application.class);
    //private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        try{
//            SpringApplication app = new SpringApplication(Application.class);
//            app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
//            app.run(args);
            SpringApplication.run(Application.class, args);
            log.info("random");
        } catch(Throwable e){
            //logger.error(fatal, "Critical Problem occurred", e);
            log.trace("app main exited");
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String a = "1";
            String b = "2";
            Integer c = 3;
            //logger.debug("name of this logger is {}", logger.getName());
            HashMap<String, String> testmap = new HashMap<>();
            testmap.put("key1","Test Spring Boot");
            testmap.put("key2", "Successfully started");
            testmap.put("key3", "in 3 2 1 ");
            log.info("Test Spring Boot Successfully started : \nin {} {} {}", a, b, c);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(testmap);
            log.info(json);
            log.info(System.getProperty("java.class.path"));
            checkBeansPresence(ctx, "QueryHandler", "DBManager", "TESTB", "testBean");

            TestBean tb = (TestBean) ctx.getBean("testBean");
            tb.sayHello(tb.mycallback());
            System.out.println(tb);

            TestBean tb2 = (TestBean) ctx.getBean("testBean");
            tb2.sayHello(tb.mycallback());
            System.out.println(tb2);
            System.out.println("username " + tb2.getUsername() + " resourcesPath "+ tb2.getResourcesPath());
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                log.info(beanName);
//            }
        };
    }

    private void checkBeansPresence(ApplicationContext ctx, String... beans) {
        for (String beanName : beans) {
            log.info("Is {} in ApplicationContext: {}",
                    beanName,ctx.containsBean(beanName));
        }
    }
}