package com.jbacon.passwordstorage.actions;

import static javax.swing.JOptionPane.OK_OPTION;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jbacon.passwordstorage.encryption.EncrypterPBE;
import com.jbacon.passwordstorage.encryption.EncryptionMode;
import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.errors.NoSuchEncryptionException;
import com.jbacon.passwordstorage.encryption.tools.EncrypterUtils;
import com.jbacon.passwordstorage.functions.AnnonymousFunction;
import com.jbacon.passwordstorage.functions.Pair;
import com.jbacon.passwordstorage.functions.ProcessFunction;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.swing.list.MasterPasswordListModel;
import com.jbacon.passwordstorage.swing.panels.ProfilePasswordEntryPanel;
import com.jbacon.passwordstorage.utils.JOptionUtil;

public class LoadProfileAction extends KeyAdapter implements ActionListener {

    private static final Log LOG = LogFactory.getLog(LoadProfileAction.class);

    private final JList availableProfilesJList;
    private final MasterPasswordListModel availableProfilesModel;
    private final ProcessFunction<Boolean> updateStoredPasswordsFunction;
    private final ProcessFunction<Pair<MasterPassword, String>> updateActiveProfileAndPassword;

    private final AnnonymousFunction updateAllActionStates;

    public LoadProfileAction(final JList availableProfilesJList, final MasterPasswordListModel availableProfilesModel,
            final ProcessFunction<Boolean> updateStoredPasswordsFunction, final ProcessFunction<Pair<MasterPassword, String>> updateActiveProfileAndPassword,
            final AnnonymousFunction updateAllActionStates) {
        this.availableProfilesJList = availableProfilesJList;
        this.availableProfilesModel = availableProfilesModel;
        this.updateStoredPasswordsFunction = updateStoredPasswordsFunction;
        this.updateActiveProfileAndPassword = updateActiveProfileAndPassword;
        this.updateAllActionStates = updateAllActionStates;
    }

    public void loadProfile() {

        // is a profile selected
        final int selectedProfileIndex = availableProfilesJList.getSelectedIndex();
        if (selectedProfileIndex < 0) {
            JOptionUtil.showMessageWindow("Please select a profile to load and try again.", "Select A Profile");
            return;
        }

        final MasterPassword masterPassword = availableProfilesModel.get(selectedProfileIndex);
        if (masterPassword == null) {
            JOptionUtil.errorMessage("The Selected Profile Returned Null From The Database", "Profile Was Null", null);
            return;
        }

        // prompt for password
        String enteredPassword = promptUserForProfilePassword();

        while (!isPasswordCorrect(masterPassword, enteredPassword)) {
            if (enteredPassword == null) {
                JOptionUtil.errorMessage("User has canclled the process to load a profile, when entering the password.", "User Cancelled Profile Load", null);
                return;
            }

            JOptionUtil.showMessageWindow("The password you entered was incorrect, please try again.", "Please Enter A Correct Password");
            enteredPassword = promptUserForProfilePassword();
        }

        // set ActiveProfile to the selected MP
        // set CurrentPassword to the entered password
        updateActiveProfileAndPassword.apply(new Pair<MasterPassword, String>(masterPassword, enteredPassword));

        // Load all the StoredPasswords for the selected ActiveProfile
        updateStoredPasswordsFunction.apply(true);

        LOG.debug("Loading a Profile - " + masterPassword);
    }

    private String promptUserForProfilePassword() {
        final ProfilePasswordEntryPanel passwordEntryPanel = new ProfilePasswordEntryPanel();
        final int result = JOptionUtil.showCustomInputWindow(passwordEntryPanel, "Enter Profile Password");

        if (result == OK_OPTION || passwordEntryPanel.isClosedByKey()) {
            return passwordEntryPanel.getPassword();
        }

        return null;
    }

    private boolean isPasswordCorrect(final MasterPassword masterPassword, final String enteredPassword) {
        if (masterPassword == null || enteredPassword == null) {
            return false;
        }

        final EncryptionType encryptionType = masterPassword.getMasterPasswordEncryptionType();

        try {
            if (encryptionType.getEncrypter() instanceof EncrypterPBE) {
                final EncrypterPBE encrypter = (EncrypterPBE) encryptionType.getEncrypter();

                final byte[] salt = EncrypterUtils.base64StringToBytes(masterPassword.getSalt());
                final byte[] cipherText = EncrypterUtils.base64StringToBytes(masterPassword.getEncryptedSecretKey());
                final char[] passPhrase = EncrypterUtils.stringToChar(enteredPassword);

                final byte[] result = encrypter.doCiper(EncryptionMode.DECRYPT_MODE, salt, cipherText, passPhrase);

                if (result == null) {
                    return false;
                }

                return true;
            }
        } catch (final NoSuchEncryptionException e) {
            LOG.error("NoSuchEncryptionException", e);
        } catch (final AbstractEncrypterException e) {
            if (e.getCause() instanceof javax.crypto.BadPaddingException) {
                return false;
            }
            LOG.error("AbstractEncrypterException", e);
        }
        return false;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        loadProfile();
        updateAllActionStates.apply();
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        if (KeyEvent.VK_ENTER == e.getKeyChar()) {
            loadProfile();
            updateAllActionStates.apply();
        }
    }
}
