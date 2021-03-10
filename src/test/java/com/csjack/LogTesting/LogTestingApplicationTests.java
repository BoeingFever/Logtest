package com.csjack.LogTesting;

import com.csjack.LogTesting.Bean.TestBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LogTestingApplicationTests {

	@Autowired
	@Qualifier("testBean")
	private TestBean tb;

	@Test
	void contextLoads() {
	}

	@Test
	void verifyBeanProperties(){
		assertEquals("RogerGor", tb.getUsername());

		Map<String, String> expectedResourcesPath = new HashMap<>();
		expectedResourcesPath.put("imgs", "/root/imgs");
		assertEquals(expectedResourcesPath, tb.getResourcesPath());
	}
}
