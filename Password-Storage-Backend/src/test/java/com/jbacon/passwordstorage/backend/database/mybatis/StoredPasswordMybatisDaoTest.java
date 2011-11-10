package com.jbacon.passwordstorage.backend.database.mybatis;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Test;

import com.jbacon.test.tools.RemoveTestFiles;

public class StoredPasswordMybatisDaoTest {

	@AfterClass
	public static void cleanUp() throws IOException {
		RemoveTestFiles.remove("dbTest.sqlite");
	}

	StoredPasswordMybatisDao dao;

	public StoredPasswordMybatisDaoTest() throws IOException {
		dao = new StoredPasswordMybatisDao("mybatisTest/Configuration.xml");
	}

	@Test
	public void testDeleteStoredPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStoredPasswords() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStoredPasswordsMasterPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testInstertStoredPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStoredPassword() {
		fail("Not yet implemented");
	}

}
