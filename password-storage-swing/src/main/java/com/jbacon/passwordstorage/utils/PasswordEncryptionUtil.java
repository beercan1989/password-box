package com.jbacon.passwordstorage.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.DecoderException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

import co.uk.baconi.cryptography.ciphers.pbe.PBECiphers;
import co.uk.baconi.cryptography.ciphers.symmetric.SymmetricCiphers;

import com.jbacon.passwordstorage.encryption.EncryptionMode;
import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.tools.EncrypterUtils;
import com.jbacon.passwordstorage.password.MasterPassword;

public class PasswordEncryptionUtil {
    
    private PasswordEncryptionUtil() {
    }
    
    public static String getEncrypted(final byte[] toEncrypt, final MasterPassword profile, final String currentPassword) throws DecoderException, UnsupportedEncodingException,
            DataLengthException, IllegalStateException, InvalidCipherTextException {
        return EncrypterUtils.bytesToBase64String(doCipher(EncryptionMode.ENCRYPT_MODE, toEncrypt, profile, currentPassword));
    }
    
    public static String getDecrypted(final String toDecrypt, final MasterPassword profile, final String currentPassword) throws UnsupportedEncodingException, DecoderException,
            DataLengthException, IllegalStateException, InvalidCipherTextException {
        return getDecrypted(EncrypterUtils.base64StringToBytes(toDecrypt), profile, currentPassword);
    }
    
    public static String getDecrypted(final byte[] toDecrypt, final MasterPassword profile, final String currentPassword) throws DecoderException, UnsupportedEncodingException,
            DataLengthException, IllegalStateException, InvalidCipherTextException {
        return EncrypterUtils.byteToString(doCipher(EncryptionMode.DECRYPT_MODE, toDecrypt, profile, currentPassword));
        
    }
    
    private static byte[] doCipher(final EncryptionMode mode, final byte[] message, final MasterPassword profile, final String currentPassword) throws DataLengthException,
            IllegalStateException, InvalidCipherTextException {
        final byte[] aesKey = getSymmetricKey(profile, currentPassword);
        try {
            final EncryptionType storedPasswordsEncryptionType = profile.getStoredPasswordsEncryptionType();
            final SymmetricCiphers cipher = SymmetricCiphers.fromString(storedPasswordsEncryptionType.algorithmName);
            
            switch (mode) {
                case DECRYPT_MODE:
                    return cipher.decrypt(aesKey, message);
                case ENCRYPT_MODE:
                default:
                    return cipher.encrypt(aesKey, message);
            }
        } finally {
            newRandom().nextBytes(aesKey);
        }
    }
    
    private static byte[] getSymmetricKey(final MasterPassword profile, final String currentPassword) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
        
        final EncryptionType masterPasswordEncryptionType = profile.getMasterPasswordEncryptionType();
        final PBECiphers cipher = PBECiphers.fromString(masterPasswordEncryptionType.algorithmName);
        
        final byte[] salt = EncrypterUtils.base64StringToBytes(profile.getSalt());
        final byte[] cipherText = EncrypterUtils.base64StringToBytes(profile.getEncryptedSecretKey());
        final char[] passPhrase = EncrypterUtils.stringToChar(currentPassword);
        
        // TODO - Introduce an IV - its similar to salt.
        final byte[] iv = new byte[salt.length * 2];
        System.arraycopy(salt, 0, iv, 0, salt.length);
        System.arraycopy(salt, 0, iv, salt.length, salt.length);
        
        return cipher.decrypt(passPhrase, salt, iv, cipherText);
    }
    
    private static SecureRandom newRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (final NoSuchAlgorithmException e) {
            return new SecureRandom();
        }
    }
}
