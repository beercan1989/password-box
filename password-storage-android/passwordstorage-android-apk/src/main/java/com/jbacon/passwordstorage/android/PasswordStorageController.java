package com.jbacon.passwordstorage.android;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.DecoderException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jbacon.passwordstorage.android.R;
import com.jbacon.passwordstorage.android.utils.AnimationUtils;
import com.jbacon.passwordstorage.android.utils.ListenerUtils;
import com.jbacon.passwordstorage.imported.encryption.EncrypterAES;
import com.jbacon.passwordstorage.imported.encryption.EncrypterPBE;
import com.jbacon.passwordstorage.imported.encryption.EncryptionMode;
import com.jbacon.passwordstorage.imported.encryption.EncryptionType;
import com.jbacon.passwordstorage.imported.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.imported.encryption.errors.NoSuchEncryptionException;
import com.jbacon.passwordstorage.imported.encryption.tools.EncrypterUtils;

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

        seekBar.setOnSeekBarChangeListener(ListenerUtils.SeekBarListener(seekBarLabel));
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        final Button button1 = (Button) findViewById(R.id.Button01);
        final Button button2 = (Button) findViewById(R.id.Button02);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                flipper.setInAnimation(AnimationUtils.inFromRightAnimation());
                flipper.setOutAnimation(AnimationUtils.outToLeftAnimation());
                flipper.showNext();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                flipper.setInAnimation(AnimationUtils.inFromLeftAnimation());
                flipper.setOutAnimation(AnimationUtils.outToRightAnimation());
                flipper.showPrevious();
            }
        });

        final TextView decryptedPassword = (TextView) findViewById(R.id.decryptedPassword);
        final TextView encryptedPassword = (TextView) findViewById(R.id.encryptedPassword);
        final Button decrypButton = (Button) findViewById(R.id.decryptButton);

        encryptedPassword.setText(ENCRYPTED_PASSWORD);
        decryptedPassword.setText("");

        decrypButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                try {
                    final byte[] encryptedKey = EncrypterUtils.hexStringToByte(ENCRYPTED_AES_KEY);
                    final byte[] salt = EncrypterUtils.hexStringToByte(SALT);
                    final byte[] encryptedPassword = EncrypterUtils.hexStringToByte(ENCRYPTED_PASSWORD);

                    final EncrypterPBE encrypterPBE = (EncrypterPBE) EncryptionType.PBE_WITH_SHA1_AND_256_AES_CBC_BC.getEncrypter();
                    final byte[] aesKey = encrypterPBE.doCiper(EncryptionMode.DECRYPT_MODE, salt, encryptedKey,
                            EncrypterUtils.stringToChar(PBE_PASSWORD));

                    final EncrypterAES encrypterAES = (EncrypterAES) EncryptionType.AES_256.getEncrypter();
                    final byte[] password = encrypterAES.doCiper(EncryptionMode.DECRYPT_MODE, encryptedPassword, aesKey);

                    decryptedPassword.setText(EncrypterUtils.byteToString(password));

                } catch (final DecoderException e) {
                    decryptedPassword.setText("It Borked");
                } catch (final NoSuchEncryptionException e) {
                    decryptedPassword.setText("It Borked");
                } catch (final AbstractEncrypterException e) {
                    decryptedPassword.setText("It Borked");
                } catch (final UnsupportedEncodingException e) {
                    decryptedPassword.setText("It Borked");
                }
            }
        });
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
