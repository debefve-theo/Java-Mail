package org.example;

import javax.mail.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Properties;

public class JMailSimplePartRecv {
    static String host = "pop.gmail.com";
    static String charset = "utf-16";
    String receiver;
    String password;

    public JMailSimplePartRecv(String receiver, String password)
    {
        this.receiver = receiver;
        this.password = password;
    }

    ArrayList<Mail> getMails()
    {
        ArrayList<Mail> mails = new ArrayList<>();
        Properties prop = System.getProperties();
        prop.put("mail.pop3.host", host);
        prop.put("file.encoding", charset);
        prop.put("mail.disable.top", true);
        prop.put("mail.pop3.port", "995");
        prop.put("mail.pop3.socketFactory.port", "995");
        prop.put("mail.pop3.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.put("mail.transport.protocol", "pop3");

        Session sess = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(receiver, password);
            }
        });

        try
        {
            Store st = sess.getStore("pop3");
            st.connect(host, receiver, password);
            Folder f = st.getFolder("INBOX");
            f.open(Folder.READ_ONLY);

            Message msg[] = f.getMessages();

            for(int i = msg.length - 1; 1 >= 0; i--)
            {
                if(msg[i].isMimeType("text/plain"))
                    mails.add(new Mail(msg[i].getSubject(), msg[i].getFrom()[0].toString(), receiver, msg[i].getSentDate().toString(), (String)msg[i].getContent()));
//                else if(msg[i].isMimeType("multipart/*"))
//                {
//
//                }
                /*System.out.println("Expéditeur : " + msg[i].getFrom()[0]);
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
                }*/

                System.out.println(i);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return mails;
    }
}
