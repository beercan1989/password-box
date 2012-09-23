package com.jbacon.passwordstorage.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

public class GenericUtils {

    public static <T> boolean areNotNull(final T... collections) {
        for (final T collection : collections) {
            if (isNull(collection)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean areNull(final T... collections) {
        for (final T collection : collections) {
            if (isNotNull(collection)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean isNotNull(final T collection) {
        return !isNull(collection);
    }

    public static <T> boolean isNull(final T collection) {
        return (collection == null) ? true : false;
    }

    public static <T> boolean areNotEmpty(final T... collections) {
        if (collections.length < 1) {
            return false;
        }
        for (final T collection : collections) {
            if (isEmpty(collection)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean areEmpty(final T... collections) {
        if (collections.length < 1) {
            return false;
        }
        for (final T collection : collections) {
            if (isNotEmpty(collection)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean isNotEmpty(final T collection) {
        if (collection == null) {
            return false;
        }

        return !isEmpty(collection);
    }

    /**
     * Checks the param as to whether its empty or not.
     * 
     * Works if the param has an accessible method of 'isEmpty', 'length', 'size', 'getLength', 'getSize' or filed of
     * 'length' or 'size'.
     * 
     * @param collection
     *            Object to test for been empty.
     * @return Whether the provided collection is empty or not.
     */
    public static <T> boolean isEmpty(final T collection) {
        if (collection == null) {
            return false;
        }

        if (collection instanceof Collection) {
            return ((Collection<?>) collection).isEmpty();
        }

        if (collection instanceof Object[]) {
            return ((Object[]) collection).length < 1;
        }

        if (collection instanceof boolean[]) {
            return ((boolean[]) collection).length < 1;
        }

        if (collection instanceof byte[]) {
            return ((byte[]) collection).length < 1;
        }

        if (collection instanceof short[]) {
            return ((short[]) collection).length < 1;
        }

        if (collection instanceof char[]) {
            return ((char[]) collection).length < 1;
        }

        if (collection instanceof int[]) {
            return ((int[]) collection).length < 1;
        }

        if (collection instanceof long[]) {
            return ((long[]) collection).length < 1;
        }

        if (collection instanceof float[]) {
            return ((float[]) collection).length < 1;
        }

        if (collection instanceof double[]) {
            return ((double[]) collection).length < 1;
        }

        final Method isEmptyMethod = ReflectionUtils.getAccessibleMethod(collection, "isEmpty");
        if (isEmptyMethod != null) {
            final Boolean isEmpty = ReflectionUtils.invokeMethod(collection, isEmptyMethod);
            if (isEmpty != null) {
                return isEmpty.booleanValue();
            }
        }

        final Method sizeMethod = ReflectionUtils.getAccessibleMethod(collection, "size");
        if (sizeMethod != null) {
            final Number size = ReflectionUtils.invokeMethod(collection, sizeMethod);
            if (size != null) {
                return size.longValue() < 1L;
            }
        }

        final Method getSizeMethod = ReflectionUtils.getAccessibleMethod(collection, "getSize");
        if (getSizeMethod != null) {
            final Number size = ReflectionUtils.invokeMethod(collection, getSizeMethod);
            if (size != null) {
                return size.longValue() < 1L;
            }
        }

        final Method lengthMethod = ReflectionUtils.getAccessibleMethod(collection, "length");
        if (lengthMethod != null) {
            final Number length = ReflectionUtils.invokeMethod(collection, lengthMethod);
            if (length != null) {
                return length.longValue() < 1L;
            }
        }

        final Method getLengthMethod = ReflectionUtils.getAccessibleMethod(collection, "getLength");
        if (getLengthMethod != null) {
            final Number length = ReflectionUtils.invokeMethod(collection, getLengthMethod);
            if (length != null) {
                return length.longValue() < 1L;
            }
        }

        final Field lengthField = ReflectionUtils.getAccessibleField(collection, "length");
        if (lengthField != null) {
            final Number length = ReflectionUtils.accessField(collection, lengthField);
            if (length != null) {
                return length.longValue() < 1L;
            }
        }

        final Field sizeField = ReflectionUtils.getAccessibleField(collection, "size");
        if (sizeField != null) {
            final Number size = ReflectionUtils.accessField(collection, sizeField);
            if (size != null) {
                return size.longValue() < 1L;
            }
        }

        return false;
    }
}
