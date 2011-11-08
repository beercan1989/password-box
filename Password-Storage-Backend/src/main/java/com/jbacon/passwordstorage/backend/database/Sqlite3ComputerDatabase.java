package com.jbacon.passwordstorage.backend.database;

import java.util.List;

import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;
import com.jbacon.passwordstorage.backend.encryption.objects.StoredPassword;

public class Sqlite3ComputerDatabase implements Sqlite3Database {

	@Override
	public void createDatabase() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMasterPassword(final String profileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSavedPassword(final String passwordName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropDatabase() {
		// TODO Auto-generated method stub

	}

	@Override
	public MasterPassword getMasterPassword(final String profileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoredPassword getStoredPassword(final String passwordName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoredPassword> getStoredPasswords(final String profileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMasterPassword(final MasterPassword masterPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertSavedPassword(final StoredPassword encryptedPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMasterPassword(final MasterPassword masterPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSavedPassword(final StoredPassword encryptedPassword) {
		// TODO Auto-generated method stub

	}

}
