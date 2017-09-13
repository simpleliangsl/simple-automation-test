package com.simple.automation.autotest.common;


import java.net.URL;

/**
 * Created by Simple Liang on 9/8/17.
 */
public class SysUtil {

    public static void waitTime(long seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch(InterruptedException e){
            // swallow the Interrupted Exception
        }
    }

    public static boolean detectResource(String path) {
        // print classpath
        String classpath = System.getProperty("java.class.path");
        System.out.println("Classpath: " + classpath);

        // print resource path
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL resource = loader.getResource(path);
        System.out.println("Resource: " + loader.getResource(path));

        return resource != null;
    }
}
