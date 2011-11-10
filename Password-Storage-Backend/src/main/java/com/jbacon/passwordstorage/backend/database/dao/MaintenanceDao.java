package com.jbacon.passwordstorage.backend.database.dao;

/**
 * Maintenance Dao
 * 
 * @author James Bacon
 */
public interface MaintenanceDao extends GenericDao {

	/**
	 * Creates all the necessary tables in the database.
	 * 
	 * @return the result of the SQL query.
	 */
	public int createAllTables();

	/**
	 * Deletes all the tables in the database.
	 * 
	 * @return the result of the SQL query.
	 */
	public int dropAllTables();
}
