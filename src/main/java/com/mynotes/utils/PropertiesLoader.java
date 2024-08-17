package com.mynotes.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesLoader {
    private static final String PATH_TO_COMMON_PROPERTIES = "properties/common.properties";

    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.putAll(loadCommonProperties());
        properties.putAll(loadEnvironmentSpecificProperties());
        return properties;
    }

    private static Properties loadCommonProperties() {
        return loadProperties(PATH_TO_COMMON_PROPERTIES);
    }

    private static Properties loadEnvironmentSpecificProperties() {
        String environment = System.getProperty("env");
        if (environment == null) {
            throw new IllegalStateException("Environment system property ('env') not specified");
        }

        String propertyFilePath = String.format("properties/%s.properties",environment);
        return loadProperties(propertyFilePath);
    }

    private static Properties loadProperties (String propertyFilePath) {
        Properties properties = new Properties();

        try (InputStream commonInput = PropertiesLoader.class.getClassLoader().getResourceAsStream(propertyFilePath)) {
            if (commonInput == null) {
                throw new FileNotFoundException(String.format("Cannot find a property file by path:'%s'", propertyFilePath));
            }
            properties.load(commonInput);
        } catch (IOException e) {
            throw new ConfigurationException("Error loading properties from file: " + propertyFilePath, e);
        }
        return properties;
    }

    public static class ConfigurationException extends RuntimeException {
        public ConfigurationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}