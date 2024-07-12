package com.mynotes.data;

import com.mynotes.utils.PropertiesLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class DataFromPropertyFiles {

    protected static final Properties properties = PropertiesLoader.getProperties();
}
