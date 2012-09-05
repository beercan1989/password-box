package com.jbacon.passwordstorage.android.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.DecoderException;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jbacon.passwordstorage.android.utils.ConstantUtils.FlipperDirection;
import com.jbacon.passwordstorage.encryption.EncrypterAES;
import com.jbacon.passwordstorage.encryption.EncrypterPBE;
import com.jbacon.passwordstorage.encryption.EncryptionMode;
import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.errors.NoSuchEncryptionException;
import com.jbacon.passwordstorage.encryption.tools.EncrypterUtils;

public final class ListenerUtils {
    private ListenerUtils() {
    }

    public static final OnSeekBarChangeListener seekBarListener(final TextView seekbarLabel) {
        return new OnSeekBarChangeListener() {
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

    public static final OnClickListener doViewFlipOnClick(final ViewFlipper flipper, final FlipperDirection flipperDirection) {
        switch (flipperDirection) {
        case NEXT:
            return new OnClickListener() {
                @Override
                public void onClick(final View view) {
                    flipper.setInAnimation(AnimationUtils.inFromRightAnimation());
                    flipper.setOutAnimation(AnimationUtils.outToLeftAnimation());
                    flipper.showNext();
                }
            };
        case PREVIOUS:
            return new OnClickListener() {
                @Override
                public void onClick(final View view) {
                    flipper.setInAnimation(AnimationUtils.inFromLeftAnimation());
                    flipper.setOutAnimation(AnimationUtils.outToRightAnimation());
                    flipper.showPrevious();
                }
            };
        }
        return null;
    }

    public static final OnClickListener decryptOnClick(final TextView decryptedPassword, final String encryptedAesKey, final String saltValue, final String encryptedPasswordValue,
            final String pbePassword) {
        return new OnClickListener() {
            @Override
            public void onClick(final View view) {
                try {
                    final byte[] encryptedKey = EncrypterUtils.hexStringToByte(encryptedAesKey);
                    final byte[] salt = EncrypterUtils.hexStringToByte(saltValue);
                    final byte[] encryptedPassword = EncrypterUtils.hexStringToByte(encryptedPasswordValue);

                    final EncrypterPBE encrypterPBE = (EncrypterPBE) EncryptionType.PBE_WITH_SHA1_AND_256_AES_CBC_BC.getEncrypter();
                    final byte[] aesKey = encrypterPBE.doCiper(EncryptionMode.DECRYPT_MODE, salt, encryptedKey, EncrypterUtils.stringToChar(pbePassword));

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
        };
    }
}
