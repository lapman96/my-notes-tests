package com.mynotes.data;

import com.mynotes.utils.PropertiesLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndpointsRoutes {
    private static final Properties properties = PropertiesLoader.getProperties();

    private static final String USERS = "/users";

    private static final String NOTES = "/notes";

    public static String getBaseUrl() {return properties.getProperty("expand_testing_api_base_url");}

    public static String login() {return USERS + "/login";}

    public static String notes() {return NOTES;}

    public static String noteById(String noteId) {return NOTES + "/" + noteId;}
}
