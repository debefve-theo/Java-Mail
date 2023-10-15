package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Mail {
    private String object;
    private String sender;
    private String receiver;
    private Date date;
    private String message;
    private ArrayList<Piece> pieces = new ArrayList<>();

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
            this.date = new SimpleDateFormat("dd/mm/yyyy").parse(date);
        }
        catch(ParseException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }

    @Override
    public String toString() {
        return getObject();
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    String getDisplayText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
