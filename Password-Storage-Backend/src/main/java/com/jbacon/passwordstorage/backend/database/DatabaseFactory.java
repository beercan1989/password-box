package com.jbacon.passwordstorage.backend.database;

import java.util.EnumMap;
import java.util.Map;

import com.jbacon.passwordstorage.backend.database.error.UnsupportedDatabaseException;

public class DatabaseFactory {

    private final Map<DatabaseType, Sqlite3Database> databases;

    public DatabaseFactory() {
        databases = new EnumMap<DatabaseType, Sqlite3Database>(DatabaseType.class);
        databases.put(DatabaseType.Android, new Sqlite3AndroidDatabase());
        databases.put(DatabaseType.Blackberry, new Sqlite3BlackberryDatabase());
        databases.put(DatabaseType.Computer, new Sqlite3ComputerDatabase());
    }

    public Sqlite3Database getDatabase(final DatabaseType databaseType) throws UnsupportedDatabaseException {
        if (!databases.containsKey(databaseType)) { throw new UnsupportedDatabaseException("Unsupported database type ["
                + databaseType.name() + "]"); }
        return databases.get(databaseType);
    }
}