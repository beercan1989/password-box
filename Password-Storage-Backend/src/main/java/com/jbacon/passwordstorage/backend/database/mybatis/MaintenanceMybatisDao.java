package com.jbacon.passwordstorage.backend.database.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jbacon.passwordstorage.backend.database.dao.MaintenanceDao;

public class MaintenanceMybatisDao implements MaintenanceDao {

	private static final String MYBATIS_CONFIGURATION = "mybatis/Configuration.xml";
	private final SqlSession databaseConnection;

	public MaintenanceMybatisDao() throws IOException {
		Reader reader = Resources.getResourceAsReader(MYBATIS_CONFIGURATION);
		databaseConnection = new SqlSessionFactoryBuilder().build(reader).openSession();
	}

	protected MaintenanceMybatisDao(final String configuration) throws IOException {
		Reader reader = Resources.getResourceAsReader(configuration);
		databaseConnection = new SqlSessionFactoryBuilder().build(reader).openSession();
	}

	@Override
	public int createAllTables() {
		int result = databaseConnection.update("createAllTables");
		polishing();
		return result;
	}

	@Override
	public int dropAllTables() {
		int result = databaseConnection.update("dropAllTables");
		polishing();
		return result;
	}

	private void polishing() {
		databaseConnection.commit();
		databaseConnection.close();
	}

}
