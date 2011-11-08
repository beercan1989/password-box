package com.jbacon.passwordstorage.backend.database;

import com.jbacon.passwordstorage.backend.database.errors.UnsupportedDatabaseException;

public enum DatabaseType implements Database {
	Computer {
		@Override
		public Sqlite3Database create() {
			return new Sqlite3ComputerDatabase();
		}
	},

	Android,

	Blackberry;

	@Override
	public Sqlite3Database create() throws UnsupportedDatabaseException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}
}
