package com.jbacon.passwordstorage.backend.database.mybatis;

import java.util.List;

import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;

public interface MasterPasswordDao {

	public int deleteSingleMasterPassword();

	public List<MasterPassword> getAllMasterPasswords();

	public List<MasterPassword> getSingleMasterPassword();

	public int instertSingleMasterPassword();

	public int updateSingleMasterPassword();

}
