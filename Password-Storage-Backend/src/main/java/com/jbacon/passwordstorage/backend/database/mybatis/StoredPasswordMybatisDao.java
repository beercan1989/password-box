package com.jbacon.passwordstorage.backend.database.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jbacon.passwordstorage.backend.database.dao.StoredPasswordDao;
import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;
import com.jbacon.passwordstorage.backend.encryption.objects.StoredPassword;

public class StoredPasswordMybatisDao implements StoredPasswordDao {

	private static final String MYBATIS_CONFIGURATION = "com.jbacon.passwordstorage.backend.database.mybatis.configuration/configuration.xml";
	private final SqlSession databaseConnection;

	public StoredPasswordMybatisDao() throws IOException {
		Reader reader = Resources.getResourceAsReader(MYBATIS_CONFIGURATION);
		databaseConnection = new SqlSessionFactoryBuilder().build(reader).openSession();
	}

	@Override
	public int deleteStoredPassword(final StoredPassword storedPassword) {
		return databaseConnection.update("deleteSingleStoredPassword", storedPassword);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<StoredPassword> getStoredPasswords() {
		return databaseConnection.selectList("getEveryStoredPassword");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<StoredPassword> getStoredPasswords(final MasterPassword masterPassword) {
		return databaseConnection.selectList("getAllStoredPasswords", masterPassword);
	}

	@Override
	public int instertStoredPassword(final StoredPassword storedPassword) {
		return databaseConnection.update("instertSingleStoredPassword", storedPassword);
	}

	@Override
	public int updateStoredPassword(final StoredPassword storedPassword) {
		return databaseConnection.update("updateSingleStoredPassword", storedPassword);
	}

}
