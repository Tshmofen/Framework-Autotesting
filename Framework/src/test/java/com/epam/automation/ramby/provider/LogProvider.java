package com.epam.automation.ramby.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogProvider {
    private static Logger log;

    public static Logger getLog() {
        if (log == null) {
            log = LogManager.getRootLogger();
        }
        return log;
    }
}
