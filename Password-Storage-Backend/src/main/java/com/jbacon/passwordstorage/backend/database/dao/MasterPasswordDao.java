package com.jbacon.passwordstorage.backend.database.dao;

import java.util.List;

import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;

/**
 * MasterPassword Dao
 * 
 * @author James Bacon
 */
public interface MasterPasswordDao extends GenericDao {

	/**
	 * Deletes a MasterPassword from the database.
	 * 
	 * @param masterPassword
	 *            the MasterPassword to delete from the database.
	 * @return the result of the SQL query.
	 */
	public int deleteMasterPassword(MasterPassword masterPassword);

	/**
	 * Retrieves a specific MasterPassword.
	 * 
	 * @param profileName
	 *            the profile name of the MasterPassword.
	 * @return the retrieved MasterPassword from the database.
	 */
	public MasterPassword getMasterPassword(String profileName);

	/**
	 * Retrieves the ID for a newly created database entry for a MasterPassword.
	 * 
	 * @param masterPassword
	 *            the MasterPassword to get an ID for.
	 * @return the ID value of the MasterPassword, null if the MasterPassword is
	 *         not in the database.
	 */
	public Integer getMasterPasswordId(MasterPassword masterPassword);

	/**
	 * Retrieves a list containing all the names of the MasterPasswords.
	 * 
	 * @return the list of every MasterPassword name.
	 */
	public List<String> getMasterPasswordNames();

	/**
	 * Retrieves a list containing all the MasterPasswords.
	 * 
	 * @return the list of every MasterPassword.
	 */
	public List<MasterPassword> getMasterPasswords();

	/**
	 * Inserts a MasterPassword into the database.
	 * 
	 * @param masterPassword
	 *            the MasterPassword to insert into the database.
	 * @return the result of the SQL query.
	 */
	public int instertMasterPassword(MasterPassword masterPassword);

	/**
	 * Updates a MasterPassword in the database.
	 * 
	 * @param masterPassword
	 *            the MasterPassword to update.
	 * @return the result of the SQL query.
	 */
	public int updateMasterPassword(MasterPassword masterPassword);

}
