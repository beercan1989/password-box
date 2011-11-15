package com.jbacon.passwordstorage.backend.database.mybatis;

import java.io.IOException;
import java.util.List;

import com.jbacon.passwordstorage.backend.database.dao.StoredPasswordDao;
import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;
import com.jbacon.passwordstorage.backend.encryption.objects.StoredPassword;

public final class StoredPasswordMybatisDao extends MybatisDao implements StoredPasswordDao {

	public StoredPasswordMybatisDao() throws IOException {
		super();
	}

	protected StoredPasswordMybatisDao(final String testConfiguration) throws IOException {
		super(testConfiguration);
	}

	@Override
	public int deleteStoredPassword(final StoredPassword storedPassword) {
		return databaseConnection.delete("deleteSingleStoredPassword", storedPassword);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<StoredPassword> getStoredPasswords() {
		return (List<StoredPassword>) databaseConnection.selectList("getEveryStoredPassword");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<StoredPassword> getStoredPasswords(final MasterPassword masterPassword) {
		return (List<StoredPassword>) databaseConnection.selectList("getAllStoredPasswords", masterPassword);
	}

	@Override
	public int instertStoredPassword(final StoredPassword storedPassword) {
		return databaseConnection.insert("instertSingleStoredPassword", storedPassword);
	}

	@Override
	public int updateStoredPassword(final StoredPassword storedPassword) {
		return databaseConnection.update("updateSingleStoredPassword", storedPassword);
	}

}
