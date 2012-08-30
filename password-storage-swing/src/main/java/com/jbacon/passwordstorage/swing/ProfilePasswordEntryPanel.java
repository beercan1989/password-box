package com.jbacon.passwordstorage.swing;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ProfilePasswordEntryPanel extends JPanel {

    /*
     * Eclipse Auto Generated
     */
    private static final long serialVersionUID = -6024339522848221876L;

    private final JPasswordField passwordField;
    private final JTextArea txtrPleaseEnterThe;

    public ProfilePasswordEntryPanel() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));

        passwordField = new JPasswordField();
        passwordField.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(final ComponentEvent ce) {
                passwordField.requestFocusInWindow();
            }
        });
        add(passwordField, BorderLayout.CENTER);

        txtrPleaseEnterThe = new JTextArea();
        txtrPleaseEnterThe.setWrapStyleWord(true);
        txtrPleaseEnterThe.setText("Please enter the password for the profile.\n");
        txtrPleaseEnterThe.setEditable(false);
        txtrPleaseEnterThe.setBackground(UIManager.getColor("Button.background"));
        add(txtrPleaseEnterThe, BorderLayout.NORTH);

    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}
