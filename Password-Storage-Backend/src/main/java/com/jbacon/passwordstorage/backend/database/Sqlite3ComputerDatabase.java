package com.jbacon.passwordstorage.backend.database;

import java.util.List;

import com.jbacon.passwordstorage.backend.encryptionobjects.EncryptedPassword;
import com.jbacon.passwordstorage.backend.encryptionobjects.MasterPassword;

public class Sqlite3ComputerDatabase implements Sqlite3Database {

	@Override
	public EncryptedPassword getStoredPassword(final String passwordName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EncryptedPassword> getStoredPasswords(final String profileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MasterPassword getMasterPassword(final String profileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSavedPassword(final String passwordName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMasterPassword(final String profileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createDatabase() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropDatabase() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertSavedPassword(final EncryptedPassword encryptedPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertMasterPassword(final MasterPassword masterPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSavedPassword(final EncryptedPassword encryptedPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMasterPassword(final MasterPassword masterPassword) {
		// TODO Auto-generated method stub

	}

}
