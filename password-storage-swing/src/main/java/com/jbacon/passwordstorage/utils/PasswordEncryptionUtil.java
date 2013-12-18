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

    public static String getEncrypted(final byte[] toEncrypt, final MasterPassword profile, final String currentPassword)
            throws DecoderException, AbstractEncrypterException, UnsupportedEncodingException {

        final byte[] aesKey = getSymmetricKey(profile, currentPassword);
        final EncrypterAES encrypter = (EncrypterAES) profile.getStoredPasswordEncryptionType().getEncrypter();
        final byte[] encryptedValue = encrypter.doCiper(EncryptionMode.ENCRYPT_MODE, toEncrypt, aesKey);

        try {
            return EncrypterUtils.bytesToBase64String(encryptedValue);
        } finally {
            newRandom().nextBytes(aesKey);
        }
    }

    public static String getDecrypted(final String toDecrypt, final MasterPassword profile, final String currentPassword)
            throws UnsupportedEncodingException, DecoderException, AbstractEncrypterException {
        return getDecrypted(EncrypterUtils.base64StringToBytes(toDecrypt), profile, currentPassword);
    }

    public static String getDecrypted(final byte[] toDecrypt, final MasterPassword profile, final String currentPassword)
            throws DecoderException, AbstractEncrypterException, UnsupportedEncodingException {

        final byte[] aesKey = getSymmetricKey(profile, currentPassword);
        final EncrypterAES aesDecrypter = (EncrypterAES) profile.getStoredPasswordEncryptionType().getEncrypter();
        final byte[] encryptedValue = aesDecrypter.doCiper(EncryptionMode.DECRYPT_MODE, toDecrypt, aesKey);

        try {
            return EncrypterUtils.byteToString(encryptedValue);
        } finally {
            newRandom().nextBytes(aesKey);
        }
    }

    private static byte[] getSymmetricKey(final MasterPassword profile, final String currentPassword)
            throws AbstractEncrypterException {
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
