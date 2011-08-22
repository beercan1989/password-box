package com.jbacon.passwordstorage.backend.database;

import java.util.List;

import com.jbacon.passwordstorage.backend.encryptionobjects.EncryptedPassword;
import com.jbacon.passwordstorage.backend.encryptionobjects.MasterPassword;

public interface Sqlite3Database {
	public EncryptedPassword getStoredPassword(String passwordName);

	public List<EncryptedPassword> getStoredPasswords(String profileName);

	public MasterPassword getMasterPassword(String profileName);

	public void deleteSavedPassword(String passwordName);

	public void deleteMasterPassword(String profileName);

	public void createDatabase();

	public void dropDatabase();

	public void insertSavedPassword(EncryptedPassword encryptedPassword);

	public void insertMasterPassword(MasterPassword masterPassword);

	public void updateSavedPassword(EncryptedPassword encryptedPassword);

	public void updateMasterPassword(MasterPassword masterPassword);
}
