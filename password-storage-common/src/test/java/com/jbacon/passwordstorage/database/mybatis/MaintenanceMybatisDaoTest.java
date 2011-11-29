package com.jbacon.passwordstorage.database.mybatis;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Test;

import com.jbacon.passwordstorage.database.mybatis.MaintenanceMybatisDao;
import com.jbacon.test.tools.RemoveTestFiles;

public class MaintenanceMybatisDaoTest {

	@AfterClass
	public static void cleanUp() throws IOException {
		RemoveTestFiles.remove("dbTest.sqlite");
	}

	MaintenanceMybatisDao dao;

	public MaintenanceMybatisDaoTest() throws IOException {
		dao = new MaintenanceMybatisDao("mybatisTest/Configuration.xml");
	}

	@Test
	public void shouldCreateMasterPasswordTable() {
		int result = dao.createMasterPasswordTable();
		assertThat(result, is(equalTo(0)));
	}

	@Test
	public void shouldCreateStoredPasswordTable() {
		int result = dao.createStoredPasswordTable();
		assertThat(result, is(equalTo(0)));
	}

	@Test
	public void shouldDropMasterPasswordTable() {
		int result = dao.dropMasterPasswordTable();
		assertThat(result, is(equalTo(0)));
	}

	@Test
	public void shouldDropStoredPasswordTable() {
		int result = dao.dropStoredPasswordTable();
		assertThat(result, is(equalTo(0)));
	}

}
