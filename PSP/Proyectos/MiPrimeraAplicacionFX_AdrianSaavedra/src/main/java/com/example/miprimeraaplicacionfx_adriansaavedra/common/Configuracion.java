package com.example.miprimeraaplicacionfx_adriansaavedra;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
    public String loadPathProperties(){
        Properties properties= new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            properties.load(input);
            return (String)properties.get("pathJson");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(System.out);
        }
        return null;//path no encontrado
    }

    public String loadPathPropertiesGroup(){
        Properties properties= new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            properties.load(input);
            return (String)properties.get("pathJsonGroup");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(System.out);
        }
        return null;//path no encontrado
    }
}
