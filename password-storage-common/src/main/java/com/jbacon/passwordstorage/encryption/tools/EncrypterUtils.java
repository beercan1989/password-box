package com.jbacon.passwordstorage.encryption.tools;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import co.uk.baconi.cryptography.utils.SecureRandomUtil;

import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.InvalidEncryptionTypeForSaltGeneration;

public class EncrypterUtils {
    
    // private static final String SALT_SIZE = "saltSize";
    // private static final String KEY_SIZE = "keysize";
    // private static final String SECURE_SALT_ALGORITHM = "secureSaltAlgorithm";
    private static final String TEXT_ENCODING_TYPE = "UTF-8";
    
    /**
     * @param base64ToByte
     * @return
     */
    public static byte[] base64StringToBytes(final String base64ToByte) {
        return Base64.decodeBase64(base64ToByte);
    }
    
    /**
     * @param byteToString
     * @return
     */
    public static String bytesToBase64String(final byte[] byteToString) {
        return Base64.encodeBase64String(byteToString);
    }
    
    /**
     * @param byteToChar
     * @return
     * @throws UnsupportedEncodingException
     */
    public static char[] byteToChar(final byte[] byteToChar) throws UnsupportedEncodingException {
        return new String(byteToChar).toCharArray();
    }
    
    /**
     * @param byteToString
     * @return
     */
    @Deprecated
    public static String byteToHexString(final byte[] byteToString) {
        return new String(Hex.encodeHex(byteToString));
    }
    
    /**
     * @param byteToString
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String byteToString(final byte[] byteToString) throws UnsupportedEncodingException {
        return new String(byteToString, TEXT_ENCODING_TYPE);
    }
    
    /**
     * @param charToByte
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] charToByte(final char[] charToByte) throws UnsupportedEncodingException {
        return new String(charToByte).getBytes(TEXT_ENCODING_TYPE);
    }
    
    /**
     * @param charToString
     * @return
     */
    public static String charToString(final char[] charToString) {
        return new String(charToString);
    }
    
    /**
     * @param hexToByte
     * @return
     * @throws DecoderException
     */
    @Deprecated
    public static byte[] hexStringToByte(final String hexToByte) throws DecoderException {
        return Hex.decodeHex(hexToByte.toCharArray());
    }
    
    /**
     * @param stringToByte
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] stringToByte(final String stringToByte) throws UnsupportedEncodingException {
        return stringToByte.getBytes(TEXT_ENCODING_TYPE);
    }
    
    /**
     * @param stringToChar
     * @return
     */
    public static char[] stringToChar(final String stringToChar) {
        return stringToChar.toCharArray();
    }
    
    /**
     * @param encryptionType
     * @return
     * @throws InvalidEncryptionTypeForSaltGeneration
     * @throws AbstractEncrypterException
     */
    public static byte[] generateSalt(final EncryptionType encryptionType) throws InvalidEncryptionTypeForSaltGeneration {
        final int saltSize = encryptionType.keySize;
        return generateSalt(encryptionType, saltSize);
    }
    
    /**
     * @param encryptionType
     * @param numberOfBytes
     * @return
     * @throws InvalidEncryptionTypeForSaltGeneration
     * @throws AbstractEncrypterException
     */
    @SuppressWarnings("deprecation")
    public static byte[] generateSalt(final EncryptionType encryptionType, final int numberOfBytes) throws InvalidEncryptionTypeForSaltGeneration {
        switch (encryptionType) {
            case PBE_WITH_MD5_AND_DES:
            case PBE_WITH_SHA1_AND_256_AES_CBC_BC:
            case PBE_WITH_SHA256_AND_128_AES_CBC:
            case PBE_WITH_SHA256_AND_256_AES_CBC:
            case PBE_WITH_SHA512_AND_256_AES_CBC:
            case PBE_WITH_WHIRLPOOL_AND_256_AES_CBC:
            case PBE_WITH_SHA256_AND_TWOFISH_CBC:
            case PBE_WITH_SHA512_AND_TWOFISH_CBC:
            case PBE_WITH_WHIRLPOOL_AND_TWOFISH_CBC:
                final byte[] salt = new byte[numberOfBytes];
                final SecureRandom saltGen = SecureRandomUtil.getSecureRandom();
                saltGen.nextBytes(salt);
                return salt;
            default:
                throw new InvalidEncryptionTypeForSaltGeneration("No such supported passwordbased encryption.");
        }
    }
}
