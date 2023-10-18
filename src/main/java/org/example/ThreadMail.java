package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.example.Utils.getProperty;

public class ThreadMail extends Thread{
    private final MainWindow parent;
    private int nbMails;
    public ThreadMail(MainWindow pa) {
        parent = pa;
    }

    @Override
    public void run() {

        while(true){
                nbMails = parent.jList2.getModel().getSize();
                ArrayList<Mail> mails1 = JMailReceive.getMails(nbMails);
                ArrayList<Mail> mails2 = new ArrayList<>();
                for(int i = 0; i < mails1.size(); i++)
                {
                    mails2.add(mails1.get(i));
                }
                for(int i = 0; i < parent.jList2.getModel().getSize(); i++)
                {
                    mails2.add(parent.jList2.getModel().getElementAt(i));
                }
            try
            {
                parent.jList2.setModel(new javax.swing.AbstractListModel<Mail>() {
                    ArrayList<Mail> strings = mails2;
                    public int getSize() { return strings.size(); }
                    public Mail getElementAt(int i) { return strings.get(i); }
                });

                System.out.println("Thread");
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

            try
            {
                Thread.sleep((Long.parseLong(getProperty("timing"))) * 1000);
            }
            catch (InterruptedException e)
            {
                System.out.println("EXCEPTION Interruption du thread mail");
                Thread.currentThread().interrupt();
            }
        }
    }
}
