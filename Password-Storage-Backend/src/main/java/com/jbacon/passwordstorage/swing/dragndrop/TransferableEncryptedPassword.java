package com.jbacon.passwordstorage.swing.dragndrop;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import com.jbacon.passwordstorage.backend.encryptionobjects.EncryptedPassword;

public class TransferableEncryptedPassword implements Transferable, ClipboardOwner {

    public static DataFlavor encryptedPasswordFlavor = null;
    public static DataFlavor localEncryptedPasswordFlavor = null;

    private final EncryptedPassword encryptedPassword;

    static {
        try {
            encryptedPasswordFlavor = new DataFlavor(EncryptedPassword.class, "Non Local EncryptedPassword");
            localEncryptedPasswordFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + "; class=EncryptedPassword", "Local EncryptedPassword");
        } catch (Exception e) {
        }
    }

    public TransferableEncryptedPassword(final EncryptedPassword encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
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
            return encryptedPassword;
        }
        throw new UnsupportedFlavorException(flavor);
    }

    @Override
    public void lostOwnership(final Clipboard clipboard, final Transferable contents) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
