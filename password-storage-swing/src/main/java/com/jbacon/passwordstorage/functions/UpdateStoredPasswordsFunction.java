package com.jbacon.passwordstorage.functions;

import java.util.List;

import javax.swing.JList;

import com.jbacon.passwordstorage.database.dao.StoredPasswordsDao;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;
import com.jbacon.passwordstorage.swing.list.MasterPasswordListModel;
import com.jbacon.passwordstorage.swing.table.StoredPasswordTableModel;
import com.jbacon.passwordstorage.utils.JOptionUtil;

public class UpdateStoredPasswordsFunction implements ProcessFunction<Boolean> {

    private final StoredPasswordTableModel storedPasswordsModel;
    private final MasterPasswordListModel availableProfilesModel;
    private final StoredPasswordsDao storedPasswordDao;
    private final JList availableProfilesJList;

    public UpdateStoredPasswordsFunction(final StoredPasswordsDao storedPasswordDao, final StoredPasswordTableModel storedPasswordsModel,
            final MasterPasswordListModel availableProfilesModel, final JList availableProfilesJList) {
        this.storedPasswordsModel = storedPasswordsModel;
        this.availableProfilesModel = availableProfilesModel;
        this.storedPasswordDao = storedPasswordDao;
        this.availableProfilesJList = availableProfilesJList;
    }

    @Override
    public void apply(final Boolean showErrorMessages) {
        storedPasswordsModel.clear();

        final int selected = availableProfilesJList.getSelectedIndex();

        if (selected < 0) {
            if (showErrorMessages) {
                JOptionUtil.errorMessage("There is no selected profile.", "No Profile Selected", null);
            }
            return;
        }

        final MasterPassword masterPassword = availableProfilesModel.get(selected);

        if (masterPassword == null) {
            if (showErrorMessages) {
                JOptionUtil.errorMessage("The master password was null, while updating the stored passwords table.", "Masterpassword was null", null);
            }
            return;
        }

        final List<StoredPassword> storedPasswords = storedPasswordDao.getStoredPasswords(masterPassword);

        if (storedPasswords != null) {
            storedPasswordsModel.addAll(storedPasswords);
        }
    }

}
