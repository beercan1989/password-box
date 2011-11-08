package com.jbacon.passwordstorage.backend.database;

import com.jbacon.passwordstorage.backend.database.errors.UnsupportedDatabaseException;

public interface Database {

	public Sqlite3Database create() throws UnsupportedDatabaseException;

}
