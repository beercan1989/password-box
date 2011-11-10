package com.jbacon.passwordstorage.backend.database.mybatis;

import java.io.IOException;

import com.jbacon.passwordstorage.backend.database.dao.MaintenanceDao;

public final class MaintenanceMybatisDao extends MybatisDao implements MaintenanceDao {

	public MaintenanceMybatisDao() throws IOException {
		super();
	}

	protected MaintenanceMybatisDao(final String testConfiguration) throws IOException {
		super(testConfiguration);
	}

	@Override
	public int createAllTables() {
		return databaseConnection.update("createAllTables");
	}

	@Override
	public int dropAllTables() {
		return databaseConnection.update("dropAllTables");
	}

}
