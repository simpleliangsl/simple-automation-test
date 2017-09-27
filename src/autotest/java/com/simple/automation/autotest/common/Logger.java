package com.simple.automation.autotest.common;

import org.joda.time.DateTime;

import java.text.MessageFormat;

/**
 * Created by Simple Liang on 9/5/17.
 */
public class Logger {

    private static final String INFO = "INFO";
    private static final String WARNING = "WARNING";
    private static final String ERROR = "ERROR";
    private static final String SUCCESS = "SUCCESS";
    private static final String ACTION = "ACTION";

    public static void logInfo(String pattern, Object ... arguments){
        log(INFO, pattern, arguments);
    }

    public static void logWarning(String pattern, Object ... arguments){
        log(WARNING, pattern, arguments);
    }

    public static void logError(String pattern, Object ... arguments){
        log(ERROR, pattern, arguments);
    }

    public static void logSuccess(String pattern, Object ... arguments){
        log(SUCCESS, pattern, arguments);
    }

    public static void logAction(String pattern, Object ... arguments){
        log(ACTION, pattern, arguments);
    }

    public static void logInfo(String message){
        log(INFO, message);
    }

    public static void logWarning(String message){
        log(WARNING, message);
    }

    public static void logError(String message){
        log(ERROR, message);
    }

    public static void logSuccess(String message){
        log(SUCCESS, message);
    }

    public static void logAction(String message){
        log(ACTION, message);
    }

    private static void log(String logType, String pattern, Object ... arguments){
        String formattedMessage = MessageFormat.format(pattern, arguments);
        log(logType, formattedMessage);
    }

    private static void log(String logType, String message){
        System.out.println(MessageFormat.format("[{0}][{1}] {2}", new DateTime().toString(), logType, message));
    }
}
