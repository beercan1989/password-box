package com.jbacon.passwordstorage.database.dao;

import java.util.List;

import com.jbacon.passwordstorage.password.MasterPassword;
import com.jbacon.passwordstorage.password.StoredPassword;

/**
 * StoredPassword Dao
 * 
 * @author James Bacon
 */
public interface StoredPasswordsDao extends GenericDao {

	/**
	 * Deletes a StoredPassword from the database.
	 * 
	 * @param storedPassword
	 *            the StoredPassword to delete from the database.
	 * @return the result of the SQL query.
	 */
	public int deleteStoredPassword(StoredPassword storedPassword);

	/**
	 * Retrieves a specific StoredPassword.
	 * 
	 * @param id
	 *            the id of the StoredPassword.
	 * @return the retrieved StoredPassword from the database.
	 */
	public StoredPassword getStoredPassword(Integer id);

	/**
	 * Retrieves the ID for a newly created database entry for a MasterPassword.
	 * 
	 * @param storedPassword
	 *            the StoredPassword to get an ID for.
	 * @return the ID value of the StoredPassword, null if the StoredPassword is
	 *         not in the database.
	 */
	public Integer getStoredPasswordId(StoredPassword storedPassword);

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
