package org.example;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class JMailSimplePart
{
    static String host = "u2.tech.hepl.local";

    public static boolean SendSimplePartMail (String sender, String receiver, String object, String message)
    {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", host);
        Session sess = Session.getDefaultInstance(prop, null);

        try {
            MimeMessage msg = new MimeMessage(sess);

            msg.setFrom(new InternetAddress(sender));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            msg.setSubject(object);
            msg.setText(message);

            Transport.send(msg);
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Errreur sur message : " + e.getMessage());
            return false;
        }
    }
}

/*
Rick Wallace
19 Avril 1982
Homme
rickw190482@gmail.com
4D@jq&8kGjs9ia65
 */