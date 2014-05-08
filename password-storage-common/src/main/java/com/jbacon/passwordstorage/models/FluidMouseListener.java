package com.jbacon.passwordstorage.models;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FluidMouseListener<ML extends MouseListener> implements MouseListener {
    
    public static <ML extends MouseListener> FluidMouseListener<ML> create(final FluidEntity<ML> fluidKeyListener) {
        return new FluidMouseListener<ML>(fluidKeyListener);
    }
    
    private final FluidEntity<ML> fluidKeyListener;
    
    public FluidMouseListener(final FluidEntity<ML> fluidKeyListener) {
        this.fluidKeyListener = fluidKeyListener;
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {
        if (fluidKeyListener.isSet()) {
            fluidKeyListener.get().mouseClicked(e);
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        if (fluidKeyListener.isSet()) {
            fluidKeyListener.get().mousePressed(e);
        }
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {
        if (fluidKeyListener.isSet()) {
            fluidKeyListener.get().mouseReleased(e);
        }
    }
    
    @Override
    public void mouseEntered(final MouseEvent e) {
        if (fluidKeyListener.isSet()) {
            fluidKeyListener.get().mouseEntered(e);
        }
    }
    
    @Override
    public void mouseExited(final MouseEvent e) {
        if (fluidKeyListener.isSet()) {
            fluidKeyListener.get().mouseExited(e);
        }
    }
    
}
