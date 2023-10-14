package org.example;

import java.io.*;
import java.util.Properties;

public class Utils {
    static public void setProperty(String property, String value)
    {
        try
        {
            OutputStream output = new FileOutputStream("src/main/resources/prop.properties");
            Properties prop = new Properties();

            prop.setProperty(property, value);

            prop.store(output, null);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    static public String getProperty(String property)
    {
        try
        {
            InputStream input = new FileInputStream("src/main/resources/prop.properties");
            Properties prop = new Properties();

            prop.load(input);

            return prop.getProperty(property);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
