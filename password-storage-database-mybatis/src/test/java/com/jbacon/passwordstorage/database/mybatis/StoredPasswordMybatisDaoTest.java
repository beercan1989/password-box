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
import com.jbacon.passwordstorage.password.StoredPassword;
import com.jbacon.test.tools.RemoveTestFiles;

public class StoredPasswordMybatisDaoTest {

	private static final String TV_SP_ENCRYPTED_PASSWORD_NOTES = "encryptedPasswordNotes";
	private static final String TV_SP_ENCRYPTED_PASSWORD = "encryptedPassword";
	private static final String TV_SP_ENCRYPTED_PASSWORD_NAME = "encryptedPasswordName";
	private static final String TEST_SQLITE_DATABASE_FILENAME = "dbTest.sqlite";
	private static final String TEST_MYBATIS_CONFIGURATION_FILENAME = "mybatisTest/Configuration.xml";

	private static final String TV_MP_SALT = "salt";
	private static final String TV_MP_ENCRYPTED_SECRET_KEY = "encryptedSecretKey";
	private static final String TV_MP_PROFILE_NAME = "profileName";

	private static MaintenanceMybatisDao MAINTENANCE_DAO;
	private static StoredPasswordMybatisDao STORED_PASSWORD_DAO;

	@AfterClass
	public static void cleanUp() throws IOException {
		RemoveTestFiles.remove(TEST_SQLITE_DATABASE_FILENAME);
	}

	private static MasterPassword generateMasterPassword(final int id) {
		return new MasterPassword(TV_MP_PROFILE_NAME + id, TV_MP_ENCRYPTED_SECRET_KEY + id, TV_MP_SALT + id, null, null, null, EncryptionType.AES_256,
				EncryptionType.PBE_WITH_MD5_AND_DES);
	}

	private static StoredPassword generateStoredPassword(final int spId, final int mpId) {
		return new StoredPassword(TV_SP_ENCRYPTED_PASSWORD_NAME + spId, TV_MP_PROFILE_NAME + mpId, TV_SP_ENCRYPTED_PASSWORD + spId,
				TV_SP_ENCRYPTED_PASSWORD_NOTES + spId, null, null, null);
	}

	@BeforeClass
	public static void setupBeforeClass() throws IOException {
		MAINTENANCE_DAO = new MaintenanceMybatisDao(TEST_MYBATIS_CONFIGURATION_FILENAME);
		STORED_PASSWORD_DAO = new StoredPasswordMybatisDao(TEST_MYBATIS_CONFIGURATION_FILENAME);

		MAINTENANCE_DAO.dropStoredPasswordTable();
		MAINTENANCE_DAO.createStoredPasswordTable();
	}

	@Test
	public void should_01_InstertStoredPassword() {
		StoredPassword toInsert = generateStoredPassword(1, 1);
		Integer result = STORED_PASSWORD_DAO.instertStoredPassword(toInsert);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(equalTo(1)));
	}

	@Test
	public void should_02_GetAllStoredPasswords() {
		List<StoredPassword> result = STORED_PASSWORD_DAO.getStoredPasswords();
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		assertThat(result.get(0).getId(), is(equalTo(1)));
	}

	@Test
	public void should_03_GetStoredPasswordId() {
		StoredPassword toGetIdFor = generateStoredPassword(1, 1);
		Integer result = STORED_PASSWORD_DAO.getStoredPasswordId(toGetIdFor);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(equalTo(1)));
	}

	@Test
	public void should_04_GetStoredPasswordById() {
		StoredPassword result = STORED_PASSWORD_DAO.getStoredPassword(1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.getEncryptedPassword(), is(equalTo(TV_SP_ENCRYPTED_PASSWORD + 1)));
		assertThat(result.getEncryptedPasswordName(), is(equalTo(TV_SP_ENCRYPTED_PASSWORD_NAME + 1)));
		assertThat(result.getEncryptedPasswordNotes(), is(equalTo(TV_SP_ENCRYPTED_PASSWORD_NOTES + 1)));
		assertThat(result.getProfileName(), is(equalTo(TV_MP_PROFILE_NAME + 1)));
	}

	@Test
	public void should_05_GetStoredPasswordsByMasterPassword() {
		MasterPassword toUse = generateMasterPassword(1);
		List<StoredPassword> result = STORED_PASSWORD_DAO.getStoredPasswords(toUse);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		assertThat(result.get(0).getId(), is(equalTo(1)));
	}

	@Test
	public void should_06_UpdateStoredPassword() {
		StoredPassword toUpdate = generateStoredPassword(1, 1);
		toUpdate.setId(1);
		toUpdate.setEncryptedPasswordNotes("second " + TV_SP_ENCRYPTED_PASSWORD_NOTES);
		Integer result = STORED_PASSWORD_DAO.updateStoredPassword(toUpdate);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(equalTo(1)));
	}

	@Test
	public void should_07_DeleteStoredPassword() {
		StoredPassword toUpdate = generateStoredPassword(1, 1);
		toUpdate.setId(1);
		Integer result = STORED_PASSWORD_DAO.deleteStoredPassword(toUpdate);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(equalTo(1)));
	}

}
