package com.jbacon.passwordstorage.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jbacon.passwordstorage.android.utils.ConstantUtils.FlipperDirection;
import com.jbacon.passwordstorage.android.utils.ListenerUtils;

/**
 * @author JBacon
 */
public class PasswordStorageController extends Activity {

    private static final String PBE_PASSWORD = "password";
    private static final String ENCRYPTED_AES_KEY = "c0ee59b064b00040737ec66e9e6399169d843cc3f35b54b07be67e9f7229845670a4ff9af03181c098406831f3612e34";
    private static final String SALT = "fe89f76a525362c5399279418660fd91b4aef558a1a344d5a9829e1419604c92";
    private static final String ENCRYPTED_PASSWORD = "bc5fdf0bb73a3309829322c69f6c5468";

    private SeekBar seekBar;
    private TextView seekBarLabel;
    private ViewFlipper flipper;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_flipper);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBarLabel = (TextView) findViewById(R.id.seekBarDisplay);

        seekBar.setOnSeekBarChangeListener(ListenerUtils.seekBarListener(seekBarLabel));
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        final Button button1 = (Button) findViewById(R.id.Button01);
        final Button button2 = (Button) findViewById(R.id.Button02);

        button1.setOnClickListener(ListenerUtils.doViewFlipOnClick(flipper, FlipperDirection.NEXT));
        button2.setOnClickListener(ListenerUtils.doViewFlipOnClick(flipper, FlipperDirection.PREVIOUS));

        final TextView decryptedPassword = (TextView) findViewById(R.id.decryptedPassword);
        final TextView encryptedPassword = (TextView) findViewById(R.id.encryptedPassword);
        final Button decrypButton = (Button) findViewById(R.id.decryptButton);

        encryptedPassword.setText(ENCRYPTED_PASSWORD);
        decryptedPassword.setText("");

        decrypButton.setOnClickListener(ListenerUtils.decryptOnClick(decryptedPassword, ENCRYPTED_AES_KEY, SALT, ENCRYPTED_PASSWORD, PBE_PASSWORD));
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
