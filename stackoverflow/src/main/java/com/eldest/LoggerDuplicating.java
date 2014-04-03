package com.eldest;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

public class LoggerDuplicating {

    @Test
    public void testIt() {
        MyClass instance = new MyClass();   // INITIALIZE MY CLASS WITH LOGGER
        MyClass instance2 = new MyClass();   // INITIALIZE MY CLASS WITH LOGGER

        instance.testMethod1();
        instance.testMethod2();
    }



    public static class MyClass {
        public static Logger logger;

        public MyClass() {
            logger = null; //Debug, info, warning, error, fatal
            logger = Logger.getRootLogger();
            BasicConfigurator.configure();
            logger.setLevel(Level.INFO);
        }

        public void testMethod1() {
            logger.info("Applying Resources");
        }

        public void testMethod2() {
            logger.info("Getting the entity of the URL response");
        }
    }


}
