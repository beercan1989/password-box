package com.jbacon.passwordstorage.tools;

import static com.jbacon.passwordstorage.tools.StringUtils.BLANK;
import static com.jbacon.passwordstorage.tools.StringUtils.SPACE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class StringUtilsTest {

	private static final String ABC = "abc";
	private static final String A = "a";

	@Test
	public void shouldFindStringAsEmpty() {
		boolean result = StringUtils.isEmpty(SPACE);
		assertThat(result, is(true));

		result = StringUtils.isEmpty(BLANK);
		assertThat(result, is(true));

		result = StringUtils.isEmpty(ABC);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindStringAsNotEmpty() {
		boolean result = StringUtils.isNotEmpty(ABC);
		assertThat(result, is(true));

		result = StringUtils.isNotEmpty(BLANK);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindStringAsNotNull() {
		boolean result = GenericUtils.isNotNull(A);
		assertThat(result, is(true));

		result = GenericUtils.isNotNull(null);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindStringAsNull() {
		boolean result = GenericUtils.isNull(null);
		assertThat(result, is(true));

		result = GenericUtils.isNull(BLANK);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindThatTheStringsAreEmpty() {
		boolean result = StringUtils.areEmpty(BLANK, BLANK, BLANK, SPACE);
		assertThat(result, is(true));

		result = StringUtils.areEmpty(BLANK, BLANK, BLANK, SPACE, A);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindThatTheStringsAreNotEmpty() {
		boolean result = StringUtils.areNotEmpty(A, A, A, A + SPACE);
		assertThat(result, is(true));

		result = StringUtils.areNotEmpty(BLANK, A, A, A + SPACE);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindThatTheStringsAreNotNull() {
		boolean result = GenericUtils.areNotNull(BLANK, BLANK, BLANK, BLANK);
		assertThat(result, is(true));

		result = GenericUtils.areNotNull(BLANK, BLANK, BLANK, BLANK, null);
		assertThat(result, is(false));
	}

	@Test
	public void shouldFindThatTheStringsAreNull() {
		boolean result = GenericUtils.areNull(null, null, null, null);
		assertThat(result, is(true));

		result = GenericUtils.areNull(BLANK, BLANK, BLANK, BLANK);
		assertThat(result, is(false));
	}
}
