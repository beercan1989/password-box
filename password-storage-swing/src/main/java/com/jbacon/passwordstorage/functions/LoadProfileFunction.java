package com.jbacon.passwordstorage.functions;

import static javax.swing.JOptionPane.OK_OPTION;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jbacon.passwordstorage.encryption.EncrypterPBE;
import com.jbacon.passwordstorage.encryption.EncryptionMode;
import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.errors.NoSuchEncryptionException;
import com.jbacon.passwordstorage.encryption.tools.EncrypterUtils;
import com.jbacon.passwordstorage.models.FluidEntity;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.swing.list.MasterPasswordListModel;
import com.jbacon.passwordstorage.swing.panels.ProfilePasswordEntryPanel;
import com.jbacon.passwordstorage.utils.JOptionUtil;
import com.jbacon.passwordstorage.utils.MouseEventUtil;

public class LoadProfileFunction implements ActionListener, AnnonymousFunction {

    private static final Log LOG = LogFactory.getLog(LoadProfileFunction.class);

    private final JList availableProfilesJList;
    private final MasterPasswordListModel availableProfilesModel;
    private final ProcessFunction<Boolean> updateStoredPasswordsFunction;
    private final FluidEntity<MasterPassword> activeProfile;
    private final FluidEntity<String> currentPassword;

    private final AnnonymousFunction updateAllActionStates;

    private final KeyAdapter enterKeyListener;
    private final MouseAdapter doubleClickListener;

    public LoadProfileFunction(final JList availableProfilesJList, final MasterPasswordListModel availableProfilesModel,
            final ProcessFunction<Boolean> updateStoredPasswordsFunction, final AnnonymousFunction updateAllActionStates, final FluidEntity<MasterPassword> activeProfile,
            final FluidEntity<String> currentPassword) {
        this.availableProfilesJList = availableProfilesJList;
        this.availableProfilesModel = availableProfilesModel;
        this.updateStoredPasswordsFunction = updateStoredPasswordsFunction;
        this.updateAllActionStates = updateAllActionStates;
        this.activeProfile = activeProfile;
        this.currentPassword = currentPassword;

        enterKeyListener = new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                if (KeyEvent.VK_ENTER == e.getKeyChar()) {
                    apply();
                    updateAllActionStates.apply();
                }
            }
        };

        doubleClickListener = new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent mouseEvent) {
                MouseEventUtil.ifDoubleClick(mouseEvent, getThis());
                updateAllActionStates.apply();
            }
        };
    }

    @Override
    public void apply() {

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
        activeProfile.set(masterPassword);
        currentPassword.set(enteredPassword);

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
        apply();
        updateAllActionStates.apply();
    }

    public KeyAdapter asEnterKeyListener() {
        return enterKeyListener;
    }

    public MouseAdapter asDoubleClickListener() {
        return doubleClickListener;
    }

    private LoadProfileFunction getThis() {
        return this;
    }
}
