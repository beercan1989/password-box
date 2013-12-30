package com.jbacon.passwordstorage.swing.panels;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.DecoderException;

import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;

public class ViewStoredPasswordPanel extends AbstractStoredPasswordPanel {

    private static final long serialVersionUID = 7627750248397407249L;

    public ViewStoredPasswordPanel(final StoredPassword password, final MasterPassword profile, final String currentPassword, final boolean doDecryption)
            throws UnsupportedEncodingException, DecoderException, AbstractEncrypterException {
        super(password, "View Password");

        setupPasswordDetails(profile, currentPassword, doDecryption);
    }
}
