package com.simple.automation.autotest.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Simple Liang on 9/13/17.
 */
public class Context {

    private static Map contextMap = new HashMap();

    public static void put(String key, Object value) {
        contextMap.put(formatKey(key), value);
    }

    public static Object get(String key) {
        return contextMap.get(formatKey(key));
    }

    public static boolean has(String key) {
        return contextMap.containsKey(formatKey(key));
    }

    synchronized private static String formatKey(String key) {
        long threadId = Thread.currentThread().getId();
        return key+"-"+threadId;
    }
}
