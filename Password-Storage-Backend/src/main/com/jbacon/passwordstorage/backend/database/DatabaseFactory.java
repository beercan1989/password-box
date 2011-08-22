package com.jbacon.passwordstorage.backend.database;

public class DatabaseFactory {

	private final Sqlite3AndroidDatabase androidDatabase;
	private final Sqlite3BlackberryDatabase blackberryDatabase;
	private final Sqlite3ComputerDatabase computerDatabase;

	public DatabaseFactory() {
		androidDatabase = new Sqlite3AndroidDatabase();
		blackberryDatabase = new Sqlite3BlackberryDatabase();
		computerDatabase = new Sqlite3ComputerDatabase();
	}

	public Sqlite3Database getInstance(final DatabaseType databaseType)
			throws DatabaseException {
		if (databaseType == DatabaseType.Android) {
			return androidDatabase;
		}
		if (databaseType == DatabaseType.Blackberry) {
			return blackberryDatabase;
		}
		if (databaseType == DatabaseType.Computer) {
			return computerDatabase;
		}
		throw new DatabaseException("Unsupported Database Type");
	}
}
