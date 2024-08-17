package com.mynotes.data;

import com.mynotes.utils.PropertiesLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PropertiesManager {


    protected static final Properties properties = PropertiesLoader.getProperties();

    public static final String IS_REMOTE_RUN = System.getProperty("run_remotely");

    public static final String TEST_BROWSER = System.getProperty("browser");
}
