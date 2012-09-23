package com.jbacon.passwordstorage.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jbacon.passwordstorage.swing.listeners.CloseJOptionPaneKeyListener;
import com.jbacon.passwordstorage.swing.listeners.RequestFocusListener;

public class ProfilePasswordEntryPanel extends JPanel {
    private static final long serialVersionUID = -6024339522848221876L;

    private final JPasswordField passwordField;
    private final JTextArea txtrPleaseEnterThe;
    private final CloseJOptionPaneKeyListener closeJOptionPaneKeyListener;

    public ProfilePasswordEntryPanel() {
        closeJOptionPaneKeyListener = new CloseJOptionPaneKeyListener();

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));

        passwordField = new JPasswordField();
        passwordField.addAncestorListener(new RequestFocusListener());
        passwordField.addKeyListener(closeJOptionPaneKeyListener);
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

    public boolean isClosedByKeyListener() {
        return closeJOptionPaneKeyListener.isClosedByKeyListener();
    }
}
