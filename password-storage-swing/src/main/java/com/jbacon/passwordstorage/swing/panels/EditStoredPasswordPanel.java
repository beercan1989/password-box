package com.jbacon.passwordstorage.swing.panels;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.DecoderException;

import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.tools.EncrypterUtils;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;
import com.jbacon.passwordstorage.swing.listeners.CloseJOptionPaneKeyListener;
import com.jbacon.passwordstorage.utils.PasswordEncryptionUtil;

public class EditStoredPasswordPanel extends AbstractStoredPasswordPanel {

    private static final long serialVersionUID = -5901240381146451861L;

    protected final CloseJOptionPaneKeyListener closeJOptionPaneKeyListener = new CloseJOptionPaneKeyListener();

    public EditStoredPasswordPanel(final StoredPassword password, final MasterPassword profile, final String currentPassword, final boolean doDecryption)
            throws UnsupportedEncodingException, DecoderException, AbstractEncrypterException {
        super(password, "Edit Password");

        passwordNameJTextArea.setEditable(false);
        passwordNotesJTextArea.addKeyListener(closeJOptionPaneKeyListener);

        passwordJTextArea.setEditable(false);
        passwordJTextArea.addKeyListener(closeJOptionPaneKeyListener);

        passwordNotesJTextArea.setEditable(false);
        passwordNameJTextArea.addKeyListener(closeJOptionPaneKeyListener);

        setupPasswordDetails(profile, currentPassword, doDecryption);
    }

    public StoredPassword getUpdatedPassword(final MasterPassword profile, final String currentPassword) throws UnsupportedEncodingException, DecoderException,
            AbstractEncrypterException {

        password.setEncryptedPasswordName(PasswordEncryptionUtil.getEncrypted(EncrypterUtils.stringToByte(passwordNameJTextArea.getText()), profile, currentPassword));
        password.setEncryptedPassword(PasswordEncryptionUtil.getEncrypted(EncrypterUtils.stringToByte(passwordJTextArea.getText()), profile, currentPassword));
        password.setEncryptedPasswordNotes(PasswordEncryptionUtil.getEncrypted(EncrypterUtils.stringToByte(passwordNotesJTextArea.getText()), profile, currentPassword));

        return password;
    }

    public boolean isClosedByKey() {
        return closeJOptionPaneKeyListener.isClosedByKeyListener();
    }
}
