package com.log.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }



    private static Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void logTest() {
        logger.info("slf4j for info");
        logger.debug("slf4j for debug");
        logger.error("slf4j for error");
        logger.warn("slf4j for warn");

        String message = "Hello SLF4J";
        logger.info("slf4j message is : {}", message);
    }
}
