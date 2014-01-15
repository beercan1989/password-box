package com.jbacon.passwordstorage.actions;

import static com.jbacon.passwordstorage.utils.GenericValidationUtil.areNull;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordsDao;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.functions.AnnonymousFunction;
import com.jbacon.passwordstorage.functions.ProcessFunction;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;
import com.jbacon.passwordstorage.swing.MainWindow;
import com.jbacon.passwordstorage.swing.panels.NewStoredPasswordPanel;
import com.jbacon.passwordstorage.utils.DBUtil;
import com.jbacon.passwordstorage.utils.JOptionUtil;

public class NewPasswordAction {
    private static final Log LOG = LogFactory.getLog(NewPasswordAction.class);

    private final StoredPasswordsDao storedPasswordDao;
    private final MasterPasswordsDao masterPasswordDao;
    private final ProcessFunction<Boolean> updateStoredPasswordsFN;
    private final AnnonymousFunction andFinallyFunction;

    public NewPasswordAction(final MasterPasswordsDao masterPasswordDao, final StoredPasswordsDao storedPasswordDao, final ProcessFunction<Boolean> updateStoredPasswordsFN,
            final AnnonymousFunction andFinallyFunction) {
        this.storedPasswordDao = storedPasswordDao;
        this.updateStoredPasswordsFN = updateStoredPasswordsFN;
        this.masterPasswordDao = masterPasswordDao;
        this.andFinallyFunction = andFinallyFunction;
    }

    public void newPassword(final MasterPassword activeProfile, final String currentPassword) {
        try {
            newPasswordUnsafe(activeProfile, currentPassword);
        } catch (final Exception e) {
            JOptionUtil.errorMessage("An error occured when the creation of your new password.", "Password Creation Error", e);
        } finally {
            andFinallyFunction.apply();
        }
    }

    private void newPasswordUnsafe(final MasterPassword activeProfile, final String currentPassword) throws UnsupportedEncodingException, DecoderException,
            AbstractEncrypterException {
        LOG.debug("Creating a new Password");
        if (activeProfile.equals(MainWindow.DEFAULT_ACTIVE_PROFILE)) {
            JOptionUtil.errorMessage("You need to create or load a profile first.", "No Profile Loaded", null);
            return;
        }
        final NewStoredPasswordPanel newPassword = new NewStoredPasswordPanel(activeProfile, currentPassword);
        if (JOptionUtil.showDefaultInputWindow(newPassword, "New Profile") == JOptionPane.OK_OPTION) {
            if (!NewStoredPasswordPanel.isValid(newPassword)) {
                JOptionUtil.errorMessage("Failed to create a new password, as you did not fill in all the fields.", "Failed To Create New Password", null);
                return;
            }

            final StoredPassword storedPassword = newPassword.buildPassword();

            if (!validateNewPassword(storedPassword)) {
                JOptionUtil.errorMessage("Failed to create a new password, as the new password is not valid, either fields were empty or none existant.",
                        "Failed To Create New Password", null);
                return;
            }

            if (DBUtil.unsuccessfulImport(storedPasswordDao.instertStoredPassword(storedPassword))) {
                JOptionUtil.errorMessage("Failed to create a new password, inserting into the database failed.", "Failed To Create New Password", null);
                return;
            }

            updateStoredPasswordsFN.apply(false);

            LOG.debug("Created storedPassword for profile " + storedPassword.getProfileName());
        }
    }

    private boolean validateNewPassword(final StoredPassword password) {
        final String profileName = password.getProfileName();
        final String encryptedPassword = password.getEncryptedPassword();
        final String encryptedPasswordName = password.getEncryptedPasswordName();
        final String encryptedPasswordNotes = password.getEncryptedPasswordNotes();

        if (areNull(profileName, encryptedPassword, encryptedPasswordName, encryptedPasswordNotes)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Failed to validate password, as a value was null. profileName: [" + profileName + "], encryptedPassword: [" + encryptedPassword
                        + "], encryptedPasswordName: [" + encryptedPasswordName + "], encryptedPasswordNotes: [" + encryptedPasswordNotes + "]");
            }
            return false;
        }

        // Profile Name matches one that already exists.
        final List<String> currentProfileNames = masterPasswordDao.getMasterPasswordNames();
        if (!currentProfileNames.contains(profileName)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Failed to validate password as the profile name did not appear in the database. profileName: [" + profileName + "]");
            }
            return false;
        }

        return true;
    }
}
