package com.jbacon.passwordstorage.backend.database;

import java.util.List;

import com.jbacon.passwordstorage.backend.encryption.objects.StoredPassword;
import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;

public interface Sqlite3Database {
	public StoredPassword getStoredPassword(String passwordName);

	public List<StoredPassword> getStoredPasswords(String profileName);

	public MasterPassword getMasterPassword(String profileName);

	public void deleteSavedPassword(String passwordName);

	public void deleteMasterPassword(String profileName);

	public void createDatabase();

	public void dropDatabase();

	public void insertSavedPassword(StoredPassword encryptedPassword);

	public void insertMasterPassword(MasterPassword masterPassword);

	public void updateSavedPassword(StoredPassword encryptedPassword);

	public void updateMasterPassword(MasterPassword masterPassword);
}
