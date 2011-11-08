package com.jbacon.passwordstorage.backend.database.mybatis;

import java.util.List;

import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;

public interface StoredPasswordDao {
	public int deleteSingleStoredPassword();

	public List<MasterPassword> getAllStoredPasswords();

	public List<MasterPassword> getSingleStoredPassword();

	public int instertSingleStoredPassword();

	public int updateSingleStoredPassword();
}
