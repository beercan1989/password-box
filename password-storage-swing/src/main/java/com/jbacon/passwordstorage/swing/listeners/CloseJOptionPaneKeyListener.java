package com.jbacon.passwordstorage.swing.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public final class CloseJOptionPaneKeyListener extends KeyAdapter {
    private boolean closedByKeyListener = false;

    @Override
    public void keyTyped(final KeyEvent e) {
        if (KeyEvent.VK_ENTER == e.getKeyChar()) {
            closedByKeyListener = true;
            JOptionPane.getRootFrame().setVisible(false);
            JOptionPane.getRootFrame().dispose();
        }
    }

    public boolean isClosedByKeyListener() {
        return closedByKeyListener;
    }
}
