package com.jbacon.passwordstorage.tools;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jbacon.passwordstorage.utils.GenericValidationUtil;

public class GenericUtilsTest {

    @Test
    public void testAreNotNull() {
        assertTrue(GenericValidationUtil.areNotNull("", ""));
    }

    @Test
    public void testAreNull() {
        assertTrue(GenericValidationUtil.areNull(null, null));

        final String first = null;
        final String second = null;

        assertTrue(GenericValidationUtil.areNull(first, second));
    }

    @Test
    public void testIsNotNull() {
        assertTrue(GenericValidationUtil.isNotNull(""));
    }

    @Test
    public void testIsNull() {
        assertTrue(GenericValidationUtil.isNull(null));

        final String first = null;

        assertTrue(GenericValidationUtil.isNull(first));
    }

    @Test
    public void testAreNotEmpty() {
        final List<String> listEmpty = new ArrayList<String>();
        final String[] arrayEmpty = new String[] {};
        assertTrue(!GenericValidationUtil.areNotEmpty(listEmpty, arrayEmpty));

        final List<String> listNotEmpty = new ArrayList<String>();
        listNotEmpty.add("");
        final String[] arrayNotEmpty = new String[] { "" };
        assertTrue(GenericValidationUtil.areNotEmpty(listNotEmpty, arrayNotEmpty));
    }

    @Test
    public void testAreEmpty() {
        final List<String> listEmpty = new ArrayList<String>();
        final String[] arrayEmpty = new String[] {};
        assertTrue(GenericValidationUtil.areEmpty(listEmpty, arrayEmpty));

        final List<String> listNotEmpty = new ArrayList<String>();
        listNotEmpty.add("");
        final String[] arrayNotEmpty = new String[] { "" };
        assertTrue(!GenericValidationUtil.areEmpty(listNotEmpty, arrayNotEmpty));
    }

    @Test
    public void testIsNotEmtpy() {
        final List<String> listEmpty = new ArrayList<String>();
        assertTrue(!GenericValidationUtil.isNotEmpty(listEmpty));

        final String[] arrayEmpty = new String[] {};
        assertTrue(!GenericValidationUtil.isNotEmpty(arrayEmpty));

        final List<String> listNotEmpty = new ArrayList<String>();
        listNotEmpty.add("");
        assertTrue(GenericValidationUtil.isNotEmpty(listNotEmpty));

        final String[] arrayNotEmpty = new String[] { "" };
        assertTrue(GenericValidationUtil.isNotEmpty(arrayNotEmpty));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(GenericValidationUtil.isEmpty(new ArrayList<String>()));
        assertTrue(GenericValidationUtil.isEmpty(new String[] {}));

        final List<String> listNotEmpty = new ArrayList<String>();
        listNotEmpty.add("");
        assertTrue(!GenericValidationUtil.isEmpty(listNotEmpty));
        assertTrue(!GenericValidationUtil.isEmpty(new String[] { "" }));

        assertTrue(GenericValidationUtil.isEmpty(new HasIsEmptyMethod(0)));
        assertTrue(GenericValidationUtil.isEmpty(new HasGetLengthMethod(0)));
        assertTrue(GenericValidationUtil.isEmpty(new HasLengthMethod(0)));
        assertTrue(GenericValidationUtil.isEmpty(new HasGetSizeMethod(0)));
        assertTrue(GenericValidationUtil.isEmpty(new HasSizeMethod(0)));
        assertTrue(GenericValidationUtil.isEmpty(new HasLengthMethod(0)));
        assertTrue(GenericValidationUtil.isEmpty(new HasSizeMethod(0)));

        assertTrue(!GenericValidationUtil.isEmpty(null));

        assertTrue(!GenericValidationUtil.isEmpty(new HasIsEmptyMethod(1L)));
        assertTrue(!GenericValidationUtil.isEmpty(new HasGetLengthMethod(1L)));
        assertTrue(!GenericValidationUtil.isEmpty(new HasLengthMethod(1L)));
        assertTrue(!GenericValidationUtil.isEmpty(new HasGetSizeMethod(1L)));
        assertTrue(!GenericValidationUtil.isEmpty(new HasSizeMethod(1L)));
        assertTrue(!GenericValidationUtil.isEmpty(new HasLengthMethod(1L)));
        assertTrue(!GenericValidationUtil.isEmpty(new HasSizeMethod(1L)));
    }

    public static class HasIsEmptyMethod {
        private final long value;

        public HasIsEmptyMethod(final long value) {
            this.value = value;
        }

        public boolean isEmpty() {
            return value < 1;
        }
    }

    public static class HasSizeMethod {
        private final long value;

        public HasSizeMethod(final long value) {
            this.value = value;
        }

        public long size() {
            return value;
        }
    }

    public static class HasGetSizeMethod {
        private final long value;

        public HasGetSizeMethod(final long value) {
            this.value = value;
        }

        public long getSize() {
            return value;
        }
    }

    public static class HasLengthMethod {
        private final long value;

        public HasLengthMethod(final long value) {
            this.value = value;
        }

        public long length() {
            return value;
        }
    }

    public static class HasGetLengthMethod {
        private final long value;

        public HasGetLengthMethod(final long value) {
            this.value = value;
        }

        public long getLength() {
            return value;
        }
    }

}
