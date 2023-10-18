package org.example;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

/**
 * @author debefve-theo
 * @author NathanEVRARD
 */

public class Attachment {
    private String nom;
    private Part part;

    public Part getPart() {
        return part;
    }
    private String path;
    private byte[] bytes;

    public void setPart(Part part) {
        this.part = part;
    }

    public Attachment(String nom, Part part) {
        this.nom = nom;
        this.part = part;
        this.path = path;
        this.bytes = bytes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void download()
    {
        try
        {
            InputStream is = part.getInputStream();
            String nf = part.getFileName();
            System.out.println(nf);
            File file = new File("src/main/java/org/example/pa/" + nf);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int c;
            while((c = is.read()) != -1)
                baos.write(c);
            baos.flush();
            baos.writeTo(fos);
            fos.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBytes() {
        return bytes;
    }
    @Override
    public String toString() {
        return getNom();
    }
}
