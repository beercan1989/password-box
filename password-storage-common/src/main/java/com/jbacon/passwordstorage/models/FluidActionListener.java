package com.jbacon.passwordstorage.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FluidActionListener<AL extends ActionListener> implements ActionListener {
    
    public static <AL extends ActionListener> FluidActionListener<AL> create(final FluidEntity<AL> fluidActionListener) {
        return new FluidActionListener<AL>(fluidActionListener);
    }
    
    private final FluidEntity<AL> fluidActionListener;
    
    public FluidActionListener(final FluidEntity<AL> fluidActionListener) {
        this.fluidActionListener = fluidActionListener;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (fluidActionListener.isSet()) {
            fluidActionListener.get().actionPerformed(e);
        }
    }
    
}
