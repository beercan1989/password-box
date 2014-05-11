package com.jbacon.passwordstorage.functions;

import static javax.swing.JOptionPane.OK_OPTION;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.uk.baconi.cryptography.ciphers.pbe.PBECiphers;

import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.tools.EncrypterUtils;
import com.jbacon.passwordstorage.models.FluidEntity;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.swing.list.MasterPasswordListModel;
import com.jbacon.passwordstorage.swing.panels.ProfilePasswordEntryPanel;
import com.jbacon.passwordstorage.utils.JOptionUtil;
import com.jbacon.passwordstorage.utils.MouseEventUtil;

public class LoadProfileFunction implements ActionListener, AnnonymousFunction, KeyListener, MouseListener {
    
    private static final Log LOG = LogFactory.getLog(LoadProfileFunction.class);
    
    private final JList availableProfilesJList;
    private final MasterPasswordListModel availableProfilesModel;
    private final FluidEntity<MasterPassword> activeProfile;
    private final FluidEntity<String> currentPassword;
    
    private final FluidEntity<ProcessFunction<Boolean>> updateStoredPasswordsFunction;
    private final FluidEntity<UpdateAllActionStatesFunction> updateAllActionStatesFunction;
    
    public LoadProfileFunction(final JList availableProfilesJList, final MasterPasswordListModel availableProfilesModel,
            final FluidEntity<ProcessFunction<Boolean>> updateStoredPasswordsFunction, final FluidEntity<UpdateAllActionStatesFunction> updateAllActionStatesFunction,
            final FluidEntity<MasterPassword> activeProfile, final FluidEntity<String> currentPassword) {
        this.availableProfilesJList = availableProfilesJList;
        this.availableProfilesModel = availableProfilesModel;
        this.updateStoredPasswordsFunction = updateStoredPasswordsFunction;
        this.updateAllActionStatesFunction = updateAllActionStatesFunction;
        this.activeProfile = activeProfile;
        this.currentPassword = currentPassword;
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
        if (updateStoredPasswordsFunction.isSet()) {
            updateStoredPasswordsFunction.get().apply(true);
        }
        
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
            
            if (EncryptionType.isValid(encryptionType)) {
                final PBECiphers decrypter = PBECiphers.fromString(encryptionType.algorithmName);
                
                final byte[] salt = EncrypterUtils.base64StringToBytes(masterPassword.getSalt());
                final byte[] cipherText = EncrypterUtils.base64StringToBytes(masterPassword.getEncryptedSecretKey());
                final char[] passPhrase = EncrypterUtils.stringToChar(enteredPassword);
                
                // TODO - Introduce an IV - its similar to salt.
                final byte[] iv = new byte[salt.length * 2];
                System.arraycopy(salt, 0, iv, 0, salt.length);
                System.arraycopy(salt, 0, iv, salt.length, salt.length);
                
                final byte[] result = decrypter.decrypt(passPhrase, salt, iv, cipherText);
                
                if (result == null) {
                    return false;
                }
                
                return true;
            }
        } catch (final Exception e) {
            LOG.error("Exception thrown while checking if isPasswordCorrect.", e);
        }
        return false;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        apply();
        
        if (updateAllActionStatesFunction.isSet()) {
            updateAllActionStatesFunction.get().apply();
        }
    }
    
    @Deprecated
    public KeyListener asEnterKeyListener() {
        return this;
    }
    
    @Deprecated
    public MouseListener asDoubleClickListener() {
        return this;
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {
        MouseEventUtil.ifDoubleClick(e, this);
        
        if (updateAllActionStatesFunction.isSet()) {
            updateAllActionStatesFunction.get().apply();
        }
    }
    
    @Override
    public void keyTyped(final KeyEvent e) {
        if (KeyEvent.VK_ENTER == e.getKeyChar()) {
            apply();
            
            if (updateAllActionStatesFunction.isSet()) {
                updateAllActionStatesFunction.get().apply();
            }
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent e) {
    }
    
    @Override
    public void mouseExited(final MouseEvent e) {
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
    }
    
    @Override
    public void keyReleased(final KeyEvent e) {
    }
}
