package com.jbacon.passwordstorage.backend.database.mybatis;

import java.io.IOException;
import java.util.List;

import com.jbacon.passwordstorage.backend.database.dao.MasterPasswordDao;
import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;

public final class MasterPasswordMybatisDao extends MybatisDao implements MasterPasswordDao {

	public MasterPasswordMybatisDao() throws IOException {
		super();
	}

	protected MasterPasswordMybatisDao(final String testConfiguration) throws IOException {
		super(testConfiguration);
	}

	@Override
	public int deleteMasterPassword(final MasterPassword masterPassword) {
		return databaseConnection.update("deleteSingleMasterPassword");
	}

	@Override
	public MasterPassword getMasterPassword(final String profileName) {
		return (MasterPassword) databaseConnection.selectOne("getSingleMasterPassword");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getMasterPasswordNames() {
		return (List<String>) databaseConnection.selectList("getAllMasterPasswordNames");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MasterPassword> getMasterPasswords() {
		return (List<MasterPassword>) databaseConnection.selectList("getAllMasterPasswords");
	}

	@Override
	public int instertMasterPassword(final MasterPassword masterPassword) {
		return databaseConnection.update("instertSingleMasterPassword");
	}

	@Override
	public int updateMasterPassword(final MasterPassword masterPassword) {
		return databaseConnection.update("updateSingleMasterPassword");
	}

}
