package com.jbacon.passwordstorage.utils;

import java.awt.event.MouseEvent;

import com.jbacon.passwordstorage.functions.AnnonymousFunction;

public class MouseEventUtil {

    public static boolean isDoubleClick(final MouseEvent mouseEvent) {
        return mouseEvent.getClickCount() >= 2;
    }

    public static boolean ifDoubleClick(final MouseEvent mouseEvent, final AnnonymousFunction... functions) {
        if (isDoubleClick(mouseEvent)) {
            for (final AnnonymousFunction function : functions) {
                function.apply();
            }
            
            return true;
        } else {
            return false;
        }
    }
}
