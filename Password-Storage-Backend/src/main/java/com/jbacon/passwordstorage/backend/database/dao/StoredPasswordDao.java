package com.jbacon.passwordstorage.backend.database.dao;

import java.util.List;

import com.jbacon.passwordstorage.backend.encryption.objects.MasterPassword;
import com.jbacon.passwordstorage.backend.encryption.objects.StoredPassword;

/**
 * StoredPassword Dao
 * 
 * @author James Bacon
 */
public interface StoredPasswordDao {

	/**
	 * Deletes a StoredPassword from the database.
	 * 
	 * @param storedPassword
	 *            the StoredPassword to delete from the database.
	 * @return the result of the SQL query.
	 */
	public int deleteStoredPassword(StoredPassword storedPassword);

	/**
	 * Retrieves a list of all StoredPasswords from the database.
	 * 
	 * @return a list of every StoredPassword in the database.
	 */
	public List<StoredPassword> getStoredPasswords();

	/**
	 * Retrieves a list of StoredPassword from the database, for a specific
	 * profile / MasterPassword.
	 * 
	 * @param masterPassword
	 *            the MasterPassword for the desired StoredPasswords.
	 * @return the list of all the StoredPasswords for the provided
	 *         MasterPassword.
	 */
	public List<StoredPassword> getStoredPasswords(MasterPassword masterPassword);

	/**
	 * Inserts a new StoredPassword into the database.
	 * 
	 * @param storedPassword
	 *            the StoredPassword to insert into the database.
	 * @return the result of the SQL query.
	 */
	public int instertStoredPassword(StoredPassword storedPassword);

	/**
	 * Updates a StoredPassword in the database with the supplied details.
	 * 
	 * @param storedPassword
	 *            the StoredPassword to update in the database.
	 * @return the result of the SQL query.
	 */
	public int updateStoredPassword(StoredPassword storedPassword);
}
