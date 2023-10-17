package org.example;

import java.io.*;
import java.util.Properties;

/**
 * @author debefve-theo
 * @author NathanEVRARD
 */

public class Utils {
    static public void setProperty(String property, String value)
    {
        try
        {
            File file = new File("src/main/resources/prop.properties");
            Properties prop = new Properties();

            prop.load(new FileInputStream(file));

            prop.setProperty(property, value);

            prop.store(new FileOutputStream(file), null);
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
