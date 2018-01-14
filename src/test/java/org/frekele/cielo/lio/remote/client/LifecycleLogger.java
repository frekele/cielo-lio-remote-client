package org.frekele.cielo.lio.remote.client;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.logging.Logger;

public interface LifecycleLogger {

    static final Logger logger = Logger.getLogger(LifecycleLogger.class.getName());

    @BeforeClass
    default void beforeClass() throws Exception {
        logger.info(this.getClass().getCanonicalName() + ": @BeforeClass");
    }

    @BeforeMethod
    default void beforeMethod() throws Exception {
        logger.info(this.getClass().getCanonicalName() + ": @BeforeMethod");
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    default void afterMethod() throws Exception {
        logger.info(this.getClass().getCanonicalName() + ": @AfterMethod");
    }

    @AfterClass
    default void afterClass() throws Exception {
        logger.info(this.getClass().getCanonicalName() + ": @AfterClass");
    }
}
