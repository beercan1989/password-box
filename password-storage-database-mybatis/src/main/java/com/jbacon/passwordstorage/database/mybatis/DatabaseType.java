package com.jbacon.passwordstorage.database.mybatis;

import java.io.IOException;

import com.jbacon.passwordstorage.database.Database;
import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordsDao;

public enum DatabaseType implements Database {
    MyBatis {
        @Override
        public MaintenanceDao createMaintenanceDao() throws IOException {
            return new MaintenanceMybatisDao();
        }

        @Override
        public MaintenanceDao createMaintenanceDao(final String configurationFile) throws IOException {
            return new MaintenanceMybatisDao(configurationFile);
        }

        @Override
        public MasterPasswordsDao createMasterPasswordDao() throws IOException {
            return new MasterPasswordMybatisDao();
        }

        @Override
        public MasterPasswordsDao createMasterPasswordDao(final String configurationFile) throws IOException {
            return new MasterPasswordMybatisDao(configurationFile);
        }

        @Override
        public StoredPasswordsDao createStoredPasswordDao() throws IOException {
            return new StoredPasswordMybatisDao();
        }

        @Override
        public StoredPasswordsDao createStoredPasswordDao(final String configurationFile) throws IOException {
            return new StoredPasswordMybatisDao(configurationFile);
        }
    };

    public abstract MaintenanceDao createMaintenanceDao(final String configurationFilePath) throws IOException;

    public abstract MasterPasswordsDao createMasterPasswordDao(final String configurationFilePath) throws IOException;

    public abstract StoredPasswordsDao createStoredPasswordDao(final String configurationFilePath) throws IOException;
}
