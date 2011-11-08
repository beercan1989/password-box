package com.jbacon.passwordstorage.backend.database.mybatis;

import java.io.IOException;

import org.junit.Test;

public class MaintenanceMybatisDaoTest {

	MaintenanceMybatisDao dao;

	public MaintenanceMybatisDaoTest() throws IOException {
		dao = new MaintenanceMybatisDao();
	}

	@Test
	public void testCreateAllTables() {
		dao.createAllTables();
	}

	@Test
	public void testDropAllTables() {
		dao.dropAllTables();
	}

}
