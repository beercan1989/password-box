package com.jbacon.passwordstorage.database.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public abstract class MybatisDao {
	private static final String DEFAULT_MYBATIS_CONFIGURATION = "mybatis/Configuration.xml";
	protected SqlSessionWrapper databaseConnection;

	public MybatisDao() throws IOException {
		setupDatabaseConnection(DEFAULT_MYBATIS_CONFIGURATION);
	}

	protected MybatisDao(final String configuration) throws IOException {
		setupDatabaseConnection(configuration);
	}

	private void setupDatabaseConnection(final String configuration) throws IOException {
		Reader reader = Resources.getResourceAsReader(configuration);
		databaseConnection = new SqlSessionWrapper(new SqlSessionFactoryBuilder().build(reader));
	}
}
