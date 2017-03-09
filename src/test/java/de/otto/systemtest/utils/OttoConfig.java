package de.otto.systemtest.utils;

import java.util.Properties;

/**
 * Global application config based on properties.
 */
public class OttoConfig {

    private static Properties properties = PropertyBundle.getProperties();

    /**
     * Gets application root url.
     *
     * @return Application root url
     */
    public static String getRootURL() {
        return properties.getProperty("url.root");
    }

    /**
     * Gets category url.
     *
     * @return Category url
     */
    public static String getCategoryURL() {
        return getRootURL() + properties.getProperty("url.category");
    }
}
