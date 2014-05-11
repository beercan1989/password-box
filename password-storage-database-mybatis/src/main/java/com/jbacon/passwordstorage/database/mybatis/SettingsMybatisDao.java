package com.jbacon.passwordstorage.database.mybatis;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

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
        final String settingName = setting.name();
        return databaseConnection.delete("deleteSetting", settingName);
    }
    
    @Override
    public String getSetting(final Setting setting) {
        final String settingName = setting.name();
        return databaseConnection.selectOne("getSetting", settingName).toString();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public EnumMap<Setting, String> getSettings() {
        final List<SettingsPair> allSettings = (List<SettingsPair>) databaseConnection.selectList("getSettings");
        final EnumMap<Setting, String> allSettingsMap = new EnumMap<Setting, String>(Setting.class);
        
        for (final SettingsPair settingsPair : allSettings) {
            allSettingsMap.put(settingsPair.getSettingName(), settingsPair.getSettingValue());
        }
        
        return allSettingsMap;
    }
    
    @Override
    public int insertSetting(final Setting setting, final String value) {
        final String settingName = setting.name();
        return databaseConnection.insert("insertSetting", new String[] { settingName, value });
    }
    
    @Override
    public int updateSetting(final Setting setting, final String value) {
        final String settingName = setting.name();
        return databaseConnection.update("updateSetting", new String[] { settingName, value });
    }
    
}
