package com.paschua.dbchecker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author fernando
 */
public class PropLoader {

    private static final String DEFAULT_PROPERTIES = "default.properties";

    public static Properties loadPropertiesFile(String path) throws IOException, FileNotFoundException {
        Properties properties = new Properties();
        if (path == null || path.equals("")) {
            properties.load(loadDefault());
        } else {
            properties.load(load(path));
        }
        return properties;
    }

    private static InputStream loadDefault() throws IOException, FileNotFoundException {
        InputStream input = PropLoader.class.getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES);
        if (input == null) {
            throw new FileNotFoundException("Property file not found: " + DEFAULT_PROPERTIES);
        }
        return input;
    }

    private static InputStream load(String path) throws FileNotFoundException {
        InputStream input = new FileInputStream(path);
        if (input == null) {
            throw new FileNotFoundException("Property file not found: " + path);
        }
        return input;
    }
}
