package com.jbacon.passwordstorage.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.DecoderException;

import com.jbacon.passwordstorage.encryption.EncrypterAES;
import com.jbacon.passwordstorage.encryption.EncrypterPBE;
import com.jbacon.passwordstorage.encryption.EncryptionMode;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.tools.EncrypterUtils;
import com.jbacon.passwordstorage.password.MasterPassword;

public class PasswordEncryptionUtil {

    private PasswordEncryptionUtil() {
    }

    public static String getEncrypted(final byte[] toEncrypt, final MasterPassword profile, final String currentPassword) throws DecoderException, AbstractEncrypterException,
            UnsupportedEncodingException {
        return EncrypterUtils.bytesToBase64String(doCipher(EncryptionMode.ENCRYPT_MODE, toEncrypt, profile, currentPassword));
    }

    public static String getDecrypted(final String toDecrypt, final MasterPassword profile, final String currentPassword) throws UnsupportedEncodingException, DecoderException,
            AbstractEncrypterException {
        return getDecrypted(EncrypterUtils.base64StringToBytes(toDecrypt), profile, currentPassword);
    }

    public static String getDecrypted(final byte[] toDecrypt, final MasterPassword profile, final String currentPassword) throws DecoderException, AbstractEncrypterException,
            UnsupportedEncodingException {
        return EncrypterUtils.byteToString(doCipher(EncryptionMode.DECRYPT_MODE, toDecrypt, profile, currentPassword));

    }

    private static byte[] doCipher(final EncryptionMode mode, final byte[] message, final MasterPassword profile, final String currentPassword) throws AbstractEncrypterException {
        final byte[] aesKey = getSymmetricKey(profile, currentPassword);
        try {
            final EncrypterAES cipher = (EncrypterAES) profile.getStoredPasswordEncryptionType().getEncrypter();
            return cipher.doCiper(mode, message, aesKey);
        } finally {
            newRandom().nextBytes(aesKey);
        }
    }

    private static byte[] getSymmetricKey(final MasterPassword profile, final String currentPassword) throws AbstractEncrypterException {
        final EncrypterPBE decrypter = (EncrypterPBE) profile.getMasterPasswordEncryptionType().getEncrypter();
        final byte[] salt = EncrypterUtils.base64StringToBytes(profile.getSalt());
        final byte[] cipherText = EncrypterUtils.base64StringToBytes(profile.getEncryptedSecretKey());
        final char[] passPhrase = EncrypterUtils.stringToChar(currentPassword);

        return decrypter.doCiper(EncryptionMode.DECRYPT_MODE, salt, cipherText, passPhrase);
    }

    private static SecureRandom newRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (final NoSuchAlgorithmException e) {
            return new SecureRandom();
        }
    }
}
