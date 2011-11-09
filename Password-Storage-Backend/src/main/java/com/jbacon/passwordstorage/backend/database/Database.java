package com.jbacon.passwordstorage.backend.database;

import java.io.IOException;

import com.jbacon.passwordstorage.backend.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.backend.database.dao.MasterPasswordDao;
import com.jbacon.passwordstorage.backend.database.dao.StoredPasswordDao;
import com.jbacon.passwordstorage.backend.database.errors.UnsupportedDatabaseException;

public interface Database {

	public MaintenanceDao createMaintenanceDao() throws UnsupportedDatabaseException, IOException;

	public MasterPasswordDao createMasterPasswordDao() throws UnsupportedDatabaseException, IOException;

	public StoredPasswordDao createStoredPasswordDao() throws UnsupportedDatabaseException, IOException;

}
