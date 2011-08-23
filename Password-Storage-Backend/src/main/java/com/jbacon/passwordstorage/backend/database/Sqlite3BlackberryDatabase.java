package com.jbacon.passwordstorage.backend.database;

import java.util.List;

import com.jbacon.passwordstorage.backend.encryptionobjects.EncryptedPassword;
import com.jbacon.passwordstorage.backend.encryptionobjects.MasterPassword;

public class Sqlite3BlackberryDatabase implements Sqlite3Database {

	public EncryptedPassword getStoredPassword(final String passwordName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EncryptedPassword> getStoredPasswords(final String profileName) {
		// TODO Auto-generated method stub
		return null;
	}

	public MasterPassword getMasterPassword(final String profileName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteSavedPassword(final String passwordName) {
		// TODO Auto-generated method stub

	}

	public void deleteMasterPassword(final String profileName) {
		// TODO Auto-generated method stub

	}

	public void createDatabase() {
		// TODO Auto-generated method stub

	}

	public void dropDatabase() {
		// TODO Auto-generated method stub

	}

	public void insertSavedPassword(final EncryptedPassword encryptedPassword) {
		// TODO Auto-generated method stub

	}

	public void insertMasterPassword(final MasterPassword masterPassword) {
		// TODO Auto-generated method stub

	}

	public void updateSavedPassword(final EncryptedPassword encryptedPassword) {
		// TODO Auto-generated method stub

	}

	public void updateMasterPassword(final MasterPassword masterPassword) {
		// TODO Auto-generated method stub

	}

}
