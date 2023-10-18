package org.example;

import javax.mail.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author debefve-theo
 * @author NathanEVRARD
 */

public class JMailReceive {
    static String host = "pop.gmail.com";
    static String charset = "utf-16";
    String receiver;
    String password;

    public JMailReceive(String receiver, String password)
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

            for(int i = msg.length - 1; i >= 0; i--)
            {
                Mail mail = new Mail();
                mail.setSender(msg[i].getFrom()[0].toString());
                mail.setReceiver(receiver);
                mail.setDate(msg[i].getSentDate());
                mail.setObject(msg[i].getSubject());

                Enumeration e = msg[i].getAllHeaders();
                ArrayList<Header> headers = new ArrayList<>();
                headers.add((Header)e.nextElement());

                while(e.hasMoreElements())
                {
                    headers.add((Header)e.nextElement());
                }
                ArrayList<String> array = new ArrayList<>();
                for(Header h : headers)
                {
                    if(h.getName().equals("Received"))
                    {
                        String temp[] = h.getValue().split(" ");
                        if(temp[0].equals("from"))
                            array.add(temp[1]);
                    }
                }
                mail.setHeaders(array);
                System.out.println("Mail -" + i + "-");
                if(msg[i].isMimeType("text/plain"))
                {
                    mail.setMessage((String)msg[i].getContent());
                }
                else if(msg[i].isMimeType("multipart/*"))
                {
                    Multipart msgMP = (Multipart) msg[i].getContent();
                    int np = msgMP.getCount();
                    for(int j = 0; j < np; j++)
                    {
                        Part p = msgMP.getBodyPart(j);
                        String d = p.getDisposition();
                        if(p.isMimeType("text/plain"))
                        {
                            mail.setMessage((String)p.getContent());
                        }
                        if(d != null && d.equalsIgnoreCase(Part.ATTACHMENT))
                        {
                            InputStream is = p.getInputStream();
//                            int c;
//                            while((c = is.read()) != -1)
//                            baos.write(c);
//                            baos.flush();
//                            baos.writeTo(bytes);
                            mail.getAttachments().add(new Attachment(p.getFileName(), p));
//                            InputStream is = p.getInputStream();
//                            String nf = p.getFileName();
//                            System.out.println(nf);
//                            File file = new File("org/example/pa" + nf);
//                            file.createNewFile();
//                            FileOutputStream fos = new FileOutputStream(file);
//                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                            int c;
//                            while((c = is.read()) != -1)
//                                baos.write(c);
//                            baos.flush();
//                            baos.writeTo(fos);
//                            fos.close();
//
//                            System.out.println("Pièce n° " + nf + " récupérée");
                        }
                    }
                }
                mails.add(mail);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return mails;
    }
}
