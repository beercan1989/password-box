package com.jbacon.passwordstorage.database.mybatis;

import java.io.IOException;

import com.jbacon.passwordstorage.database.dao.MaintenanceDao;

public final class MaintenanceMybatisDao extends MybatisDao implements MaintenanceDao {

	public MaintenanceMybatisDao() throws IOException {
		super();
	}

	protected MaintenanceMybatisDao(final String testConfiguration) throws IOException {
		super(testConfiguration);
	}

	@Override
	public int createMasterPasswordTable() {
		return databaseConnection.update("createMasterPasswordTable");
	}

	@Override
	public int createSettingsTable() {
		return databaseConnection.update("createSettingsTable");
	}

	@Override
	public int createStoredPasswordTable() {
		return databaseConnection.update("createStoredPasswordTable");
	}

	@Override
	public int dropMasterPasswordTable() {
		return databaseConnection.update("dropMasterPasswordTable");
	}

	@Override
	public int dropSettingsTable() {
		return databaseConnection.update("dropSettingsTable");
	}

	@Override
	public int dropStoredPasswordTable() {
		return databaseConnection.update("dropStoredPasswordTable");
	}

}
