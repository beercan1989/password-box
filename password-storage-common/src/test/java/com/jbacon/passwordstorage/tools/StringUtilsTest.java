package com.jbacon.passwordstorage.tools;

import static com.jbacon.passwordstorage.utils.StringUtil.BLANK;
import static com.jbacon.passwordstorage.utils.StringUtil.SPACE;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.jbacon.passwordstorage.utils.GenericValidationUtil;
import com.jbacon.passwordstorage.utils.StringUtil;

public class StringUtilsTest {

	private static final String ABC = "abc";
	private static final String A = "a";

	@Test
	public void shouldFindStringAsEmpty() {
		boolean result = StringUtil.isEmpty(SPACE);
		assertThat(result, is(true));

		result = StringUtil.isEmpty(BLANK);
		assertThat(result, is(true));

		result = StringUtil.isEmpty(ABC);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindStringAsNotEmpty() {
		boolean result = StringUtil.isNotEmpty(ABC);
		assertThat(result, is(true));

		result = StringUtil.isNotEmpty(BLANK);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindStringAsNotNull() {
		boolean result = GenericValidationUtil.isNotNull(A);
		assertThat(result, is(true));

		result = GenericValidationUtil.isNotNull(null);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindStringAsNull() {
		boolean result = GenericValidationUtil.isNull(null);
		assertThat(result, is(true));

		result = GenericValidationUtil.isNull(BLANK);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindThatTheStringsAreEmpty() {
		boolean result = StringUtil.areEmpty(BLANK, BLANK, BLANK, SPACE);
		assertThat(result, is(true));

		result = StringUtil.areEmpty(BLANK, BLANK, BLANK, SPACE, A);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindThatTheStringsAreNotEmpty() {
		boolean result = StringUtil.areNotEmpty(A, A, A, A + SPACE);
		assertThat(result, is(true));

		result = StringUtil.areNotEmpty(BLANK, A, A, A + SPACE);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindThatTheStringsAreNotNull() {
		boolean result = GenericValidationUtil.areNotNull(BLANK, BLANK, BLANK, BLANK);
		assertThat(result, is(true));

		result = GenericValidationUtil.areNotNull(BLANK, BLANK, BLANK, BLANK, null);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindThatTheStringsAreNull() {
		boolean result = GenericValidationUtil.areNull(null, null, null, null);
		assertThat(result, is(true));

		result = GenericValidationUtil.areNull(BLANK, BLANK, BLANK, BLANK);
		assertThat(result, is(false));
	}
}
