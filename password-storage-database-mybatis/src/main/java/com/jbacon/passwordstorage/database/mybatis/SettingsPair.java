package com.jbacon.passwordstorage.database.mybatis;

import com.jbacon.passwordstorage.settings.Setting;

public class SettingsPair {
	private Setting settingName;
	private String settingValue;

	public SettingsPair() {
	}

	public SettingsPair(final Setting settingName, final String settingValue) {
		this.settingName = settingName;
		this.settingValue = settingValue;
	}

	public Setting getSettingName() {
		return settingName;
	}

	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingName(final Setting settingName) {
		this.settingName = settingName;
	}

	public void setSettingValue(final String settingValue) {
		this.settingValue = settingValue;
	}
}
