
package com.example.hospitalcrud.config;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Configuration {

    private static Configuration instance=null;
    private Properties p;

    private Configuration() {
        try {
            p = new Properties();
            p.load(Configuration.class.getClassLoader().getResourceAsStream("config/properties.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getInstance() {

        if (instance==null) {
            instance=new Configuration();
        }
        return instance;
    }

    public String getProperty(String key) {
        return p.getProperty(key);
    }
    public void setProperty(String key, String value) {
        p.setProperty(key, value);
        saveProperties();
    }

    public void saveProperties() {
        try (OutputStream output = new FileOutputStream("src\\main\\resources\\config\\properties.txt")) {
            p.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
