package com.jbacon.passwordstorage.database;

import java.io.IOException;

import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordsDao;

public interface Database {

    public MaintenanceDao createMaintenanceDao() throws IOException;

    public MasterPasswordsDao createMasterPasswordDao() throws IOException;

    public StoredPasswordsDao createStoredPasswordDao() throws IOException;
}
