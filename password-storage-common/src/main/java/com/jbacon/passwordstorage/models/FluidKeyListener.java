package com.jbacon.passwordstorage.models;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FluidKeyListener<KL extends KeyListener> implements KeyListener {
    
    public static <KL extends KeyListener> FluidKeyListener<KL> create(final FluidEntity<KL> fluidKeyListener) {
        return new FluidKeyListener<KL>(fluidKeyListener);
    }
    
    private final FluidEntity<KL> fluidKeyListener;
    
    public FluidKeyListener(final FluidEntity<KL> fluidKeyListener) {
        this.fluidKeyListener = fluidKeyListener;
    }
    
    @Override
    public void keyTyped(final KeyEvent e) {
        if (fluidKeyListener.isSet()) {
            fluidKeyListener.get().keyTyped(e);
        }
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        if (fluidKeyListener.isSet()) {
            fluidKeyListener.get().keyPressed(e);
        }
    }
    
    @Override
    public void keyReleased(final KeyEvent e) {
        if (fluidKeyListener.isSet()) {
            fluidKeyListener.get().keyReleased(e);
        }
    }
    
}
