package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.Properties;

/**
 * @author debefve-theo
 * @author NathanEVRARD
 */

public class Utils {
    static String path = "src/main/resources/prop.properties";
    static public void setProperty(String property, String value)
    {
        try
        {
            File file = new File(path);
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

    static public String OpenFileviaExplorer()
    {
        try
        {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File("."));

            int result = fileChooser.showOpenDialog(null);

            if(result == JFileChooser.APPROVE_OPTION)
            {
                System.out.println("Ouverture réussie");
                return fileChooser.getSelectedFile().getAbsolutePath();
            }
            else if(result == JFileChooser.CANCEL_OPTION)
            {
                System.out.println("Cancelled");
                return "";
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
        return "";
    }
}
