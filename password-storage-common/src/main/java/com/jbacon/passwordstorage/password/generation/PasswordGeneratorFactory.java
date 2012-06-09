package com.jbacon.passwordstorage.password.generation;

import static com.jbacon.passwordstorage.password.generation.CharSet.LOWER_ALPHA;
import static com.jbacon.passwordstorage.password.generation.CharSet.MIXED_ALPHA;
import static com.jbacon.passwordstorage.password.generation.CharSet.MIXED_ALPHA_NUMERIC;
import static com.jbacon.passwordstorage.password.generation.CharSet.NUMERIC;
import static com.jbacon.passwordstorage.password.generation.CharSet.SPECIAL;
import static com.jbacon.passwordstorage.password.generation.CharSet.UPPER_ALPHA;
import static com.jbacon.passwordstorage.password.generation.CharSet.add;
import static com.jbacon.passwordstorage.password.generation.CharSet.concat;

public enum PasswordGeneratorFactory {
    
    LETTER {
        @Override
        public PasswordGenerator getPasswordGenerator() {
            return new PasswordGeneratorImpl(LOWER_ALPHA.getChars());
        }
    },
    
    NUMBER {
        @Override
        public PasswordGenerator getPasswordGenerator() {
            return new PasswordGeneratorImpl(NUMERIC.getChars());
        }
    },
    
    CAPITAL {
        @Override
        public PasswordGenerator getPasswordGenerator() {
            return new PasswordGeneratorImpl(UPPER_ALPHA.getChars());
        }
    },
    
    LETTER_NUMBER {
        @Override
        public PasswordGenerator getPasswordGenerator() {
            return new PasswordGeneratorImpl(concat(LOWER_ALPHA, NUMERIC));
        }
    },
    
    LETTER_CAPITAL {
        @Override
        public PasswordGenerator getPasswordGenerator() {
            return new PasswordGeneratorImpl(MIXED_ALPHA.getChars());
        }
    },
    
    CAPITAL_NUMBER {
        @Override
        public PasswordGenerator getPasswordGenerator() {
            return new PasswordGeneratorImpl(concat(NUMERIC, UPPER_ALPHA));
        }
    },
    
    LETTER_CAPITAL_NUMBER {
        @Override
        public PasswordGenerator getPasswordGenerator() {
            return new PasswordGeneratorImpl(MIXED_ALPHA_NUMERIC.getChars());
        }
    },
    
    LETTER_NUMBER_PUNCTUATION {
        @Override
        public PasswordGenerator getPasswordGenerator() {
            return new PasswordGeneratorImpl(concat(LOWER_ALPHA, NUMERIC, SPECIAL));
        }
    };
    
    public static PasswordGenerator getCustomPasswordGenerator(final char[] chars, final char... extraChars) {
        return new PasswordGeneratorImpl(add(chars, extraChars));
    }
    
    public abstract PasswordGenerator getPasswordGenerator();
}
