package com.jbacon.passwordstorage.swing.dragndrop;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import com.jbacon.passwordstorage.password.StoredPassword;

public class EncryptedPasswordTransferable implements Transferable, ClipboardOwner {

    private final StoredPassword encryptedPassword;

    public EncryptedPasswordTransferable(final StoredPassword encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { EncryptionPasswordDataFlavour.encryptedPasswordFlavor, EncryptionPasswordDataFlavour.localEncryptedPasswordFlavor };
    }

    @Override
    public boolean isDataFlavorSupported(final DataFlavor flavor) {
        if (flavor.equals(EncryptionPasswordDataFlavour.encryptedPasswordFlavor) || flavor.equals(EncryptionPasswordDataFlavour.localEncryptedPasswordFlavor)) {
            return true;
        }
        return false;
    }

    @Override
    public Object getTransferData(final DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(EncryptionPasswordDataFlavour.encryptedPasswordFlavor) || flavor.equals(EncryptionPasswordDataFlavour.localEncryptedPasswordFlavor)) {
            return encryptedPassword;
        }
        throw new UnsupportedFlavorException(flavor);
    }

    @Override
    public void lostOwnership(final Clipboard clipboard, final Transferable contents) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
