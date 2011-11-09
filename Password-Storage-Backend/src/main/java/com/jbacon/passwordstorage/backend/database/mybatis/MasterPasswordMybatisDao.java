package com.jbacon.passwordstorage.backend.database.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jbacon.passwordstorage.backend.database.dao.MasterPasswordDao;
import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;

public class MasterPasswordMybatisDao implements MasterPasswordDao {

	private static final String MYBATIS_CONFIGURATION = "com.jbacon.passwordstorage.backend.database.mybatis.configuration/configuration.xml";
	private final SqlSession databaseConnection;

	public MasterPasswordMybatisDao() throws IOException {
		Reader reader = Resources.getResourceAsReader(MYBATIS_CONFIGURATION);
		databaseConnection = new SqlSessionFactoryBuilder().build(reader).openSession();
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
		return databaseConnection.selectList("getAllMasterPasswordNames");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MasterPassword> getMasterPasswords() {
		return databaseConnection.selectList("getAllMasterPasswords");
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
