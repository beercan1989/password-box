package com.jbacon.passwordstorage.database.mybatis;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Test;

import com.jbacon.passwordstorage.database.mybatis.MasterPasswordMybatisDao;
import com.jbacon.test.tools.RemoveTestFiles;

public class MasterPasswordMybatisDaoTest {

	@AfterClass
	public static void cleanUp() throws IOException {
		RemoveTestFiles.remove("dbTest.sqlite");
	}

	MasterPasswordMybatisDao dao;

	public MasterPasswordMybatisDaoTest() throws IOException {
		dao = new MasterPasswordMybatisDao("mybatisTest/Configuration.xml");
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
	public void testInstertMasterPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMasterPassword() {
		fail("Not yet implemented");
	}

}
