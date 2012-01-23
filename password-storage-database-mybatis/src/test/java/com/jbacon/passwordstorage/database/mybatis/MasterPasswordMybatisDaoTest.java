package com.jbacon.passwordstorage.database.mybatis;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.test.tools.RemoveTestFiles;

public class MasterPasswordMybatisDaoTest {

	private static final String TEST_SQLITE_DATABASE_FILENAME = "dbTest.sqlite";
	private static final String TEST_MYBATIS_CONFIGURATION_FILENAME = "mybatisTest/Configuration.xml";

	private static final String TEST_VALUE_SALT = "salt";
	private static final String TEST_VALUE_ENCRYPTED_SECRET_KEY = "encryptedSecretKey";
	private static final String TEST_VALUE_PROFILE_NAME = "profileName";

	private static MaintenanceMybatisDao MAINTENANCE_DAO;
	private static MasterPasswordMybatisDao MASTER_PASSWORD_DAO;

	@AfterClass
	public static void cleanUp() throws IOException {
		RemoveTestFiles.remove(TEST_SQLITE_DATABASE_FILENAME);
	}

	private static void createTables() throws IOException {
		MAINTENANCE_DAO = new MaintenanceMybatisDao(TEST_MYBATIS_CONFIGURATION_FILENAME);
		MAINTENANCE_DAO.createMasterPasswordTable();
	}

	private static void dropTables() throws IOException {
		MAINTENANCE_DAO = new MaintenanceMybatisDao(TEST_MYBATIS_CONFIGURATION_FILENAME);
		MAINTENANCE_DAO.dropMasterPasswordTable();
	}

	private static MasterPassword generateMasterPassword(final int id) {
		return new MasterPassword(TEST_VALUE_PROFILE_NAME + id, TEST_VALUE_ENCRYPTED_SECRET_KEY + id, TEST_VALUE_SALT + id, null, null, null,
				EncryptionType.PBE_WITH_MD5_AND_DES, EncryptionType.AES_256);
	}

	@BeforeClass
	public static void setupBeforeClass() throws IOException {
		dropTables();
		createTables();
		MASTER_PASSWORD_DAO = new MasterPasswordMybatisDao(TEST_MYBATIS_CONFIGURATION_FILENAME);
	}

	@Test
	public void should01BeAbleToInstertMasterPassword() {
		Integer result = MASTER_PASSWORD_DAO.instertMasterPassword(generateMasterPassword(1));
		assertThat(result, is(equalTo(1)));
	}

	@Test
	public void should02BeAbleToGetMasterPasswords() {
		List<MasterPassword> result = MASTER_PASSWORD_DAO.getMasterPasswords();
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
	}

	@Test
	public void should03BeAbleToGetMasterPasswordNames() {
		List<String> result = MASTER_PASSWORD_DAO.getMasterPasswordNames();
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		assertThat(result.get(0), is(equalTo(TEST_VALUE_PROFILE_NAME + 1)));
	}

	@Test
	public void should04BeAbleToGetMasterPasswordByProfileName() {
		MasterPassword result = MASTER_PASSWORD_DAO.getMasterPassword(TEST_VALUE_PROFILE_NAME + 1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.getProfileName(), is(equalTo(TEST_VALUE_PROFILE_NAME + 1)));
		assertThat(result.getId(), is(equalTo(1)));
		assertThat(result.getEncryptedSecretKey(), is(equalTo(TEST_VALUE_ENCRYPTED_SECRET_KEY + 1)));
		assertThat(result.getSalt(), is(equalTo(TEST_VALUE_SALT + 1)));
		assertThat(result.getMasterPasswordEncryptionType(), is(equalTo(EncryptionType.PBE_WITH_MD5_AND_DES)));
		assertThat(result.getStoredPasswordEncryptionType(), is(equalTo(EncryptionType.AES_256)));
	}

	@Test
	public void should05BeableToGetMasterPasswordById() {
		MasterPassword result = MASTER_PASSWORD_DAO.getMasterPassword(1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.getProfileName(), is(equalTo(TEST_VALUE_PROFILE_NAME + 1)));
		assertThat(result.getId(), is(equalTo(1)));
		assertThat(result.getEncryptedSecretKey(), is(equalTo(TEST_VALUE_ENCRYPTED_SECRET_KEY + 1)));
		assertThat(result.getSalt(), is(equalTo(TEST_VALUE_SALT + 1)));
	}

	@Test
	public void should06BeAbleToGetMasterPasswordID() {
		MasterPassword toGetIdFor = MASTER_PASSWORD_DAO.getMasterPassword(TEST_VALUE_PROFILE_NAME + 1);
		Integer result = MASTER_PASSWORD_DAO.getMasterPasswordId(toGetIdFor);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(equalTo(1)));
	}

	@Test
	public void should07BeAbleToUpdateMasterPassword() {
		MasterPassword toUpdate = generateMasterPassword(2);
		toUpdate.setId(1);
		Integer result = MASTER_PASSWORD_DAO.updateMasterPassword(toUpdate);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(equalTo(1)));
	}

	@Test
	public void should08BeAbleToDeleteMasterPassword() {
		MasterPassword toDelete = generateMasterPassword(2);
		toDelete.setId(1);
		Integer result = MASTER_PASSWORD_DAO.deleteMasterPassword(toDelete);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(equalTo(1)));
	}
}
