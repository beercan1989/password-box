package com.jbacon.passwordstorage.backend.database.mybatis;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Test;

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
	public void shouldCreateAllTables() {
		int result = dao.createAllTables();
		assertThat(result, is(equalTo(0)));
	}

	@Test
	public void shouldDropAllTables() {
		int result = dao.dropAllTables();
		assertThat(result, is(equalTo(0)));
	}

}
