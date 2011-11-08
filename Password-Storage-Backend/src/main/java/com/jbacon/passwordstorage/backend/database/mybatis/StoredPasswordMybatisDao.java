package com.jbacon.passwordstorage.backend.database.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;

public class StoredPasswordMybatisDao implements StoredPasswordDao {

	private static final String MYBATIS_CONFIGURATION = "com.jbacon.passwordstorage.backend.database.mybatis.configuration/configuration.xml";
	private final SqlSession databaseConnection;

	public StoredPasswordMybatisDao() throws IOException {
		Reader reader = Resources.getResourceAsReader(MYBATIS_CONFIGURATION);
		databaseConnection = new SqlSessionFactoryBuilder().build(reader).openSession();
	}

	@Override
	public int deleteSingleStoredPassword() {
		return databaseConnection.update("deleteSingleStoredPassword");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MasterPassword> getAllStoredPasswords() {
		return databaseConnection.selectList("getAllStoredPasswords");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MasterPassword> getSingleStoredPassword() {
		return databaseConnection.selectList("getSingleStoredPassword");
	}

	@Override
	public int instertSingleStoredPassword() {
		return databaseConnection.update("instertSingleStoredPassword");
	}

	@Override
	public int updateSingleStoredPassword() {
		return databaseConnection.update("updateSingleStoredPassword");
	}
}
