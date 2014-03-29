package com.jbacon.passwordstorage.utils;

public class DBUtil {

    public static boolean unsuccessfulImport(final int result) {
        if (result == 1) {
            return false;
        }
        return true;
    }

    public static <T> T createMybatisDao(final Class<T> mybatisDaoClass) {
        try {
            return mybatisDaoClass.newInstance();
        } catch (final Exception e) {
            JOptionUtil.errorMessage("Failed to load mybatis configuration details", "Mybatis Fail", e);
            System.exit(-1);
            return null; // Will never reach here.
        }
    }
}
