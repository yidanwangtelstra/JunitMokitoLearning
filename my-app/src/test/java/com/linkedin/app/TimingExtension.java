package com.linkedin.app;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.logging.Logger;

public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final Logger logger = Logger.getLogger(TimingExtension.class.getName());

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        // we will log the start time of the test method and store it in the ExtensionContext
        logger.info(() -> String.format("Starting execution of test method: %s , in class: %s, method name: %s",
                context.getDisplayName(), context.getRequiredTestClass().getName(), context.getRequiredTestMethod().getName()));
        context.getStore(ExtensionContext.Namespace.GLOBAL).put("start_time", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception{
        // retrieve the start time from the ExtensionContext and calculate the execution time
        long startTime = context.getStore(ExtensionContext.Namespace.GLOBAL).remove("start_time", long.class);
        long executionTime = System.currentTimeMillis() - startTime;
        logger.info(() -> String.format("Finished execution of the test method: %s in %d ms", context.getDisplayName(), executionTime));
    }
}
