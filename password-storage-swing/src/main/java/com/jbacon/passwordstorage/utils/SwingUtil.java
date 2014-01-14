package com.jbacon.passwordstorage.utils;

import java.awt.Component;

import javax.swing.AbstractButton;

public class SwingUtil {
    public static void setEnabled(final boolean isEnabled, final Component... components) {
        for (final Component component : components) {
            component.setEnabled(isEnabled);
        }
    }

    public static void setSelected(final boolean isSelected, final AbstractButton... components) {
        for (final AbstractButton button : components) {
            button.setSelected(isSelected);
        }
    }

    public static void setVisible(final boolean isVisible, final Component... components) {
        for (final Component component : components) {
            component.setVisible(isVisible);
        }
    }
}
