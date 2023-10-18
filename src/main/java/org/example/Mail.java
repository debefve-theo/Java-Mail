package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author debefve-theo
 * @author NathanEVRARD
 */

public class Mail {
    private String object;
    private String sender;
    private String receiver;
    private String date;
    private String message;
    private ArrayList<Attachment> attachments = new ArrayList<>();
    private ArrayList<String> headers = new ArrayList<>();

    public Mail()
    {
        this.object = "";
        this.sender = "";
        this.receiver = "";
        this.message = "";
        setDate(new Date());
    }
    public Mail(String object, String sender, String receiver, String date, String message) {
        super();
        this.object = object;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        setDate(date);
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public void setDate(String date)
    {
        try
        {
            Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            this.date = d.toString();
        }
        catch(ParseException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void setDate(Date date)
    {

        this.date = new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
    public String getDate(){return date;}
    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }

    public ArrayList<String> getHeaders() {
        return headers;
    }

    public void setHeaders(ArrayList<String> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return getObject();
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    String getDisplayText() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
