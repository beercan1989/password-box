package com.jbacon.passwordstorage.swing.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import com.jbacon.passwordstorage.functions.AnnonymousFunction;
import com.jbacon.passwordstorage.functions.FunctionFrom;

public class MouseActionListener extends MouseAdapter {

    public static MouseAdapter get(final AnnonymousFunction annonymousFunction) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                annonymousFunction.apply();
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                annonymousFunction.apply();
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                annonymousFunction.apply();
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                annonymousFunction.apply();
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                annonymousFunction.apply();
            }

            @Override
            public void mouseWheelMoved(final MouseWheelEvent e) {
                annonymousFunction.apply();
            }

            @Override
            public void mouseDragged(final MouseEvent e) {
                annonymousFunction.apply();
            }

            @Override
            public void mouseMoved(final MouseEvent e) {
                annonymousFunction.apply();
            }
        };
    }

    public static MouseAdapter get(final FunctionFrom<MouseEvent> mouseFunction, final FunctionFrom<MouseWheelEvent> mouseWheelFunction) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                mouseFunction.apply(e);
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                mouseFunction.apply(e);
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                mouseFunction.apply(e);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                mouseFunction.apply(e);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                mouseFunction.apply(e);
            }

            @Override
            public void mouseWheelMoved(final MouseWheelEvent e) {
                mouseWheelFunction.apply(e);
            }

            @Override
            public void mouseDragged(final MouseEvent e) {
                mouseFunction.apply(e);
            }

            @Override
            public void mouseMoved(final MouseEvent e) {
                mouseFunction.apply(e);
            }
        };
    }

}
