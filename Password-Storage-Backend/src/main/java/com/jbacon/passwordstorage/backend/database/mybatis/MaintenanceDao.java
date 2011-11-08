package com.jbacon.passwordstorage.backend.database.mybatis;

public interface MaintenanceDao {

	public int createAllTables();

	public int dropAllTables();
}
