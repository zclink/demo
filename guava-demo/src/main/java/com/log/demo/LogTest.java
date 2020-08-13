package com.log.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    private static Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        System.exit(1);
        logger.info("slf4j for info");
        logger.debug("slf4j for debug");
        logger.error("slf4j for error");
        logger.warn("slf4j for warn");

        String message = "Hello SLF4J";
        logger.info("slf4j message is : {}", message);
    }
}
