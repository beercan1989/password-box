package com.jbacon.passwordstorage.blackberry;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * @author JBacon
 */
public class PasswordStorageController extends Activity {
    private SeekBar  seekBar;
    private TextView seekBarLabel;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBarLabel = (TextView) findViewById(R.id.seekBarDisplay);
        seekBar.setOnSeekBarChangeListener(ActionUtils.SeekBarListener(seekBarLabel));
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_close:
                finish();
                return true;
            default:
                return false;
        }
    }
}
