package com.jbacon.passwordstorage.database.dao;

import java.util.EnumMap;

import com.jbacon.passwordstorage.settings.Setting;

/**
 * Settings Dao
 * 
 * @author James Bacon
 */
public interface SettingsDao extends GenericDao {

	/**
	 * Deletes the setting from the database.
	 * 
	 * @param setting
	 * @return
	 */
	public int deleteSetting(final Setting setting);

	/**
	 * Gets the setting value from the database.
	 * 
	 * @param setting
	 * @return
	 */
	public String getSetting(final Setting setting);

	/**
	 * Gets an EnumMap of all the settings in the database.
	 * 
	 * @return
	 */
	public EnumMap<Setting, String> getSettings();

	/**
	 * Inserts a new setting into the database.
	 * 
	 * @param setting
	 * @param value
	 * @return
	 */
	public int insertSetting(final Setting setting, final String value);

	/**
	 * Updates an existing setting in the database.
	 * 
	 * @param setting
	 * @param value
	 * @return
	 */
	public int updateSetting(final Setting setting, final String value);

}
