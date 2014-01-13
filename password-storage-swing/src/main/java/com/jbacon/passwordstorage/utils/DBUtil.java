package com.jbacon.passwordstorage.utils;

public class DBUtil {
    public static boolean unsuccessfulImport(final int result) {
        if (result == 1) {
            return false;
        }
        return true;
    }
}
