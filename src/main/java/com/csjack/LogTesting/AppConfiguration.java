package com.csjack.LogTesting;

import com.csjack.LogTesting.Bean.TestBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    public AppConfiguration() {
        System.out.println("TestConfiguration init");
    }

    @Bean(name="testBean",initMethod="start",destroyMethod="cleanUp")
    @ConfigurationProperties(prefix = "testbean")
    public TestBean testBean() {
        return new TestBean();
    }
}
