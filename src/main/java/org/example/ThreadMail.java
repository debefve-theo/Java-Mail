package org.example;

import java.util.ArrayList;

import static org.example.Utils.getProperty;

public class ThreadMail extends Thread{
    private final MainWindow parent;

    public ThreadMail(MainWindow pa) {
        parent = pa;
    }

    ArrayList<Mail> mails = null;

    @Override
    public void run() {
        int nbMail = 0;

        while(true){

            try
            {
                parent.jList2.setModel(new javax.swing.AbstractListModel<Mail>() {
                    ArrayList<Mail> strings = JMailReceive.getMails();
                    public int getSize() { return strings.size(); }
                    public Mail getElementAt(int i) { return strings.get(i); }
                });
                nbMail = Integer.parseInt(String.valueOf(parent.jList2.getSize()));
                System.out.println("Thread");
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

            try
            {
                int i = Integer.parseInt(getProperty("timing"));
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
