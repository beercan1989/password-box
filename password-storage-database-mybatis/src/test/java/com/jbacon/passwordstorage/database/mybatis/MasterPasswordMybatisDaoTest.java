package com.jbacon.passwordstorage.database.mybatis;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
		return new MasterPassword(TEST_VALUE_PROFILE_NAME + id, TEST_VALUE_ENCRYPTED_SECRET_KEY + id, TEST_VALUE_SALT + id, null, null, null);
	}

	@BeforeClass
	public static void setup() throws IOException {
		dropTables();
		createTables();

		MASTER_PASSWORD_DAO = new MasterPasswordMybatisDao(TEST_MYBATIS_CONFIGURATION_FILENAME);
	}

	@Test
	public void shouldBeAbleToInstertMasterPassword() {
		Integer result = MASTER_PASSWORD_DAO.instertMasterPassword(generateMasterPassword(1));
		assertThat(result, is(equalTo(1)));
	}

	@Test
	public void testDeleteMasterPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMasterPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMasterPasswordNames() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMasterPasswords() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMasterPassword() {
		fail("Not yet implemented");
	}
}
