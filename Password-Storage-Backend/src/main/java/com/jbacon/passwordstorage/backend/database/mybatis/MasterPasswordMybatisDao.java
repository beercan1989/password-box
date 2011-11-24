package com.jbacon.passwordstorage.backend.database.mybatis;

import java.io.IOException;
import java.util.List;

import com.jbacon.passwordstorage.backend.database.dao.MasterPasswordDao;
import com.jbacon.passwordstorage.backend.password.MasterPassword;

public final class MasterPasswordMybatisDao extends MybatisDao implements MasterPasswordDao {

	public MasterPasswordMybatisDao() throws IOException {
		super();
	}

	protected MasterPasswordMybatisDao(final String testConfiguration) throws IOException {
		super(testConfiguration);
	}

	@Override
	public int deleteMasterPassword(final MasterPassword masterPassword) {
		return databaseConnection.delete("deleteMasterPassword", masterPassword);
	}

	@Override
	public MasterPassword getMasterPassword(final Integer id) {
		return (MasterPassword) databaseConnection.selectOne("getMasterPasswordById", id);
	}

	@Override
	public MasterPassword getMasterPassword(final String profileName) {
		return (MasterPassword) databaseConnection.selectOne("getMasterPasswordByProfileName", profileName);
	}

	@Override
	public Integer getMasterPasswordId(final MasterPassword masterPassword) {
		return (Integer) databaseConnection.selectOne("getMasterPasswordId", masterPassword);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getMasterPasswordNames() {
		return (List<String>) databaseConnection.selectList("getMasterPasswordNames");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MasterPassword> getMasterPasswords() {
		return (List<MasterPassword>) databaseConnection.selectList("getMasterPasswords");
	}

	@Override
	public int instertMasterPassword(final MasterPassword masterPassword) {
		return databaseConnection.insert("instertMasterPassword", masterPassword);
	}

	@Override
	public int updateMasterPassword(final MasterPassword masterPassword) {
		return databaseConnection.update("updateMasterPassword", masterPassword);
	}

}
