package com.jbacon.passwordstorage.actions;

import java.util.List;

import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.swing.list.MasterPasswordListModel;

public class UpdateAvailableProfilesAction {

    private final MasterPasswordListModel availableProfilesModel;
    private final MasterPasswordsDao masterPasswordDao;

    public UpdateAvailableProfilesAction(final MasterPasswordListModel availableProfilesModel, final MasterPasswordsDao masterPasswordDao) {
        this.availableProfilesModel = availableProfilesModel;
        this.masterPasswordDao = masterPasswordDao;
    }

    public void updateAvailableProfiles(final MasterPassword currentActiveProfile) {
        availableProfilesModel.clear();
        final List<MasterPassword> masterPasswords = masterPasswordDao.getMasterPasswords();

        if (masterPasswords != null) {
            availableProfilesModel.addAll(masterPasswords);
        }
    }

}
