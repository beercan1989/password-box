package com.jbacon.passwordstorage.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtil {
    private ReflectionUtil() {
    }

    public static <T> Method getAccessibleMethod(final T object, final String methodName) {
        try {
            final Class<? extends Object> clazz = object.getClass();
            try {
                final Method method = clazz.getMethod(methodName);
                if (isNotNull(method)) {
                    return method;
                }
            } catch (final SecurityException e) {
            } catch (final NoSuchMethodException e) {
            }

            try {
                final Method declaredMethod = clazz.getDeclaredMethod(methodName);
                if (isNotNull(declaredMethod)) {
                    return declaredMethod;
                }
            } catch (final SecurityException e) {
            } catch (final NoSuchMethodException e) {
            }
        } catch (final NullPointerException e) {
        }

        return null;
    }

    public static <T> Field getAccessibleField(final T object, final String fieldName) {
        try {
            final Class<? extends Object> clazz = object.getClass();

            try {
                final Field field = clazz.getField(fieldName);
                if (isNotNull(field)) {
                    return field;
                }
            } catch (final SecurityException e) {
            } catch (final NoSuchFieldException e) {
            }

            try {
                final Field declaredField = clazz.getDeclaredField(fieldName);
                if (isNotNull(declaredField)) {
                    return declaredField;
                }
            } catch (final SecurityException e) {
            } catch (final NoSuchFieldException e) {
            }
        } catch (final NullPointerException e) {
        }

        return null;
    }

    private static <T> boolean isNotNull(final T object) {
        return object != null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(final Object object, final Method method, final Object... args) {
        try {
            if (args != null && args.length >= 1) {
                final Object methodResult = method.invoke(object, args);
                return (T) methodResult;
            } else {
                final Object methodResult = method.invoke(object);
                return (T) methodResult;
            }
        } catch (final IllegalArgumentException e) {
        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
        } catch (final ClassCastException e) {
        } catch (final NullPointerException e) {
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T accessField(final Object object, final Field field) {
        try {
            final Object fieldEntry = field.get(object);
            return (T) fieldEntry;
        } catch (final IllegalArgumentException e) {
        } catch (final IllegalAccessException e) {
        } catch (final ClassCastException e) {
        } catch (final NullPointerException e) {
        }

        return null;
    }
}
