package com.jbacon.passwordstorage.functions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.swing.list.MasterPasswordListModel;

public class UpdateAvailableProfilesFunction implements ActionListener, AnnonymousFunction {

    private final MasterPasswordListModel availableProfilesModel;
    private final MasterPasswordsDao masterPasswordDao;

    public UpdateAvailableProfilesFunction(final MasterPasswordListModel availableProfilesModel, final MasterPasswordsDao masterPasswordDao) {
        this.availableProfilesModel = availableProfilesModel;
        this.masterPasswordDao = masterPasswordDao;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        apply();
    }

    @Override
    public void apply() {
        availableProfilesModel.clear();
        final List<MasterPassword> masterPasswords = masterPasswordDao.getMasterPasswords();

        if (masterPasswords != null) {
            availableProfilesModel.addAll(masterPasswords);
        }
    }

}
