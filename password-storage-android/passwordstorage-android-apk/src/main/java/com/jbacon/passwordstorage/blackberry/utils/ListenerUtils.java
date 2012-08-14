package com.jbacon.passwordstorage.blackberry.utils;

import android.widget.SeekBar;
import android.widget.TextView;

public final class ListenerUtils {
    private ListenerUtils() {
    }

    /**
     * Updates the TextView with the progress of the SeekBar.
     * 
     * @param seekbarLabel
     *            The TextView to update, which acts as the SeekBar's label.
     * @return An action listener for a SeekBar.
     */
    public static final SeekBar.OnSeekBarChangeListener SeekBarListener(final TextView seekbarLabel) {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                seekbarLabel.setText("Seek Bar Value: " + progress);
            }

            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {
            }
        };
    }
}
