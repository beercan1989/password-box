package com.jbacon.passwordstorage.password.generation;

import static com.jbacon.passwordstorage.password.generation.CharSet.LOWER_ALPHA;
import static com.jbacon.passwordstorage.password.generation.CharSet.MIXED_ALPHA;
import static com.jbacon.passwordstorage.password.generation.CharSet.MIXED_ALPHA_NUMERIC;
import static com.jbacon.passwordstorage.password.generation.CharSet.NUMERIC;
import static com.jbacon.passwordstorage.password.generation.CharSet.UPPER_ALPHA;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CharSetTest {
    
    @Test
    public void shouldBeAbleToAddOneOrMoreArraysIntoOne() {
        final Character[] result = CharSet.add(LOWER_ALPHA.get(), UPPER_ALPHA.get(), NUMERIC.get());
        assertThat(result, is(not(nullValue())));
        assertThat(result.length, is(equalTo(62)));
    };
    
    @Test
    public void shouldBeAbleToConcatOneOrMoreCharSet() {
        final Character[] result = CharSet.concat(LOWER_ALPHA, UPPER_ALPHA, NUMERIC);
        
        assertThat(result, is(not(nullValue())));
        assertThat(result.length, is(equalTo(62)));
    };
    
    @Test
    public void shouldBeAbleToGetCharacterArrayForCharSet() {
        final Character[] result = LOWER_ALPHA.get();
        
        assertThat(result, is(not(nullValue())));
        assertThat(result, is(instanceOf(Character[].class)));
        assertThat(result.length, is(greaterThan(0)));
    };
    
    @Test
    public void shouldHaveLowerAlphaChars() {
        final Character[] result = LOWER_ALPHA.get();
        assertThat(result.length, is(equalTo(26)));
        assertThat(Arrays.asList(result), containsInAnyOrder('a', 'b', 'c', 'd', 'e', 'f', //
                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', //
                'v', 'w', 'x', 'y', 'z'));
        
        final Set<Character> resultSet = new HashSet<Character>(Arrays.asList(result));
        assertThat(resultSet, is(not(nullValue())));
        assertThat(resultSet.size(), is(equalTo(26)));
    };
    
    @Test
    public void shouldHaveMixedAlphaChars() {
        final Character[] result = MIXED_ALPHA.get();
        assertThat(result.length, is(equalTo(52)));
        assertThat(Arrays.asList(result), containsInAnyOrder('a', 'b', 'c', 'd', 'e', 'f', //
                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', //
                'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', //
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        
        final Set<Character> resultSet = new HashSet<Character>(Arrays.asList(result));
        assertThat(resultSet, is(not(nullValue())));
        assertThat(resultSet.size(), is(equalTo(52)));
    };
    
    @Test
    public void shouldHaveMixedAlphaNumericChars() {
        final Character[] result = MIXED_ALPHA_NUMERIC.get();
        assertThat(result.length, is(equalTo(62)));
        assertThat(Arrays.asList(result), containsInAnyOrder('a', 'b', 'c', 'd', 'e', 'f', //
                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', //
                'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', //
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', //
                'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        
        final Set<Character> resultSet = new HashSet<Character>(Arrays.asList(result));
        assertThat(resultSet, is(not(nullValue())));
        assertThat(resultSet.size(), is(equalTo(62)));
    }
    
    @Test
    public void shouldHaveNumericChars() {
        final Character[] result = NUMERIC.get();
        assertThat(result.length, is(equalTo(10)));
        assertThat(Arrays.asList(result), containsInAnyOrder('0', '1', '2', '3', '4', '5', //
                '6', '7', '8', '9'));
        
        final Set<Character> resultSet = new HashSet<Character>(Arrays.asList(result));
        assertThat(resultSet, is(not(nullValue())));
        assertThat(resultSet.size(), is(equalTo(10)));
    }
    
    @Test
    public void shouldHaveUpperAlphaChars() {
        final Character[] result = UPPER_ALPHA.get();
        assertThat(result.length, is(equalTo(26)));
        assertThat(Arrays.asList(result), containsInAnyOrder('A', 'B', 'C', 'D', 'E', 'F', //
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', //
                'V', 'W', 'X', 'Y', 'Z'));
        
        final Set<Character> resultSet = new HashSet<Character>(Arrays.asList(result));
        assertThat(resultSet, is(not(nullValue())));
        assertThat(resultSet.size(), is(equalTo(26)));
    }
    
}
