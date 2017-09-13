package com.simple.automation.autotest.web.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Simple Liang on 9/5/17.
 */
public class Config {
    private static Properties properties;
    private static final String CONFIG_DIR = getSystemProperty("autoConfigDir", "autotest-config");

    public static Boolean getBool(String key, Boolean defaultValue) {
        String value = getProperty(key);
        return value != null ? Boolean.valueOf(value) : defaultValue;
    }

    public static Integer getInt(String key, Integer defaultValue) {
        String value = getProperty(key);
        return value != null ? Integer.valueOf(value) : defaultValue;
    }

    public static Long getLong(String key, Long defaultValue) {
        String value = getProperty(key);
        return value != null ? Long.valueOf(value) : defaultValue;
    }

    public static Float getFloat(String key, Float defaultValue) {
        String value = getProperty(key);
        return value != null ? Float.valueOf(value) : defaultValue;
    }

    public static Double getDouble(String key, Double defaultValue) {
        String value = getProperty(key);
        return value != null ? Double.valueOf(value) : defaultValue;
    }

    public static String getString(String key, String defaultValue) {
        String value = getProperty(key);
        return value != null ? value : defaultValue;
    }

    public static String getProperty(String key) {
        try {
            if (properties == null) properties = loadProperties();
            return getSystemProperty(key, properties.getProperty(key));
        } catch(Exception e) {
            return null;
        }
    }

    public static String getSystemProperty(String key, String defaultValue) {
        return System.getProperty(key)!= null ? System.getProperty(key) : defaultValue;
    }

    private static Properties loadProperties() throws IOException {

        String baseConfigPath = CONFIG_DIR + "/base.properties";
        Properties base = loadProperties(baseConfigPath);

        String autoConfigPath = CONFIG_DIR + "/" + getSystemProperty("autoConfig", "dev") + ".properties";
        Properties custom = loadProperties(autoConfigPath);

        // merge custom properties into base properties
        base.putAll(custom);

        return base;
    }

    private static Properties loadProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = Config.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            if (inputStream != null) {
                properties.load(reader);
            } else {
                throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
            }
        }
        catch(Exception e) {
            Logger.logError("Exception: " + e);
        }
        finally {
            if(inputStream != null) inputStream.close();
        }

        return properties;
    }
}
