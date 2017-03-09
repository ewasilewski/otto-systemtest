package de.otto.systemtest.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Property bundle to load properties.
 */
public class PropertyBundle {

        private static Properties properties;
        private static final String PROPERTY_FILE_NAME = "otto-systemtest.properties";

        static {
            properties = new Properties();
            try{
                InputStream in = PropertyBundle.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
                properties.load(in);
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException : " + PROPERTY_FILE_NAME+ " not found");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IOException : Can't read " + PROPERTY_FILE_NAME);
                e.printStackTrace();
            }
        }

        /**
        * Gets properties.
         *
        * @return Properties.
        */
        public static Properties getProperties() {
            return properties;
        }

}
