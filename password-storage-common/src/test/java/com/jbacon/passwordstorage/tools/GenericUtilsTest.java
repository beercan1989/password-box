package com.jbacon.passwordstorage.tools;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GenericUtilsTest {

    @Test
    public void testAreNotNull() {
        assertTrue(GenericUtils.areNotNull("", ""));
    }

    @Test
    public void testAreNull() {
        assertTrue(GenericUtils.areNull(null, null));

        final String first = null;
        final String second = null;

        assertTrue(GenericUtils.areNull(first, second));
    }

    @Test
    public void testIsNotNull() {
        assertTrue(GenericUtils.isNotNull(""));
    }

    @Test
    public void testIsNull() {
        assertTrue(GenericUtils.isNull(null));

        final String first = null;

        assertTrue(GenericUtils.isNull(first));
    }

    @Test
    public void testAreNotEmpty() {
        final List<String> listEmpty = new ArrayList<String>();
        final String[] arrayEmpty = new String[] {};
        assertTrue(!GenericUtils.areNotEmpty(listEmpty, arrayEmpty));

        final List<String> listNotEmpty = new ArrayList<String>();
        listNotEmpty.add("");
        final String[] arrayNotEmpty = new String[] { "" };
        assertTrue(GenericUtils.areNotEmpty(listNotEmpty, arrayNotEmpty));
    }

    @Test
    public void testAreEmpty() {
        final List<String> listEmpty = new ArrayList<String>();
        final String[] arrayEmpty = new String[] {};
        assertTrue(GenericUtils.areEmpty(listEmpty, arrayEmpty));

        final List<String> listNotEmpty = new ArrayList<String>();
        listNotEmpty.add("");
        final String[] arrayNotEmpty = new String[] { "" };
        assertTrue(!GenericUtils.areEmpty(listNotEmpty, arrayNotEmpty));
    }

    @Test
    public void testIsNotEmtpy() {
        final List<String> listEmpty = new ArrayList<String>();
        assertTrue(!GenericUtils.isNotEmpty(listEmpty));

        final String[] arrayEmpty = new String[] {};
        assertTrue(!GenericUtils.isNotEmpty(arrayEmpty));

        final List<String> listNotEmpty = new ArrayList<String>();
        listNotEmpty.add("");
        assertTrue(GenericUtils.isNotEmpty(listNotEmpty));

        final String[] arrayNotEmpty = new String[] { "" };
        assertTrue(GenericUtils.isNotEmpty(arrayNotEmpty));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(GenericUtils.isEmpty(new ArrayList<String>()));
        assertTrue(GenericUtils.isEmpty(new String[] {}));

        final List<String> listNotEmpty = new ArrayList<String>();
        listNotEmpty.add("");
        assertTrue(!GenericUtils.isEmpty(listNotEmpty));
        assertTrue(!GenericUtils.isEmpty(new String[] { "" }));

        assertTrue(GenericUtils.isEmpty(new HasIsEmptyMethod(0)));
        assertTrue(GenericUtils.isEmpty(new HasGetLengthMethod(0)));
        assertTrue(GenericUtils.isEmpty(new HasLengthMethod(0)));
        assertTrue(GenericUtils.isEmpty(new HasGetSizeMethod(0)));
        assertTrue(GenericUtils.isEmpty(new HasSizeMethod(0)));
        assertTrue(GenericUtils.isEmpty(new HasLengthMethod(0)));
        assertTrue(GenericUtils.isEmpty(new HasSizeMethod(0)));

        assertTrue(!GenericUtils.isEmpty(null));

        assertTrue(!GenericUtils.isEmpty(new HasIsEmptyMethod(1L)));
        assertTrue(!GenericUtils.isEmpty(new HasGetLengthMethod(1L)));
        assertTrue(!GenericUtils.isEmpty(new HasLengthMethod(1L)));
        assertTrue(!GenericUtils.isEmpty(new HasGetSizeMethod(1L)));
        assertTrue(!GenericUtils.isEmpty(new HasSizeMethod(1L)));
        assertTrue(!GenericUtils.isEmpty(new HasLengthMethod(1L)));
        assertTrue(!GenericUtils.isEmpty(new HasSizeMethod(1L)));
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
