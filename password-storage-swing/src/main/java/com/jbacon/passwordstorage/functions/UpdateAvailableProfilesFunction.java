package com.jbacon.passwordstorage.functions;

import java.util.List;

import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.swing.list.MasterPasswordListModel;

public class UpdateAvailableProfilesFunction implements AnnonymousFunction {

    private final MasterPasswordListModel availableProfilesModel;
    private final MasterPasswordsDao masterPasswordDao;

    public UpdateAvailableProfilesFunction() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void apply() {
        availableProfilesModel.clear();
        final List<MasterPassword> masterPasswords = masterPasswordDao.getMasterPasswords();

        if (masterPasswords != null) {
            availableProfilesModel.addAll(masterPasswords);
            if (!masterPasswords.contains(activeProfile)) {
                activeProfile = DEFAULT_ACTIVE_PROFILE;
                activeProfileJLabel.setText(activeProfile.getProfileName());
            }
        }
    }

}
