package com.jbacon.passwordstorage.backend.database.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;

public class MasterPasswordMybatisDao implements MasterPasswordDao {

	private static final String MYBATIS_CONFIGURATION = "com.jbacon.passwordstorage.backend.database.mybatis.configuration/configuration.xml";
	private final SqlSession databaseConnection;

	public MasterPasswordMybatisDao() throws IOException {
		Reader reader = Resources.getResourceAsReader(MYBATIS_CONFIGURATION);
		databaseConnection = new SqlSessionFactoryBuilder().build(reader).openSession();
	}

	@Override
	public int deleteSingleMasterPassword() {
		return databaseConnection.update("deleteSingleMasterPassword");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MasterPassword> getAllMasterPasswords() {
		return databaseConnection.selectList("getAllMasterPasswords");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MasterPassword> getSingleMasterPassword() {
		return databaseConnection.selectList("getSingleMasterPassword");
	}

	@Override
	public int instertSingleMasterPassword() {
		return databaseConnection.update("instertSingleMasterPassword");
	}

	@Override
	public int updateSingleMasterPassword() {
		return databaseConnection.update("updateSingleMasterPassword");
	}

}
