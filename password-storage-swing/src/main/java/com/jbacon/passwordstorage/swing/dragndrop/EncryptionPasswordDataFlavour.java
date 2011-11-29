package com.jbacon.passwordstorage.swing.dragndrop;

import java.awt.datatransfer.DataFlavor;

import com.jbacon.passwordstorage.password.StoredPassword;

public class EncryptionPasswordDataFlavour extends DataFlavor {

    public static DataFlavor encryptedPasswordFlavor = null;
    public static DataFlavor localEncryptedPasswordFlavor = null;

    static {
        encryptedPasswordFlavor = new DataFlavor(StoredPassword.class, "Non Local EncryptedPassword");
        localEncryptedPasswordFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + "; class=EncryptedPassword", "Local EncryptedPassword");
    }
}
