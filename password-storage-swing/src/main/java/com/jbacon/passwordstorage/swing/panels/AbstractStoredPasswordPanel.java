package com.jbacon.passwordstorage.swing.panels;

import java.io.UnsupportedEncodingException;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.codec.DecoderException;

import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;
import com.jbacon.passwordstorage.swing.listeners.CloseJOptionPaneKeyListener;
import com.jbacon.passwordstorage.utils.PasswordEncryptionUtil;

public abstract class AbstractStoredPasswordPanel extends JPanel {

    private static final long serialVersionUID = 7935700589519594046L;

    protected final StoredPassword password;
    protected final CloseJOptionPaneKeyListener closeJOptionPaneKeyListener = new CloseJOptionPaneKeyListener();

    public AbstractStoredPasswordPanel(final StoredPassword password) {
        this.password = password;
    }

    public boolean isClosedByKey() {
        return closeJOptionPaneKeyListener.isClosedByKeyListener();
    }

    protected void setupPasswordDetails(final MasterPassword profile, final String currentPassword, final boolean doDecryption) throws UnsupportedEncodingException,
            DecoderException, AbstractEncrypterException {

        getPasswordIdJTextArea().setText(password.getId().toString());
        getProfileNameJTextArea().setText(password.getProfileName());

        if (doDecryption) {
            getPasswordNameJTextArea().setText(PasswordEncryptionUtil.getDecrypted(password.getEncryptedPasswordName(), profile, currentPassword));
            getPasswordJTextArea().setText(PasswordEncryptionUtil.getDecrypted(password.getEncryptedPassword(), profile, currentPassword));
            getPasswordNotesJTextArea().setText(PasswordEncryptionUtil.getDecrypted(password.getEncryptedPasswordNotes(), profile, currentPassword));
        } else {
            getPasswordNameJTextArea().setEnabled(false);
            getPasswordNameJTextArea().setText(password.getEncryptedPasswordName());
            getPasswordJTextArea().setText(password.getEncryptedPassword());
            getPasswordJTextArea().setEnabled(false);
            getPasswordNotesJTextArea().setText(password.getEncryptedPasswordNotes());
            getPasswordNotesJTextArea().setEnabled(false);
        }
    }

    protected abstract JTextArea getPasswordIdJTextArea();

    protected abstract JTextArea getProfileNameJTextArea();

    protected abstract JTextArea getPasswordNameJTextArea();

    protected abstract JTextArea getPasswordJTextArea();

    protected abstract JTextArea getPasswordNotesJTextArea();
}
