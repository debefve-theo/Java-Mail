package org.example;

import javax.mail.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class JMailSimplePartRecv {
    static String host = "pop.gmail.com";
    static String charset = "utf-16";
    public static void main(String args[])
    {
        Properties prop = System.getProperties();
        prop.put("mail.pop3.host", host);
        prop.put("file.encoding", charset);
        prop.put("mail.disable.top", true);
        prop.put("mail.pop3.port", "995");
        prop.put("mail.pop3.socketFactory.port", "995");
        prop.put("mail.pop3.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.put("mail.transport.protocol", "pop3");

        String user = "nathan.evrard2002@gmail.com";
        String pass = "gagm ftks aghx oqnq";

        Session sess = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(user, pass);
            }
        });

        try
        {
            Store st = sess.getStore("pop3");
            st.connect(host, user, pass);
            Folder f = st.getFolder("INBOX");
            f.open(Folder.READ_ONLY);

            Message msg[] = f.getMessages();
            System.out.println("Liste des messages : ");

            for(int i = 0; i < msg.length; i++)
            {
                System.out.println("Expéditeur : " + msg[i].getFrom()[0]);
                System.out.println("Sujet : " + msg[i].getSubject());
                System.out.println("Date : " + msg[i].getSentDate());
                System.out.println("---------------------------------------------------------");

                if(msg[i].isMimeType("text/plain"))
                {
                    System.out.println("Texte : " + (String)msg[i].getContent());
                }
                else if(msg[i].isMimeType("multipart/*"))
                {
                    System.out.println("MULTIPART");
                    Multipart msgMP = (Multipart) msg[i].getContent();
                    int np = msgMP.getCount();
                    System.out.println("np : " + np);
                    for(int j = 0; j < np; j++)
                    {
                        System.out.println("num : " + j);
                        Part p = msgMP.getBodyPart(j);
                        String d = p.getDisposition();
                        if(p.isMimeType("text/plain"))
                            System.out.println("Texte : " + (String)p.getContent());
                        if(d != null && d.equalsIgnoreCase(Part.ATTACHMENT))
                        {
                            InputStream is = p.getInputStream();
                            String nf = p.getFileName();
                            System.out.println(nf);
                            File file = new File("C:\\Users\\natha\\Documents\\MesCours\\B3\\Q1\\E-Commerce\\Labo\\Java-Mail\\src\\main\\java\\org\\example\\pa\\" + nf);
                            file.createNewFile();
                            FileOutputStream fos = new FileOutputStream(file);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            int c;
                            while((c = is.read()) != -1)
                                baos.write(c);
                            baos.flush();
                            baos.writeTo(fos);
                            fos.close();

                            System.out.println("Pièce n° " + nf + " récupérée");
                        }
                    }
                }

                System.out.println("---------------------------------------------------------");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
