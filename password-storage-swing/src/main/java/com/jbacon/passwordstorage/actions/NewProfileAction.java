package com.jbacon.passwordstorage.actions;

import static com.jbacon.passwordstorage.utils.GenericValidationUtil.isNotNull;
import static javax.swing.JOptionPane.OK_OPTION;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.functions.AnnonymousFunction;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.swing.panels.NewMasterPasswordPanel;
import com.jbacon.passwordstorage.utils.DBUtil;
import com.jbacon.passwordstorage.utils.JOptionUtil;
import com.jbacon.passwordstorage.utils.StringUtil;

public class NewProfileAction {
    private static final Log LOG = LogFactory.getLog(NewProfileAction.class);

    private final MasterPasswordsDao masterPasswordDao;
    private final AnnonymousFunction updateAvailableProfilesFN;

    public NewProfileAction(final MasterPasswordsDao masterPasswordDao, final AnnonymousFunction updateAvailableProfilesFN) {
        this.masterPasswordDao = masterPasswordDao;
        this.updateAvailableProfilesFN = updateAvailableProfilesFN;
    }

    public void newProfile() throws AbstractEncrypterException {
        LOG.debug("Creating a new Profile");
        final NewMasterPasswordPanel newProfile = new NewMasterPasswordPanel();
        if (JOptionUtil.showDefaultInputWindow(newProfile, "New Profile") == OK_OPTION) {

            if (!NewMasterPasswordPanel.isValid(newProfile)) {
                JOptionUtil.errorMessage("Failed to create a new profile, as you did not fill in all the fields.", "Failed To Create New Profile", null);
                return;
            }

            final MasterPassword masterPassword = newProfile.buildProfile();

            if (!validateNewProfile(masterPassword)) {
                JOptionUtil.errorMessage("Failed to create a new profile, as the new profile is not valid, either fields were empty or none existant.",
                        "Failed To Create New Profile", null);
                return;
            }

            if (DBUtil.unsuccessfulImport(masterPasswordDao.instertMasterPassword(masterPassword))) {
                JOptionUtil.errorMessage("Failed to create a new profile, inserting into the database failed.", "Failed To Create New Profile", null);
                return;
            }

            updateAvailableProfilesFN.apply();
            LOG.debug("Created masterPassword with a name of " + masterPassword.getProfileName());
        }
    }

    private boolean validateNewProfile(final MasterPassword profile) {
        final boolean encryptionTypesValid = EncryptionType.areValid(profile.getMasterPasswordEncryptionType(), profile.getStoredPasswordEncryptionType());
        final boolean areNotEmpty = StringUtil.areNotEmpty(profile.getEncryptedSecretKey(), profile.getProfileName(), profile.getSalt());

        if (encryptionTypesValid && areNotEmpty) {
            final List<String> currentProfileNames = masterPasswordDao.getMasterPasswordNames();
            if (isNotNull(currentProfileNames) && currentProfileNames.contains(profile.getProfileName())) {
                return false;
            }
            return true;
        }

        return false;
    }
}
