package org.example;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author theod
 */
public class MailRenderer extends DefaultListCellRenderer {
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Cast value to your email object type
        Mail email = (Mail) value;

        // Set the text you want to display for each item
        setText(email.getDisplayText());

        // Customize other visual aspects based on the email object properties
        // For example, you might set icons, colors, or font styles here based on email properties

        // Return the customized component
        return this;
    }
}
