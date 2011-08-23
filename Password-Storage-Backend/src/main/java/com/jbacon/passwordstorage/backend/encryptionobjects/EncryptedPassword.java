package com.jbacon.passwordstorage.backend.encryptionobjects;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.Serializable;

//Added in transferable code for drag and drop
public class EncryptedPassword implements Serializable, Transferable, ClipboardOwner {

    private static final long serialVersionUID = -1839898907563182564L;
    public static DataFlavor encryptedPasswordFlavor = null;
    public static DataFlavor localEncryptedPasswordFlavor = null;

    static {
        try {
            encryptedPasswordFlavor = new DataFlavor(EncryptedPassword.class, "Non Local EncryptedPassword");
            localEncryptedPasswordFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + "; class=EncryptedPassword", "Local EncryptedPassword");
        } catch (Exception e) {
        }
    }

    private String encryptedPasswordName;
    private String encryptedPassword;
    private String encryptedWebsiteUrl;
    private PasswordType passwordType;
    private String profileName;

    public EncryptedPassword(final String encryptedPasswordName, final String profileName, final String encryptedPassword, final PasswordType passwordType,
            final String encryptedWebsiteURL) {
        this.encryptedPasswordName = encryptedPasswordName;
        this.profileName = profileName;
        this.encryptedPassword = encryptedPassword;
        this.passwordType = passwordType;

        if (passwordType == PasswordType.WEBSITE) {
            this.encryptedWebsiteUrl = encryptedWebsiteURL;
        } else {
            this.encryptedWebsiteUrl = "N/A";
        }
    }

    public String getEncryptedPasswordName() {
        return encryptedPasswordName;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public PasswordType getPasswordType() {
        return passwordType;
    }

    public String getEncryptedWebsiteURL() {
        return encryptedWebsiteUrl;
    }

    public void setEncryptedPasswordName(final String encryptedPasswordName) {
        this.encryptedPasswordName = encryptedPasswordName;
    }

    public void setProfileName(final String profileName) {
        this.profileName = profileName;
    }

    public void setEncryptedPassword(final String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setPasswordType(final PasswordType passwordType) {
        this.passwordType = passwordType;
        if (passwordType != null && passwordType != PasswordType.WEBSITE) {
            this.encryptedWebsiteUrl = "N/A";
        }
    }

    public void setEncryptedWebsiteURL(final String encryptedWebsiteUrl) {
        this.encryptedWebsiteUrl = encryptedWebsiteUrl;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { encryptedPasswordFlavor, localEncryptedPasswordFlavor };
    }

    @Override
    public boolean isDataFlavorSupported(final DataFlavor flavor) {
        if (flavor.equals(encryptedPasswordFlavor) || flavor.equals(localEncryptedPasswordFlavor)) {
            return true;
        }
        return false;
    }

    @Override
    public Object getTransferData(final DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(encryptedPasswordFlavor) || flavor.equals(localEncryptedPasswordFlavor)) {
            return this;
        }
        throw new UnsupportedFlavorException(flavor);
    }

    @Override
    public void lostOwnership(final Clipboard clipboard, final Transferable contents) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return "Name: " + encryptedPasswordName + ", Type: " + passwordType + ", URL: " + encryptedWebsiteUrl;
    }
}
