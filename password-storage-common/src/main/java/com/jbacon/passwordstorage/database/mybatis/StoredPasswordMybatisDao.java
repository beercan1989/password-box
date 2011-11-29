package com.jbacon.passwordstorage.database.mybatis;

import java.io.IOException;
import java.util.List;

import com.jbacon.passwordstorage.database.dao.StoredPasswordDao;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;

public final class StoredPasswordMybatisDao extends MybatisDao implements StoredPasswordDao {

	public StoredPasswordMybatisDao() throws IOException {
		super();
	}

	protected StoredPasswordMybatisDao(final String testConfiguration) throws IOException {
		super(testConfiguration);
	}

	@Override
	public int deleteStoredPassword(final StoredPassword storedPassword) {
		return databaseConnection.delete("deleteStoredPassword", storedPassword);
	}

	@Override
	public StoredPassword getStoredPassword(final Integer id) {
		return (StoredPassword) databaseConnection.selectOne("getStoredPasswordById", id);
	}

	@Override
	public Integer getStoredPasswordId(final StoredPassword storedPassword) {
		return (Integer) databaseConnection.selectOne("getStoredPasswordId");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<StoredPassword> getStoredPasswords() {
		return (List<StoredPassword>) databaseConnection.selectList("getEveryStoredPassword");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<StoredPassword> getStoredPasswords(final MasterPassword masterPassword) {
		return (List<StoredPassword>) databaseConnection.selectList("getStoredPasswords", masterPassword);
	}

	@Override
	public int instertStoredPassword(final StoredPassword storedPassword) {
		return databaseConnection.insert("instertStoredPassword", storedPassword);
	}

	@Override
	public int updateStoredPassword(final StoredPassword storedPassword) {
		return databaseConnection.update("updateStoredPassword", storedPassword);
	}

}
