package com.jbacon.passwordstorage.utils;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showOptionDialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JOptionUtil {
    private static final Log LOG = LogFactory.getLog(JOptionUtil.class);

    public static int showDefaultInputWindow(final Object message, final String title) {
        return showConfirmDialog(null, message, title, OK_CANCEL_OPTION);
    }

    public static int showCustomInputWindow(final Object message, final String title) {
        return showCustomInputWindow(message, title, new String[] { "OK", "Cancel" });
    }

    public static int showCustomInputWindow(final Object message, final String title, final String[] options) {
        return showOptionDialog(null, message, title, OK_CANCEL_OPTION, QUESTION_MESSAGE, null, options, "");
    }

    public static void showMessageWindow(final Object message, final String title) {
        showMessageDialog(null, message, title, INFORMATION_MESSAGE);
    }

    public static String showSimpleInputWindow(final String message, final String title) {
        return showInputDialog(null, message, title, QUESTION_MESSAGE);
    }

    public static void errorMessage(final String message, final String title, final Exception e) {
        showMessageDialog(null, message, title, ERROR_MESSAGE);
        LOG.error(message, e);
    }
}
