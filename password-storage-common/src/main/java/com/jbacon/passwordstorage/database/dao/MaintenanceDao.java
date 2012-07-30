package com.jbacon.passwordstorage.database.dao;

/**
 * Maintenance Dao
 * 
 * @author James Bacon
 */
public interface MaintenanceDao extends GenericDao {

	/**
	 * Creates the MasterPasswords table in the database.
	 * 
	 * @return the result of the SQL query.
	 */
	public int createMasterPasswordTable();

	/**
	 * Creates the Settings table in the database.
	 * 
	 * @return the result of the SQL query.
	 */
	public int createSettingsTable();

	/**
	 * Creates the StoredPasswords table in the database.
	 * 
	 * @return the result of the SQL query.
	 */
	public int createStoredPasswordTable();

	/**
	 * Deletes the MasterPasswords table in the database.
	 * 
	 * @return the result of the SQL query.
	 */
	public int dropMasterPasswordTable();

	/**
	 * Deletes the Settings table in the database.
	 * 
	 * @return the result of the SQL query.
	 */
	public int dropSettingsTable();

	/**
	 * Deletes the StoredPasswords table in the database.
	 * 
	 * @return the result of the SQL query.
	 */
	public int dropStoredPasswordTable();
}
