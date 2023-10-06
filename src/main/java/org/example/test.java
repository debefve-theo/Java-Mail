package org.example;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class test {

    static String hostSmtp = "smtp.gmail.com"; // u2.tech.hepl.local
    static String hostPop3 = "pop.gmail.com"; // u2.tech.hepl.local
    static String portSmtp = "465";
    static String portPop3 = "995";
    static String charset = "iso-8859-1";

    public static void main(String[] args) {
        final String user = "theo.debefve@gmail.com"; // Remplacez par votre adresse Gmail
        final String pass = "wege qdnc eubr dtma"; // Remplacez par votre mot de passe Gmail

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
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            System.out.println("Création du message");
            MimeMessage msg = new MimeMessage(sess);
            msg.setFrom(new InternetAddress(user));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user));
            msg.setSubject("Test");
            msg.setText("Hello world");

            System.out.println("Envoi du message");
            Transport.send(msg);
            System.out.println("Message envoyé");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
