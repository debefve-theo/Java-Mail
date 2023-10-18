package org.example;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

/**
 * @author debefve-theo
 * @author NathanEVRARD
 */

public class JMailSend {

    static String hostSmtp = "smtp.gmail.com"; // u2.tech.hepl.local
    static String portSmtp = "465";
    static String charset = "utf-16";


    public static void SendMail (Mail m, boolean multiPart)
    {
        Properties prop = System.getProperties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.host", hostSmtp);
        prop.put("mail.smtp.port", portSmtp);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", portSmtp);
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        prop.put("file.encoding", charset);

        System.out.println("Création d'une session mail");

        String pass = Utils.getProperty("password");

        Session sess = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(m.getSender(), pass);
            }
        });

        if (multiPart)
        {
            try
            {
                MimeMessage msg = new MimeMessage (sess);

                msg.setFrom (new InternetAddress (m.getSender()));
                msg.setRecipient (Message.RecipientType.TO, new InternetAddress (m.getReceiver()));
                msg.setSubject(m.getObject());

                System.out.println("Début construction du multipart");
                Multipart msgMP = new MimeMultipart();
                System.out.println("1ère composante");
                MimeBodyPart msgBP = new MimeBodyPart();
                msgBP.setText(m.getMessage());
                msgMP.addBodyPart(msgBP);

                for(Attachment a : m.getPieces())
                {
                    String nf = a.getPath();
                    msgBP = new MimeBodyPart();
                    DataSource so = new FileDataSource (nf);
                    msgBP.setDataHandler (new DataHandler (so));
                    msgBP.setFileName(a.getNom());
                    msgMP.addBodyPart(msgBP);
                }

                msg.setContent(msgMP);

                System.out.println("Envoi du message");
                Transport.send(msg);
                System.out.println("Message envoyé");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            try {
                System.out.println("Création du message");
                MimeMessage msg = new MimeMessage(sess);
                msg.setFrom(new InternetAddress(m.getSender()));
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(m.getReceiver()));
                msg.setSubject(m.getObject());
                msg.setText(m.getMessage());

                System.out.println("Envoi du message");
                Transport.send(msg);
                System.out.println("Message envoyé");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

