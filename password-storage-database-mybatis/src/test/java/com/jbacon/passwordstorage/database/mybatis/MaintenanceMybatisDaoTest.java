package com.jbacon.passwordstorage.database.mybatis;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MaintenanceMybatisDaoTest {

    private static MaintenanceMybatisDao MAINTENANCE_DAO;

    @AfterClass
    public static void cleanUp() throws IOException {
        // RemoveTestFiles.remove("dbTest.sqlite");
    }

    @BeforeClass
    public static void setup() throws IOException {
        MAINTENANCE_DAO = new MaintenanceMybatisDao("mybatisTest/Configuration.xml");
    }

    @Test
    public void shouldCreateMasterPasswordTable() {
        final int result = MAINTENANCE_DAO.createMasterPasswordTable();
        assertThat(result, is(equalTo(0)));
    }

    @Test
    public void shouldCreateStoredPasswordTable() {
        final int result = MAINTENANCE_DAO.createStoredPasswordTable();
        assertThat(result, is(equalTo(0)));
    }

    @Test
    public void shouldDropMasterPasswordTable() {
        final int result = MAINTENANCE_DAO.dropMasterPasswordTable();
        assertThat(result, is(equalTo(0)));
    }

    @Test
    public void shouldDropStoredPasswordTable() {
        final int result = MAINTENANCE_DAO.dropStoredPasswordTable();
        assertThat(result, is(equalTo(0)));
    }
}
