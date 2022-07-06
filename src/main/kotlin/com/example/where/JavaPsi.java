package com.example.where;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class JavaPsi {
    public static void main(String[] args) {
        // Count Args
        int argsLength = args.length;
        Runnable sayArgsLength = new Runnable() {
            @Override
            public void run() {
                System.out.println("Length: " + argsLength );
            }
        };
        sayArgsLength.run();

        // Names
        String amanda = "Amanda";
        String amandaAndIan = amanda + " and Ian";

        // TextFields
        JTextPane textName = new JTextPane();
        JTextPane textPassword = new JTextPane();
        JTextPane textEmail = new JTextPane();
        JTextPane textPhone = new JTextPane();
        JTextPane textAddress = new JTextPane();
        JTextPane textCity = new JTextPane();
        JButton buttonSubmit = new JButton("Submit");

        // Math.asin(myStrings, Math.toRadians(yaw, pitch, roll))
        // Math.asin(myStrings, Math.toRadians(yaw, pitch, roll))
        // Math.setText() "Value".setText()
        // setText() textName "value"
        // setText() amanda.toLowercase() Locale.ROOT
        textName.setText(amanda.toLowerCase(Locale.ROOT));
        textName.setPreferredSize(new Dimension(200, 30));
        textPassword.setText(amandaAndIan);

        String time = new String("value");

        /*
        textName {
            name: amanda.toLowercase(Locale.ROOT)
            preferedSize: Dimension(200, 30)
            backgroundColor: Color(255, 255, 255)
        }

        */

        // button Submit clicked -> print name and password
        ActionListener submitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer error = 0;
                System.out.println("Name: " + textName.getText());
                System.out.println("Password: " + textPassword.getText());
                System.out.println("Error: " + error);
            }
        };
        buttonSubmit.addActionListener(submitAction);
    }
}
