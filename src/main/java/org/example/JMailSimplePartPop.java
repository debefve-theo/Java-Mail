package org.example;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class JMailSimplePartPop
{
    static String hostSmtp = "smtp.gmail.com"; // u2.tech.hepl.local
    static String portSmtp = "465";
    static String pass;

    public static void SendSimplePartMailPop (String sender, String receiver, String object, String message)
    {
        pass = Utils.getProperty("password");
        Properties prop = System.getProperties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.host", hostSmtp);
        prop.put("mail.smtp.port", portSmtp);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", portSmtp);
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        System.out.println("Création d'une session mail");
        Session sess = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(sender, pass);
            }
        });

        try {
            System.out.println("Création du message");
            MimeMessage msg = new MimeMessage(sess);
            msg.setFrom(new InternetAddress(sender));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            msg.setSubject(object);
            msg.setText(message);

            System.out.println("Envoi du message");
            Transport.send(msg);
            System.out.println("Message envoyé");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}