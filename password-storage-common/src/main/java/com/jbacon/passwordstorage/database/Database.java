package com.jbacon.passwordstorage.database;

import java.io.IOException;

import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordsDao;
import com.jbacon.passwordstorage.database.errors.UnsupportedDatabaseException;

public interface Database {

    public MaintenanceDao createMaintenanceDao() throws IOException, UnsupportedDatabaseException;

    public MasterPasswordsDao createMasterPasswordDao() throws IOException, UnsupportedDatabaseException;

    public StoredPasswordsDao createStoredPasswordDao() throws IOException, UnsupportedDatabaseException;
}
