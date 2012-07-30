package com.jbacon.passwordstorage.database.mybatis;

import java.io.IOException;
import java.util.EnumMap;

import com.jbacon.passwordstorage.database.dao.SettingsDao;
import com.jbacon.passwordstorage.settings.Setting;

public final class SettingsMybatisDao extends MybatisDao implements SettingsDao {

	public SettingsMybatisDao() throws IOException {
		super();
	}

	protected SettingsMybatisDao(final String testConfiguration) throws IOException {
		super(testConfiguration);
	}

	@Override
	public int deleteSetting(final Setting setting) {
		// TODO Auto-generated method stub
		return databaseConnection.delete("deleteSetting", setting.name());
	}

	@Override
	public String getSetting(final Setting setting) {
		// TODO Auto-generated method stub
		return databaseConnection.selectOne("getSetting", setting.name()).toString();
	}

	@Override
	public EnumMap<Setting, String> getSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertSetting(final Setting setting, final String value) {
		// TODO Auto-generated method stub
		return databaseConnection.insert("insertSetting", new String[] { setting.name(), value });
	}

	@Override
	public int updateSetting(final Setting setting, final String value) {
		// TODO Auto-generated method stub
		return databaseConnection.update("insertSetting", new String[] { setting.name(), value });
	}

}
